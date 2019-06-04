package service;

import model.Category;
import model.dto.CategoryCount;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    List<CategoryCount> getDataOfCategories(Integer buyerId);
}
