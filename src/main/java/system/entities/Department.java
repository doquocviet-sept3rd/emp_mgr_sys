package system.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "department", schema = "emp_mgr_sys")
public class Department {

    private long id;
    private long managerId;
    private String name;
    private long wardId;

    private Employee manager;
    private Ward ward;

    private Collection<Employee> employees;
    private Collection<JobHistory> jobHistories;

    public Department() {
    }

    public Department(long id, String name, long managerId, long wardId) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.wardId = wardId;
    }

    public Department(String name, long managerId, long wardId) {
        this.name = name;
        this.managerId = managerId;
        this.wardId = wardId;
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
    @Column(name = "manager_id", nullable = true)
    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    @Basic
    @Column(name = "name", nullable = false, unique = true)
    @Nationalized
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ward_id", nullable = false)
    public long getWardId() {
        return wardId;
    }

    public void setWardId(long wardId) {
        this.wardId = wardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && managerId == that.managerId && wardId == that.wardId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, managerId, name, wardId);
    }

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @ManyToOne
    @JoinColumn(name = "ward_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    @OneToMany(mappedBy = "department")
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    @OneToMany(mappedBy = "department")
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    public Collection<JobHistory> getJobHistories() {
        return jobHistories;
    }

    public void setJobHistories(Collection<JobHistory> jobHistories) {
        this.jobHistories = jobHistories;
    }
}
