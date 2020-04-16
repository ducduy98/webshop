package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.CommentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentCrud extends CrudRepository<CommentEntity,Integer> {

    @Query("select c from CommentEntity c where c.productByProduct.idproduct=?1")
    public List<CommentEntity> findProduct(int product);
}
