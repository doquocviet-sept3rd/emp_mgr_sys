package system.daos.impls;

import system.daos.IDepartmentDAO;
import system.entities.Department;

import java.util.List;

public class DepartmentDAO extends BasicDAO<Department> implements IDepartmentDAO {

    private static final String className = "Department";

    @Override
    public List<Department> findAll() {
        return super.findAll(className);
    }

    @Override
    public Department findOne(Long id) {
        return super.findOne(className, id);
    }

    @Override
    public List<Department> findAllByKey(String key, String filter) {
        String hqlQuery = "select d from Department d where ";
        switch (filter) {
            case "id" -> hqlQuery += "d.id like '%" + key + "%'";

            case "name" -> hqlQuery += "d.name like '%" + key + "%'";

            case "manager" -> hqlQuery += " d.manager.name like '%" + key + "%'";

            case "address" -> hqlQuery += " d.ward.name like '%" + key + "%'" +
                    " or d.ward.district.name like '%" + key + "%'" +
                    " or d.ward.district.province.name like '%" + key + "%'" +
                    " or d.ward.district.province.country.name like '%" + key + "%'" +
                    " or d.ward.district.province.country.region.name like '%" + key + "%'";

            default -> hqlQuery += "d.id like '%" + key + "%'" +
                    " or d.name like '%" + key + "%'" +
                    " or d.manager.name like '%" + key + "%'" +
                    " or d.ward.name like '%" + key + "%'" +
                    " or d.ward.district.name like '%" + key + "%'" +
                    " or d.ward.district.province.name like '%" + key + "%'" +
                    " or d.ward.district.province.country.name like '%" + key + "%'" +
                    " or d.ward.district.province.country.region.name like '%" + key + "%'";
        }
        return super.query(hqlQuery);
    }
}
