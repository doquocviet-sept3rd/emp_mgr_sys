package system.services;

import system.entities.District;

import java.util.List;

public interface IDistrictService {

    List<District> findAll();

    List<District> findAllByProvince(long provinceId);

}
