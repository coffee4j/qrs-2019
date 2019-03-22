package de.rwth.swc.qrs2019.results;

import de.rwth.swc.qrs2019.execution.TestInput;

public class ExecutedTestInput {

    private final TestInput testInput;
    private final ExecutionInformation executionInformation;

    public ExecutedTestInput(TestInput testInput, ExecutionInformation executioninformation) {
        this.testInput = testInput;
        this.executionInformation = executioninformation;
    }

    public TestInput getTestInput() {
        return testInput;
    }

    public ExecutionInformation getExecutionInformation() {
        return executionInformation;
    }
}
