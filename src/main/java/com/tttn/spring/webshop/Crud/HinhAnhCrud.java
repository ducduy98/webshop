package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.HinhanhEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HinhAnhCrud extends CrudRepository<HinhanhEntity, Integer> {

    @Query("select ha from HinhanhEntity ha where ha.productByProduct.idproduct=?1 ")
    public List<HinhanhEntity> getHinhAnhProduct(int id);
}
