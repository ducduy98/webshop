package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.ProvinceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceCrud extends CrudRepository<ProvinceEntity,String> {
}
