package com.example.computerrepair.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Neighborhood {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hood_id")
    private Long hoodId;
    private String nameHood;

    @OneToMany(mappedBy = "neighborhood", cascade = CascadeType.REMOVE)
    private Set<Address> addresses;

    public Neighborhood() {

    }

    public Neighborhood(String nameHood) {
        this.nameHood = nameHood;
    }

    public Long getHoodId() {
        return hoodId;
    }

    public void setHoodId(Long hoodId) {
        this.hoodId = hoodId;
    }

    public String getNameHood() {
        return nameHood;
    }

    public void setNameHood(String nameHood) {
        this.nameHood = nameHood;
    }
}
