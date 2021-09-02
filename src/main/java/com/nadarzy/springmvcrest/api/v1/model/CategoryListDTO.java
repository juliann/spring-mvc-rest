package com.nadarzy.springmvcrest.api.v1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryListDTO {
  private List<CategoryDTO> categories;

  public CategoryListDTO(List<CategoryDTO> allCategories) {
    categories = allCategories;
  }
}
