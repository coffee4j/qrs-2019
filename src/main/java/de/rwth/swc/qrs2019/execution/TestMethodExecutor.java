package de.rwth.swc.qrs2019.execution;

import de.rwth.swc.qrs2019.results.ExecutedTestInput;
import de.rwth.swc.qrs2019.results.ExecutionInformation;
import de.rwth.swc.qrs2019.modelling.Experiment;

import static de.rwth.swc.qrs2019.execution.RandomModelBuilder.reverseOrderedOfConcreteValues;

public class TestMethodExecutor {

    private final Experiment experiment;
    private final int[] order;
    private final AbstractToConcreteValuesConverter converter;

    public TestMethodExecutor(final Experiment experiment, final int[] order, final AbstractToConcreteValuesConverter converter) {
        this.experiment = experiment;
        this.order = order;
        this.converter = converter;
    }

    public ExecutedTestInput execute(int[] input) {
        final TestInput testInput = new TestInput(input, converter.convert(input));

        return execute(testInput);
    }

    private ExecutedTestInput execute(TestInput testInput ) {
        final Object[] concreteValues = testInput.getConcreteValues();
        final Object[] orderedInput = reverseOrderedOfConcreteValues(concreteValues, order);

        final ExecutionInformation information = experiment.executeTestMethod(orderedInput);

        return new ExecutedTestInput(testInput, information);
    }
}
