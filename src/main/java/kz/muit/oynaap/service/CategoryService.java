package kz.muit.oynaap.service;

import kz.muit.oynaap.models.Category;
import kz.muit.oynaap.repository.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryRespository categoryRepo;

    public List<Category> getAllCategories() {
        return categoryRepo.selectAllCategories();
    }

    public void addNewCategory(Category category) {
        categoryRepo.insertCategory(category);
    }

    public void deleteCategory(int category_id) {
        categoryRepo.deleteCategory(category_id);
    }

    public void updateCategory(Category category) {
        categoryRepo.updateCategory(category);
    }

    public Category selectCategory(int category_id) {
        Category category = categoryRepo.selectCategory(category_id);
        return category;
    }


}
