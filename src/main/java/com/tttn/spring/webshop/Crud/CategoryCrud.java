package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryCrud extends CrudRepository<CategoryEntity,Integer> {
}
