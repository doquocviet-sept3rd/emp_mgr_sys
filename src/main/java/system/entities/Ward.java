package system.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ward", schema = "emp_mgr_sys")
public class Ward {

    private long id;
    private long districtId;
    private String name;

    private District district;

    private Collection<Department> departments;
    private Collection<Employee> employeesById;

    public Ward() {

    }

    public Ward(long id, String name, long districtId) {
        this.id = id;
        this.name = name;
        this.districtId = districtId;
    }

    public Ward(String name, long districtId) {
        this.name = name;
        this.districtId = districtId;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "district_id", nullable = false)
    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }

    @Basic
    @Column(name = "name", nullable = false)
    @Nationalized
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ward ward = (Ward) o;
        return id == ward.id && districtId == ward.districtId && Objects.equals(name, ward.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, districtId, name);
    }

    @OneToMany(mappedBy = "ward")
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    public Collection<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Collection<Department> departments) {
        this.departments = departments;
    }

    @OneToMany(mappedBy = "ward")
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    public Collection<Employee> getEmployeesById() {
        return employeesById;
    }

    public void setEmployeesById(Collection<Employee> employeesById) {
        this.employeesById = employeesById;
    }

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
