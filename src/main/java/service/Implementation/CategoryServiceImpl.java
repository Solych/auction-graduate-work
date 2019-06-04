package service.Implementation;

import model.Category;
import model.dto.CategoryCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CategoryRepository;
import service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<CategoryCount> getDataOfCategories(Integer buyerId) {
        return categoryRepository.findCategoriesWithCountForUser(buyerId);
    }
}
