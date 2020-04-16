package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.NhapProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NhapProductCrud extends CrudRepository<NhapProductEntity,Integer> {

    @Query("select n from NhapProductEntity n where n.nhapHangEntityByIdnhaphang.idnhaphang=?1")
    public List<NhapProductEntity> findNhap(String idNhap);
}
