package system.services;

import system.entities.JobHistory;

import java.util.List;

public interface IJobHistoryService {

    List<JobHistory> findAll();

    JobHistory findOne(long id);

    List<JobHistory> findAllByKey(String key, String filer);

    Long insert(JobHistory jobHistory);

    boolean update(JobHistory jobHistory);

    JobHistory findOnePrevious(long employeeId);
}
