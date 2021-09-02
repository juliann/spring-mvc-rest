package com.nadarzy.springmvcrest.services;

import com.nadarzy.springmvcrest.api.v1.mapper.CustomerMapper;
import com.nadarzy.springmvcrest.api.v1.model.CustomerDTO;
import com.nadarzy.springmvcrest.repositiories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public List<CustomerDTO> getAllCustomers() {
    return customerRepository.findAll().stream()
        .map(
            customer -> {
              CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
              customerDTO.setCustomerUrl("/api/v1/customer/" + customerDTO.getId());
              return customerDTO;
            })
        .collect(Collectors.toList());
  }

  @Override
  public CustomerDTO getCustomerById(Long id) {
    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customerRepository.getById(id));
    customerDTO.setCustomerUrl("/api/v1/customer/" + customerDTO.getId());
    return customerDTO;
  }
}
