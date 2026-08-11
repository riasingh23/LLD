package job.scheduler;

import java.util.List;

public interface JobRunStrategy {
    void run(List<Job> jobs);
}
