package de.rwth.swc.qrs2019.execution;

import de.rwth.swc.coffee4j.engine.CombinatorialTestModel;
import de.rwth.swc.coffee4j.engine.generator.ipog.Ipog;
import de.rwth.swc.coffee4j.engine.generator.negative.IpogNeg;
import de.rwth.swc.coffee4j.model.InputParameterModel;
import de.rwth.swc.coffee4j.model.Parameter;
import de.rwth.swc.coffee4j.model.constraints.Constraint;
import de.rwth.swc.coffee4j.model.converter.IndexBasedModelConverter;
import de.rwth.swc.qrs2019.modelling.Experiment;
import de.rwth.swc.qrs2019.modelling.Scenario;
import de.rwth.swc.qrs2019.results.TestPerformanceResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static de.rwth.swc.qrs2019.evaluation.CSVBasedReporter.printAnalysisOfPerformanceTestResults;
import static de.rwth.swc.qrs2019.execution.LoggingHelper.info;

public class PerformanceExperimentRunner {

    private static final int WARM_UP = 0;
    private static final int REPETITIONS = 6;

    private static final int POSITIVE_STRENGTH = 4;
    private static final int NEGATIVE_STRENGTH = 3;
    private static final int SCENARIOS = 1;

    private String getExperimentName(Experiment experiment) {
        final String className = experiment.getClass().toString();
        final int index = className.indexOf("Scenario");

        return className.substring(index);
    }

    public static void computePerformance(Experiment experiment, Scenario scenario) {
        new PerformanceExperimentRunner().run(experiment, scenario);
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
        final List<TestPerformanceResult> ipogcResults = runIpogC(experiment, scenario);
        final List<TestPerformanceResult> ipognegResults = runIpogNeg(experiment, scenario);

        printAnalysisOfPerformanceTestResults("IPOG-C", ipogcResults);
        printAnalysisOfPerformanceTestResults("IPOG-NEG", ipognegResults);
    }

    private List<TestPerformanceResult> runIpogC(Experiment experiment, Scenario scenario) {
        final List<TestPerformanceResult> results = new ArrayList<>();

        for(int strength = 1; strength <= POSITIVE_STRENGTH; strength++) {
            final String title = getExperimentName(experiment) + " " + strength;

            for(int i = 0; i < SCENARIOS; i++) {
                final Parameter[] orderedParameters = scenario.getOrderedParameters(i);
                final InputParameterModel inputParameterModel = createUnconstrainedInputParameterModel(strength, orderedParameters);

                final IndexBasedModelConverter converter = new IndexBasedModelConverter(inputParameterModel);
                final CombinatorialTestModel model = converter.getConvertedModel();

                final Runnable generator = () ->
                        new Ipog()
                                .generate(model, new StandardOutputReporter())
                                .forEach(testInputGroupSupplier -> testInputGroupSupplier.get().getTestInputs());

                results.add(
                        new TestPerformanceResult(
                                getExperimentName(experiment),
                                Integer.toString(strength),
                                execute(title, generator)
                        )
                );
            }
        }

        return results;
    }

    private List<TestPerformanceResult> runIpogNeg(Experiment experiment, Scenario scenario) {
        final List<TestPerformanceResult> results = new ArrayList<>();

        for(int strengthPos = 1; strengthPos <= POSITIVE_STRENGTH; strengthPos++) {
            for(int strengthNeg = 0; strengthNeg <= NEGATIVE_STRENGTH; strengthNeg++) {
                final String title = getExperimentName(experiment) + " " + strengthPos + "-" + strengthNeg;

                for(int i = 0; i < SCENARIOS; i++) {
                    final Parameter[] orderedParameters = scenario.getOrderedParameters(i);
                    final InputParameterModel ipm_pos = createConstrainedInputParameterModel(strengthPos, orderedParameters, experiment.getErrorConstraints());
                    final IndexBasedModelConverter converter_pos = new IndexBasedModelConverter(ipm_pos);
                    final CombinatorialTestModel model_pos = converter_pos.getConvertedModel();

                    final InputParameterModel ipm_neg = createConstrainedInputParameterModel(strengthNeg, orderedParameters, experiment.getErrorConstraints());
                    final IndexBasedModelConverter converter_neg = new IndexBasedModelConverter(ipm_neg);
                    final CombinatorialTestModel model_neg = converter_neg.getConvertedModel();

                    final Runnable generator = () -> {
                        new Ipog()
                                .generate(model_pos, new StandardOutputReporter())
                                .forEach(testInputGroupSupplier -> testInputGroupSupplier.get().getTestInputs());

                        new IpogNeg()
                                .generate(model_neg, new StandardOutputReporter())
                                .forEach(testInputGroupSupplier -> testInputGroupSupplier.get().getTestInputs());
                    };

                    results.add(
                            new TestPerformanceResult(
                                    getExperimentName(experiment),
                                    strengthPos + "-" + strengthNeg,
                                    execute(title, generator)
                            )
                    );
                }
            }
        }

        return results;
    }

    private static long[] execute(final String title, final Runnable generator) {
        for (int i = 0; i < WARM_UP; i++) {
            generator.run();
        }

        final long times[] = new long[REPETITIONS];

        for (int i = 0; i < times.length; i++) {

            info("execute no. " + i + " of " + title);

            final long begin = System.nanoTime();

            generator.run();

            final long duration = System.nanoTime() - begin;

            times[i] = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS);
        }

        return times;
    }
}
