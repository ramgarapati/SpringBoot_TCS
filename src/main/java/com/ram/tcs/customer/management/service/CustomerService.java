package com.ram.tcs.customer.management.service;

import com.ram.tcs.customer.management.entity.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    Customer findById(Long id);
    Customer findByName(String name);
    Customer findByEmail(String email);

    Customer updateCustomer(Customer department,
                                Long departmentId);

    void deleteCustomerById(Long departmentId);
}