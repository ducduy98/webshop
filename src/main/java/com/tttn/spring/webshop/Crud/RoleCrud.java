package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleCrud extends CrudRepository<RoleEntity,Integer> {
}
