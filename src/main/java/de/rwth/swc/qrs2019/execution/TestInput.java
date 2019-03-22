package de.rwth.swc.qrs2019.execution;

public class TestInput {

    private final int[] abstractValues;
    private final Object[] concreteValues;

    public TestInput(int[] abstractValues, Object[] concreteValues) {
        this.abstractValues = abstractValues;
        this.concreteValues = concreteValues;
    }

    public int[] getAbstractValues() {
        return abstractValues;
    }

    public Object[] getConcreteValues() {
        return concreteValues;
    }
}
