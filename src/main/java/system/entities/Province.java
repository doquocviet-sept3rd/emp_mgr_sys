package system.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "province", schema = "emp_mgr_sys")
public class Province {

    private long id;
    private long countryId;
    private String name;

    private Country country;

    private Collection<District> districts;

    public Province() {

    }

    public Province(long id, String name, long countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }

    public Province(String name, long countryId) {
        this.name = name;
        this.countryId = countryId;
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
    @Column(name = "country_id", nullable = false)
    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
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
        Province province = (Province) o;
        return id == province.id && countryId == province.countryId && Objects.equals(name, province.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countryId, name);
    }

    @OneToMany(mappedBy = "province")
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    public Collection<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Collection<District> districts) {
        this.districts = districts;
    }

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
