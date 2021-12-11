package system.services.impls;

import system.entities.Job;
import system.services.IJobService;
import system.utils.repositories.RepositoriesDAO;

import java.util.List;

public class JobService implements IJobService {

    @Override
    public List<Job> findAll() {
        return RepositoriesDAO.getJobDAO().findAll();
    }

    @Override
    public Job findOne(long id) {
        return RepositoriesDAO.getJobDAO().findOne(id);
    }

    @Override
    public Long insert(Job job) {
        return RepositoriesDAO.getJobDAO().insert(job);
    }

    @Override
    public boolean update(Job job) {
        return RepositoriesDAO.getJobDAO().update(job);
    }

    @Override
    public boolean delete(Job job) {
        return RepositoriesDAO.getJobDAO().delete(job);
    }

    @Override
    public List<Job> findAllByKey(String key, String filter) {
        return RepositoriesDAO.getJobDAO().findAllByKey(key, filter);
    }
}
