package com.tttn.spring.webshop.model;


import javax.persistence.*;

@Entity
@Table(name = "province")
public class ProvinceEntity {

    private String provinceid;

    private String name;

    @Id
    @Column(name = "provinceid")
    public String getProvinceid() {
        return provinceid;
    }

    public ProvinceEntity setProvinceid(String provinceid) {
        this.provinceid = provinceid;
        return this;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public ProvinceEntity setName(String name) {
        this.name = name;
        return this;
    }
}
