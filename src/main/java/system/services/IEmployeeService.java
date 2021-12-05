package system.services;

import system.entities.Employee;

import java.util.List;

public interface IEmployeeService {

    List<Employee> findAll();

    Employee findOne(long id);

    Long insert(Employee employee);

    boolean update(Employee employee);

    boolean delete(Employee employee);

    List<Employee> findAllByKey(String key, String filter);
}
