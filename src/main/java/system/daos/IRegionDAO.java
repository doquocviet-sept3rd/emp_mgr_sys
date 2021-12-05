package system.daos;

import system.entities.Region;

import java.util.List;

public interface IRegionDAO extends IBasicDAO<Region> {

    List<Region> findAll();

    Region findOne(Long id);

}
