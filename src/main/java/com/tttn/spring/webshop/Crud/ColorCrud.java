package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.ColorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorCrud extends CrudRepository<ColorEntity,Integer> {
}
