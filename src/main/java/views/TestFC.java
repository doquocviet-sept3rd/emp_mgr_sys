package views;

import system.daos.impls.DepartmentDAO;
import system.daos.impls.EmployeeDAO;
import system.daos.impls.WardDAO;
import system.entities.Department;
import system.entities.Employee;
import system.entities.Ward;

public class TestFC {
    public static void main(String[] args) {
//        Department department = new DepartmentDAO().findOne(Long.parseLong("29"));
//        System.out.println("Dept: " + department.getName());
//
        Ward ward = new WardDAO().findOne(Long.parseLong("1"));
        System.out.println("Ward: " + ward);

        Employee e = new EmployeeDAO().findOne(Long.parseLong("1"));
        System.out.println(" Employee: " + e);
    }
}
