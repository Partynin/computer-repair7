package com.example.computerrepair.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "start_date")
    private Date startDate;
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "guarantee_id")
    private Guarantee guarantee;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    public Service() {
    }

    public Service(Client client, Device device, String serviceName, Integer price) {
        this.serviceName = serviceName;
        this.startDate = new Date();
        this.price = price;
        this.client = client;
        this.device = device;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Guarantee getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Guarantee guarantee) {
        this.guarantee = guarantee;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}

