package com.tttn.spring.webshop;

import com.tttn.spring.webshop.Crud.ProductCrud;
import com.tttn.spring.webshop.model.ColorEntity;
import com.tttn.spring.webshop.model.ProductEntity;
import com.tttn.spring.webshop.model.SizeEntity;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@SpringBootTest
class WebshopApplicationTests {

    @Autowired
    ProductCrud productCrud;

    @Test
    @Transactional
    void contextLoads() {
        ProductEntity entity=productCrud.findById(1).orElse(null);
        Hibernate.initialize(entity.getProductSizeColorEntities());
        Map<ColorEntity, List<SizeEntity>> list=entity.getListMap();
        list.forEach((k,v)->{
            System.out.println(k.getColor());
            v.forEach(c-> System.out.println(c.getSize()));
        });
    }

}
