package system.daos.impls;

import system.daos.ICountryDAO;
import system.entities.Country;

import java.util.List;

public class CountryDAO extends BasicDAO<Country> implements ICountryDAO {

    private static final String className = "Country";

    @Override
    public List<Country> findAll() {
        return super.findAll(className);
    }

    @Override
    public Country findOne(Long id) {
        return super.findOne(className, id);
    }

    @Override
    public List<Country> findAllByRegion(long regionId) {
        return super.query("select c from Country c where c.regionId = " + regionId);
    }
}
