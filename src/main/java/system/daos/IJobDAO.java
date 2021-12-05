package system.daos;

import system.entities.Job;

import java.util.List;

public interface IJobDAO extends IBasicDAO<Job> {

    List<Job> findAll();

    Job findOne(Long id);

    List<Job> findAllByKey(String key, String filter);
}
