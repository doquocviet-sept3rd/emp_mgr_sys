package system.services.impls;

import system.entities.District;
import system.services.IDistrictService;
import system.utils.repositories.RepositoriesDAO;

import java.util.List;

public class DistrictService implements IDistrictService {

    @Override
    public List<District> findAll() {
        return RepositoriesDAO.getDistrictDAO().findAll();
    }

    @Override
    public List<District> findAllByProvince(long provinceId) {
        return RepositoriesDAO.getDistrictDAO().findAllByProvince(provinceId);
    }
}
