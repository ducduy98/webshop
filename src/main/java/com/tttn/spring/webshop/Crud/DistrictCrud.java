package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.DistrictEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictCrud extends CrudRepository<DistrictEntity,String> {

    @Query("select d from DistrictEntity d where d.provinceid=?1")
    public List<DistrictEntity> getHuyen(String provinceid);
}
