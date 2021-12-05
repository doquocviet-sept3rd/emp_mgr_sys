package system.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "job", schema = "emp_mgr_sys")
public class Job {

    private long id;
    private double maxSalary;
    private double minSalary;
    private String title;

    private Collection<Employee> employees;
    private Collection<JobHistory> jobHistories;

    public Job() {

    }

    public Job(long id, double maxSalary, double minSalary, String title) {
        this.id = id;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.title = title;
    }

    public Job(double maxSalary, double minSalary, String title) {
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.title = title;
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
    @Column(name = "max_salary", nullable = false)
    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Basic
    @Column(name = "min_salary", nullable = false)
    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    @Basic
    @Column(name = "title", nullable = false, unique = true)
    @Nationalized
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return id == job.id && Double.compare(job.maxSalary, maxSalary) == 0 && Double.compare(job.minSalary, minSalary) == 0 && Objects.equals(title, job.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maxSalary, minSalary, title);
    }

    @OneToMany(mappedBy = "job")
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    @OneToMany(mappedBy = "job")
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    public Collection<JobHistory> getJobHistories() {
        return jobHistories;
    }

    public void setJobHistories(Collection<JobHistory> jobHistories) {
        this.jobHistories = jobHistories;
    }
}
