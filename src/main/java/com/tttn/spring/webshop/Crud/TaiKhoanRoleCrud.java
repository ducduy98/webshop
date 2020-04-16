package com.tttn.spring.webshop.Crud;

import com.tttn.spring.webshop.model.TaikhoanEntity;
import com.tttn.spring.webshop.model.TaikhoanRoleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaiKhoanRoleCrud extends CrudRepository<TaikhoanRoleEntity,Integer> {

    @Query("select t from TaikhoanRoleEntity t where t.roleByRole.idrole=3 and t.taikhoanByTaikhoan.status=1")
    public List<TaikhoanRoleEntity> getNhanVien();
}
