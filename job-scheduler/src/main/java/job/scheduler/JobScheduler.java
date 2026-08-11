package job.scheduler;

import java.util.List;

public class JobScheduler {
    private List<Job> jobs;
    private JobRunStrategy jobRunStrategy;

    public JobScheduler(List<Job> jobs, JobRunStrategy jobRunStrategy) {
        this.jobs = jobs;
        this.jobRunStrategy = jobRunStrategy;
    }

    public Job addJob(Job job) {
        jobs.add(job);
        return job;
    }

    public Job removeJob(Job job) {
        jobs.remove(job);
        return job;
    }

    public void runScheduleJobs() {
        jobRunStrategy.run(jobs);
    }
    public void runJobs(List<Job> jobs) {
        jobRunStrategy.run(jobs);
    }
}
