package com.ethnocopia;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1/customers")
public class Main {
    public static  void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //
//    @GetMapping("/")
//    public GreetResponse greet() {
//        GreetResponse greetResponse = new GreetResponse("hello", List.of("Java", "Golang", "JavaScript"), new Person("Alex", 28, 30_000));
//        return greetResponse;
//    }
//
//    record Person(String name, int age, double savings) {};
//
//    record GreetResponse(String greet, List<String> favProgrammingLanguages, Person person) {};
    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(String name, String email, int age) {}

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);

        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
        Optional<Customer> customerWrapper = customerRepository.findById(id);
        if (!customerWrapper.isPresent())
            return;

        Customer customer = customerWrapper.get();
        customer.setName(request.name);
        customer.setAge(request.age);
        customer.setEmail(request.email);

        customerRepository.save(customer);
    }
}
