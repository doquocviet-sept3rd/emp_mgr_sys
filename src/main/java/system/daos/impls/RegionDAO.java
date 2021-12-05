package system.daos.impls;

import system.daos.IRegionDAO;
import system.entities.Region;

import java.util.List;

public class RegionDAO extends BasicDAO<Region> implements IRegionDAO {

    private static final String className = "Region";

    @Override
    public List<Region> findAll() {
        return super.findAll(className);
    }

    @Override
    public Region findOne(Long id) {
        return super.findOne(className, id);
    }
}
