package de.rwth.swc.qrs2019;

import de.rwth.swc.coffee4j.model.Parameter;
import de.rwth.swc.qrs2019.modelling.Config2;
import de.rwth.swc.qrs2019.modelling.Scenario;
import de.rwth.swc.qrs2019.modelling.Value5;

public class Scenario1_1 implements Scenario {

    public int[] getParameterOrder(int index) {
        return PARAMETER_ORDER[index];
    }

    public Parameter[] getOrderedParameters(int index) {
        return ORDERED_PARAMETERS[index];
    }

    public int[][] PARAMETER_ORDER = {
            new int[]{ 2, 0, 9, 7, 3, 1, 4, 8, 6, 5 },
            new int[]{ 1, 0, 4, 2, 9, 7, 3, 5, 8, 6 },
            new int[]{ 8, 2, 0, 9, 5, 6, 3, 4, 1, 7 },
            new int[]{ 1, 8, 0, 9, 7, 2, 3, 6, 4, 5 },
            new int[]{ 8, 2, 9, 0, 6, 3, 5, 7, 1, 4 },
            new int[]{ 0, 2, 5, 6, 3, 7, 9, 8, 4, 1 },
            new int[]{ 2, 3, 9, 7, 4, 0, 5, 1, 8, 6 },
            new int[]{ 4, 8, 0, 1, 3, 5, 6, 7, 9, 2 },
            new int[]{ 8, 3, 7, 9, 2, 1, 5, 4, 6, 0 },
            new int[]{ 7, 3, 6, 0, 5, 4, 1, 9, 2, 8 },
    };

