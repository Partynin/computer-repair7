package com.example.computerrepair.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "discount_type")
    private String discountType;
    private int amount;
    @Column(name = "start_date")
    private Date startDate;
    private boolean status;

    @OneToMany(mappedBy = "discount")
    private Set<Service> services;

    public Discount() {
    }

    public Discount(String discountType, int amount) {
        this.discountType = discountType;
        this.amount = amount;
        this.startDate = new Date();
        this.status = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
