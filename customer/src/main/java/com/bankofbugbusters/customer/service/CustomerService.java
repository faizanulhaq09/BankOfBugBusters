package com.bankofbugbusters.customer.service;

import com.bankofbugbusters.customer.dto.CreateCustomerRequest;
import com.bankofbugbusters.customer.dto.CustomerResponse;

import java.util.List;

public interface  CustomerService {
    CustomerResponse createCustomer(CreateCustomerRequest request);
    CustomerResponse getCustomerById(Long id);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse updateCustomer(Long id, CreateCustomerRequest request);
    void deleteCustomer(Long id);


}
