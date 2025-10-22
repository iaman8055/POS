package com.Aman.Aman.Pos.System.Mapper;

import java.time.LocalDateTime;

import com.Aman.Aman.Pos.System.Payload.dto.BranchDto;
import com.Aman.Aman.Pos.System.model.Branch;
import com.Aman.Aman.Pos.System.model.Store;

public class BranchMapper {

    // Convert Entity → DTO
    public static BranchDto toDto(Branch branch) {
        if (branch == null) return null;

        return BranchDto.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .email(branch.getEmail())
                .workingDays(branch.getWorkingDays())
                .openTime(branch.getOpenTime())
                .closeTime(branch.getCloseTime())
                .createdAt(branch.getCreatedAt())
                .updatedAt(branch.getUpdatedAt())
                .StoreId(branch.getStore() != null ? branch.getStore().getId() : null)
                .build();
    }

    // Convert DTO → Entity
    public static Branch toEntity(BranchDto branchDto, Store store) {
        if (branchDto == null) return null;

        return Branch.builder()
                .id(branchDto.getId())
                .name(branchDto.getName())
                .address(branchDto.getAddress())
                .phone(branchDto.getPhone())
                .email(branchDto.getEmail())
                .workingDays(branchDto.getWorkingDays())
                .openTime(branchDto.getOpenTime())
                .closeTime(branchDto.getCloseTime())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .store(store)
                .build();
    }
}
