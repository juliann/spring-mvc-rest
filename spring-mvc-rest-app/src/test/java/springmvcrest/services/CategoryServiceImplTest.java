package springmvcrest.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import springmvcrest.api.v1.model.CategoryDTO;
import springmvcrest.model.Category;
import springmvcrest.repositiories.CategoryRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

  private static final Long ID = 2L;
  private static final String NAME = "Jim";

  @Mock CategoryRepository categoryRepository;

  CategoryService categoryService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    categoryService = new CategoryServiceImpl(categoryRepository);
  }

  @Test
  void getAllCategories() {
    List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
    when(categoryRepository.findAll()).thenReturn(categories);

    List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

    Assertions.assertEquals(3, categoryDTOS.size());
  }

  @Test
  void getCategoryByName() {
    Category category = new Category();
    category.setId(ID);
    category.setName(NAME);

    when(categoryRepository.findByName(anyString())).thenReturn(category);
    CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

    Assertions.assertEquals(ID, categoryDTO.getId());
    Assertions.assertEquals(NAME, categoryDTO.getName());
  }
}
