package system.utils.repositories;

import system.daos.*;
import system.daos.impls.*;

public class RepositoriesDAO {

    private static ICountryDAO countryDAO = null;
    private static IDepartmentDAO departmentDAO = null;
    private static IDistrictDAO districtDAO = null;
    private static IEmployeeDAO employeeDAO = null;
    private static IJobDAO jobDAO = null;
    private static IJobHistoryDAO jobHistoryDAO = null;
    private static IProvinceDAO provinceDAO = null;
    private static IRegionDAO regionDAO = null;
    private static IWardDAO wardDAO = null;

    public static ICountryDAO getCountryDAO() {
        if (countryDAO == null) {
            countryDAO = new CountryDAO();
        }
        return countryDAO;
    }

    public static IDepartmentDAO getDepartmentDAO() {
        if (departmentDAO == null) {
            departmentDAO = new DepartmentDAO();
        }
        return departmentDAO;
    }

    public static IDistrictDAO getDistrictDAO() {
        if (districtDAO == null) {
            districtDAO = new DistrictDAO();
        }
        return districtDAO;
    }

    public static IEmployeeDAO getEmployeeDAO() {
        if (employeeDAO == null) {
            employeeDAO = new EmployeeDAO();
        }
        return employeeDAO;
    }

    public static IJobDAO getJobDAO() {
        if (jobDAO == null) {
            jobDAO = new JobDAO();
        }
        return jobDAO;
    }

    public static IJobHistoryDAO getJobHistoryDAO() {
        if (jobHistoryDAO == null) {
            jobHistoryDAO = new JobHistoryDAO();
        }
        return jobHistoryDAO;
    }

    public static IProvinceDAO getProvinceDAO() {
        if (provinceDAO == null) {
            provinceDAO = new ProvinceDAO();
        }
        return provinceDAO;
    }

    public static IRegionDAO getRegionDAO() {
        if (regionDAO == null) {
            regionDAO = new RegionDAO();
        }
        return regionDAO;
    }

    public static IWardDAO getWardDAO() {
        if (wardDAO == null) {
            wardDAO = new WardDAO();
        }
        return wardDAO;
    }
}
