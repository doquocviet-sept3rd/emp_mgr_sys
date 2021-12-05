package system.daos.impls;

import system.daos.IWardDAO;
import system.entities.Ward;

import java.util.List;

public class WardDAO extends BasicDAO<Ward> implements IWardDAO {

    private static final String className = "Ward";

    @Override
    public List<Ward> findAll() {
        return super.findAll(className);
    }

    @Override
    public Ward findOne(Long id) {
        return super.findOne(className, id);
    }

    @Override
    public List<Ward> findAllByDistrict(long districtId) {
        return super.query("select w from Ward w where w.districtId = " + districtId);
    }
}
