package com.Aman.Aman.Pos.System.service;

import com.Aman.Aman.Pos.System.Payload.dto.CustomerDto;
import com.Aman.Aman.Pos.System.model.Customer;

import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(Long id,CustomerDto customerDto) throws Exception;
    void deleteCustomer(Long id) throws Exception;
    CustomerDto getCustomerbyId(Long id) throws Exception;
    List<Customer>getAllCustomers();
    List<Customer>getAllCustomerByKeyword(String keyword) throws Exception;
}
