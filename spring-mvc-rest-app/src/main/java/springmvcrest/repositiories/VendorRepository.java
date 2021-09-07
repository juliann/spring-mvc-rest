package springmvcrest.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import springmvcrest.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
  Vendor findByName(String name);
}
