package com.js.springboothibernate.service;

import com.js.springboothibernate.dto.CustomerData;

import java.util.List;

public interface CustomerService {
    CustomerData saveCustomer(CustomerData customer);
    boolean deleteCustomer(final Long customerId);
    List<CustomerData> getAllCustomers();
    CustomerData getCustomerById(final Long customerId);
}
