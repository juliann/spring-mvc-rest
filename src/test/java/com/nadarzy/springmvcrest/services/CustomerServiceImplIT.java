package com.nadarzy.springmvcrest.services;

import com.nadarzy.springmvcrest.api.v1.model.CustomerDTO;
import com.nadarzy.springmvcrest.bootstrap.Bootstrap;
import com.nadarzy.springmvcrest.model.Customer;
import com.nadarzy.springmvcrest.repositiories.CategoryRepository;
import com.nadarzy.springmvcrest.repositiories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CustomerServiceImplIT {

  @Autowired CustomerRepository customerRepository;

  @Autowired CategoryRepository categoryRepository;

  CustomerService customerService;

  @BeforeEach
  public void setUp() throws Exception {
    System.out.println("Loading Customer Data");
    System.out.println(customerRepository.findAll().size());

    // setup data for testing
    Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
    bootstrap.run(); // load data

    customerService = new CustomerServiceImpl(customerRepository);
  }

  @Test
  public void patchCustomerUpdateFirstName() throws Exception {
    String updatedName = "UpdatedName";
    long id = getCustomerIdValue();

    Customer originalCustomer = customerRepository.getOne(id);
    assertNotNull(originalCustomer);
    // save original first name
    String originalFirstName = originalCustomer.getFirstName();
    String originalLastName = originalCustomer.getLastName();

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setFirstName(updatedName);

    customerService.patchCustomer(id, customerDTO);

    Customer updatedCustomer = customerRepository.findById(id).get();

    assertNotNull(updatedCustomer);
    assertEquals(updatedName, updatedCustomer.getFirstName());
    assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
    assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
  }

  @Test
  public void patchCustomerUpdateLastName() throws Exception {
    String updatedName = "UpdatedName";
    long id = getCustomerIdValue();

    Customer originalCustomer = customerRepository.getOne(id);
    assertNotNull(originalCustomer);

    // save original first/last name
    String originalFirstName = originalCustomer.getFirstName();
    String originalLastName = originalCustomer.getLastName();

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setLastName(updatedName);

    customerService.patchCustomer(id, customerDTO);

    Customer updatedCustomer = customerRepository.findById(id).get();

    assertNotNull(updatedCustomer);
    assertEquals(updatedName, updatedCustomer.getLastName());
    assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
    assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
  }

  private Long getCustomerIdValue() {
    List<Customer> customers = customerRepository.findAll();

    System.out.println("Customers Found: " + customers.size());

    // return first id
    return customers.get(0).getId();
  }
}
