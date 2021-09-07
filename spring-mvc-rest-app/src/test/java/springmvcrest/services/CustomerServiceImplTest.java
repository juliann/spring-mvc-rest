package springmvcrest.services;

import com.nadarzy.model.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import springmvcrest.model.Customer;
import springmvcrest.repositiories.CustomerRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static springmvcrest.controllers.v1.CustomerController.BASE_URL;

class CustomerServiceImplTest {

  @Mock CustomerRepository customerRepository;

  CustomerService customerService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    customerService = new CustomerServiceImpl(customerRepository);
  }

  @Test
  public void getAllCustomers() {
    // given
    Customer customer1 = new Customer();
    customer1.setId(1L);
    customer1.setFirstName("Michale");
    customer1.setLastName("Weston");

    Customer customer2 = new Customer();
    customer2.setId(2L);
    customer2.setFirstName("Sam");
    customer2.setLastName("Axe");

    when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

    // when
    List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

    // then
    assertEquals(2, customerDTOS.size());
  }

  @Test
  public void getCustomerById() {
    // given
    Customer customer1 = new Customer();
    customer1.setId(1L);
    customer1.setFirstName("Michale");
    customer1.setLastName("Weston");

    when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(customer1));

    // when
    CustomerDTO customerDTO = customerService.getCustomerById(1L);

    assertEquals("Michale", customerDTO.getFirstName());
  }

  @Test
  public void createNewCustomer() {

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
    assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
    assertEquals(BASE_URL + "/1", savedDto.getCustomerUrl());
  }

  @Test
  public void saveCustomerByDTO() {

    // given
    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setFirstName("Jim");

    Customer savedCustomer = new Customer();
    savedCustomer.setFirstName(customerDTO.getFirstName());
    savedCustomer.setLastName(customerDTO.getLastName());
    savedCustomer.setId(1L);

    when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

    // when
    CustomerDTO savedDto = customerService.saveCustomerByDTO(1L, customerDTO);

    // then
    assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
    assertEquals(BASE_URL + "/1", savedDto.getCustomerUrl());
  }

  @Test
  public void deleteCustomerById() throws Exception {

    Long id = 1L;

    customerRepository.deleteById(id);

    verify(customerRepository, times(1)).deleteById(anyLong());
  }
}
