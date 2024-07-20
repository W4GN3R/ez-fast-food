package br.com.fiap.ez.fastfood.application.ports.in;

import java.util.List;

import br.com.fiap.ez.fastfood.domain.model.Category;

public interface CategoryService {
	
	Category createCategory(Category category);
    List<Category> listCategories();
    Category findById(Long id);
    void deleteCategory(Long id);
}
