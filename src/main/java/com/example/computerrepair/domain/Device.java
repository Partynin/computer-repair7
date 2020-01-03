package com.example.computerrepair.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "device_name")
    private String deviceName;
    @Column(name = "model_device")
    private String modelDevice;
    @Column(name = "serial_number")
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "device", cascade = CascadeType.REMOVE)
    private Set<Service> services;

    public Device(String deviceName, String modelDevice, String serialNumber, Client client) {
        this.deviceName = deviceName;
        this.modelDevice = modelDevice;
        this.serialNumber = serialNumber;
        this.client = client;
    }

    public Device() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getModelDevice() {
        return modelDevice;
    }

    public void setModelDevice(String modelDevice) {
        this.modelDevice = modelDevice;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
