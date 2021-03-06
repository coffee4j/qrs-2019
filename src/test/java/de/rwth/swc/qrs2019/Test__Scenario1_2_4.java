package de.rwth.swc.qrs2019;

import de.rwth.swc.coffee4j.model.constraints.Constraint;
import de.rwth.swc.qrs2019.execution.ProgramPath;
import de.rwth.swc.qrs2019.modelling.Config3;
import de.rwth.swc.qrs2019.modelling.Experiment;
import de.rwth.swc.qrs2019.modelling.Fault;
import de.rwth.swc.qrs2019.modelling.Value7;
import de.rwth.swc.qrs2019.results.ExecutionInformation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.util.Arrays;

import static de.rwth.swc.coffee4j.model.constraints.ConstraintBuilder.constrain;
import static de.rwth.swc.qrs2019.execution.EffectivenessExperimentRunner.computeEffectiveness;
import static de.rwth.swc.qrs2019.execution.PerformanceExperimentRunner.computePerformance;

public class Test__Scenario1_2_4 implements Experiment {

    private static int V1 = 0;
    private static int V2 = 1;
    private static int V3 = 2;
    private static int V4 = 3;
    private static int V5 = 4;
    private static int X1 = 5;
    private static int X2 = 6;
    private static int X3 = 7;
    
    private static int X4 = 8;
    private static int X5 = 9;

    private static final Fault[] POSITIVE_FAULTS = {
            new Fault("+fault-01 (d=1)", 1, (input) -> Value7.III.equals(input[V1])),
            new Fault("+fault-02 (d=1)", 1, (input) -> Value7.IV .equals(input[V2])),
            new Fault("+fault-03 (d=1)", 1, (input) -> Value7.III.equals(input[V3])),
            new Fault("+fault-04 (d=1)", 1, (input) -> Value7.II .equals(input[V4])),
            new Fault("+fault-05 (d=1)", 1, (input) -> Config3.A .equals(input[X2])),

            new Fault("+fault-06 (d=2)", 2, (input) -> Value7.II .equals(input[V1]) && Value7.II .equals(input[V2])),
            new Fault("+fault-07 (d=2)", 2, (input) -> Value7.III.equals(input[V2]) && Value7.II .equals(input[V3])),
            new Fault("+fault-08 (d=2)", 2, (input) -> Value7.IV .equals(input[V4]) && Value7.III.equals(input[V5])),
            new Fault("+fault-09 (d=2)", 2, (input) -> Value7.IV .equals(input[V5]) && Config3.B .equals(input[X1])),
            new Fault("+fault-10 (d=2)", 2, (input) -> Config3.B .equals(input[X2]) && Config3.B .equals(input[X3])),

            new Fault("+fault-11 (d=3)", 3, (input) -> Value7.IV .equals(input[V1]) && Value7.III.equals(input[V2]) && Value7.II .equals(input[V3])),
            new Fault("+fault-12 (d=3)", 3, (input) -> Value7.V  .equals(input[V2]) && Value7.IV .equals(input[V3]) && Value7.III.equals(input[V4])),
            new Fault("+fault-13 (d=3)", 3, (input) -> Value7.V  .equals(input[V3]) && Value7.V  .equals(input[V4]) && Value7.V  .equals(input[V5])),
            new Fault("+fault-14 (d=3)", 3, (input) -> Value7.IV .equals(input[V4]) && Value7.IV .equals(input[V5]) && Config3.B .equals(input[X4])),
            new Fault("+fault-15 (d=3)", 3, (input) -> Value7.II .equals(input[V5]) && Config3.B .equals(input[X1]) && Config3.B .equals(input[X2])),

            new Fault("+fault-16 (d=4)", 4, (input) -> Value7.IV .equals(input[V1]) && Value7.V  .equals(input[V5]) && Config3.B .equals(input[X3]) && Config3.B .equals(input[X4])),
            new Fault("+fault-17 (d=4)", 4, (input) -> Value7.V  .equals(input[V1]) && Value7.V  .equals(input[V2]) && Value7.V  .equals(input[V3]) && Value7.V  .equals(input[V4])),
            new Fault("+fault-18 (d=4)", 4, (input) -> Value7.III.equals(input[V2]) && Value7.III.equals(input[V3]) && Value7.IV .equals(input[V4]) && Value7.IV .equals(input[V5])),
            new Fault("+fault-19 (d=4)", 4, (input) -> Value7.IV .equals(input[V2]) && Value7.II .equals(input[V3]) && Value7.III.equals(input[V4]) && Config3.B .equals(input[X5])),
            new Fault("+fault-20 (d=4)", 4, (input) -> Value7.II .equals(input[V4]) && Value7.II .equals(input[V5]) && Config3.B .equals(input[X1]) && Config3.B .equals(input[X2])),
    };

