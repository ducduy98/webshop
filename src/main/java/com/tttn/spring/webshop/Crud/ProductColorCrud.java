package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.ProductColorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductColorCrud extends CrudRepository<ProductColorEntity,Integer> {
}
