package com.example.computerrepair.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Long addressId;
    private String neighborhood;
    private String street;
    private String house;
    private String apartment;

    @OneToMany(mappedBy = "address")
    private Set<Client> clients;

    public Address() {
    }

    public Address(String neighborhood, String street, String house, String apartment) {
        this.neighborhood = neighborhood;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}

