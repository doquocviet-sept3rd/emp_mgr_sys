package system.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "emp_mgr_sys")
public class Employee {

    private long id;
    private Timestamp birthDate;
    private long departmentId;
    private String email;
    private boolean gender;
    private Timestamp hireDate;
    private long jobId;
    private String name;
    private String phNumber;
    private double salary;
    private long wardId;

    private Job job;
    private Ward ward;

    private Collection<Department> departments;
    private Department department;
    private Collection<JobHistory> jobHistories;

    public Employee() {
    }

    public Employee(long id, String name, String email, boolean gender, Timestamp birthDate, Timestamp hireDate, String phNumber, double salary, long jobId, long departmentId, long wardId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.phNumber = phNumber;
        this.salary = salary;
        this.jobId = jobId;
        this.departmentId = departmentId;
        this.wardId = wardId;
    }

    public Employee(String name, String email, boolean gender, Timestamp birthDate, Timestamp hireDate, String phNumber, double salary, long jobId, long departmentId, long wardId) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.phNumber = phNumber;
        this.salary = salary;
        this.jobId = jobId;
        this.departmentId = departmentId;
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
    @Column(name = "birth_date", nullable = false)
    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "department_id", nullable = true)
    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "gender", nullable = false)
    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "hire_date", nullable = false)
    public Timestamp getHireDate() {
        return hireDate;
    }

    public void setHireDate(Timestamp hireDate) {
        this.hireDate = hireDate;
    }

    @Basic
    @Column(name = "job_id", nullable = false)
    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
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

    @Basic
    @Column(name = "ph_number", nullable = false)
    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    @Basic
    @Column(name = "salary", nullable = false)
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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
        Employee employee = (Employee) o;
        return id == employee.id && departmentId == employee.departmentId && gender == employee.gender && jobId == employee.jobId && Double.compare(employee.salary, salary) == 0 && wardId == employee.wardId && Objects.equals(birthDate, employee.birthDate) && Objects.equals(email, employee.email) && Objects.equals(hireDate, employee.hireDate) && Objects.equals(name, employee.name) && Objects.equals(phNumber, employee.phNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, birthDate, departmentId, email, gender, hireDate, jobId, name, phNumber, salary, wardId);
    }

    @OneToMany(mappedBy = "manager")
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    public Collection<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Collection<Department> departments) {
        this.departments = departments;
    }

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @ManyToOne
    @JoinColumn(name = "ward_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    @OneToMany(mappedBy = "employee")
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    public Collection<JobHistory> getJobHistories() {
        return jobHistories;
    }

    public void setJobHistories(Collection<JobHistory> jobHistories) {
        this.jobHistories = jobHistories;
    }
}
