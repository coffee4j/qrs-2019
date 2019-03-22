package de.rwth.swc.qrs2019.execution;

public class InputSpaceBasedAbstractToConcreteValuesConverter implements AbstractToConcreteValuesConverter {

    private final Object[][] inputSpaceModel;

    public InputSpaceBasedAbstractToConcreteValuesConverter(Object[][] inputSpaceModel) {
        this.inputSpaceModel = inputSpaceModel;
    }

    @Override
    public Object[] convert(int[] abstractValues) {
        final Object[] concreteValues = new Object[abstractValues.length];

        for(int i = 0; i < abstractValues.length; i++) {
            final Object[] values = inputSpaceModel[i];
            final int index = abstractValues[i];
            final Object value = values[index];

            concreteValues[i] = value;
        }

        return concreteValues;
    }
}
