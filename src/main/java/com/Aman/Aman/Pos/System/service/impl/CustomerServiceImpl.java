package com.Aman.Aman.Pos.System.service.impl;

import com.Aman.Aman.Pos.System.Mapper.CustomerMapper;
import com.Aman.Aman.Pos.System.Payload.dto.CustomerDto;
import com.Aman.Aman.Pos.System.Repository.CustomerRepository;
import com.Aman.Aman.Pos.System.model.Customer;
import com.Aman.Aman.Pos.System.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    // ✅ Create new customer
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.toEntity(customerDto);
        Customer saved = customerRepository.save(customer);
        return CustomerMapper.toDto(saved);
    }

    // ✅ Update existing customer
    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) throws Exception {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new Exception("Customer not found with ID: " + id));

        existing.setFirstName(customerDto.getFirstName());
        existing.setEmail(customerDto.getEmail());
        existing.setPhone(customerDto.getPhone());

        Customer updated = customerRepository.save(existing);
        return CustomerMapper.toDto(updated);
    }

    // ✅ Delete a customer
    @Override
    public void deleteCustomer(Long id) throws Exception {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new Exception("Customer not found with ID: " + id));
        customerRepository.delete(customer);
    }

    // ✅ Get customer by ID
    @Override
    public CustomerDto getCustomerbyId(Long id) throws Exception {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new Exception("Customer not found with ID: " + id));
        return CustomerMapper.toDto(customer);
    }

    // ✅ Get all customers
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // ✅ Search customers by keyword (name, email, or phone)
    @Override
    public List<Customer> getAllCustomerByKeyword(String keyword) throws Exception {
        List<Customer> customers = customerRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                keyword, keyword
        );
        if (customers.isEmpty()) {
            throw new Exception("No customers found matching keyword: " + keyword);
        }
        return customers;
    }
}
