package springmvcrest.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import springmvcrest.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
