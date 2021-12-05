package system.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "job_history", schema = "emp_mgr_sys")
public class JobHistory {

    private long id;
    private long departmentId;
    private long employeeId;
    private Timestamp endDate;
    private long jobId;
    private Timestamp startDate;

    private Department department;
    private Employee employee;
    private Job job;

    public JobHistory() {

    }

    public JobHistory(long id, long employeeId, long jobId, long departmentId, Timestamp startDate, Timestamp endDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.jobId = jobId;
        this.departmentId = departmentId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public JobHistory(long employeeId, long jobId, long departmentId, Timestamp startDate, Timestamp endDate) {
        this.employeeId = employeeId;
        this.jobId = jobId;
        this.departmentId = departmentId;
        this.startDate = startDate;
        this.endDate = endDate;
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
    @Column(name = "department_id", nullable = false)
    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "employee_id", nullable = false)
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
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
    @Column(name = "start_date", nullable = false)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobHistory that = (JobHistory) o;
        return id == that.id && departmentId == that.departmentId && employeeId == that.employeeId && jobId == that.jobId && Objects.equals(endDate, that.endDate) && Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departmentId, employeeId, endDate, jobId, startDate);
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
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
