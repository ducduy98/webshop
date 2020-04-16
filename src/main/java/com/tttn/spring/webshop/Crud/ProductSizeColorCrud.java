package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.ProductSizeColorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductSizeColorCrud extends CrudRepository<ProductSizeColorEntity,Integer> {


    @Query("select psc from ProductSizeColorEntity psc where psc.productByProduct.idproduct=?1 and psc.sizeBySize.idsize=?2 and psc.colorByColor.idcolor=?3"
            )
    public ProductSizeColorEntity getChiTiet(int idProduct,int idSize,int idColor);
}
