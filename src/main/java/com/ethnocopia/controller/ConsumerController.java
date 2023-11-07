package com.ethnocopia.controller;

import com.ethnocopia.dto.NewCustomerRequest;
import com.ethnocopia.dto.Response;
import com.ethnocopia.model.Customer;
import com.ethnocopia.repository.CustomerRepository;
import com.ethnocopia.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
@Validated
public class ConsumerController {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    @GetMapping("/get-customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("/add-customer")
    public ResponseEntity<Response> addCustomer(@RequestBody NewCustomerRequest request) {
        return customerService.addCustomer(request);
    }

    @DeleteMapping("/delete-customer/{customerId}")
    public ResponseEntity<Response> deleteCustomer(@PathVariable("customerId") Integer id) {
        return customerService.deleteCustomer(id);
    }

    @PutMapping("/update-customer/{customerId}")
    public ResponseEntity<Response> updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
        return customerService.updateCustomer(id, request);
    }
}
