package system.daos.impls;

import system.daos.IProvinceDAO;
import system.entities.Province;

import java.util.List;

public class ProvinceDAO extends BasicDAO<Province> implements IProvinceDAO {

    private static final String className = "Province";

    @Override
    public List<Province> findAll() {
        return super.findAll(className);
    }

    @Override
    public Province findOne(Long id) {
        return super.findOne(className, id);
    }

    @Override
    public List<Province> findAllByCountry(long countryId) {
        return super.query("select p from Province p where p.countryId = " + countryId);
    }
}
