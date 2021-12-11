package system.services.impls;

import system.entities.Department;
import system.services.IDepartmentService;
import system.utils.repositories.RepositoriesDAO;

import java.util.List;

public class DepartmentService implements IDepartmentService {

    @Override
    public List<Department> findAll() {
        return RepositoriesDAO.getDepartmentDAO().findAll();
    }

    @Override
    public Department findOne(long id) {
        return RepositoriesDAO.getDepartmentDAO().findOne(id);
    }

    @Override
    public boolean delete(Department department) {
        return RepositoriesDAO.getDepartmentDAO().delete(department);
    }

    @Override
    public Long insert(Department department) {
        return RepositoriesDAO.getDepartmentDAO().insert(department);
    }

    @Override
    public boolean update(Department department) {
        return RepositoriesDAO.getDepartmentDAO().update(department);
    }

    @Override
    public List<Department> findAllByKey(String key, String filter) {
        return RepositoriesDAO.getDepartmentDAO().findAllByKey(key, filter);
    }
}
