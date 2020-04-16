package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.CartEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartCrud extends CrudRepository<CartEntity,Integer> {

    @Query("select c from CartEntity c where c.taikhoanByTaikhoan.id=?1 and c.status=1")
    public List<CartEntity> getListCart(String userName);

    @Query("select c from CartEntity c where c.taikhoanByTaikhoan.id=?1 and c.productSizeColorEntity.idproductSizeColor=?2 and c.status=1")
    public CartEntity checkHang(String idTaikhoan,int idProductSizeColor);




}
