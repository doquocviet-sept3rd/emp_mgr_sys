package system.services;

import system.entities.Job;

import java.util.List;

public interface IJobService {

    List<Job> findAll();

    Job findOne(long id);

    Long insert(Job job);

    boolean update(Job job);

    boolean delete(Job job);

    List<Job> findAllByKey(String key, String filter);
}
