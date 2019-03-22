package de.rwth.swc.qrs2019.execution;

import de.rwth.swc.coffee4j.model.InputParameterModel;
import de.rwth.swc.coffee4j.model.Parameter;
import de.rwth.swc.coffee4j.model.Value;

public class InputParameterModelBasedAbstractToConcreteValuesConverter implements AbstractToConcreteValuesConverter {

    private final InputParameterModel inputParameterModel;

    public InputParameterModelBasedAbstractToConcreteValuesConverter(InputParameterModel inputParameterModel) {
        this.inputParameterModel = inputParameterModel;
    }

    @Override
    public Object[] convert(int[] abstractValues) {
        final Object[] concretValues = new Object[inputParameterModel.getParameters().size()];

        for (int parameterId = 0; parameterId < inputParameterModel.size(); parameterId++) {
            final Parameter parameter = inputParameterModel.getParameters().get(parameterId);
            final Value correspondingValue = parameter.getValues().get(abstractValues[parameterId]);

            concretValues[parameterId] = correspondingValue.get();
        }

        return concretValues;
    }
}
