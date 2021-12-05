package system.daos;

import java.util.List;

public interface IBasicDAO<Entity> {

    List<Entity> findAll(String className);

    Long insert(Entity entity);

    boolean update(Entity entity);

    boolean delete(Entity entity);

    Entity findOne(String className, Long id);

    List<Entity> query(String query);

    void ExecuteStatement(String query);

}
