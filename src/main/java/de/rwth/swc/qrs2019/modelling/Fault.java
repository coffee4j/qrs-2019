package de.rwth.swc.qrs2019.modelling;

import java.util.Objects;
import java.util.function.Predicate;

public class Fault {
    private final String name;
    private final int dimension;
    private final Predicate<Object[]> condition;

    public Fault(String name, int dimension, Predicate<Object[]> condition) {
        this.name = name;
        this.dimension = dimension;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public int getDimension() {
        return dimension;
    }

    public boolean isCoveredBy(Object[] input) {
        return condition.test(input);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fault fault = (Fault) o;
        return Objects.equals(name, fault.name) &&
                Objects.equals(dimension, fault.dimension) &&
                Objects.equals(condition, fault.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dimension, condition);
    }
}