    private static final Fault[] NEGATIVE_FAULTS = {
            new Fault("-fault-01", 4 /*t=0*/, (input) -> Value7.I.equals(input[V1]) && Value7.II.equals(input[V2]) && Value7.III.equals(input[V3]) && Value7.IV.equals(input[V4])),
            new Fault("-fault-02", 4 /*t=0*/, (input) -> Value7.I.equals(input[V2]) && Value7.II.equals(input[V3]) && Value7.III.equals(input[V4]) && Value7.IV.equals(input[V5])),
            new Fault("-fault-03", 4 /*t=0*/, (input) -> Value7.I.equals(input[V3]) && Value7.II.equals(input[V4]) && Value7.III.equals(input[V5]) && Value7.IV.equals(input[V1])),
            new Fault("-fault-04", 4 /*t=0*/, (input) -> Value7.I.equals(input[V4]) && Value7.II.equals(input[V5]) && Value7.III.equals(input[V1]) && Value7.IV.equals(input[V2])),
            new Fault("-fault-05", 4 /*t=0*/, (input) -> Value7.I.equals(input[V5]) && Value7.II.equals(input[V1]) && Value7.III.equals(input[V2]) && Value7.IV.equals(input[V3])),

            new Fault("-fault-06", 5 /*t=1*/, (input) -> Config3.A.equals(input[X5]) && (Value7.I.equals(input[V1]) && Value7.II.equals(input[V2]) && Value7.III.equals(input[V3]) && Value7.IV.equals(input[V4]))),
            new Fault("-fault-07", 5 /*t=1*/, (input) -> Config3.B.equals(input[X3]) && (Value7.I.equals(input[V2]) && Value7.II.equals(input[V3]) && Value7.III.equals(input[V4]) && Value7.IV.equals(input[V5]))),
            new Fault("-fault-08", 5 /*t=1*/, (input) -> Config3.B.equals(input[X4]) && (Value7.I.equals(input[V3]) && Value7.II.equals(input[V4]) && Value7.III.equals(input[V5]) && Value7.IV.equals(input[V1]))),
            new Fault("-fault-09", 5 /*t=1*/, (input) -> Config3.A.equals(input[X2]) && (Value7.I.equals(input[V4]) && Value7.II.equals(input[V5]) && Value7.III.equals(input[V1]) && Value7.IV.equals(input[V2]))),
            new Fault("-fault-10", 5 /*t=1*/, (input) -> Config3.A.equals(input[X1]) && (Value7.I.equals(input[V5]) && Value7.II.equals(input[V1]) && Value7.III.equals(input[V2]) && Value7.IV.equals(input[V3]))),

            new Fault("-fault-11", 6 /*t=2*/, (input) -> Config3.A.equals(input[X4]) && Config3.B.equals(input[X3]) && (Value7.I.equals(input[V1]) && Value7.II.equals(input[V2]) && Value7.III.equals(input[V3]) && Value7.IV.equals(input[V4]))),
            new Fault("-fault-12", 6 /*t=2*/, (input) -> Config3.A.equals(input[X3]) && Config3.B.equals(input[X2]) && (Value7.I.equals(input[V2]) && Value7.II.equals(input[V3]) && Value7.III.equals(input[V4]) && Value7.IV.equals(input[V5]))),
            new Fault("-fault-13", 6 /*t=2*/, (input) -> Config3.A.equals(input[X2]) && Config3.B.equals(input[X1]) && (Value7.I.equals(input[V3]) && Value7.II.equals(input[V4]) && Value7.III.equals(input[V5]) && Value7.IV.equals(input[V1]))),
            new Fault("-fault-14", 6 /*t=2*/, (input) -> Config3.A.equals(input[X1]) && Config3.B.equals(input[X5]) && (Value7.I.equals(input[V4]) && Value7.II.equals(input[V5]) && Value7.III.equals(input[V1]) && Value7.IV.equals(input[V2]))),
            new Fault("-fault-15", 6 /*t=2*/, (input) -> Config3.A.equals(input[X5]) && Config3.B.equals(input[X4]) && (Value7.I.equals(input[V5]) && Value7.II.equals(input[V1]) && Value7.III.equals(input[V2]) && Value7.IV.equals(input[V3]))),

            new Fault("-fault-16", 7 /*t=3*/, (input) -> Config3.B.equals(input[X2]) && Config3.B.equals(input[X3]) && Config3.B.equals(input[X5]) && (Value7.I.equals(input[V1]) && Value7.II.equals(input[V2])) && Value7.III.equals(input[V3]) && Value7.IV.equals(input[V4])),
            new Fault("-fault-17", 7 /*t=3*/, (input) -> Config3.B.equals(input[X1]) && Config3.B.equals(input[X3]) && Config3.A.equals(input[X4]) && (Value7.I.equals(input[V2]) && Value7.II.equals(input[V3])) && Value7.III.equals(input[V4]) && Value7.IV.equals(input[V5])),
            new Fault("-fault-18", 7 /*t=3*/, (input) -> Config3.A.equals(input[X3]) && Config3.A.equals(input[X4]) && Config3.A.equals(input[X5]) && (Value7.I.equals(input[V3]) && Value7.II.equals(input[V4])) && Value7.III.equals(input[V5]) && Value7.IV.equals(input[V1])),
            new Fault("-fault-19", 7 /*t=3*/, (input) -> Config3.A.equals(input[X2]) && Config3.B.equals(input[X4]) && Config3.A.equals(input[X5]) && (Value7.I.equals(input[V4]) && Value7.II.equals(input[V5])) && Value7.III.equals(input[V1]) && Value7.IV.equals(input[V2])),
            new Fault("-fault-20", 7 /*t=3*/, (input) -> Config3.A.equals(input[X1]) && Config3.B.equals(input[X2]) && Config3.A.equals(input[X3]) && (Value7.I.equals(input[V5]) && Value7.II.equals(input[V1])) && Value7.III.equals(input[V2]) && Value7.IV.equals(input[V3])),
    };

