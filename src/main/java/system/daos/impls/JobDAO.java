package system.daos.impls;

import system.daos.IJobDAO;
import system.entities.Job;

import java.util.List;

public class JobDAO extends BasicDAO<Job> implements IJobDAO {

    private static final String className = "Job";

    @Override
    public List<Job> findAll() {
        return super.findAll(className);
    }

    @Override
    public Job findOne(Long id) {
        return super.findOne(className, id);
    }

    @Override
    public List<Job> findAllByKey(String key, String filter) {
        String hqlQuery = "select j from Job j where ";
        switch (filter) {
            case "id" -> hqlQuery += "j.id like '%" + key + "%'";

            case "max salary" -> hqlQuery += " j.maxSalary like '%" + key + "%'";

            case "min salary" -> hqlQuery += " j.minSalary like '%" + key + "%'";

            case "title" -> hqlQuery += " j.title like '%" + key + "%'";

            default -> hqlQuery += "j.id like '%" + key + "%'" +
                    " j.maxSalary like '%" + key + "%'" +
                    " j.minSalary like '%" + key + "%'" +
                    " j.title like '%" + key + "%'";
        }
        return super.query(hqlQuery);
    }
}
