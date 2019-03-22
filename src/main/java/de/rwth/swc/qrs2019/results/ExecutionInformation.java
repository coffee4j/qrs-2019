package de.rwth.swc.qrs2019.results;

import de.rwth.swc.qrs2019.execution.ProgramPath;
import de.rwth.swc.qrs2019.modelling.Fault;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ExecutionInformation {

    private ProgramPath programPath;
    private boolean skipped;
    private final List<Fault> activatedFaults;

    public ExecutionInformation() {
        this.programPath = ProgramPath.SKIPPED;
        this.activatedFaults = new ArrayList<>();
    }

    public boolean isSkipped() {
        return skipped;
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }

    public void setProgramPath(ProgramPath programPath) {
        this.programPath = programPath;
    }

    public ProgramPath getProgramPath() {
        return programPath;
    }

    public List<Fault> getActivatedFaults() {
        return activatedFaults;
    }

    public void reachFault(Fault fault, Object ... values) {
        if(fault.isCoveredBy(values)) {
            activatedFaults.add(fault);
        }
    }

    public TestExecutionType deriveTestResult() {
        if(isSkipped()) {
            return TestExecutionType.SKIPPED;
        } else if(getActivatedFaults().size() > 0) {
            return TestExecutionType.FAILED;
        } else {
            return TestExecutionType.PASSED;
        }
    }

    public Stream<Fault> streamCoveredFaults(List<Fault> faults, Object ... values) {
        return faults.stream().filter(fault -> fault.isCoveredBy(values));
    }
}
