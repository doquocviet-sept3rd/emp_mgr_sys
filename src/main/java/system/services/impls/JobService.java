package system.services.impls;

import system.daos.IJobDAO;
import system.daos.impls.JobDAO;
import system.entities.Job;
import system.services.IJobService;

import java.util.List;

public class JobService implements IJobService {

    private final IJobDAO jobDAO = new JobDAO();

    @Override
    public List<Job> findAll() {
        return jobDAO.findAll();
    }

    @Override
    public Job findOne(long id) {
        return jobDAO.findOne(id);
    }

    @Override
    public Long insert(Job job) {
        return jobDAO.insert(job);
    }

    @Override
    public boolean update(Job job) {
        return jobDAO.update(job);
    }

    @Override
    public boolean delete(Job job) {
        return jobDAO.delete(job);
    }

    @Override
    public List<Job> findAllByKey(String key, String filter) {
        return jobDAO.findAllByKey(key, filter);
    }
}
