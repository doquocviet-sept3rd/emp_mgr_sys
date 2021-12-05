package system.daos;

import system.entities.District;

import java.util.List;

public interface IDistrictDAO extends IBasicDAO<District> {

    List<District> findAll();

    District findOne(Long id);

    List<District> findAllByProvince(long provinceId);
}
