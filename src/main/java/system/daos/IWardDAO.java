package system.daos;

import system.entities.Ward;

import java.util.List;

public interface IWardDAO extends IBasicDAO<Ward> {

    List<Ward> findAll();

    Ward findOne(Long id);

    List<Ward> findAllByDistrict(long districtId);

}
