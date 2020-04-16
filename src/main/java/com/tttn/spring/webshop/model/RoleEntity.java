package com.tttn.spring.webshop.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "role", schema = "shop", catalog = "")
public class RoleEntity {
    private int idrole;
    private String nameRole;
    private Collection<TaikhoanRoleEntity> taikhoanRolesByIdrole;

    @Id
    @Column(name = "idrole")
    public int getIdrole() {
        return idrole;
    }

    public void setIdrole(int idrole) {
        this.idrole = idrole;
    }

    @Basic
    @Column(name = "namerole")
    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return idrole == that.idrole ;

    }

    @Override
    public int hashCode() {
        return Objects.hash(idrole);
    }

    @OneToMany(mappedBy = "roleByRole")
    public Collection<TaikhoanRoleEntity> getTaikhoanRolesByIdrole() {
        return taikhoanRolesByIdrole;
    }

    public void setTaikhoanRolesByIdrole(Collection<TaikhoanRoleEntity> taikhoanRolesByIdrole) {
        this.taikhoanRolesByIdrole = taikhoanRolesByIdrole;
    }
}
