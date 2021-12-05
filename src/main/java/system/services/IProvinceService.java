package system.services;

import system.entities.Province;

import java.util.List;

public interface IProvinceService {

    List<Province> findAll();

    List<Province> findAllByCountry(long countryId);

}
