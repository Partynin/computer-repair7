package com.example.computerrepair.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "street_id")
    private Long streetId;
    private String nameStreet;

    @OneToMany(mappedBy = "street", cascade = CascadeType.REMOVE)
    private Set<Address> addresses;

    public Street() {

    }

    public Street(String nameStreet) {
        this.nameStreet = nameStreet;
    }

    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    public String getNameStreet() {
        return nameStreet;
    }

    public void setNameStreet(String nameStreet) {
        this.nameStreet = nameStreet;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
