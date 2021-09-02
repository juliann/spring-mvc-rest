package com.nadarzy.springmvcrest.services;

import com.nadarzy.springmvcrest.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
  List<CategoryDTO> getAllCategories();

  CategoryDTO getCategoryByName(String name);
}
