package system.services;

import system.entities.Country;

import java.util.List;

public interface ICountryService {

    List<Country> findAll();

    List<Country> findAllByRegion(long regionId);

}
