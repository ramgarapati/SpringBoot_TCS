package com.ram.tcs.customer.management.service;

import com.ram.tcs.customer.management.entity.Customer;
import com.ram.tcs.customer.management.repository.CustomerRepository;
import com.ram.tcs.customer.management.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Customer customer = new Customer(1L, "Ram G", "ramgarapati9@gmail.com",10.00,"","");
        Customer customer2 = new Customer(2L, "Product 2", "ramg9@gmail.com",20.0,"2025-05-05T00:00:00.000Z",null);
        List<Customer> customerList = Arrays.asList(customer, customer2);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.findById(1L);
        assertEquals("Ram G", result.getName());
        assertEquals("ramgarapati9@gmail.com", result.getEmail());
    }

    @Test
    public void testFindById() {
        Customer customer = new Customer(1L, "Ram G", "ramgarapati9@gmail.com",10.00,"2025-05-05T00:00:00.000Z",null);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.findById(1L);
        assertEquals("Silver", result.getCustomerTier());
    }

    @Test
    public void testFindById_GOLD() {
        Customer customer = new Customer(1L, "Ram G", "ramgarapati9@gmail.com",3000.00,"2025-01-05T00:00:00.000Z","Gold");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.findById(1L);
        assertEquals("Gold", result.getCustomerTier());
    }

    @Test
    public void testFindById_Platinum() {
        Customer customer = new Customer(1L, "Ram G", "ramgarapati9@gmail.com",1000000.00,"2025-05-05T00:00:00.000Z","Platinum");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.findById(1L);
        assertEquals("Platinum", result.getCustomerTier());
    }

    @Test
    public void testFindById_Silver() {
        Customer customer = new Customer(1L, "Ram G", "ramgarapati9@gmail.com",10000.00,"2025-05-05T00:00:00.000Z",null);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.findById(1L);
        assertEquals("Ram G", result.getName());
    }

    @Test
    public void testFindByName() {
        Customer customer = new Customer(1L, "Ram G", "ramgarapati9@gmail.com",10.00,"2025-05-10T00:00:00.000Z",null);
        Customer customer2 = new Customer(2L, "Product 2", "ramg9@gmail.com",20.0,"2025-05-05T00:00:00.000Z",null);
        List<Customer> customerList = Arrays.asList(customer, customer2);
        when(customerRepository.findAll()).thenReturn(customerList);
        Customer result = customerService.findByName("Ram G");
        assertEquals("ramgarapati9@gmail.com", result.getEmail());
    }

    @Test
    public void testFindByEmail() {
        Customer customer = new Customer(1L, "Ram G", "ramgarapati9@gmail.com",10.00,"2025-05-14T00:00:00.000Z",null);
        Customer customer2 = new Customer(2L, "Product 2", "ramg9@gmail.com",20.0,"2025-05-05T00:00:00.000Z",null);
        List<Customer> customerList = Arrays.asList(customer, customer2);
        when(customerRepository.findAll()).thenReturn(customerList);
        Customer result = customerService.findByEmail("ramgarapati9@gmail.com");
        assertEquals("ramgarapati9@gmail.com", result.getEmail());
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer(1L, "Ram G", "ramgarapati9@gmail.com",10.00,"2025-05-14T00:00:00.000Z","");
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.saveCustomer(customer);
        assertEquals("Ram G", result.getName());
    }

    @Test
    public void testUpdateProduct() {
        Customer customer = new Customer(1L, "Ram G", "ramgarapati9@gmail.com",10.00,"2025-05-10T00:00:00.000Z","");
        Customer customer2 = new Customer(2L, "Customer 2", "ramg9@gmail.com",20.0,"2025-05-09T00:00:00.000Z","");


        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer2);

        Customer result = customerService.updateCustomer(customer2,1L);
        assertEquals("Customer 2", result.getName());
        assertEquals(20.0, result.getAnnualSpend());
    }

    @Test
    public void testDeleteProduct() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(new Customer(1L, "Ram G", "ramgarapati9@gmail.com",10.00,"05/14/2025",null)));
        customerService.deleteCustomerById(1L);
    }
}
