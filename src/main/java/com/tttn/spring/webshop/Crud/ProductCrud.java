package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCrud extends CrudRepository<ProductEntity, Integer> {

    @Query(value = "select p from ProductEntity p where p.sold>0 order by p.sold desc")
    public List<ProductEntity> buyMany(Pageable pageable);

    @Query(value = "select p from ProductEntity p where p.status=1 order by p.dateCreated desc")
    public List<ProductEntity> dateCreatedGetList();

    public List<ProductEntity> findByProductContaining(String product);

    @Query(
            "select pe from ProductEntity pe left join pe.productSizeColorEntities psce" +
                    " left join psce.colorByColor c " +
                    " where (c.idcolor = :color or :color is null) " +
                    "and (pe.price >= :pmin or :pmin is null) " +
                    "and (pe.price <= :pmax or :pmax is null)"
    )
    public List<ProductEntity> searchMultiField(
            @Param("color") Integer idColor,
            @Param("pmin") Integer priceMin,
            @Param("pmax") Integer priceMax);
}
