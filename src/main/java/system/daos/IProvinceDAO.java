package system.daos;

import system.entities.Province;

import java.util.List;

public interface IProvinceDAO extends IBasicDAO<Province> {

    List<Province> findAll();

    Province findOne(Long id);

    List<Province> findAllByCountry(long countryId);

}
