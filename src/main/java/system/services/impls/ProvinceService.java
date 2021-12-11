package system.services.impls;

import system.entities.Province;
import system.services.IProvinceService;
import system.utils.repositories.RepositoriesDAO;

import java.util.List;

public class ProvinceService implements IProvinceService {

    @Override
    public List<Province> findAll() {
        return RepositoriesDAO.getProvinceDAO().findAll();
    }

    @Override
    public List<Province> findAllByCountry(long countryId) {
        return RepositoriesDAO.getProvinceDAO().findAllByCountry(countryId);
    }
}
