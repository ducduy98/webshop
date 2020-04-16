package com.tttn.spring.webshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ward")
public class WardEntity {

    private String wardid;
    private String name;
    private String districtid;

    @Id
    @Column(name = "wardid")
    public String getWardid() {
        return wardid;
    }

    public WardEntity setWardid(String wardid) {
        this.wardid = wardid;
        return this;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public WardEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(name = "districtid")
    public String getDistrictid() {
        return districtid;
    }

    public WardEntity setDistrictid(String districtid) {
        this.districtid = districtid;
        return this;
    }
}
