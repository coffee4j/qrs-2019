package de.rwth.swc.qrs2019.execution;

import de.rwth.swc.coffee4j.model.InputParameterModel;
import de.rwth.swc.coffee4j.model.Parameter;
import de.rwth.swc.coffee4j.model.constraints.Constraint;
import de.rwth.swc.qrs2019.results.ExecutedTestInput;
import de.rwth.swc.qrs2019.modelling.Experiment;
import de.rwth.swc.qrs2019.modelling.Fault;
import de.rwth.swc.qrs2019.modelling.Scenario;
import de.rwth.swc.qrs2019.results.TestEffectivenessResult;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static de.rwth.swc.qrs2019.evaluation.CSVBasedReporter.printAllTestResults;
import static de.rwth.swc.qrs2019.evaluation.CSVBasedReporter.printAnalysisOfTestResults;
import static de.rwth.swc.qrs2019.execution.ExperimentGenerator.generateNegativeTestSuite;
import static de.rwth.swc.qrs2019.execution.ExperimentGenerator.generatePositiveTestSuite;
import static de.rwth.swc.qrs2019.execution.LoggingHelper.info;

public class EffectivenessExperimentRunner {

    private static final int POSITIVE_STRENGTH = 5;
    private static final int NEGATIVE_STRENGTH_I = 4;
    private static final int NEGATIVE_STRENGTH_II = 3;
    private static final int SCENARIOS = 10;

    private String getExperimentName(Experiment experiment) {
        final String className = experiment.getClass().toString();
        final int index = className.indexOf("Scenario");

        return className.substring(index);
    }

    public static void computeEffectiveness(Experiment experiment, Scenario scenario) {
        new EffectivenessExperimentRunner().run(experiment, scenario);
    }

    private InputParameterModel createUnconstrainedInputParameterModel(int strength, Parameter[] parameters) {
        return InputParameterModel.inputParameterModel("ipm")
                .strength(strength)
                .parameters(parameters)
                .build();
    }

    private InputParameterModel createConstrainedInputParameterModel(int strength, Parameter[] parameters, Constraint[] constraints) {
        return InputParameterModel.inputParameterModel("ipm")
                .strength(strength)
                .parameters(parameters)
                .errorConstraints(constraints)
                .build();
    }

    private void run(Experiment experiment, Scenario scenario) {
        final List<TestEffectivenessResult> ipogcResults = runIpogC(experiment, scenario);
        final List<TestEffectivenessResult> ipognegResults = runIpogNeg(experiment, scenario);

        printAllTestResults("IPOG-C", ipogcResults);
        printAllTestResults("IPOG-NEG", ipognegResults);

        printAnalysisOfTestResults("IPOG-C", ipogcResults);
        printAnalysisOfTestResults("IPOG-NEG", ipognegResults);
    }

    private List<TestEffectivenessResult> runIpogC(Experiment experiment, Scenario scenario) {
        final List<TestEffectivenessResult> results = new ArrayList<>();

        for(int strength = 1; strength <= POSITIVE_STRENGTH; strength++) {
            for(int i = 0; i < SCENARIOS; i++) {
                final Parameter[] orderedParameter = scenario.getOrderedParameters(i);
                final int[] parameterOrder = scenario.getParameterOrder(i);

                results.add( selectWithIpogC(experiment, strength, orderedParameter, parameterOrder) );
            }
        }

        return results;
    }

    private TestEffectivenessResult selectWithIpogC(Experiment experiment, int strength, Parameter[] parameters, int[] order) {
        final InputParameterModel model = createUnconstrainedInputParameterModel(strength, parameters);
        final AbstractToConcreteValuesConverter converter = new InputParameterModelBasedAbstractToConcreteValuesConverter(model);
        final TestMethodExecutor executor = new TestMethodExecutor(experiment, order, converter);

        info("generate ipog-c with " + strength);
        final List<int[]> testSuite = generatePositiveTestSuite(model);

        final Pair<List<int[]>, List<int[]>> testSuites = partition(testSuite, experiment, converter, parameters);

        return executeTestSuite(experiment, Integer.toString(strength), executor, testSuites);
    }

