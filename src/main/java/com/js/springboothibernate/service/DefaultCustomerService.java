package com.js.springboothibernate.service;

import com.js.springboothibernate.commons.I18Constants;
import com.js.springboothibernate.data.Customer;
import com.js.springboothibernate.dto.CustomerData;
import com.js.springboothibernate.exception.NoSuchElementFoundException;
import com.js.springboothibernate.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service("customerService")
@AllArgsConstructor
public class DefaultCustomerService implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    private final MessageSource messageSource;

    /**
     * Create a customer based on the data sent to the service class.
     * @param customer
     * @return DTO representation of the customer
     */
    @Override
    public CustomerData saveCustomer(CustomerData customer) {
        Customer customerModel = populateCustomerEntity(customer);
        return populateCustomerData(customerRepository.save(customerModel));
    }

    /**
     * Delete customer based on the customer Id.
     * @param customerId
     * @return boolean flag indicating request status
     */
    @Override
    public boolean deleteCustomer(Long customerId) {
        getCustomerById(customerId); // validates customerId is present in the database.
        customerRepository.deleteById(customerId);
        return true;
    }

    /**
     * Method to return list of all available customers in the system.
     * @return List<CustomerData>
     */
    @Override
    public List<CustomerData> getAllCustomers() {
        List<CustomerData> customers = new ArrayList<>();
        List<Customer> customerList = customerRepository.findAll();
        customerList.forEach(customer -> {
            customers.add(populateCustomerData(customer));
        });
        return customers;
    }

    /**
     * Get customer by Id.
     * @param customerId
     * @return CustomerData
     */
    @Override
    public CustomerData getCustomerById(Long customerId) {
        return populateCustomerData( customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementFoundException(getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(customerId)))));
    }

    /**
     * Utility method to convert Customer JPA entity to DTO object for frontend data.
     * @param customer
     * @return CustomerData
     */
    private CustomerData populateCustomerData(final Customer customer) {
        CustomerData customerData = new CustomerData();
        customerData.setId(customer.getId());
        customerData.setFirstName(customer.getFirstName());
        customerData.setLastName(customer.getLastName());
        customerData.setEmail(customer.getEmail());
        return customerData;
    }

    /**
     * Utility method to map the frontend customer object to the JPA customer entity.
     * @param customerData
     * @return Customer
     */
    private Customer populateCustomerEntity(CustomerData customerData) {
        Customer customer = new Customer();
        customer.setFirstName(customerData.getFirstName());
        customer.setLastName(customerData.getLastName());
        customer.setEmail(customerData.getEmail());
        return customer;
    }

    /**
     * Utility to resolve a message with support for parameterization and internationalization.
     * @param key
     * @param params
     * @return
     */
    private String getLocalMessage(String key, String... params) {
        return messageSource.getMessage(key, params, Locale.ENGLISH);
    }
}
