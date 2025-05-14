package com.ram.tcs.customer.management.controller;

import com.ram.tcs.customer.management.entity.Customer;
import com.ram.tcs.customer.management.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerManagementController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/createCustomer")
    public Customer saveCustomer(
            @Valid @RequestBody Customer customer, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new RuntimeException("create customer failed");
        }
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/customers/{id}")
    public Customer findById(@PathVariable("id") Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/customers")
    public Customer findByName(@RequestParam(required = false) String name,@RequestParam(required=false)String email) {
        if(name!=null) {
            return customerService.findByName(name);
        }
        if(email!=null){
            return customerService.findByEmail(email);
        }
        return null;
    }


    @PutMapping("/updateCustomer/{id}")
    public Customer
    updateCustomer(@RequestBody Customer customer,
                     @PathVariable("id") Long id) {
        return customerService.updateCustomer(
                customer, id);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomerById(@PathVariable("id")
                                               Long id) {
        customerService.deleteCustomerById(
                id);
        return "Deleted Successfully";
    }
}