package system.daos;

import system.entities.Employee;

import java.util.List;

public interface IEmployeeDAO extends IBasicDAO<Employee> {

    List<Employee> findAll();

    Employee findOne(Long id);

    List<Employee> findAllByKey(String key, String filter);

}
