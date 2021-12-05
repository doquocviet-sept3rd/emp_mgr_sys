package system.daos.impls;

import system.daos.IJobHistoryDAO;
import system.entities.JobHistory;

import java.util.List;

public class JobHistoryDAO extends BasicDAO<JobHistory> implements IJobHistoryDAO {

    private static final String className = "JobHistory";

    @Override
    public List<JobHistory> findAll() {
        return super.findAll(className);
    }

    @Override
    public JobHistory findOne(Long id) {
        return super.findOne(className, id);
    }

    @Override
    public List<JobHistory> findAllByKey(String key, String filter) {
        String hqlQuery = "select jh from JobHistory jh where ";
        switch (filter) {
            case "id" -> hqlQuery += "jh.id like '%" + key + "%'";

            case "employee" -> hqlQuery += " jh.employee.name like '%" + key + "%'";

            case "job" -> hqlQuery += " jh.job.title like '%" + key + "%'";

            case "department" -> hqlQuery += " jh.department.name like '%" + key + "%'";

            default -> hqlQuery += "jh.id like '%" + key + "%'" +
                    " jh.maxSalary like '%" + key + "%'" +
                    " jh.minSalary like '%" + key + "%'" +
                    " jh.title like '%" + key + "%'";
        }
        return super.query(hqlQuery);
    }

    @Override
    public JobHistory findOnePrevious(long employeeId) {
        String hqlQuery = "select jh from JobHistory jh where jh.employeeId = " + employeeId + " and jh.endDate is null";
        return super.query(hqlQuery).get(0);
    }
}
