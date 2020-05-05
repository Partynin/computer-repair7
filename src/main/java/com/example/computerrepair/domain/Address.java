package com.example.computerrepair.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Long addressId;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @ManyToOne
    @JoinColumn(name = "hood_id")
    private Neighborhood neighborhood;
    @ManyToOne
    @JoinColumn(name = "street_id")
    private Street street;
    private Integer house;
    private Integer apartment;
    private Integer index;

    @OneToMany(mappedBy = "address")
    private Set<Client> clients;

    public Address() {
    }

    public Address(City city, Neighborhood neighborhood, Street street, Integer house, Integer apartment, Integer index) {
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.index = index;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}

