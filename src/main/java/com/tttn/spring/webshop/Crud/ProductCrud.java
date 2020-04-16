package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProductCrud extends CrudRepository<ProductEntity,Integer> {

    @Query(value = "select p from ProductEntity p where p.sold>0 order by p.sold desc")
    public List<ProductEntity> buyMany(Pageable pageable);

    @Query(value = "select p from ProductEntity p where p.status=1 order by p.dateCreated desc ")
    public List<ProductEntity> dateCreatedGetList();

    public List<ProductEntity> findByProductContaining(String product);
}
