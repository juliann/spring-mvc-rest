package com.nadarzy.springmvcrest.services;

import com.nadarzy.springmvcrest.api.v1.model.CustomerDTO;
import com.nadarzy.springmvcrest.model.Customer;
import com.nadarzy.springmvcrest.repositiories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static com.nadarzy.springmvcrest.controllers.v1.CustomerController.BASE_URL;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

  @Mock CustomerRepository customerRepository;

  CustomerService customerService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    customerService = new CustomerServiceImpl(customerRepository);
  }

  @Test
  public void getAllCustomers() throws Exception {
    // given
    Customer customer1 = new Customer();
    customer1.setId(1l);
    customer1.setFirstName("Michale");
    customer1.setLastName("Weston");

    Customer customer2 = new Customer();
    customer2.setId(2l);
    customer2.setFirstName("Sam");
    customer2.setLastName("Axe");

    when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

    // when
    List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

    // then
    Assertions.assertEquals(2, customerDTOS.size());
  }

  @Test
  public void getCustomerById() throws Exception {
    // given
    Customer customer1 = new Customer();
    customer1.setId(1L);
    customer1.setFirstName("Michale");
    customer1.setLastName("Weston");

    when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(customer1));

    // when
    CustomerDTO customerDTO = customerService.getCustomerById(1L);

    Assertions.assertEquals("Michale", customerDTO.getFirstName());
  }

  @Test
  public void createNewCustomer() throws Exception {

    // given
    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setFirstName("Jim");

    Customer savedCustomer = new Customer();
    savedCustomer.setFirstName(customerDTO.getFirstName());
    savedCustomer.setLastName(customerDTO.getLastName());
    savedCustomer.setId(1L);

    when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

    // when
    CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

    // then
    Assertions.assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
    Assertions.assertEquals(BASE_URL + "/1", savedDto.getCustomerUrl());
  }

  //  @Test
  //  public void saveCustomerByDTO() throws Exception {
  //
  //    // given
  //    CustomerDTO customerDTO = new CustomerDTO();
  //    customerDTO.setFirstname("Jim");
  //
  //    Customer savedCustomer = new Customer();
  //    savedCustomer.setFirstname(customerDTO.getFirstname());
  //    savedCustomer.setLastname(customerDTO.getLastname());
  //    savedCustomer.setId(1l);
  //
  //    when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
  //
  //    // when
  //    CustomerDTO savedDto = customerService.saveCustomerByDTO(1L, customerDTO);
  //
  //    // then
  //    assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
  //    assertEquals("/api/v1/customers/1", savedDto.getCustomerUrl());
  //  }

  //  @Test
  //  public void deleteCustomerById() throws Exception {
  //
  //    Long id = 1L;
  //
  //    customerRepository.deleteById(id);
  //
  //    verify(customerRepository, times(1)).deleteById(anyLong());
  //  }

}
