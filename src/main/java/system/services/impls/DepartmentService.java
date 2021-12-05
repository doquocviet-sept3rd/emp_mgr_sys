package system.services.impls;

import system.daos.IDepartmentDAO;
import system.daos.impls.DepartmentDAO;
import system.entities.Department;
import system.services.IDepartmentService;

import java.util.List;

public class DepartmentService implements IDepartmentService {

    private final IDepartmentDAO departmentDAO = new DepartmentDAO();

    @Override
    public List<Department> findAll() {
        return departmentDAO.findAll();
    }

    @Override
    public Department findOne(long id) {
        return departmentDAO.findOne(id);
    }

    @Override
    public boolean delete(Department department) {
        return departmentDAO.delete(department);
    }

    @Override
    public Long insert(Department department) {
        return departmentDAO.insert(department);
    }

    @Override
    public boolean update(Department department) {
        return departmentDAO.update(department);
    }

    @Override
    public List<Department> findAllByKey(String key, String filter) {
        return departmentDAO.findAllByKey(key, filter);
    }
}
