package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.ProductSizeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeCrud extends CrudRepository<ProductSizeEntity,Integer> {
}
