package com.nadarzy.springmvcrest.api.v1.mapper;

import com.nadarzy.springmvcrest.api.v1.model.CategoryDTO;
import com.nadarzy.springmvcrest.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
