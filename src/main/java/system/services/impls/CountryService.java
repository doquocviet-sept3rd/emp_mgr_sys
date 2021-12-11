package system.services.impls;

import system.entities.Country;
import system.services.ICountryService;
import system.utils.repositories.RepositoriesDAO;

import java.util.List;

public class CountryService implements ICountryService {

    @Override
    public List<Country> findAll() {
        return RepositoriesDAO.getCountryDAO().findAll();
    }

    @Override
    public List<Country> findAllByRegion(long regionId) {
        return RepositoriesDAO.getCountryDAO().findAllByRegion(regionId);
    }
}
