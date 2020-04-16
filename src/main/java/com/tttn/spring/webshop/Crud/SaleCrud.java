package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.SaleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleCrud extends CrudRepository<SaleEntity,Integer> {



}
