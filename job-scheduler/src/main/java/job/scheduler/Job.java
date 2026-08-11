package job.scheduler;

import java.util.List;

public abstract class Job {
    List<Schedule> schedules;
    Priority priority;

    public void execute() {
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }

    public void removeSchedule(Schedule schedule) {
        schedules.remove(schedule);
    }

}
