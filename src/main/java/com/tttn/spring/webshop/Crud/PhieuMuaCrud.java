package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.PhieumuaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface PhieuMuaCrud extends CrudRepository<PhieumuaEntity,String> {

    @Query("select pm from PhieumuaEntity pm where pm.status=?1 order by  pm.date desc")
    public Page<PhieumuaEntity> getTheoStatus(int status, Pageable pageable);

    @Query("select pm from PhieumuaEntity pm where pm.status=?1 order by  pm.date desc")
    public List<PhieumuaEntity> getListFindStatus(int status);

    @Query("select pm from PhieumuaEntity as pm where pm.date>=:startDate and pm.date<:closeDate and pm.status=4")
    public List<PhieumuaEntity> getListFindDate(@Param("startDate") Date startDate,@Param("closeDate") Date closeDate);
}
