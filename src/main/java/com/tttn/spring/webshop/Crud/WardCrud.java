package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.WardEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardCrud extends CrudRepository<WardEntity,String> {

    @Query("select w from WardEntity w where w.districtid=?1")
    public List<WardEntity> getXa(String districtid);
}
