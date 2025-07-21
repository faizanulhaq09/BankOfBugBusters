package com.bankofbugbusters.customer.service;

import com.bankofbugbusters.customer.dto.CreateCustomerRequest;
import com.bankofbugbusters.customer.dto.CustomerResponse;
import com.bankofbugbusters.customer.entity.Customer;
import com.bankofbugbusters.customer.exception.ResourceNotFoundException;
import com.bankofbugbusters.customer.mapper.CustomerMapper;
import com.bankofbugbusters.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);


    public CustomerServiceImpl(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        log.info("Creating new customer: {}", request);
        Customer customer = mapper.toEntity(request);
        Customer saved = repository.save(customer);
        log.debug("Saved customer: {}", saved);
        return mapper.toResponse(saved);
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        log.info("Fetching customer with ID: {}", id);

        Customer customer = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Customer not found with ID: {}", id);
                    return new ResourceNotFoundException("Customer not found with id: " + id);
                });

        log.debug("Fetched customer: {}", customer);
        return mapper.toResponse(customer);
    }


    @Override
    public List<CustomerResponse> getAllCustomers(){
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CreateCustomerRequest request) {
        log.info("Updating customer with ID: {}", id);

        Customer customer = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Customer not found for update, ID: {}", id);
                    return new ResourceNotFoundException("Customer not found with id: " + id);
                });

        customer.setFullName(request.fullName());
        customer.setEmail(request.email());
        customer.setPhoneNumber(request.phoneNumber());
        customer.setAddress(request.address());
        customer.setNationalId(request.nationalId());

        Customer updated = repository.save(customer);

        log.debug("Updated customer details: {}", updated);
        return mapper.toResponse(updated);
    }


    @Override
    public void deleteCustomer(Long id) {
        log.info("Deleting customer with ID: {}", id);

        Customer customer = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Customer not found for delete, ID: {}", id);
                    return new ResourceNotFoundException("Customer not found with id: " + id);
                });

        repository.delete(customer);
        log.info("Deleted customer with ID: {}", id);
    }


}
