package system.services.impls;

import system.daos.ICountryDAO;
import system.daos.impls.CountryDAO;
import system.entities.Country;
import system.services.ICountryService;

import java.util.List;

public class CountryService implements ICountryService {

    private final ICountryDAO countryDAO = new CountryDAO();

    @Override
    public List<Country> findAll() {
        return countryDAO.findAll();
    }

    @Override
    public List<Country> findAllByRegion(long regionId) {
        return countryDAO.findAllByRegion(regionId);
    }
}
