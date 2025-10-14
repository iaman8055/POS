package com.Aman.Aman.Pos.System.service;

import com.Aman.Aman.Pos.System.Payload.dto.StoreDto;
import com.Aman.Aman.Pos.System.model.Store;
import com.Aman.Aman.Pos.System.model.User;

import java.util.List;

public interface StoreService {
    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(Long id) throws Exception;
    List<StoreDto>getAllStores();
    Store getStoreByAdmin();
    StoreDto updateStore(Long id,StoreDto storeDto) throws Exception;
    void deleteStore(Long id);
    StoreDto getStoreByEmployee() throws Exception;
}
