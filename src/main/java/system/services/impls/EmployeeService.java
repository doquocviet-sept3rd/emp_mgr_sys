package system.services.impls;

import system.entities.Employee;
import system.services.IEmployeeService;
import system.utils.repositories.RepositoriesDAO;

import java.util.List;

public class EmployeeService implements IEmployeeService {

    @Override
    public List<Employee> findAll() {
        return RepositoriesDAO.getEmployeeDAO().findAll();
    }

    @Override
    public Employee findOne(long id) {
        return RepositoriesDAO.getEmployeeDAO().findOne(id);
    }

    @Override
    public Long insert(Employee employee) {
        return RepositoriesDAO.getEmployeeDAO().insert(employee);
    }

    @Override
    public boolean update(Employee employee) {
        return RepositoriesDAO.getEmployeeDAO().update(employee);
    }

    @Override
    public boolean delete(Employee employee) {
        return RepositoriesDAO.getEmployeeDAO().delete(employee);
    }

    @Override
    public List<Employee> findAllByKey(String key, String filter) {
        return RepositoriesDAO.getEmployeeDAO().findAllByKey(key, filter);
    }
}
