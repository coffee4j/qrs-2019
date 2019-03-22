package de.rwth.swc.qrs2019.modelling;

import de.rwth.swc.coffee4j.model.constraints.Constraint;
import de.rwth.swc.qrs2019.results.ExecutionInformation;

public interface Experiment {

    Constraint[] getErrorConstraints();

    ExecutionInformation executeTestMethod(Object[] input);

    Fault[] getPositiveFaults();
    Fault[] getNegativeFaults();
    Fault getNegativeFault(String name);
}
