package system.services.impls;

import system.entities.Ward;
import system.services.IWardService;
import system.utils.repositories.RepositoriesDAO;

import java.util.List;

public class WardService implements IWardService {

    @Override
    public List<Ward> findAll() {
        return RepositoriesDAO.getWardDAO().findAll();
    }

    @Override
    public List<Ward> findAllByDistrict(long districtId) {
        return RepositoriesDAO.getWardDAO().findAllByDistrict(districtId);
    }
}
