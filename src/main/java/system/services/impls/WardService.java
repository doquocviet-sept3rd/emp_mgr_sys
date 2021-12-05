package system.services.impls;

import system.daos.IWardDAO;
import system.daos.impls.WardDAO;
import system.entities.Ward;
import system.services.IWardService;

import java.util.List;

public class WardService implements IWardService {

    private final IWardDAO wardDAO = new WardDAO();

    @Override
    public List<Ward> findAll() {
        return wardDAO.findAll();
    }

    @Override
    public List<Ward> findAllByDistrict(long districtId) {
        return wardDAO.findAllByDistrict(districtId);
    }
}
