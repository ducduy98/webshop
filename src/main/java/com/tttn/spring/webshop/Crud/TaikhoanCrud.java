package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.TaikhoanEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaikhoanCrud extends CrudRepository<TaikhoanEntity,String> {


}
