package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.PhieumuaCartEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhieuMuaCartCrud extends CrudRepository<PhieumuaCartEntity,Integer> {

    @Query("select pmc from PhieumuaCartEntity pmc where pmc.phieumuaByPhieumua.taikhoanByTaikhoan.id=?1 and pmc.cartByCart.productSizeColorEntity.productByProduct.idproduct=?2 and pmc.phieumuaByPhieumua.status=4")
    public List<PhieumuaCartEntity> getPhieuMua(String taikhoan, int idProduct);

}
