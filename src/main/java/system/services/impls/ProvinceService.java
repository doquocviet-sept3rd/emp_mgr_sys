package system.services.impls;

import system.daos.IProvinceDAO;
import system.daos.impls.ProvinceDAO;
import system.entities.Province;
import system.services.IProvinceService;

import java.util.List;

public class ProvinceService implements IProvinceService {

    private final IProvinceDAO provinceDAO = new ProvinceDAO();

    @Override
    public List<Province> findAll() {
        return provinceDAO.findAll();
    }

    @Override
    public List<Province> findAllByCountry(long countryId) {
        return provinceDAO.findAllByCountry(countryId);
    }
}
