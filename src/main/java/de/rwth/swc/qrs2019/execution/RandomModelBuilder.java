package de.rwth.swc.qrs2019.execution;

import de.rwth.swc.coffee4j.model.Parameter;
import de.rwth.swc.coffee4j.model.Value;
import de.rwth.swc.qrs2019.modelling.*;
import org.apache.commons.math3.util.MathArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomModelBuilder {

    public static void main(String[] args) {
        generateRandomModel(10, Value7.class, 10, Config3.class);
    }

    public static <T extends Enum<T>, U extends Enum<U>>
    void generateRandomModel(int numOfValues, Class<T> valueClass, int numOfConfigs, Class<U> configClass) {

        final StringBuilder ordersStringBuilder = new StringBuilder();
        final StringBuilder parametersStringBuilder = new StringBuilder();

        for(int i = 0; i < 10; i++) {
            final Parameter[] parameters = buildModelParameters(numOfValues, valueClass, numOfConfigs, configClass);
            final int[] parameterOrder = buildRandomParameterOrder(parameters);
            final Parameter[] orderedParameters = orderParameters(parameters, parameterOrder);

            final String str = Arrays.stream(orderedParameters).map(parameter -> {
                final String values = parameter.getValues()
                        .stream()
                        .map(RandomModelBuilder::valueToString)
                        .collect(Collectors.joining(", "));

                return "Parameter.parameter(\"" +parameter.getName() + "\")" + ".values(" + values + ").build()";
            }).collect(Collectors.joining(",\n"));

            final String order = "new int[]{ " + Arrays.stream(parameterOrder).mapToObj(Integer::toString).collect(Collectors.joining(", ")) + " },";
            ordersStringBuilder.append(order);
            ordersStringBuilder.append("\n");

            parametersStringBuilder.append("new Parameter[] {");
            parametersStringBuilder.append(str);
            parametersStringBuilder.append("}");
            parametersStringBuilder.append("\n");
            parametersStringBuilder.append("\n");
        }

        System.out.println(ordersStringBuilder);
        System.out.println();
        System.out.println(parametersStringBuilder);
    }

    public static String valueToString(Value value) {
        final Object object = value.get();
        final String className = object.getClass().toString();

        int index = className.indexOf("Value");
        if(index == -1) {
            index = className.indexOf("Config");
        }

        return className.substring(index) + "." + object.toString();
    }

    public static <T extends Enum<T>, U extends Enum<U>>
    Parameter[] buildModelParameters(int numOfValues, Class<T> valueClass, int numOfConfigs, Class<U> configClass) {

        final ParameterValuesRandomSupplier<T> valueSupplier = new ParameterValuesRandomSupplier<>(valueClass);
        final ParameterValuesRandomSupplier<U> configSupplier = new ParameterValuesRandomSupplier<>(configClass);

        final List<Parameter> parameters = new ArrayList<>();

        for(int i = 1; i <= numOfValues; i++) {
            final String parameterName = "v" + i;
            final Parameter parameter = Parameter.parameter(parameterName).values((Object[]) valueSupplier.get()).build();

            parameters.add(parameter);
        }

        for(int i = 1; i <= numOfConfigs; i++) {
            final String parameterName = "x" + i;
            final Parameter parameter = Parameter.parameter(parameterName).values((Object[]) configSupplier.get()).build();

            parameters.add(parameter);
        }

        return parameters.toArray(new Parameter[0]);
    }

    public static int[] buildRandomParameterOrder(Parameter[] parameters) {
        int[] indices  = IntStream.range(0, parameters.length).toArray();

        MathArrays.shuffle(indices);

        return indices;
    }

    public static Parameter[] orderParameters(Parameter[] parameters, int[] order) {
        final Parameter[] orderedParameters = new Parameter[parameters.length];

        for(int j = 0; j < order.length; j++) {
            orderedParameters[j] = parameters[order[j]];
        }

        return orderedParameters;
    }

    public static Object[] reverseOrderedOfConcreteValues(Object[] values, int[] order) {
        final Object[] parameters = new Object[values.length];

        for(int j = 0; j < order.length; j++) {
            parameters[order[j]] = values[j];
        }
        return parameters;
    }
}
