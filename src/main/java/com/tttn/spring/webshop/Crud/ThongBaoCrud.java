package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.ThongbaoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThongBaoCrud extends CrudRepository<ThongbaoEntity,Integer> {
}
