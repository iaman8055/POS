package com.Aman.Aman.Pos.System.service.impl;

import com.Aman.Aman.Pos.System.Mapper.BranchMapper;
import com.Aman.Aman.Pos.System.Payload.dto.BranchDto;
import com.Aman.Aman.Pos.System.Repository.BranchRepository;
import com.Aman.Aman.Pos.System.Repository.StoreRepository;
import com.Aman.Aman.Pos.System.model.Branch;
import com.Aman.Aman.Pos.System.model.Store;
import com.Aman.Aman.Pos.System.model.User;
import com.Aman.Aman.Pos.System.service.BranchService;
import com.Aman.Aman.Pos.System.service.StoreService;
import com.Aman.Aman.Pos.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;
    @Override
    public BranchDto createBranch(BranchDto branchDto, User user) throws Exception {

        User currentUser= userService.getCurrentUser();
        Store store=storeRepository.findByStoreAdminId(currentUser.getId());

        Branch branch = BranchMapper.toEntity(branchDto,store);
        Branch savedBranch=branchRepository.save(branch);
        return BranchMapper.toDto(savedBranch);
    }

    @Override
    public BranchDto updateBranch(Long id, BranchDto branchDto, User user) throws Exception {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new Exception("Branch not found"));

        branch.setName(branchDto.getName());
        branch.setAddress(branchDto.getAddress());
        branch.setPhone(branchDto.getPhone());
        branch.setEmail(branchDto.getEmail());
        branch.setWorkingDays(branchDto.getWorkingDays());
        branch.setOpenTime(branchDto.getOpenTime());
        branch.setCloseTime(branchDto.getCloseTime());
        branch.setUpdatedAt(LocalDateTime.now());

        Branch updatedBranch=branchRepository.save(branch);
        return BranchMapper.toDto(updatedBranch);
    }

    @Override
    public void deleteBranch(Long id) throws Exception {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new Exception("Branch not found"));

        branchRepository.delete(branch);
    }

    @Override
    public List<BranchDto> getAllBranchesbyStoreId(Long storeId) throws Exception {
        List<Branch>branches=branchRepository.findByStoreId(storeId);
        return branches.stream().map(BranchMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BranchDto getBranchById(Long id) throws Exception {
        Branch branch=branchRepository.findById(id).orElseThrow(
                ()->new Exception("Branch is not found")
        );

        return BranchMapper.toDto(branch);
    }
}
