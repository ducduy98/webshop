package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.ChitietEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietCrud extends CrudRepository<ChitietEntity,Integer> {
}
