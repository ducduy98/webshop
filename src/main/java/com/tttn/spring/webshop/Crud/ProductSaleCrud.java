package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.ProductSaleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSaleCrud extends CrudRepository<ProductSaleEntity,Integer> {


    @Query(value = "select a from ProductSaleEntity a where a.status=1 ORDER BY a.idproductSale DESC ")

    public List<ProductSaleEntity> getSaleProduct();


}
