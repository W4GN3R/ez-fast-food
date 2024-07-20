package br.com.fiap.ez.fastfood.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.ez.fastfood.application.ports.in.CategoryService;
import br.com.fiap.ez.fastfood.application.ports.out.CategoryRepository;
import br.com.fiap.ez.fastfood.domain.model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }
    
    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    @Override
    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Categoria não encontrada");
        }
    }
    
    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = findById(id);
        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);
    }

}
