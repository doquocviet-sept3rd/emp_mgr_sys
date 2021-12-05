package system.services.impls;

import system.daos.IRegionDAO;
import system.daos.impls.RegionDAO;
import system.entities.Region;
import system.services.IRegionService;

import java.util.List;

public class RegionService implements IRegionService {

    private final IRegionDAO regionDAO = new RegionDAO();

    @Override
    public List<Region> findAll() {
        return regionDAO.findAll();
    }
}
