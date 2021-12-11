package system.services.impls;

import system.entities.JobHistory;
import system.services.IJobHistoryService;
import system.utils.repositories.RepositoriesDAO;

import java.util.List;

public class JobHistoryService implements IJobHistoryService {

    @Override
    public List<JobHistory> findAll() {
        return RepositoriesDAO.getJobHistoryDAO().findAll();
    }

    @Override
    public JobHistory findOne(long id) {
        return RepositoriesDAO.getJobHistoryDAO().findOne(id);
    }

    @Override
    public List<JobHistory> findAllByKey(String key, String filer) {
        return RepositoriesDAO.getJobHistoryDAO().findAllByKey(key, filer);
    }

    @Override
    public Long insert(JobHistory jobHistory) {
        return RepositoriesDAO.getJobHistoryDAO().insert(jobHistory);
    }

    @Override
    public boolean update(JobHistory jobHistory) {
        return RepositoriesDAO.getJobHistoryDAO().update(jobHistory);
    }

    @Override
    public JobHistory findOnePrevious(long employeeId) {
        return RepositoriesDAO.getJobHistoryDAO().findOnePrevious(employeeId);
    }
}
