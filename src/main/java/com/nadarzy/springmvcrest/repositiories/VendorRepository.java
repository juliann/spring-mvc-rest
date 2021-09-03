package com.nadarzy.springmvcrest.repositiories;

import com.nadarzy.springmvcrest.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
  Vendor findByName(String name);
}
