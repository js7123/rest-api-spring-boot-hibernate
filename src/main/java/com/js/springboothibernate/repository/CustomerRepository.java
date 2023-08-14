package com.js.springboothibernate.repository;

import com.js.springboothibernate.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
