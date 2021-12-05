package system.daos.impls;

import system.daos.IDistrictDAO;
import system.entities.District;

import java.util.List;

public class DistrictDAO extends BasicDAO<District> implements IDistrictDAO {

    private static final String className = "District";

    @Override
    public List<District> findAll() {
        return super.findAll(className);
    }

    @Override
    public District findOne(Long id) {
        return super.findOne(className, id);
    }

    @Override
    public List<District> findAllByProvince(long provinceId) {
        return super.query("select d from District d where d.provinceId = " + provinceId);
    }
}
