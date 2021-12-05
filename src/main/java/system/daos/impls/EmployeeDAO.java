package system.daos.impls;

import system.daos.IEmployeeDAO;
import system.entities.Employee;

import java.util.List;

public class EmployeeDAO extends BasicDAO<Employee> implements IEmployeeDAO {

    private static final String className = "Employee";

    @Override
    public List<Employee> findAll() {
        return super.findAll(className);
    }

    @Override
    public Employee findOne(Long id) {
        return super.findOne(className, id);
    }

    @Override
    public List<Employee> findAllByKey(String key, String filter) {
        String hqlQuery = "select e from Employee e where ";
        switch (filter) {
            case "id" -> hqlQuery += "e.id like '%" + key + "%'";

            case "name" -> hqlQuery += "e.name like '%" + key + "%'";

            case "email" -> hqlQuery += " e.email like '%" + key + "%'";

            case "address" -> hqlQuery += " e.ward.name like '%" + key + "%'" +
                    " or e.ward.district.name like '%" + key + "%'" +
                    " or e.ward.district.province.name like '%" + key + "%'" +
                    " or e.ward.district.province.country.name like '%" + key + "%'" +
                    " or e.ward.district.province.country.region.name like '%" + key + "%'";

            case "job" -> hqlQuery += "e.job.title like '%" + key + "%'";

            case "department" -> hqlQuery += "e.department.name like '%" + key + "%'";

            default -> hqlQuery += "e.id like '%" + key + "%'" +
                    " or e.name like '%" + key + "%'" +
                    " or e.email like '%" + key + "%'" +
                    " or e.ward.name like '%" + key + "%'" +
                    " or e.ward.district.name like '%" + key + "%'" +
                    " or e.ward.district.province.name like '%" + key + "%'" +
                    " or e.ward.district.province.country.name like '%" + key + "%'" +
                    " or e.ward.district.province.country.region.name like '%" + key + "%'" +
                    " or e.job.title like '%" + key + "%'" +
                    " or e.department.name like '%" + key + "%'";
        }
        return super.query(hqlQuery);
    }
}
