package springmvcrest.services;

import com.nadarzy.model.CustomerDTO;
import org.springframework.stereotype.Service;
import springmvcrest.api.v1.mapper.CustomerMapper;
import springmvcrest.controllers.v1.CustomerController;
import springmvcrest.exceptions.ResourceNotFoundException;
import springmvcrest.model.Customer;
import springmvcrest.repositiories.CustomerRepository;

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
              customerDTO.setCustomerUrl(CustomerController.BASE_URL + customerDTO.getId());
              return customerDTO;
            })
        .collect(Collectors.toList());
  }

  @Override
  public CustomerDTO getCustomerById(Long id) {
    return customerRepository
        .findById(id)
        .map(customerMapper::customerToCustomerDTO)
        .map(
            customerDTO -> {
              customerDTO.setCustomerUrl(CustomerController.BASE_URL + "/" + customerDTO.getId());
              return customerDTO;
            })
        .orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
    //    Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
    //    Customer savedCustomer = customerRepository.save(customer);
    //    CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
    //    returnDTO.setCustomerUrl(CustomerController.BASE_URL + returnDTO.getId());
    //      return returnDTO;
    return saveAndReturnCustomerDto(customerMapper.customerDTOToCustomer(customerDTO));
  }

  private CustomerDTO saveAndReturnCustomerDto(Customer customer) {
    Customer savedCustomer = customerRepository.save(customer);
    CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
    returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/" + savedCustomer.getId());
    return returnDTO;
  }

  @Override
  public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
    Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
    customer.setId(id);
    return saveAndReturnCustomerDto(customer);
  }

  @Override
  public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
    return customerRepository
        .findById(id)
        .map(
            customer -> {
              if (customerDTO.getFirstName() != null) {
                customer.setFirstName(customerDTO.getFirstName());
              }
              if (customerDTO.getLastName() != null) {
                customer.setLastName(customerDTO.getLastName());
              }

              CustomerDTO customerDTO1 =
                  customerMapper.customerToCustomerDTO(customerRepository.save(customer));
              customerDTO1.setCustomerUrl(CustomerController.BASE_URL + "/" + id);
              return customerDTO1;
            })
        .orElseThrow(RuntimeException::new);
  }

  @Override
  public void deleteCustomerById(Long id) {
    customerRepository.deleteById(id);
  }
}
