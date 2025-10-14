package com.Aman.Aman.Pos.System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Aman.Aman.Pos.System.model.Store;

public interface StoreRepository extends JpaRepository<Store,Long> {

    Store findByStoreAdminId(Long adminId);

}
