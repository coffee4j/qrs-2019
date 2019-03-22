package de.rwth.swc.qrs2019.modelling;

import de.rwth.swc.coffee4j.model.Parameter;

public interface Scenario {
    int[] getParameterOrder(int index);

    Parameter[] getOrderedParameters(int index);
}
