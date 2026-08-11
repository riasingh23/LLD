### Requirement
- Schedule a job
- Execute jobs based on priority(LOW,MEDIUM,HIGH) and based on request(adhoc/periodic)


### Classes
- JobScheduler
- Job (**abstract** vs interface)
- Schedule
- JobRunStrategy (**abstract** vs interface)
- Priority (Enum)

### Class Relation
- JobScheduler -> Job
- JobScheduler -> JobRunStrategy
- Job -> Schedule
- Job -> Priority

### Class Diagram 
JobScheduler
- List<Job> jobs
- JobRunStrategy
+ JobScheduler()
+ addJob(Job job) -> Job
+ removeJob(Job job) -> Job
+ runScheduleJobs() {JobRunStrategy.run(jobs)}
+ runJob(List<Job> job) {JobRunStrategy.run(jobs)}

Job
- List<Schedule>
- Priority
+ execute()
+ addSchedule()
+ removeSchedule()

Priority
- HIGH
- MEDIUM
- LOW

Schedule
- String schedule
+ getSchedule()

JobRunStrategy
+ run(List<Job>)
