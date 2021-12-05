package system.daos;

import system.entities.Department;

import java.util.List;

public interface IDepartmentDAO extends IBasicDAO<Department> {

    List<Department> findAll();

    Department findOne(Long id);

    List<Department> findAllByKey(String key, String filter);
}
