package system.services;

import system.entities.Department;

import java.util.List;

public interface IDepartmentService {

    List<Department> findAll();

    Department findOne(long id);

    boolean delete(Department department);

    Long insert(Department department);

    boolean update(Department department);

    List<Department> findAllByKey(String key, String filter);

}
