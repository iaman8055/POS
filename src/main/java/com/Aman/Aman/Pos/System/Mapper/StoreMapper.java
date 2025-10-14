package com.Aman.Aman.Pos.System.Mapper;

import java.time.LocalDate;

import com.Aman.Aman.Pos.System.Payload.dto.StoreDto;
import com.Aman.Aman.Pos.System.model.Store;
import com.Aman.Aman.Pos.System.model.User;

public class StoreMapper {
    public static StoreDto toDto(Store store){
        StoreDto storeDto=new StoreDto();
        storeDto.setId(store.getId());
        storeDto.setBrand(store.getBrand());
        storeDto.setDescription(store.getDescription());
        storeDto.setStoreAdmin(UserMapper.toDto(store.getStoreAdmin()));
        storeDto.setStoreType(store.getStoreType());
        storeDto.setContact(store.getContact());
        storeDto.setStatus(store.getStatus());
        storeDto.setCreatedAt(store.getCreatedAt());
        storeDto.setUpdatedAt(store.getUpdatedAt());
        storeDto.setContact(store.getContact());
        return storeDto;

    }
    public static Store toEntity(StoreDto storeDto, User StoreAdmin){
        Store store=new Store();
        store.setId(storeDto.getId());
        store.setBrand(storeDto.getBrand());
        store.setDescription(storeDto.getDescription());
        store.setStoreAdmin(StoreAdmin);
        store.setStoreType(storeDto.getStoreType());
        store.setContact(storeDto.getContact());
        return store;
    }
}
