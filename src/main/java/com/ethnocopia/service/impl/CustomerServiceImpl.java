package com.ethnocopia.service.impl;

import com.ethnocopia.dto.NewCustomerRequest;
import com.ethnocopia.dto.Response;
import com.ethnocopia.model.Constants;
import com.ethnocopia.model.Customer;
import com.ethnocopia.repository.CustomerRepository;
import com.ethnocopia.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public ResponseEntity<Response> addCustomer(NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        customerRepository.save(customer);

        return new ResponseEntity<>(Constants.successResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> deleteCustomer(Integer id) {
        Optional<Customer> customerWrapper = customerRepository.findById(id);
        Response response;
        if (customerWrapper.isEmpty()) {
            return new ResponseEntity<>(Constants.notFound, HttpStatus.NOT_FOUND);
        }

        customerRepository.deleteById(id);
        return new ResponseEntity<>(Constants.successResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> updateCustomer(Integer id, NewCustomerRequest request) {
        Optional<Customer> customerWrapper = customerRepository.findById(id);

        if (customerWrapper.isEmpty()) {
            return new ResponseEntity<>(Constants.successResponse, HttpStatus.NOT_FOUND);
        }
        Customer customer = customerWrapper.get();
        customer.setName(request.name());
        customer.setAge(request.age());
        customer.setEmail(request.email());

        customerRepository.save(customer);

        return new ResponseEntity<>(Constants.successResponse, HttpStatus.OK);
    }
}
