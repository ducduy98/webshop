package com.tttn.spring.webshop.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "district")
public class DistrictEntity {

    private String districtid;
    private String name;
    private String provinceid;

    @Id
    @Column(name = "districtid")
    public String getDistrictid() {
        return districtid;
    }

    public DistrictEntity setDistrictid(String districtid) {
        this.districtid = districtid;
        return this;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public DistrictEntity setName(String name) {
        this.name = name;
        return this;
    }


    @Column(name = "provinceid")
    public String getProvinceid() {
        return provinceid;
    }

    public DistrictEntity setProvinceid(String provinceid) {
        this.provinceid = provinceid;
        return this;
    }
}
