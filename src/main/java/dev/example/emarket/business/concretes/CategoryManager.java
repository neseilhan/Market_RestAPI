package dev.example.emarket.business.concretes;

import dev.example.emarket.business.abstracts.ICategoryService;
import dev.example.emarket.dao.CategoryRepo;
import dev.example.emarket.entities.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryManager(CategoryRepo categoryRepo) {

        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }
}