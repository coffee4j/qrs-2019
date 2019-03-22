package de.rwth.swc.qrs2019;

import de.rwth.swc.coffee4j.model.Parameter;
import de.rwth.swc.qrs2019.modelling.*;

public class Scenario1_2 implements Scenario {

    public int[] getParameterOrder(int index) {
        return PARAMETER_ORDER[index];
    }

    public Parameter[] getOrderedParameters(int index) {
        return ORDERED_PARAMETERS[index];
    }

    public int[][] PARAMETER_ORDER = {
            new int[]{ 9, 2, 5, 4, 6, 3, 8, 0, 7, 1 },
            new int[]{ 8, 2, 0, 6, 1, 4, 9, 5, 7, 3 },
            new int[]{ 9, 8, 3, 1, 6, 4, 7, 2, 0, 5 },
            new int[]{ 8, 3, 4, 7, 5, 9, 1, 6, 0, 2 },
            new int[]{ 0, 3, 8, 7, 4, 9, 1, 5, 6, 2 },
            new int[]{ 1, 5, 2, 9, 6, 7, 4, 8, 3, 0 },
            new int[]{ 0, 9, 1, 5, 6, 7, 3, 2, 4, 8 },
            new int[]{ 5, 1, 7, 6, 2, 9, 4, 3, 0, 8 },
            new int[]{ 9, 6, 7, 4, 2, 8, 3, 0, 1, 5 },
            new int[]{ 4, 3, 0, 6, 9, 8, 5, 1, 7, 2 }
    };

