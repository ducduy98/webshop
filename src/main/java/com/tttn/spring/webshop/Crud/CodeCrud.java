package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.CodeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeCrud extends CrudRepository<CodeEntity,Integer> {

    @Query("select code from CodeEntity code where code.code=?1 and code.status=1")
    public CodeEntity codeExist(String code);

    @Query("select code from CodeEntity code where code.status=1")
    public List<CodeEntity> findStatus1();

    @Query("select code from CodeEntity code where code.status=2")
    public List<CodeEntity> findStatus2();
}
