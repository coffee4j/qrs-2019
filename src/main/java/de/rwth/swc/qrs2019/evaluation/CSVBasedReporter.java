package de.rwth.swc.qrs2019.evaluation;

import de.rwth.swc.qrs2019.results.TestEffectivenessResult;
import de.rwth.swc.qrs2019.results.TestPerformanceResult;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CSVBasedReporter {

    public static void printAllTestResults(String header, List<TestEffectivenessResult> testResults) {
        final DecimalFormat df = new DecimalFormat("0.00");

        sortEffectivenessResult(testResults);

        System.out.println(header);
        System.out.println("Scenario" + "; " + "Strength" + "; "
                + "Pos-Size" + "; " + "Neg-Size" + "; "
                + "Pos-Eff." + "; " + "Neg-Eff." + "; " + "Eff." + "; "
                + "pos-faults" + "; " + "neg-faults");

        for(TestEffectivenessResult testResult : testResults) {
            System.out.print(testResult.getExperiment() + "; ");
            System.out.print(testResult.getStrength() + "; ");
            System.out.print(testResult.getPositiveTestSuiteSize() + "; ");
            System.out.print(testResult.getNegativeTestSuiteSize() + "; ");
            System.out.print(df.format(testResult.getPositiveFaultDetectionEffectiveness()) + "; ");
            System.out.print(df.format(testResult.getNegativeFaultDetectionEffectiveness()) + "; ");
            System.out.print(df.format(testResult.getFaultDetectionEffectiveness()) + "; ");
            System.out.print( testResult.getActivatedPositiveFaults()
                    .stream()
                    .map(fault -> fault.getName())
                    .collect(Collectors.joining(", "))
                    + "; "
            );
            System.out.print( testResult.getActivatedNegativeFaults()
                    .stream()
                    .map(fault -> fault.getName())
                    .collect(Collectors.joining(", "))
            );

            System.out.println();
        }

        System.out.println();
    }

    private static void sortEffectivenessResult(List<TestEffectivenessResult> testResults) {
        testResults.sort(
                Comparator
                        .comparing((TestEffectivenessResult testResult) -> testResult.getExperiment() )
                        .thenComparing((TestEffectivenessResult testResult) -> testResult.getStrength())
        );
    }

    private static void sortPerformanceResult(List<TestPerformanceResult> testResults) {
        testResults.sort(
                Comparator
                        .comparing((TestPerformanceResult testResult) -> testResult.getExperiment() )
                        .thenComparing((TestPerformanceResult testResult) -> testResult.getStrength())
        );
    }

    public static void printAnalysisOfTestResults(String header, List<TestEffectivenessResult> testResults) {
        final DecimalFormat df = new DecimalFormat("0.00");

        sortEffectivenessResult(testResults);

        final String scenario = testResults.get(0).getExperiment();

        final Map<String, Double> averagesOfPositiveTestSuiteSize = testResults.stream().collect(
                Collectors.groupingBy(
                        TestEffectivenessResult::getStrength,
                        Collectors.averagingInt(TestEffectivenessResult::getPositiveTestSuiteSize)
                )
        );

        final Map<String, Double> averagesOfNegativeTestSuiteSize = testResults.stream().collect(
                Collectors.groupingBy(
                        TestEffectivenessResult::getStrength,
                        Collectors.averagingInt(TestEffectivenessResult::getNegativeTestSuiteSize)
                )
        );

        final Map<String, Double> averagesOfPositiveFaultDetectionEffectiveness = testResults.stream().collect(
                Collectors.groupingBy(
                        TestEffectivenessResult::getStrength,
                        Collectors.averagingDouble(TestEffectivenessResult::getPositiveFaultDetectionEffectiveness)
                )
        );

        final Map<String, Double> averagesOfNegativeFaultDetectionEffectiveness = testResults.stream().collect(
                Collectors.groupingBy(
                        TestEffectivenessResult::getStrength,
                        Collectors.averagingDouble(TestEffectivenessResult::getNegativeFaultDetectionEffectiveness)
                )
        );

        final Map<String, Double> averagesOfFaultDetectionEffectiveness = testResults.stream().collect(
                Collectors.groupingBy(
                        TestEffectivenessResult::getStrength,
                        Collectors.averagingDouble(TestEffectivenessResult::getFaultDetectionEffectiveness)
                )
        );

        System.out.println(header);
        System.out.println("Scenario" + "; " + "Strength" + "; " + "Pos-Size" + "; " + "Neg-Size" + "; " + "Pos-Eff." + "; " + "Neg-Eff." + "; " + "Eff.");

        for(String strength : averagesOfPositiveTestSuiteSize.keySet()) {
            final double averagePositiveTestSuiteSize = averagesOfPositiveTestSuiteSize.get(strength);
            final double averageNegativeTestSuiteSize = averagesOfNegativeTestSuiteSize.get(strength);
            final double averagePositiveFaultDetectionEffectiveness = averagesOfPositiveFaultDetectionEffectiveness.get(strength);
            final double averageNegativeFaultDetectionEffectiveness = averagesOfNegativeFaultDetectionEffectiveness.get(strength);
            final double averageFaultDetectionEffectiveness = averagesOfFaultDetectionEffectiveness.get(strength);

            System.out.print(scenario + "; ");
            System.out.print(strength + "; ");
            System.out.print(df.format( averagePositiveTestSuiteSize) + "; ");
            System.out.print(df.format( averageNegativeTestSuiteSize ) + "; ");
            System.out.print(df.format( averagePositiveFaultDetectionEffectiveness) + "; ");
            System.out.print(df.format( averageNegativeFaultDetectionEffectiveness ) + "; ");
            System.out.print(df.format( averageFaultDetectionEffectiveness ) );
            System.out.println();
        }

        System.out.println();
    }

    public static void printAnalysisOfPerformanceTestResults(String header, List<TestPerformanceResult> testResults) {
        final DecimalFormat df = new DecimalFormat("0.00");

        sortPerformanceResult(testResults);

        final String scenario = testResults.get(0).getExperiment();

        final Map<String, Double> averageTimes = testResults.stream().collect(
                Collectors.groupingBy(
                        TestPerformanceResult::getStrength,
                        Collectors.averagingDouble(TestPerformanceResult::getAverageTime)
                )
        );

        System.out.println(header);
        System.out.println("Scenario" + "; " + "Time");

        for(String strength : averageTimes.keySet()) {
            final double averageTime = averageTimes.get(strength);

            System.out.print(scenario + "; ");
            System.out.print(strength + "; ");
            System.out.print(df.format( averageTime ) + "; ");
            System.out.println();
        }

        System.out.println();
    }
}
