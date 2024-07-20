package br.com.fiap.ez.fastfood.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.ez.fastfood.application.ports.in.CategoryService;
import br.com.fiap.ez.fastfood.application.ports.out.CategoryRepository;
import br.com.fiap.ez.fastfood.application.ports.out.ProductRepository;
import br.com.fiap.ez.fastfood.domain.model.Category;
import br.com.fiap.ez.fastfood.domain.model.Product;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
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
        boolean hasProducts = productRepository.existsByCategoryId(id);
        if (hasProducts) {
            throw new RuntimeException("Categoria não pode ser excluída porque há produtos vinculados a ela.");
        }
        categoryRepository.deleteById(id);
    }
    
    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = findById(id);
        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);
    }

	@Override
	public List<Product> findByCategoryId(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsByCategoryId(Long categoryId) {
		// TODO Auto-generated method stub
		return false;
	}

}
