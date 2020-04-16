package com.tttn.spring.webshop.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mail", schema = "shop", catalog = "")
public class MailEntity {
    private int idmail;
    private String noiDung;
    private TaikhoanEntity taikhoanByTaikhoan;

    @Id
    @Column(name = "idmail")
    public int getIdmail() {
        return idmail;
    }

    public void setIdmail(int idmail) {
        this.idmail = idmail;
    }

    @Basic
    @Column(name = "noiDung")
    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailEntity that = (MailEntity) o;
        return idmail == that.idmail &&
                Objects.equals(noiDung, that.noiDung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmail, noiDung);
    }

    @ManyToOne
    @JoinColumn(name = "taikhoan", referencedColumnName = "id")
    public TaikhoanEntity getTaikhoanByTaikhoan() {
        return taikhoanByTaikhoan;
    }

    public void setTaikhoanByTaikhoan(TaikhoanEntity taikhoanByTaikhoan) {
        this.taikhoanByTaikhoan = taikhoanByTaikhoan;
    }
}
