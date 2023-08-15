package com.js.springboothibernate.controller;

import com.js.springboothibernate.dto.CustomerData;
import com.js.springboothibernate.service.CustomerService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Resource(name= "customerService")
    private CustomerService customerService;

    /**
     * Get all customer data in the system.
     * @return List<CustomerData>
     */
    @GetMapping
    public List<CustomerData> getCustomers() {
        return customerService.getAllCustomers();
    }

    /**
     * Method to get the customer data based on the customer Id.
     * @param id
     * @return CustomerData
     */
    @GetMapping("/customer/{id}")
    public CustomerData getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    /**
     * Post request to create cystomer information in the system.
     * @param customerData
     * @return CustomerData
     */
    @PostMapping("/customer")
    public CustomerData saveCustomer(final @RequestBody CustomerData customerData) {
        return customerService.saveCustomer(customerData);
    }

    /**
     * Delete customer from the system based on the customer Id.
     * @param id
     * @return Boolean flag indicating request status
     */
    @DeleteMapping("/customer/{id}")
    public Boolean deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }
}
