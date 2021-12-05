package system.services.impls;

import system.daos.IJobHistoryDAO;
import system.daos.impls.JobHistoryDAO;
import system.entities.JobHistory;
import system.services.IJobHistoryService;

import java.util.List;

public class JobHistoryService implements IJobHistoryService {

    private final IJobHistoryDAO jobHistoryDAO = new JobHistoryDAO();

    @Override
    public List<JobHistory> findAll() {
        return jobHistoryDAO.findAll();
    }

    @Override
    public JobHistory findOne(long id) {
        return jobHistoryDAO.findOne(id);
    }

    @Override
    public List<JobHistory> findAllByKey(String key, String filer) {
        return jobHistoryDAO.findAllByKey(key, filer);
    }

    @Override
    public Long insert(JobHistory jobHistory) {
        return jobHistoryDAO.insert(jobHistory);
    }

    @Override
    public boolean update(JobHistory jobHistory) {
        return jobHistoryDAO.update(jobHistory);
    }

    @Override
    public JobHistory findOnePrevious(long employeeId) {
        return jobHistoryDAO.findOnePrevious(employeeId);
    }
}
