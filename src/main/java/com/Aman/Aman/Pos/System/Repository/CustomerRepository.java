package com.Aman.Aman.Pos.System.Repository;

import com.Aman.Aman.Pos.System.Payload.dto.CustomerDto;
import com.Aman.Aman.Pos.System.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String fullName,String email);
}
