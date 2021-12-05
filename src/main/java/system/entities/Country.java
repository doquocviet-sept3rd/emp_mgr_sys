package system.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "country", schema = "emp_mgr_sys")
public class Country {

    private long id;
    private String name;
    private long regionId;

    private Region region;

    private Collection<Province> provinces;

    public Country() {
    }

    public Country(long id, String name, long regionId) {
        this.id = id;
        this.name = name;
        this.regionId = regionId;
    }

    public Country(String name, long regionId) {
        this.name = name;
        this.regionId = regionId;
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
    @Column(name = "region_id", nullable = false)
    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id && regionId == country.regionId && Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, regionId);
    }

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @OneToMany(mappedBy = "country")
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    public Collection<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(Collection<Province> provinces) {
        this.provinces = provinces;
    }
}
