package system.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "district", schema = "emp_mgr_sys")
public class District {

    private long id;
    private String name;
    private Long provinceId;

    private Province province;

    private Collection<Ward> wards;

    public District() {
    }

    public District(long id, String name, long provinceId) {
        this.id = id;
        this.name = name;
        this.provinceId = provinceId;
    }

    public District(String name, long provinceId) {
        this.name = name;
        this.provinceId = provinceId;
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
    @Column(name = "name", nullable = false)
    @Nationalized
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "province_id", nullable = false)
    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district = (District) o;
        return id == district.id && Objects.equals(name, district.name) && Objects.equals(provinceId, district.provinceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, provinceId);
    }

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @OneToMany(mappedBy = "district")
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    public Collection<Ward> getWards() {
        return wards;
    }

    public void setWards(Collection<Ward> wards) {
        this.wards = wards;
    }
}
