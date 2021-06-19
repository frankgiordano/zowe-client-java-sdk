package zosjobs.input;

import java.util.Optional;

public class CommonJobParms {

    private Optional<String> jobId;
    private Optional<String> jobName;

    public CommonJobParms(String jobId, String jobName) {
        this.jobId = Optional.ofNullable(jobId);
        this.jobName = Optional.ofNullable(jobName);
    }

    public Optional<String> getJobId() {
        return jobId;
    }

    public Optional<String> getJobName() {
        return jobName;
    }

}
