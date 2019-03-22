package de.rwth.swc.qrs2019.execution;

import de.rwth.swc.coffee4j.engine.report.Report;
import de.rwth.swc.coffee4j.engine.report.ReportLevel;
import de.rwth.swc.coffee4j.engine.report.Reporter;

import java.util.function.Supplier;

/**
 * Class which can be used to simulate a reporter in testing. Just prints  * all reported information to the standard output with the log level.
 */
public class StandardOutputReporter implements Reporter {
    @Override
    public void report(ReportLevel level, Report report) {
        System.out.println("Report for level " + level.toString() + ": " + report);
    }

    @Override
    public void report(ReportLevel level, Supplier<Report> reportSupplier) {
        System.out.println("Report for level " + level.toString() + ": " + reportSupplier.get());
    }
}