    public Fault[] getPositiveFaults() { return POSITIVE_FAULTS; }

    public Fault getNegativeFault(String name) {
        return Arrays.stream(NEGATIVE_FAULTS).filter(fault -> fault.getName().equals(name)).findFirst().orElseThrow();
    }

    public Fault[] getNegativeFaults() {
        return NEGATIVE_FAULTS;
    }

    public ExecutionInformation executeTestMethod(Object[] input) {
        final ExecutionInformation information = new ExecutionInformation();

        if(Value7.I.equals(input[V1]) && Value7.II.equals(input[V2]) && Value7.III.equals(input[V3]) && Value7.IV.equals(input[V4])) {
            information.setProgramPath(ProgramPath.NEGATIVE);
            information.reachFault(getNegativeFault("-fault-01"), input);
            information.reachFault(getNegativeFault("-fault-06"), input);
            information.reachFault(getNegativeFault("-fault-11"), input);
            information.reachFault(getNegativeFault("-fault-16"), input);
        } else if(Value7.I.equals(input[V2]) && Value7.II.equals(input[V3]) && Value7.III.equals(input[V4]) && Value7.IV.equals(input[V5])) {
            information.setProgramPath(ProgramPath.NEGATIVE);
            information.reachFault(getNegativeFault("-fault-02"), input);
            information.reachFault(getNegativeFault("-fault-07"), input);
            information.reachFault(getNegativeFault("-fault-12"), input);
            information.reachFault(getNegativeFault("-fault-17"), input);
        } else if(Value7.I.equals(input[V3]) && Value7.II.equals(input[V4]) && Value7.III.equals(input[V5]) && Value7.IV.equals(input[V1])) {
            information.setProgramPath(ProgramPath.NEGATIVE);
            information.reachFault(getNegativeFault("-fault-03"), input);
            information.reachFault(getNegativeFault("-fault-08"), input);
            information.reachFault(getNegativeFault("-fault-13"), input);
            information.reachFault(getNegativeFault("-fault-18"), input);
        } else if(Value7.I.equals(input[V4]) && Value7.II.equals(input[V5]) && Value7.III.equals(input[V1]) && Value7.IV.equals(input[V2])) {
            information.setProgramPath(ProgramPath.NEGATIVE);
            information.reachFault(getNegativeFault("-fault-04"), input);
            information.reachFault(getNegativeFault("-fault-09"), input);
            information.reachFault(getNegativeFault("-fault-14"), input);
            information.reachFault(getNegativeFault("-fault-19"), input);
        } else if(Value7.I.equals(input[V5]) && Value7.II.equals(input[V1]) && Value7.III.equals(input[V2]) && Value7.IV.equals(input[V3])) {
            information.setProgramPath(ProgramPath.NEGATIVE);
            information.reachFault(getNegativeFault("-fault-05"), input);
            information.reachFault(getNegativeFault("-fault-10"), input);
            information.reachFault(getNegativeFault("-fault-15"), input);
            information.reachFault(getNegativeFault("-fault-20"), input);
        } else {
            information.setProgramPath(ProgramPath.POSITIVE);

            Arrays.stream(getPositiveFaults()).forEach(fault -> information.reachFault(fault, input));
        }

        return information;
    }

