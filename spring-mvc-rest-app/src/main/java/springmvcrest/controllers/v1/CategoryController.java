package springmvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springmvcrest.api.v1.model.CategoryDTO;
import springmvcrest.api.v1.model.CategoryListDTO;
import springmvcrest.services.CategoryService;

import static springmvcrest.controllers.v1.CategoryController.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class CategoryController {
  public static final String BASE_URL = "/api/v1/categories/";
  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping({"", "/"})
  @ResponseStatus(HttpStatus.OK)
  public CategoryListDTO getAllCategories() {
    return new CategoryListDTO(categoryService.getAllCategories());
  }

  @GetMapping("{name}")
  @ResponseStatus(HttpStatus.OK)
  public CategoryDTO getCategoryByName(@PathVariable String name) {
    return categoryService.getCategoryByName(name);
  }
}