    public static final Parameter[][] ORDERED_PARAMETERS = {
            new Parameter[] {
                    Parameter.parameter("x5").values(Config3.A, Config3.C, Config3.B).build(),
                    Parameter.parameter("v3").values(Value7.III, Value7.VI, Value7.IV, Value7.V, Value7.I, Value7.VII, Value7.II).build(),
                    Parameter.parameter("x1").values(Config3.A, Config3.C, Config3.B).build(),
                    Parameter.parameter("v5").values(Value7.I, Value7.II, Value7.VII, Value7.V, Value7.VI, Value7.IV, Value7.III).build(),
                    Parameter.parameter("x2").values(Config3.B, Config3.C, Config3.A).build(),
                    Parameter.parameter("v4").values(Value7.VII, Value7.IV, Value7.III, Value7.II, Value7.V, Value7.VI, Value7.I).build(),
                    Parameter.parameter("x4").values(Config3.B, Config3.A, Config3.C).build(),
                    Parameter.parameter("v1").values(Value7.IV, Value7.VI, Value7.V, Value7.II, Value7.III, Value7.I, Value7.VII).build(),
                    Parameter.parameter("x3").values(Config3.C, Config3.B, Config3.A).build(),
                    Parameter.parameter("v2").values(Value7.III, Value7.I, Value7.VI, Value7.VII, Value7.IV, Value7.II, Value7.V).build()
            },

            new Parameter[] {
                    Parameter.parameter("x4").values(Config3.C, Config3.B, Config3.A).build(),
                    Parameter.parameter("v3").values(Value7.V, Value7.II, Value7.III, Value7.IV, Value7.VI, Value7.I, Value7.VII).build(),
                    Parameter.parameter("v1").values(Value7.II, Value7.VI, Value7.IV, Value7.I, Value7.V, Value7.III, Value7.VII).build(),
                    Parameter.parameter("x2").values(Config3.B, Config3.C, Config3.A).build(),
                    Parameter.parameter("v2").values(Value7.VII, Value7.IV, Value7.III, Value7.II, Value7.V, Value7.VI, Value7.I).build(),
                    Parameter.parameter("v5").values(Value7.VI, Value7.II, Value7.III, Value7.VII, Value7.I, Value7.V, Value7.IV).build(),
                    Parameter.parameter("x5").values(Config3.A, Config3.C, Config3.B).build(),
                    Parameter.parameter("x1").values(Config3.C, Config3.A, Config3.B).build(),
                    Parameter.parameter("x3").values(Config3.A, Config3.C, Config3.B).build(),
                    Parameter.parameter("v4").values(Value7.II, Value7.VI, Value7.I, Value7.IV, Value7.V, Value7.VII, Value7.III).build()
            },

            new Parameter[] {
                    Parameter.parameter("x5").values(Config3.C, Config3.A, Config3.B).build(),
                    Parameter.parameter("x4").values(Config3.A, Config3.B, Config3.C).build(),
                    Parameter.parameter("v4").values(Value7.I, Value7.V, Value7.II, Value7.III, Value7.IV, Value7.VI, Value7.VII).build(),
                    Parameter.parameter("v2").values(Value7.III, Value7.IV, Value7.VI, Value7.V, Value7.II, Value7.I, Value7.VII).build(),
                    Parameter.parameter("x2").values(Config3.A, Config3.B, Config3.C).build(),
                    Parameter.parameter("v5").values(Value7.IV, Value7.I, Value7.VI, Value7.V, Value7.II, Value7.III, Value7.VII).build(),
                    Parameter.parameter("x3").values(Config3.B, Config3.C, Config3.A).build(),
                    Parameter.parameter("v3").values(Value7.V, Value7.II, Value7.VI, Value7.I, Value7.VII, Value7.IV, Value7.III).build(),
                    Parameter.parameter("v1").values(Value7.VII, Value7.II, Value7.V, Value7.IV, Value7.VI, Value7.I, Value7.III).build(),
                    Parameter.parameter("x1").values(Config3.A, Config3.C, Config3.B).build()
            },

            new Parameter[] {
                    Parameter.parameter("x4").values(Config3.C, Config3.A, Config3.B).build(),
                    Parameter.parameter("v4").values(Value7.VI, Value7.IV, Value7.III, Value7.VII, Value7.V, Value7.I, Value7.II).build(),
                    Parameter.parameter("v5").values(Value7.I, Value7.V, Value7.VI, Value7.II, Value7.VII, Value7.III, Value7.IV).build(),
                    Parameter.parameter("x3").values(Config3.B, Config3.C, Config3.A).build(),
                    Parameter.parameter("x1").values(Config3.A, Config3.C, Config3.B).build(),
                    Parameter.parameter("x5").values(Config3.B, Config3.C, Config3.A).build(),
                    Parameter.parameter("v2").values(Value7.II, Value7.I, Value7.VII, Value7.IV, Value7.III, Value7.VI, Value7.V).build(),
                    Parameter.parameter("x2").values(Config3.C, Config3.B, Config3.A).build(),
                    Parameter.parameter("v1").values(Value7.V, Value7.IV, Value7.VI, Value7.II, Value7.III, Value7.I, Value7.VII).build(),
                    Parameter.parameter("v3").values(Value7.VI, Value7.VII, Value7.II, Value7.V, Value7.I, Value7.III, Value7.IV).build()
            },

            new Parameter[] {
                    Parameter.parameter("v1").values(Value7.VII, Value7.II, Value7.III, Value7.I, Value7.V, Value7.IV, Value7.VI).build(),
                    Parameter.parameter("v4").values(Value7.I, Value7.IV, Value7.VII, Value7.II, Value7.VI, Value7.V, Value7.III).build(),
                    Parameter.parameter("x4").values(Config3.C, Config3.B, Config3.A).build(),
                    Parameter.parameter("x3").values(Config3.C, Config3.B, Config3.A).build(),
                    Parameter.parameter("v5").values(Value7.VI, Value7.IV, Value7.VII, Value7.I, Value7.III, Value7.II, Value7.V).build(),
                    Parameter.parameter("x5").values(Config3.A, Config3.C, Config3.B).build(),
                    Parameter.parameter("v2").values(Value7.VII, Value7.V, Value7.III, Value7.I, Value7.IV, Value7.II, Value7.VI).build(),
                    Parameter.parameter("x1").values(Config3.A, Config3.B, Config3.C).build(),
                    Parameter.parameter("x2").values(Config3.B, Config3.C, Config3.A).build(),
                    Parameter.parameter("v3").values(Value7.V, Value7.III, Value7.VI, Value7.VII, Value7.I, Value7.II, Value7.IV).build()
            },

            new Parameter[] {
                    Parameter.parameter("v2").values(Value7.VII, Value7.III, Value7.VI, Value7.II, Value7.V, Value7.I, Value7.IV).build(),
                    Parameter.parameter("x1").values(Config3.A, Config3.B, Config3.C).build(),
                    Parameter.parameter("v3").values(Value7.III, Value7.II, Value7.VII, Value7.V, Value7.IV, Value7.VI, Value7.I).build(),
                    Parameter.parameter("x5").values(Config3.C, Config3.A, Config3.B).build(),
                    Parameter.parameter("x2").values(Config3.B, Config3.A, Config3.C).build(),
                    Parameter.parameter("x3").values(Config3.A, Config3.B, Config3.C).build(),
                    Parameter.parameter("v5").values(Value7.I, Value7.IV, Value7.VII, Value7.V, Value7.II, Value7.VI, Value7.III).build(),
                    Parameter.parameter("x4").values(Config3.A, Config3.C, Config3.B).build(),
                    Parameter.parameter("v4").values(Value7.III, Value7.II, Value7.IV, Value7.VI, Value7.V, Value7.I, Value7.VII).build(),
                    Parameter.parameter("v1").values(Value7.II, Value7.VI, Value7.VII, Value7.IV, Value7.III, Value7.I, Value7.V).build()
            },

            new Parameter[] {
                    Parameter.parameter("v1").values(Value7.VI, Value7.IV, Value7.V, Value7.II, Value7.III, Value7.VII, Value7.I).build(),
                    Parameter.parameter("x5").values(Config3.A, Config3.C, Config3.B).build(),
                    Parameter.parameter("v2").values(Value7.V, Value7.VI, Value7.I, Value7.III, Value7.IV, Value7.VII, Value7.II).build(),
                    Parameter.parameter("x1").values(Config3.C, Config3.A, Config3.B).build(),
                    Parameter.parameter("x2").values(Config3.C, Config3.B, Config3.A).build(),
                    Parameter.parameter("x3").values(Config3.B, Config3.A, Config3.C).build(),
                    Parameter.parameter("v4").values(Value7.III, Value7.VII, Value7.IV, Value7.V, Value7.II, Value7.VI, Value7.I).build(),
                    Parameter.parameter("v3").values(Value7.I, Value7.VII, Value7.IV, Value7.II, Value7.VI, Value7.V, Value7.III).build(),
                    Parameter.parameter("v5").values(Value7.III, Value7.IV, Value7.I, Value7.VI, Value7.V, Value7.VII, Value7.II).build(),
                    Parameter.parameter("x4").values(Config3.C, Config3.B, Config3.A).build()
            },

            new Parameter[] {
                    Parameter.parameter("x1").values(Config3.B, Config3.A, Config3.C).build(),
                    Parameter.parameter("v2").values(Value7.VI, Value7.V, Value7.III, Value7.IV, Value7.I, Value7.II, Value7.VII).build(),
                    Parameter.parameter("x3").values(Config3.B, Config3.C, Config3.A).build(),
                    Parameter.parameter("x2").values(Config3.C, Config3.A, Config3.B).build(),
                    Parameter.parameter("v3").values(Value7.IV, Value7.I, Value7.VI, Value7.III, Value7.VII, Value7.II, Value7.V).build(),
                    Parameter.parameter("x5").values(Config3.C, Config3.A, Config3.B).build(),
                    Parameter.parameter("v5").values(Value7.II, Value7.VI, Value7.III, Value7.V, Value7.VII, Value7.IV, Value7.I).build(),
                    Parameter.parameter("v4").values(Value7.VII, Value7.III, Value7.VI, Value7.IV, Value7.II, Value7.V, Value7.I).build(),
                    Parameter.parameter("v1").values(Value7.VI, Value7.VII, Value7.IV, Value7.II, Value7.I, Value7.III, Value7.V).build(),
                    Parameter.parameter("x4").values(Config3.A, Config3.B, Config3.C).build()
            },

            new Parameter[] {
                    Parameter.parameter("x5").values(Config3.C, Config3.A, Config3.B).build(),
                    Parameter.parameter("x2").values(Config3.C, Config3.B, Config3.A).build(),
                    Parameter.parameter("x3").values(Config3.C, Config3.B, Config3.A).build(),
                    Parameter.parameter("v5").values(Value7.II, Value7.V, Value7.IV, Value7.VI, Value7.I, Value7.VII, Value7.III).build(),
                    Parameter.parameter("v3").values(Value7.IV, Value7.VI, Value7.III, Value7.V, Value7.I, Value7.VII, Value7.II).build(),
                    Parameter.parameter("x4").values(Config3.A, Config3.C, Config3.B).build(),
                    Parameter.parameter("v4").values(Value7.II, Value7.IV, Value7.VII, Value7.VI, Value7.I, Value7.V, Value7.III).build(),
                    Parameter.parameter("v1").values(Value7.IV, Value7.III, Value7.VI, Value7.I, Value7.II, Value7.V, Value7.VII).build(),
                    Parameter.parameter("v2").values(Value7.III, Value7.V, Value7.I, Value7.VII, Value7.IV, Value7.II, Value7.VI).build(),
                    Parameter.parameter("x1").values(Config3.C, Config3.B, Config3.A).build()
            },

            new Parameter[] {
                    Parameter.parameter("v5").values(Value7.VI, Value7.II, Value7.V, Value7.I, Value7.III, Value7.VII, Value7.IV).build(),
                    Parameter.parameter("v4").values(Value7.V, Value7.VI, Value7.IV, Value7.VII, Value7.I, Value7.II, Value7.III).build(),
                    Parameter.parameter("v1").values(Value7.II, Value7.VII, Value7.V, Value7.I, Value7.IV, Value7.III, Value7.VI).build(),
                    Parameter.parameter("x2").values(Config3.C, Config3.B, Config3.A).build(),
                    Parameter.parameter("x5").values(Config3.C, Config3.A, Config3.B).build(),
                    Parameter.parameter("x4").values(Config3.A, Config3.C, Config3.B).build(),
                    Parameter.parameter("x1").values(Config3.A, Config3.C, Config3.B).build(),
                    Parameter.parameter("v2").values(Value7.IV, Value7.VI, Value7.III, Value7.V, Value7.VII, Value7.II, Value7.I).build(),
                    Parameter.parameter("x3").values(Config3.B, Config3.C, Config3.A).build(),
                    Parameter.parameter("v3").values(Value7.I, Value7.VII, Value7.VI, Value7.V, Value7.IV, Value7.III, Value7.II).build()
            }
    };
}
