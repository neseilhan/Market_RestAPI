package dev.example.emarket.business.abstracts;

import dev.example.emarket.entities.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {
    Category save(Category category);

    Category get(int id);

    Page<Category> cursor(int page, int pageSize);
}
