package com.nadarzy.springmvcrest.api.v1.mapper;

import com.nadarzy.springmvcrest.api.v1.model.CategoryDTO;
import com.nadarzy.springmvcrest.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
  CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  CategoryDTO categoryToCategoryDTO(Category category);
}
