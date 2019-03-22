package de.rwth.swc.qrs2019.results;

import java.util.Arrays;
import java.util.Objects;

public class TestPerformanceResult {
    private final String experiment;
    private final String strength;
    private final long[] measuredTimes;

    public TestPerformanceResult(String experiment, String strength, long[] measuredTimes) {
        this.experiment = experiment;
        this.strength = strength;
        this.measuredTimes = measuredTimes;
    }

    public String getExperiment() {
        return experiment;
    }

    public String getStrength() {
        return strength;
    }

    public long[] getMeasuredTimes() {
        return measuredTimes;
    }

    public double getAverageTime() {
        return Arrays.stream(measuredTimes).mapToDouble(time -> time).average().orElseThrow();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPerformanceResult that = (TestPerformanceResult) o;
        return Objects.equals(experiment, that.experiment) &&
                Objects.equals(strength, that.strength) &&
                Arrays.equals(measuredTimes, that.measuredTimes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experiment, strength, measuredTimes);
    }
}
