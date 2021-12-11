package system.utils.repositories;

import system.services.*;
import system.services.impls.*;

public class RepositoriesService {

    private static ICountryService countryService = null;
    private static IDepartmentService departmentService = null;
    private static IDistrictService districtService = null;
    private static IEmployeeService employeeService = null;
    private static IJobHistoryService jobHistoryService = null;
    private static IJobService jobService = null;
    private static IProvinceService provinceService = null;
    private static IRegionService regionService = null;
    private static IWardService wardService = null;

    public static ICountryService getCountryService() {
        if (countryService == null) {
            countryService = new CountryService();
        }
        return countryService;
    }

    public static IDepartmentService getDepartmentService() {
        if (departmentService == null) {
            departmentService = new DepartmentService();
        }
        return departmentService;
    }

    public static IDistrictService getDistrictService() {
        if (districtService == null) {
            districtService = new DistrictService();
        }
        return districtService;
    }

    public static IEmployeeService getEmployeeService() {
        if (employeeService == null) {
            employeeService = new EmployeeService();
        }
        return employeeService;
    }

    public static IJobHistoryService getJobHistoryService() {
        if (jobHistoryService == null) {
            jobHistoryService = new JobHistoryService();
        }
        return jobHistoryService;
    }

    public static IJobService getJobService() {
        if (jobService == null) {
            jobService = new JobService();
        }
        return jobService;
    }

    public static IProvinceService getProvinceService() {
        if (provinceService == null) {
            provinceService = new ProvinceService();
        }
        return provinceService;
    }

    public static IRegionService getRegionService() {
        if (regionService == null) {
            regionService = new RegionService();
        }
        return regionService;
    }

    public static IWardService getWardService() {
        if (wardService == null) {
            wardService = new WardService();
        }
        return wardService;
    }
}
