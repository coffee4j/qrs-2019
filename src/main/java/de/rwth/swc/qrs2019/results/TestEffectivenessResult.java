package de.rwth.swc.qrs2019.results;

import de.rwth.swc.qrs2019.modelling.Fault;

import java.util.List;
import java.util.Objects;

public class TestEffectivenessResult {

    private final String experiment;
    private final String strength;
    private final int positiveTestSuiteSize;
    private final int negativeTestSuiteSize;
    private final int numberOfPositiveFaults;
    private final int numberOfNegativeFaults;
    private final List<Fault> activatedPositiveFaults;
    private final List<Fault> activatedNegativeFaults;

    public TestEffectivenessResult(String experiment, String strength,
                                   int positiveTestSuiteSize,
                                   int negativeTestSuiteSize,
                                   int numberOfPositiveFaults,
                                   int numberOfNegativeFaults,
                                   List<Fault> activatedPositiveFaults,
                                   List<Fault> activatedNegativeFaults) {
        this.experiment = experiment;
        this.strength = strength;
        this.positiveTestSuiteSize = positiveTestSuiteSize;
        this.negativeTestSuiteSize = negativeTestSuiteSize;
        this.numberOfPositiveFaults = numberOfPositiveFaults;
        this.numberOfNegativeFaults = numberOfNegativeFaults;
        this.activatedPositiveFaults = activatedPositiveFaults;
        this.activatedNegativeFaults = activatedNegativeFaults;
    }

    public String getExperiment() {
        return experiment;
    }

    public String getStrength() {
        return strength;
    }

    public int getPositiveTestSuiteSize() {
        return positiveTestSuiteSize;
    }

    public int getNegativeTestSuiteSize() {
        return negativeTestSuiteSize;
    }

    public List<Fault> getActivatedPositiveFaults() {
        return activatedPositiveFaults;
    }

    public List<Fault> getActivatedNegativeFaults() {
        return activatedNegativeFaults;
    }

    public double getPositiveFaultDetectionEffectiveness() {
        return ((double) activatedPositiveFaults.size()) / numberOfPositiveFaults;
    }

    public double getNegativeFaultDetectionEffectiveness() {
        return ((double) activatedNegativeFaults.size()) / numberOfNegativeFaults;
    }

    public double getFaultDetectionEffectiveness() {
        return ((double) (activatedPositiveFaults.size() + activatedNegativeFaults.size())) / (numberOfPositiveFaults + numberOfNegativeFaults);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEffectivenessResult that = (TestEffectivenessResult) o;
        return positiveTestSuiteSize == that.positiveTestSuiteSize &&
                negativeTestSuiteSize == that.negativeTestSuiteSize &&
                numberOfPositiveFaults == that.numberOfPositiveFaults &&
                numberOfNegativeFaults == that.numberOfNegativeFaults &&
                experiment.equals(that.experiment) &&
                strength.equals(that.strength) &&
                activatedPositiveFaults.equals(that.activatedPositiveFaults) &&
                activatedNegativeFaults.equals(that.activatedNegativeFaults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experiment, strength, positiveTestSuiteSize, negativeTestSuiteSize, numberOfPositiveFaults, numberOfNegativeFaults, activatedPositiveFaults, activatedNegativeFaults);
    }
}
