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

public class Test__Scenario2_2_2 implements Experiment {

    private static int V1 = 0;
    private static int V2 = 1;
    private static int V3 = 2;
    private static int V4 = 3;
    private static int V5 = 4;
    private static int V6 = 5;
    private static int V7 = 6;
    private static int V8 = 7;
    private static int V9 = 8;
    private static int V10 = 9;
    private static int X1 = 10;
    private static int X2 = 11;
    private static int X3 = 12;
    private static int X4 = 13;
    private static int X5 = 14;
    private static int X6 = 15;
    private static int X7 = 16;
    private static int X8 = 17;
    private static int X9 = 18;
    private static int X10 = 19;

    private static final Fault[] POSITIVE_FAULTS = {
            new Fault("+fault-01 (d=1)", 1, (input) -> Value7.III.equals(input[V6])),
            new Fault("+fault-02 (d=1)", 1, (input) -> Value7.IV .equals(input[V7])),
            new Fault("+fault-03 (d=1)", 1, (input) -> Value7.III.equals(input[V8])),
            new Fault("+fault-04 (d=1)", 1, (input) -> Value7.II .equals(input[V9])),
            new Fault("+fault-05 (d=1)", 1, (input) -> Config3.A .equals(input[X7])),

            new Fault("+fault-06 (d=2)", 2, (input) -> Value7.II .equals(input[V6]) && Value7.II .equals(input[V7])),
            new Fault("+fault-07 (d=2)", 2, (input) -> Value7.III.equals(input[V7]) && Value7.II .equals(input[V8])),
            new Fault("+fault-08 (d=2)", 2, (input) -> Value7.IV .equals(input[V9]) && Value7.III.equals(input[V10])),
            new Fault("+fault-09 (d=2)", 2, (input) -> Value7.IV .equals(input[V10])&& Config3.B .equals(input[X6])),
            new Fault("+fault-10 (d=2)", 2, (input) -> Config3.B .equals(input[X7]) && Config3.B .equals(input[X8])),

            new Fault("+fault-11 (d=3)", 3, (input) -> Value7.IV .equals(input[V6]) && Value7.III.equals(input[V7]) && Value7.II .equals(input[V8])),
            new Fault("+fault-12 (d=3)", 3, (input) -> Value7.V  .equals(input[V7]) && Value7.IV .equals(input[V8]) && Value7.III.equals(input[V9])),
            new Fault("+fault-13 (d=3)", 3, (input) -> Value7.V  .equals(input[V8]) && Value7.V  .equals(input[V9]) && Value7.V  .equals(input[V10])),
            new Fault("+fault-14 (d=3)", 3, (input) -> Value7.IV .equals(input[V9]) && Value7.IV .equals(input[V10])&& Config3.B .equals(input[X9])),
            new Fault("+fault-15 (d=3)", 3, (input) -> Value7.II .equals(input[V10])&& Config3.B .equals(input[X6]) && Config3.B .equals(input[X7])),

            new Fault("+fault-16 (d=4)", 4, (input) -> Value7.IV .equals(input[V6]) && Value7.V  .equals(input[V10])&& Config3.B .equals(input[X8]) && Config3.B .equals(input[X9])),
            new Fault("+fault-17 (d=4)", 4, (input) -> Value7.V  .equals(input[V6]) && Value7.V  .equals(input[V7]) && Value7.V  .equals(input[V8]) && Value7.V  .equals(input[V9])),
            new Fault("+fault-18 (d=4)", 4, (input) -> Value7.III.equals(input[V7]) && Value7.III.equals(input[V8]) && Value7.IV .equals(input[V9]) && Value7.IV .equals(input[V10])),
            new Fault("+fault-19 (d=4)", 4, (input) -> Value7.IV .equals(input[V7]) && Value7.II .equals(input[V8]) && Value7.III.equals(input[V9]) && Config3.B .equals(input[X10])),
            new Fault("+fault-20 (d=4)", 4, (input) -> Value7.II .equals(input[V9]) && Value7.II .equals(input[V10])&& Config3.B .equals(input[X6]) && Config3.B .equals(input[X7])),
    };

