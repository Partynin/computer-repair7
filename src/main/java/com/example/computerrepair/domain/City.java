package com.example.computerrepair.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id")
    private Long cityId;
    private String nameCity;

    @OneToMany(mappedBy = "street", cascade = CascadeType.REMOVE)
    private Set<Address> addresses;

    public City() {

    }

    public City(String nameCity) {
        this.nameCity = nameCity;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
