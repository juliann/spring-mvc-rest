package com.nadarzy.springmvcrest.bootstrap;

import com.nadarzy.springmvcrest.model.Category;
import com.nadarzy.springmvcrest.model.Customer;
import com.nadarzy.springmvcrest.repositiories.CategoryRepository;
import com.nadarzy.springmvcrest.repositiories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
  private final CategoryRepository categoryRepository;
  private final CustomerRepository customerRepository;

  public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
    this.categoryRepository = categoryRepository;
    this.customerRepository = customerRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    loadCategories();
    loadCustomers();
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
