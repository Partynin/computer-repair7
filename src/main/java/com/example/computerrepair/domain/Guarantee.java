package com.example.computerrepair.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Guarantee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "guarantee_type")
    private String guaranteeType;
    @Column(name = "start_date")
    private Date startDate;
    private int duration;
    private boolean status;

    @OneToMany(mappedBy = "guarantee")
    private Set<Service> services;

    public Guarantee() {
    }

    public Guarantee(String guaranteeType, int duration) {
        this.guaranteeType = guaranteeType;
        this.startDate = new Date();
        this.duration = duration;
        this.status = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public Date getStartDate() {

        return startDate;
    }

    public void setStartDate(Date startDate) {

        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

