package system.services.impls;

import system.daos.IEmployeeDAO;
import system.daos.impls.EmployeeDAO;
import system.entities.Employee;
import system.services.IEmployeeService;

import java.util.List;

public class EmployeeService implements IEmployeeService {

    IEmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findOne(long id) {
        return employeeDAO.findOne(id);
    }

    @Override
    public Long insert(Employee employee) {
        return employeeDAO.insert(employee);
    }

    @Override
    public boolean update(Employee employee) {
        return employeeDAO.update(employee);
    }

    @Override
    public boolean delete(Employee employee) {
        return employeeDAO.delete(employee);
    }

    @Override
    public List<Employee> findAllByKey(String key, String filter) {
        return employeeDAO.findAllByKey(key, filter);
    }
}
