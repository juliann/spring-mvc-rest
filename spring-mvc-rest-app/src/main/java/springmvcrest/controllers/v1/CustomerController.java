package springmvcrest.controllers.v1;

import com.nadarzy.model.CustomerDTO;
import com.nadarzy.model.CustomerListDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springmvcrest.services.CustomerService;

@Api(
    value = "",
    tags = {"This is my Customer Controller"})
@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

  public static final String BASE_URL = "/api/v1/customers";
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @ApiOperation(value = "This will get a list of all Customers", notes = "These are notes")
  @GetMapping({"", "/"})
  public ResponseEntity<CustomerListDTO> getAllCustomers() {
    CustomerListDTO customerListDTO = new CustomerListDTO();
    customerListDTO.getCustomers().addAll(customerService.getAllCustomers());
    return new ResponseEntity<>((customerListDTO), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
    return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
    return new ResponseEntity<>(customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerDTO> updateCustomer(
      @RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
    return new ResponseEntity<>(customerService.saveCustomerByDTO(id, customerDTO), HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CustomerDTO> patchCustomer(
      @RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
    return new ResponseEntity<>(customerService.patchCustomer(id, customerDTO), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomerById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
