package springmvcrest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springmvcrest.model.Category;
import springmvcrest.model.Customer;
import springmvcrest.model.Vendor;
import springmvcrest.repositiories.CategoryRepository;
import springmvcrest.repositiories.CustomerRepository;
import springmvcrest.repositiories.VendorRepository;

@Component
public class Bootstrap implements CommandLineRunner {
  private final CategoryRepository categoryRepository;
  private final CustomerRepository customerRepository;
  private final VendorRepository vendorRepository;

  public Bootstrap(
      CategoryRepository categoryRepository,
      CustomerRepository customerRepository,
      VendorRepository vendorRepository) {
    this.categoryRepository = categoryRepository;
    this.customerRepository = customerRepository;
    this.vendorRepository = vendorRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    loadCategories();
    loadCustomers();
    loadVendors();
  }

  private void loadVendors() {
    Vendor vendor1 = new Vendor();
    vendor1.setName("Vendor 1");
    vendorRepository.save(vendor1);

    Vendor vendor2 = new Vendor();
    vendor2.setName("Vendor 2");
    vendorRepository.save(vendor2);
  }

  private void loadCustomers() {

    Customer customer1 = new Customer();
    customer1.setFirstName("Max");
    customer1.setLastName("Mustermann");
    customerRepository.save(customer1);

    Customer customer2 = new Customer();
    customer2.setFirstName("Tora");
    customer2.setLastName("Hums");
    customerRepository.save(customer2);

    System.out.println("Customers loaded: " + customerRepository.count());
  }

  private void loadCategories() {
    Category fruits = new Category();
    fruits.setName("Fruits");

    Category dried = new Category();
    dried.setName("Dried");

    Category fresh = new Category();
    fresh.setName("Fresh");

    Category exotic = new Category();
    exotic.setName("Exotic");

    Category nuts = new Category();
    nuts.setName("Nuts");

    categoryRepository.save(fruits);
    categoryRepository.save(dried);
    categoryRepository.save(fresh);
    categoryRepository.save(exotic);
    categoryRepository.save(nuts);

    System.out.println("Categories loaded: " + categoryRepository.count());
  }
}
