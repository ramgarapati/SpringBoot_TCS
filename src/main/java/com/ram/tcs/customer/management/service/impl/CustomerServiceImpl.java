package com.ram.tcs.customer.management.service.impl;

import com.ram.tcs.customer.management.entity.Customer;
import com.ram.tcs.customer.management.repository.CustomerRepository;
import com.ram.tcs.customer.management.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        if(getLastPurchasedDate(customer.getLastPurchaseDate())!= null){
            customer.setLastPurchaseDate(getLastPurchasedDate(customer.getLastPurchaseDate()));
        }
        return customerRepository.save(customer);
    }

    // Read operation
    @Override
    public Customer findById(Long id)
    {
        Customer cust = customerRepository.findById(id).get();
        String purchasedDate = cust.getLastPurchaseDate();

        if (purchasedDate != null) {
            try {
                OffsetDateTime dateTime = OffsetDateTime.parse(purchasedDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
               if(cust.getAnnualSpend()<1000 ){
                    cust.setCustomerTier("Silver");
                }else if(cust.getAnnualSpend()>=1000 && cust.getAnnualSpend()<10000 && dateTime.isBefore(OffsetDateTime.now().minusYears(1))) {
                    cust.setCustomerTier("Gold");
                }else if(cust.getAnnualSpend()>=10000 && dateTime.isBefore(OffsetDateTime.now().minusMonths(6))){
                    cust.setCustomerTier("Platinum");
                }
            } catch (DateTimeParseException e) {
                e.printStackTrace();
                log.error("Invalid date format: {}", purchasedDate);
            }
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
                customer.getLastPurchaseDate()) && !depDB.getLastPurchaseDate().equalsIgnoreCase(customer.getLastPurchaseDate()) && getLastPurchasedDate(customer.getLastPurchaseDate()) != null) {
            depDB.setLastPurchaseDate(
                    getLastPurchasedDate(customer.getLastPurchaseDate()));
        }

        return customerRepository.save(depDB);
    }

    // Delete operation
    @Override
    public String deleteCustomerById(Long id) {
        if(customerRepository.findById(id).isPresent()){
            customerRepository.deleteById(id);
            return "Deleted Successfully";
        }else{
        return "Customer ID was not found try with existing customer ID";
        }
    }

    private String getLastPurchasedDate(String lastPurchaseDate) {
        String formattedDate = null;
        try {
            lastPurchaseDate = lastPurchaseDate+"T00:00:00.000Z";
            LocalDate localDate = LocalDate.parse(lastPurchaseDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            log.info("Parsed date: {}", localDate);
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            formattedDate = localDate.atStartOfDay().atOffset(ZoneOffset.UTC).format(formatter2);
            log.info("Formatted date: {}", formattedDate);
            return formattedDate;
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            log.error("Error parsing DateTime string: " + e.getMessage());
        }
        return formattedDate;
    }

}