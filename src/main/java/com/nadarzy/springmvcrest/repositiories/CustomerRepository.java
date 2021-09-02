package com.nadarzy.springmvcrest.repositiories;

import com.nadarzy.springmvcrest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
