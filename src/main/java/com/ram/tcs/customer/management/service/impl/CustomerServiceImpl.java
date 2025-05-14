package com.ram.tcs.customer.management.service.impl;

import com.ram.tcs.customer.management.entity.Customer;
import com.ram.tcs.customer.management.repository.CustomerRepository;
import com.ram.tcs.customer.management.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        UUID uuid = UUID.randomUUID();
        customer.setId(Math.abs(uuid.getMostSignificantBits()));
        return customerRepository.save(customer);
    }

    // Read operation
    @Override
    public Customer findById(Long id)
    {
        Customer cust = customerRepository.findById(id).get();
        if(cust.getAnnualSpend()<1000){
            cust.setCustomerTier("Silver");
        }else if(cust.getAnnualSpend()>=1000 && cust.getAnnualSpend()<10000) {
            cust.setCustomerTier("Gold");
        }else if(cust.getAnnualSpend()>=10000){
            cust.setCustomerTier("Platinum");
        }
        return cust;
    }

    @Override
    public Customer findByName(String name) {
        Iterable<Customer> customerList =
                customerRepository.findAll();
        while (customerList.iterator().hasNext()) {
            Customer customer = customerList.iterator().next();
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public Customer findByEmail(String email) {
        Iterable<Customer>  customerList =
                customerRepository.findAll();
        while (customerList.iterator().hasNext()) {
            Customer customer = customerList.iterator().next();
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;}

    @Override
    public Customer
    updateCustomer(Customer customer,
                     Long id) {
        Customer depDB
                = customerRepository.findById(id)
                .get();

        if (Objects.nonNull(customer.getName())
                && !"".equalsIgnoreCase(
                customer.getName()) && !depDB.getName().equalsIgnoreCase(customer.getName())) {
            depDB.setName(
                    customer.getName());
        }

        if (Objects.nonNull(customer.getEmail())
                && !"".equalsIgnoreCase(
                customer.getEmail()) && !depDB.getEmail().equalsIgnoreCase(customer.getEmail())) {
            depDB.setEmail(
                    customer.getEmail());
        }

        depDB.setAnnualSpend(
            customer.getAnnualSpend());

        if (Objects.nonNull(customer.getLastPurchaseDate())
                && !"".equalsIgnoreCase(
                customer.getLastPurchaseDate()) && !depDB.getLastPurchaseDate().equalsIgnoreCase(customer.getLastPurchaseDate())) {
            depDB.setLastPurchaseDate(
                    customer.getLastPurchaseDate());
        }

        return customerRepository.save(depDB);
    }

    // Delete operation
    @Override
    public void deleteCustomerById(Long departmentId)
    {
        customerRepository.deleteById(departmentId);
    }

}