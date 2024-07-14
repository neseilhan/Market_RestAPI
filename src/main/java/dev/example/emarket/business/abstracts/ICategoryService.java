package dev.example.emarket.business.abstracts;

import dev.example.emarket.entities.Category;

public interface ICategoryService {
    Category save(Category category);
    Category get(int id);
}
