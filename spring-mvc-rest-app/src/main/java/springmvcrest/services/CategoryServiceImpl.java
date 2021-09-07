package springmvcrest.services;

import org.springframework.stereotype.Service;
import springmvcrest.api.v1.mapper.CategoryMapper;
import springmvcrest.api.v1.model.CategoryDTO;
import springmvcrest.repositiories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository) {

    this.categoryRepository = categoryRepository;
  }

  @Override
  public List<CategoryDTO> getAllCategories() {
    return categoryRepository.findAll().stream()
        .map(categoryMapper::categoryToCategoryDTO)
        .collect(Collectors.toList());
  }

  @Override
  public CategoryDTO getCategoryByName(String name) {
    return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
  }
}