    public static final Parameter[][] ORDERED_PARAMETERS = {
            new Parameter[] {
                    Parameter.parameter("v3").values(Value5.II, Value5.I, Value5.V, Value5.IV, Value5.III).build(),
                    Parameter.parameter("v1").values(Value5.IV, Value5.I, Value5.V, Value5.II, Value5.III).build(),
                    Parameter.parameter("x5").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("x3").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v4").values(Value5.III, Value5.II, Value5.I, Value5.IV, Value5.V).build(),
                    Parameter.parameter("v2").values(Value5.I, Value5.V, Value5.IV, Value5.III, Value5.II).build(),
                    Parameter.parameter("v5").values(Value5.IV, Value5.II, Value5.V, Value5.III, Value5.I).build(),
                    Parameter.parameter("x4").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("x2").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("x1").values(Config2.A, Config2.B).build()
            },
            new Parameter[] {
                    Parameter.parameter("v2").values(Value5.II, Value5.IV, Value5.III, Value5.V, Value5.I).build(),
                    Parameter.parameter("v1").values(Value5.II, Value5.I, Value5.IV, Value5.III, Value5.V).build(),
                    Parameter.parameter("v5").values(Value5.I, Value5.IV, Value5.III, Value5.V, Value5.II).build(),
                    Parameter.parameter("v3").values(Value5.III, Value5.V, Value5.IV, Value5.II, Value5.I).build(),
                    Parameter.parameter("x5").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("x3").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("v4").values(Value5.IV, Value5.I, Value5.III, Value5.II, Value5.V).build(),
                    Parameter.parameter("x1").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("x4").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("x2").values(Config2.B, Config2.A).build()
            },
            new Parameter[] {
                    Parameter.parameter("x4").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v3").values(Value5.III, Value5.IV, Value5.II, Value5.V, Value5.I).build(),
                    Parameter.parameter("v1").values(Value5.IV, Value5.V, Value5.III, Value5.I, Value5.II).build(),
                    Parameter.parameter("x5").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("x1").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("x2").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("v4").values(Value5.III, Value5.II, Value5.IV, Value5.I, Value5.V).build(),
                    Parameter.parameter("v5").values(Value5.I, Value5.III, Value5.II, Value5.IV, Value5.V).build(),
                    Parameter.parameter("v2").values(Value5.V, Value5.IV, Value5.III, Value5.I, Value5.II).build(),
                    Parameter.parameter("x3").values(Config2.B, Config2.A).build()
            },
            new Parameter[] {
                    Parameter.parameter("v2").values(Value5.III, Value5.II, Value5.V, Value5.IV, Value5.I).build(),
                    Parameter.parameter("x4").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v1").values(Value5.II, Value5.IV, Value5.III, Value5.I, Value5.V).build(),
                    Parameter.parameter("x5").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("x3").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("v3").values(Value5.V, Value5.II, Value5.III, Value5.I, Value5.IV).build(),
                    Parameter.parameter("v4").values(Value5.II, Value5.I, Value5.III, Value5.IV, Value5.V).build(),
                    Parameter.parameter("x2").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("v5").values(Value5.IV, Value5.III, Value5.I, Value5.II, Value5.V).build(),
                    Parameter.parameter("x1").values(Config2.B, Config2.A).build()
            },
            new Parameter[] {
                    Parameter.parameter("x4").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v3").values(Value5.I, Value5.V, Value5.II, Value5.III, Value5.IV).build(),
                    Parameter.parameter("x5").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v1").values(Value5.I, Value5.IV, Value5.II, Value5.V, Value5.III).build(),
                    Parameter.parameter("x2").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("v4").values(Value5.V, Value5.III, Value5.IV, Value5.II, Value5.I).build(),
                    Parameter.parameter("x1").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("x3").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("v2").values(Value5.II, Value5.I, Value5.IV, Value5.III, Value5.V).build(),
                    Parameter.parameter("v5").values(Value5.III, Value5.I, Value5.IV, Value5.V, Value5.II).build()
            },
            new Parameter[] {
                    Parameter.parameter("v1").values(Value5.V, Value5.IV, Value5.II, Value5.I, Value5.III).build(),
                    Parameter.parameter("v3").values(Value5.II, Value5.III, Value5.IV, Value5.V, Value5.I).build(),
                    Parameter.parameter("x1").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("x2").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v4").values(Value5.IV, Value5.V, Value5.III, Value5.II, Value5.I).build(),
                    Parameter.parameter("x3").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("x5").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("x4").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v5").values(Value5.IV, Value5.II, Value5.I, Value5.V, Value5.III).build(),
                    Parameter.parameter("v2").values(Value5.I, Value5.IV, Value5.III, Value5.V, Value5.II).build()
            },
            new Parameter[] {
                    Parameter.parameter("v3").values(Value5.V, Value5.IV, Value5.I, Value5.III, Value5.II).build(),
                    Parameter.parameter("v4").values(Value5.III, Value5.II, Value5.I, Value5.V, Value5.IV).build(),
                    Parameter.parameter("x5").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("x3").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v5").values(Value5.IV, Value5.V, Value5.II, Value5.I, Value5.III).build(),
                    Parameter.parameter("v1").values(Value5.III, Value5.V, Value5.IV, Value5.II, Value5.I).build(),
                    Parameter.parameter("x1").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("v2").values(Value5.V, Value5.I, Value5.II, Value5.IV, Value5.III).build(),
                    Parameter.parameter("x4").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("x2").values(Config2.B, Config2.A).build()
            },
            new Parameter[] {
                    Parameter.parameter("v5").values(Value5.I, Value5.III, Value5.IV, Value5.V, Value5.II).build(),
                    Parameter.parameter("x4").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v1").values(Value5.II, Value5.IV, Value5.III, Value5.I, Value5.V).build(),
                    Parameter.parameter("v2").values(Value5.II, Value5.V, Value5.IV, Value5.I, Value5.III).build(),
                    Parameter.parameter("v4").values(Value5.V, Value5.IV, Value5.I, Value5.II, Value5.III).build(),
                    Parameter.parameter("x1").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("x2").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("x3").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("x5").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v3").values(Value5.V, Value5.III, Value5.II, Value5.I, Value5.IV).build()
            },
            new Parameter[] {
                    Parameter.parameter("x4").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v4").values(Value5.IV, Value5.I, Value5.V, Value5.II, Value5.III).build(),
                    Parameter.parameter("x3").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("x5").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v3").values(Value5.III, Value5.I, Value5.V, Value5.II, Value5.IV).build(),
                    Parameter.parameter("v2").values(Value5.III, Value5.I, Value5.IV, Value5.V, Value5.II).build(),
                    Parameter.parameter("x1").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("v5").values(Value5.IV, Value5.III, Value5.I, Value5.II, Value5.V).build(),
                    Parameter.parameter("x2").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v1").values(Value5.III, Value5.IV, Value5.II, Value5.V, Value5.I).build()
            },

            new Parameter[] {
                    Parameter.parameter("x3").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v4").values(Value5.IV, Value5.V, Value5.III, Value5.I, Value5.II).build(),
                    Parameter.parameter("x2").values(Config2.B, Config2.A).build(),
                    Parameter.parameter("v1").values(Value5.IV, Value5.II, Value5.V, Value5.I, Value5.III).build(),
                    Parameter.parameter("x1").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("v5").values(Value5.IV, Value5.V, Value5.I, Value5.III, Value5.II).build(),
                    Parameter.parameter("v2").values(Value5.I, Value5.II, Value5.III, Value5.V, Value5.IV).build(),
                    Parameter.parameter("x5").values(Config2.A, Config2.B).build(),
                    Parameter.parameter("v3").values(Value5.I, Value5.IV, Value5.III, Value5.II, Value5.V).build(),
                    Parameter.parameter("x4").values(Config2.A, Config2.B).build()
            }
    };
}
