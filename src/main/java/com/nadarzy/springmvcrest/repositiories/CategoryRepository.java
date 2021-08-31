package com.nadarzy.springmvcrest.repositiories;

import com.nadarzy.springmvcrest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
