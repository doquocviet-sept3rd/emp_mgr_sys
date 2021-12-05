package system.daos;

import system.entities.Country;

import java.util.List;

public interface ICountryDAO extends IBasicDAO<Country> {

    List<Country> findAll();

    Country findOne(Long id);

    List<Country> findAllByRegion(long regionId);
}
