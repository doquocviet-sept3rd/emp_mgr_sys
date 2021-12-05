package system.services.impls;

import system.daos.IDistrictDAO;
import system.daos.impls.DistrictDAO;
import system.entities.District;
import system.services.IDistrictService;

import java.util.List;

public class DistrictService implements IDistrictService {

    private final IDistrictDAO districtDAO = new DistrictDAO();

    @Override
    public List<District> findAll() {
        return districtDAO.findAll();
    }

    @Override
    public List<District> findAllByProvince(long provinceId) {
        return districtDAO.findAllByProvince(provinceId);
    }
}
