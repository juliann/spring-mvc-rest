package springmvcrest.api.v1.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springmvcrest.api.v1.model.CategoryDTO;
import springmvcrest.model.Category;

class CategoryMapperTest {

  public static final String NAME = "joe";
  public static final Long ID = 1L;
  CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

  @BeforeEach
  void setUp() {}

  @Test
  void categoryToCategoryDTO() {
    // given
    Category category = new Category();
    category.setName(NAME);
    category.setId(ID);
    // when
    CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
    // then
    Assertions.assertEquals(ID, categoryDTO.getId());
    Assertions.assertEquals(NAME, categoryDTO.getName());
  }
}
