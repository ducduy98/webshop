package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.SizeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeCrud extends CrudRepository<SizeEntity,Integer> {

    public List<SizeEntity> findBySize(int size);
}
