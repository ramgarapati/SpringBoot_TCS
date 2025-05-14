package com.ram.tcs.customer.management.controller;


import com.ram.tcs.customer.management.entity.Customer;
import com.ram.tcs.customer.management.repository.CustomerRepository;
import com.ram.tcs.customer.management.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerManagementControllerTest {
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerManagementController customerManagementController;

    @Mock
    BindingResult bindingResult;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Customer customer = new Customer(1L, "Ram G", "ramgarapati9@gmail.com",10.00,"","");
        when(customerService.findById(any())).thenReturn(customer);

        Customer result = customerManagementController.findById(1L);
        assertEquals("Ram G", result.getName());
        assertEquals("ramgarapati9@gmail.com", result.getEmail());
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = new Customer(1L, "Ram G", "ramgarapati9@gmail.com",10.00,"05/14/2025",null);
        when(customerService.findById(any())).thenReturn(customer);

        Customer result = customerManagementController.findById(1L);
        assertEquals("Ram G", result.getName());
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer(1L, "Ram G", "ramgarapati9@gmail.com",10.00,"05/14/2025","");
        when(customerService.saveCustomer(customer)).thenReturn(customer);
        when(bindingResult.hasErrors()).thenReturn(false);
        Customer result = customerManagementController.saveCustomer(customer,bindingResult);
        assertEquals("Ram G", result.getName());
    }

    @Test
    public void testSaveCustomer_BindException() {
        Customer customer = new Customer(1L, "Ram G", "ramgarapati9@gmail.com",10.00,"05/14/2025","");
        when(bindingResult.hasErrors()).thenReturn(true);
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,() -> customerManagementController.saveCustomer(customer,bindingResult));
        assertNotNull("runtime exception",runtimeException.getMessage() );
    }

    @Test
    public void testUpdateProduct() {
        Customer customer2 = new Customer(2L, "Customer 2", "ramg9@gmail.com",20.0,"05/14/2025","");
        customerManagementController.updateCustomer(customer2,1L);
    }


    @Test
    public void testDeleteProduct2() {
        customerManagementController.deleteCustomerById(1L);
    }
}
