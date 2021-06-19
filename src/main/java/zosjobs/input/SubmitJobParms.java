package zosjobs.input;

import java.util.Optional;

public class SubmitJobParms {

    private Optional<String> jobDataSet;
    private Optional<String> jclSymbols = Optional.empty();

    public SubmitJobParms(String jobDataSet) {
        this.jobDataSet = Optional.ofNullable(jobDataSet);
    }

    public SubmitJobParms(String jobDataSet, String jclSymbols) {
        this.jobDataSet = Optional.ofNullable(jobDataSet);
        this.jclSymbols = Optional.ofNullable(jclSymbols);
    }

    public Optional<String> getJobDataSet() {
        return jobDataSet;
    }

    public void setJobDataSet(String jobDataSet) {
        this.jobDataSet = Optional.ofNullable(jobDataSet);
    }

    public Optional<String> getJclSymbols() {
        return jclSymbols;
    }

    public void setJclSymbols(String jclSymbols) {
        this.jclSymbols = Optional.ofNullable(jclSymbols);
    }

}
