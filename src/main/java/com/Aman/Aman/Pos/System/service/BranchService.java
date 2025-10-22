package com.Aman.Aman.Pos.System.service;

import com.Aman.Aman.Pos.System.Payload.dto.BranchDto;
import com.Aman.Aman.Pos.System.model.User;

import java.util.List;

public interface BranchService {
    BranchDto createBranch(BranchDto branchDto, User user) throws Exception;

    BranchDto updateBranch(Long id, BranchDto branchDto, User user) throws Exception;

    void deleteBranch(Long id) throws Exception;

    List<BranchDto> getAllBranchesbyStoreId(Long storeId) throws Exception;

    BranchDto getBranchById(Long id) throws Exception;
}
