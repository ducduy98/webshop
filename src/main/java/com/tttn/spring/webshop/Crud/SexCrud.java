package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.SexEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SexCrud extends CrudRepository<SexEntity,Integer> {
}
