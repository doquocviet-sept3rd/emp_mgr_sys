package system.daos;

import system.entities.JobHistory;

import java.util.List;

public interface IJobHistoryDAO extends IBasicDAO<JobHistory> {

    List<JobHistory> findAll();

    JobHistory findOne(Long id);

    List<JobHistory> findAllByKey(String key, String filter);

    JobHistory findOnePrevious(long employeeId);
}
