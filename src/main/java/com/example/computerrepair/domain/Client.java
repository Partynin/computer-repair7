package com.example.computerrepair.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lastname;
    private String name;
    private String patronymic;
    private Long phone;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "client")
    private Set<Service> services;

    @OneToMany(mappedBy = "client")
    private Set<Device> devices;

    public Client() {
    }

    public Client(String lastname, String name, String patronymic, Long phone, Address address) {
        this.lastname = lastname;
        this.name = name;
        this.patronymic = patronymic;
        this.phone = phone;
        this.address = address;
    }

    public void setNewPersonalInformation(String lastname, String name, String patronymic, Long phone) {
        setLastname(lastname);
        setName(name);
        setPatronymic(patronymic);
        setPhone(phone);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }
}

