package com.tttn.spring.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "taikhoan", schema = "shop", catalog = "")
public class TaikhoanEntity {
    private String id;
    private String pass;
    private int status;
    private Collection<CartEntity> cartsById;
    private Collection<ChitietEntity> chitietsById;
    private Collection<CommentEntity> commentsById;
    private Collection<MailEntity> mailById;
    private Collection<PhieumuaEntity> phieumuasById;
    private Collection<TaikhoanRoleEntity> taikhoanRolesById;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getStatus() {
        return status;
    }

    public TaikhoanEntity setStatus(int status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaikhoanEntity that = (TaikhoanEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pass, that.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pass);
    }

    @OneToMany(mappedBy = "taikhoanByTaikhoan")
    @JsonBackReference
    public Collection<CartEntity> getCartsById() {
        return cartsById;
    }

    public void setCartsById(Collection<CartEntity> cartsById) {
        this.cartsById = cartsById;
    }

    @OneToMany(mappedBy = "taikhoanByTaikhoan")
    @JsonBackReference
    public Collection<ChitietEntity> getChitietsById() {
        return chitietsById;
    }

    public void setChitietsById(Collection<ChitietEntity> chitietsById) {
        this.chitietsById = chitietsById;
    }

    @OneToMany(mappedBy = "taikhoanByTaikhoan")
    @JsonBackReference
    public Collection<CommentEntity> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<CommentEntity> commentsById) {
        this.commentsById = commentsById;
    }

    @OneToMany(mappedBy = "taikhoanByTaikhoan")
    @JsonBackReference
    public Collection<MailEntity> getMailById() {
        return mailById;
    }

    public void setMailById(Collection<MailEntity> mailById) {
        this.mailById = mailById;
    }

    @OneToMany(mappedBy = "taikhoanByTaikhoan")
    @JsonBackReference
    public Collection<PhieumuaEntity> getPhieumuasById() {
        return phieumuasById;
    }

    public void setPhieumuasById(Collection<PhieumuaEntity> phieumuasById) {
        this.phieumuasById = phieumuasById;
    }

    @OneToMany(mappedBy = "taikhoanByTaikhoan",fetch = FetchType.EAGER)
    @JsonBackReference
    public Collection<TaikhoanRoleEntity> getTaikhoanRolesById() {
        return taikhoanRolesById;
    }

    public void setTaikhoanRolesById(Collection<TaikhoanRoleEntity> taikhoanRolesById) {
        this.taikhoanRolesById = taikhoanRolesById;
    }
}
