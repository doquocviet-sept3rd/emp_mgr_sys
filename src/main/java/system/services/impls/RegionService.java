package system.services.impls;

import system.entities.Region;
import system.services.IRegionService;
import system.utils.repositories.RepositoriesDAO;

import java.util.List;

public class RegionService implements IRegionService {

    @Override
    public List<Region> findAll() {
        return RepositoriesDAO.getRegionDAO().findAll();
    }
}
