package springmvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import springmvcrest.api.v1.model.CustomerDTO;
import springmvcrest.model.Customer;

@Mapper
public interface CustomerMapper {

  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

  CustomerDTO customerToCustomerDTO(Customer customer);

  Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
