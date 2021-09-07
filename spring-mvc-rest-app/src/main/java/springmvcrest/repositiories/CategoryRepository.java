package springmvcrest.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import springmvcrest.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Category findByName(String name);
}
