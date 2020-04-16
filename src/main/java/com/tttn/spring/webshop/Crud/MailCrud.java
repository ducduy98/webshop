package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.MailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailCrud extends CrudRepository<MailEntity,Integer> {
}
