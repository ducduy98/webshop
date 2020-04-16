package com.tttn.spring.webshop.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "taikhoan_role", schema = "shop", catalog = "")
public class TaikhoanRoleEntity {
    private int idtaikhoanRole;
    private TaikhoanEntity taikhoanByTaikhoan;
    private RoleEntity roleByRole;

    @Id
    @Column(name = "idtaikhoanrole")
    public int getIdtaikhoanRole() {
        return idtaikhoanRole;
    }

    public void setIdtaikhoanRole(int idtaikhoanRole) {
        this.idtaikhoanRole = idtaikhoanRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaikhoanRoleEntity that = (TaikhoanRoleEntity) o;
        return idtaikhoanRole == that.idtaikhoanRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtaikhoanRole);
    }

    @ManyToOne
    @JoinColumn(name = "taikhoan", referencedColumnName = "id")
    public TaikhoanEntity getTaikhoanByTaikhoan() {
        return taikhoanByTaikhoan;
    }

    public void setTaikhoanByTaikhoan(TaikhoanEntity taikhoanByTaikhoan) {
        this.taikhoanByTaikhoan = taikhoanByTaikhoan;
    }

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "idrole")
    public RoleEntity getRoleByRole() {
        return roleByRole;
    }

    public void setRoleByRole(RoleEntity roleByRole) {
        this.roleByRole = roleByRole;
    }
}
