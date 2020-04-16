package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.ProductCategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductCategoryCrud extends CrudRepository<ProductCategoryEntity,Integer> {

    @Query("select pc from ProductCategoryEntity pc where pc.productByProduct.idproduct=?1 and pc.categoryByCategory.idcategory=?2")
    public ProductCategoryEntity getProductAndCategory(int idproduct,int idcategory);
}