    private static final Fault[] NEGATIVE_FAULTS = {
            new Fault("-fault-01", 2 /*t=0*/, (input) -> Value7.I.equals(input[V1]) && Value7.II.equals(input[V2])),
            new Fault("-fault-02", 2 /*t=0*/, (input) -> Value7.I.equals(input[V2]) && Value7.II.equals(input[V3])),
            new Fault("-fault-03", 2 /*t=0*/, (input) -> Value7.I.equals(input[V3]) && Value7.II.equals(input[V4])),
            new Fault("-fault-04", 2 /*t=0*/, (input) -> Value7.I.equals(input[V4]) && Value7.II.equals(input[V5])),
            new Fault("-fault-05", 2 /*t=0*/, (input) -> Value7.I.equals(input[V5]) && Value7.II.equals(input[V1])),

            new Fault("-fault-06", 3 /*t=1*/, (input) -> Config3.A.equals(input[X5]) && (Value7.I.equals(input[V1]) && Value7.II.equals(input[V2]))),
            new Fault("-fault-07", 3 /*t=1*/, (input) -> Config3.B.equals(input[X3]) && (Value7.I.equals(input[V2]) && Value7.II.equals(input[V3]))),
            new Fault("-fault-08", 3 /*t=1*/, (input) -> Config3.B.equals(input[X4]) && (Value7.I.equals(input[V3]) && Value7.II.equals(input[V4]))),
            new Fault("-fault-09", 3 /*t=1*/, (input) -> Config3.A.equals(input[X2]) && (Value7.I.equals(input[V4]) && Value7.II.equals(input[V5]))),
            new Fault("-fault-10", 3 /*t=1*/, (input) -> Config3.A.equals(input[X1]) && (Value7.I.equals(input[V5]) && Value7.II.equals(input[V1]))),

            new Fault("-fault-11", 4 /*t=2*/, (input) -> Config3.A.equals(input[X4]) && Config3.B.equals(input[X3]) && (Value7.I.equals(input[V1]) && Value7.II.equals(input[V2]))),
            new Fault("-fault-12", 4 /*t=2*/, (input) -> Config3.A.equals(input[X3]) && Config3.B.equals(input[X2]) && (Value7.I.equals(input[V2]) && Value7.II.equals(input[V3]))),
            new Fault("-fault-13", 4 /*t=2*/, (input) -> Config3.A.equals(input[X2]) && Config3.B.equals(input[X1]) && (Value7.I.equals(input[V3]) && Value7.II.equals(input[V4]))),
            new Fault("-fault-14", 4 /*t=2*/, (input) -> Config3.A.equals(input[X1]) && Config3.B.equals(input[X5]) && (Value7.I.equals(input[V4]) && Value7.II.equals(input[V5]))),
            new Fault("-fault-15", 4 /*t=2*/, (input) -> Config3.A.equals(input[X5]) && Config3.B.equals(input[X4]) && (Value7.I.equals(input[V5]) && Value7.II.equals(input[V1]))),

            new Fault("-fault-16", 5 /*t=3*/, (input) -> Config3.B.equals(input[X2]) && Config3.B.equals(input[X3]) && Config3.B.equals(input[X5]) && (Value7.I.equals(input[V1]) && Value7.II.equals(input[V2]))),
            new Fault("-fault-17", 5 /*t=3*/, (input) -> Config3.B.equals(input[X1]) && Config3.B.equals(input[X3]) && Config3.A.equals(input[X4]) && (Value7.I.equals(input[V2]) && Value7.II.equals(input[V3]))),
            new Fault("-fault-18", 5 /*t=3*/, (input) -> Config3.A.equals(input[X3]) && Config3.A.equals(input[X4]) && Config3.A.equals(input[X5]) && (Value7.I.equals(input[V3]) && Value7.II.equals(input[V4]))),
            new Fault("-fault-19", 5 /*t=3*/, (input) -> Config3.A.equals(input[X2]) && Config3.B.equals(input[X4]) && Config3.A.equals(input[X5]) && (Value7.I.equals(input[V4]) && Value7.II.equals(input[V5]))),
            new Fault("-fault-20", 5 /*t=3*/, (input) -> Config3.A.equals(input[X1]) && Config3.B.equals(input[X2]) && Config3.A.equals(input[X3]) && (Value7.I.equals(input[V5]) && Value7.II.equals(input[V1]))),
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

        if(Value7.I.equals(input[V1]) && Value7.II.equals(input[V2])) {
            information.setProgramPath(ProgramPath.NEGATIVE);
            information.reachFault(getNegativeFault("-fault-01"), input);
            information.reachFault(getNegativeFault("-fault-06"), input);
            information.reachFault(getNegativeFault("-fault-11"), input);
            information.reachFault(getNegativeFault("-fault-16"), input);
        } else if(Value7.I.equals(input[V2]) && Value7.II.equals(input[V3])) {
            information.setProgramPath(ProgramPath.NEGATIVE);
            information.reachFault(getNegativeFault("-fault-02"), input);
            information.reachFault(getNegativeFault("-fault-07"), input);
            information.reachFault(getNegativeFault("-fault-12"), input);
            information.reachFault(getNegativeFault("-fault-17"), input);
        } else if(Value7.I.equals(input[V3]) && Value7.II.equals(input[V4])) {
            information.setProgramPath(ProgramPath.NEGATIVE);
            information.reachFault(getNegativeFault("-fault-03"), input);
            information.reachFault(getNegativeFault("-fault-08"), input);
            information.reachFault(getNegativeFault("-fault-13"), input);
            information.reachFault(getNegativeFault("-fault-18"), input);
        } else if(Value7.I.equals(input[V4]) && Value7.II.equals(input[V5])) {
            information.setProgramPath(ProgramPath.NEGATIVE);
            information.reachFault(getNegativeFault("-fault-04"), input);
            information.reachFault(getNegativeFault("-fault-09"), input);
            information.reachFault(getNegativeFault("-fault-14"), input);
            information.reachFault(getNegativeFault("-fault-19"), input);
        } else if(Value7.I.equals(input[V5]) && Value7.II.equals(input[V1])) {
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
                constrain("v1", "v2").by((Value7 v1, Value7 v2) -> !(Value7.I.equals(v1) && Value7.II.equals(v2))),
                constrain("v2", "v3").by((Value7 v2, Value7 v3) -> !(Value7.I.equals(v2) && Value7.II.equals(v3))),
                constrain("v3", "v4").by((Value7 v3, Value7 v4) -> !(Value7.I.equals(v3) && Value7.II.equals(v4))),
                constrain("v4", "v5").by((Value7 v4, Value7 v5) -> !(Value7.I.equals(v4) && Value7.II.equals(v5))),
                constrain("v5", "v1").by((Value7 v5, Value7 v1) -> !(Value7.I.equals(v5) && Value7.II.equals(v1)))
        };
    }

    @Test
    void testEffectiveness() {
        computeEffectiveness(this, new Scenario2_2());
    }
}