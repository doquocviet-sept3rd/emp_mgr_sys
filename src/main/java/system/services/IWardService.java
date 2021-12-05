package system.services;

import system.entities.Ward;

import java.util.List;

public interface IWardService {

    List<Ward> findAll();

    List<Ward> findAllByDistrict(long districtId);
}
