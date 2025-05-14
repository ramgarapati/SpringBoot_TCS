package com.ram.tcs.customer.management.repository;

import com.ram.tcs.customer.management.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer save(Customer customer);
    List<Customer> findAll();
    void deleteById(Long Id);
}