    private List<TestEffectivenessResult> runIpogNeg(Experiment experiment, Scenario scenario) {
        final List<TestEffectivenessResult> results = new ArrayList<>();

        for(int i = 0; i < SCENARIOS; i++) {
            final Parameter[] orderedParameters = scenario.getOrderedParameters(i);
            final int[] parameterOrder = scenario.getParameterOrder(i);

            final List<List<int[]>> posTestSuites = IntStream.rangeClosed(1, NEGATIVE_STRENGTH_I)
                    .mapToObj(strength -> {
                        final InputParameterModel model = createConstrainedInputParameterModel(strength, orderedParameters, experiment.getErrorConstraints());

                        info("generate ipog-plus with " + strength);
                        return generatePositiveTestSuite(model);
                    })
                    .collect(Collectors.toList());

            final List<List<int[]>> negTestSuites = IntStream.rangeClosed(0, NEGATIVE_STRENGTH_II)
                    .mapToObj(strength -> {
                        final InputParameterModel negModel = createConstrainedInputParameterModel(strength, orderedParameters, experiment.getErrorConstraints());

                        info("generate ipog-negative with " + strength);
                        return generateNegativeTestSuite(negModel);
                    })
                    .collect(Collectors.toList());

            for(int strengthPos = 1; strengthPos <= NEGATIVE_STRENGTH_I; strengthPos++) {
                final InputParameterModel model = createConstrainedInputParameterModel(strengthPos, orderedParameters, experiment.getErrorConstraints());
                final AbstractToConcreteValuesConverter converter = new InputParameterModelBasedAbstractToConcreteValuesConverter(model);
                final TestMethodExecutor executor = new TestMethodExecutor(experiment, parameterOrder, converter);

                for(int strengthNeg = 0; strengthNeg <= NEGATIVE_STRENGTH_II; strengthNeg++) {
                    final Pair<List<int[]>, List<int[]>> testSuites = Pair.create(posTestSuites.get(strengthPos - 1), negTestSuites.get(strengthNeg));

                    results.add( executeTestSuite(experiment, strengthPos + "-" + strengthNeg, executor, testSuites) );
                }
            }
        }

        return results;
    }

    private TestEffectivenessResult executeTestSuite(Experiment experiment, String strength, TestMethodExecutor executor,
                                                     Pair<List<int[]>, List<int[]>> testSuites) {

        final List<int[]> testSuite = new ArrayList<>();
        testSuite.addAll(testSuites.getFirst());
        testSuite.addAll(testSuites.getSecond());

        final List<ExecutedTestInput> selectedExecutedTestInputs =
                testSuite.stream()
                        .map(executor::execute)
                        .collect(Collectors.toList());

        final List<Fault> activatedPositiveFaults = Arrays
                .stream(experiment.getPositiveFaults())
                .filter(fault -> selectedExecutedTestInputs
                        .stream()
                        .anyMatch(executedTestInput -> executedTestInput.getExecutionInformation().getActivatedFaults().contains(fault)))
                .collect(Collectors.toList());

        final List<Fault> activatedNegativeFaults = Arrays
                .stream(experiment.getNegativeFaults())
                .filter(fault -> selectedExecutedTestInputs
                        .stream()
                        .anyMatch(executedTestInput -> executedTestInput.getExecutionInformation().getActivatedFaults().contains(fault)))
                .collect(Collectors.toList());

        return new TestEffectivenessResult(
                getExperimentName(experiment),
                strength,
                testSuites.getFirst().size(), testSuites.getSecond().size(),
                experiment.getPositiveFaults().length , experiment.getNegativeFaults().length, activatedPositiveFaults, activatedNegativeFaults);
    }

    private static Pair<List<int[]>, List<int[]>> partition(List<int[]> abstractTestInputs,
                                                            Experiment experiment,
                                                            AbstractToConcreteValuesConverter converter,
                                                            Parameter[] parameters) {
        List<int[]> positiveTestInputs = new ArrayList<>();
        List<int[]> negativeTestInputs = new ArrayList<>();

        for(int[] abstractTestInput : abstractTestInputs) {
            Object[] testInput = converter.convert(abstractTestInput);

            if(checkConstraints(experiment, parameters, testInput)) {
                positiveTestInputs.add(abstractTestInput);
            } else {
                negativeTestInputs.add(abstractTestInput);
            }
        }

        return Pair.create(positiveTestInputs, negativeTestInputs);
    }

    private static boolean checkConstraints(Experiment experiment, Parameter[] parameters, Object[] testInput) {
        for(Constraint constraint : experiment.getErrorConstraints()) {
            List<Object> subset = buildArgumentsForConstraint(constraint, testInput, parameters);

            if(!constraint.getConstraintFunction().check(subset)) {
                return false;
            }
        }

        return true;
    }


    private static List<Object> buildArgumentsForConstraint(Constraint constraint, Object[] testInput, Parameter[] parameters) {
        List<Object> subset = new ArrayList<>(constraint.getParameterNames().size());

        for(int i = 0; i < constraint.getParameterNames().size(); i++) {
            subset.add( testInput[firstIndexOf(parameters, constraint.getParameterNames().get(i)).orElseThrow()] );
        }

        return subset;
    }


    private static Optional<Integer> firstIndexOf(Parameter[] array, String element) {
        for(int i = 0; i < array.length; i++) {
            if(array[i].getName().equals(element))
                return Optional.of(i);
        }

        return Optional.empty();
    }
}
