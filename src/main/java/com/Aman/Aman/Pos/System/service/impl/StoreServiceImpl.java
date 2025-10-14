package com.Aman.Aman.Pos.System.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Aman.Aman.Pos.System.Mapper.StoreMapper;
import com.Aman.Aman.Pos.System.Payload.dto.StoreDto;
import com.Aman.Aman.Pos.System.Repository.StoreRepository;
import com.Aman.Aman.Pos.System.model.Store;
import com.Aman.Aman.Pos.System.model.StoreContact;
import com.Aman.Aman.Pos.System.model.User;
import com.Aman.Aman.Pos.System.service.StoreService;
import com.Aman.Aman.Pos.System.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final UserService userService;
    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        Store store= StoreMapper.toEntity(storeDto,user);
        return StoreMapper.toDto(storeRepository.save(store));
    }

    @Override
    public StoreDto getStoreById(Long id) throws Exception {

        Store store=storeRepository.findById(id).orElseThrow(()-> new Exception("Store not found"));
        return StoreMapper.toDto(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
       List<Store>dtoes=storeRepository.findAll();
       return dtoes.stream().map(StoreMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() {
        User admin=userService.getCurrentUser();
        return storeRepository.findByStoreAdminId(admin.getId());
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws Exception {
        User currentUser=userService.getCurrentUser();
        Store existing= storeRepository.findByStoreAdminId(currentUser.getId());
        if(existing==null){
            throw new Exception("Store not found");

        }
        existing.setBrand(storeDto.getBrand());
        existing.setDescription(storeDto.getDescription());
        if(storeDto.getStoreType()!=null){
            existing.setStoreType(storeDto.getStoreType());
        }
        if(storeDto.getContact()!=null){
            StoreContact contact=StoreContact.builder()
                    .address(storeDto.getContact().getAddress())
                    .phone(storeDto.getContact().getPhone()).build();
            existing.setContact(contact);
        }
        Store updatedstore=storeRepository.save(existing);
        return StoreMapper.toDto(updatedstore);
    }

    @Override
    public void deleteStore(Long id) {
        User currentUser=userService.getCurrentUser();
        Store store=storeRepository.findByStoreAdminId(currentUser.getId());
        storeRepository.delete(store);

    }

    @Override
    public StoreDto getStoreByEmployee() throws Exception {
        User currentUser=userService.getCurrentUser();
        if(currentUser==null){
            throw new Exception("You don't have the permission to access this store");
        }
        return StoreMapper.toDto(currentUser.getStore());
    }
}