    public Constraint[] getErrorConstraints() {
        return new Constraint[] {
                constrain("v1", "v2", "v3", "v4").by((Value7 v1, Value7 v2, Value7 v3, Value7 v4) -> !(Value7.I.equals(v1) && Value7.II.equals(v2) && Value7.III.equals(v3) && Value7.IV.equals(v4))),
                constrain("v2", "v3", "v4", "v5").by((Value7 v2, Value7 v3, Value7 v4, Value7 v5) -> !(Value7.I.equals(v2) && Value7.II.equals(v3) && Value7.III.equals(v4) && Value7.IV.equals(v5))),
                constrain("v3", "v4", "v5", "v1").by((Value7 v3, Value7 v4, Value7 v5, Value7 v1) -> !(Value7.I.equals(v3) && Value7.II.equals(v4) && Value7.III.equals(v5) && Value7.IV.equals(v1))),
                constrain("v4", "v5", "v1", "v2").by((Value7 v4, Value7 v5, Value7 v1, Value7 v2) -> !(Value7.I.equals(v4) && Value7.II.equals(v5) && Value7.III.equals(v1) && Value7.IV.equals(v2))),
                constrain("v5", "v1", "v2", "v3").by((Value7 v5, Value7 v1, Value7 v2, Value7 v3) -> !(Value7.I.equals(v5) && Value7.II.equals(v1) && Value7.III.equals(v2) && Value7.IV.equals(v3)))
        };
    }

    @Test
    void testEffectiveness() {
        computeEffectiveness(this, new Scenario1_2());
    }
}