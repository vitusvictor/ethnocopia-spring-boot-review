package com.ethnocopia.service;

import com.ethnocopia.dto.NewCustomerRequest;
import com.ethnocopia.dto.Response;
import com.ethnocopia.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();

    ResponseEntity<Response> addCustomer(NewCustomerRequest request);

    ResponseEntity<Response> deleteCustomer(Integer id);

    ResponseEntity<Response> updateCustomer(Integer id, NewCustomerRequest request);
}
