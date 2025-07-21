package com.bankofbugbusters.customer.mapper;

import com.bankofbugbusters.customer.dto.CreateCustomerRequest;
import com.bankofbugbusters.customer.dto.CustomerResponse;
import com.bankofbugbusters.customer.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toEntity(CreateCustomerRequest request) {
        return new Customer(
                null,
                request.email(),
                request.fullName(),
                request.phoneNumber(),
                request.address(),
                request.nationalId()
        );
    }

    public  CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getNationalId()
        );
    }
}
