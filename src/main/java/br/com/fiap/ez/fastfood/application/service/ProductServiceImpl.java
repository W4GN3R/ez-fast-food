package br.com.fiap.ez.fastfood.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.ez.fastfood.application.dto.ProductDTO;
import br.com.fiap.ez.fastfood.application.ports.in.ProductService;
import br.com.fiap.ez.fastfood.application.ports.out.CategoryRepository;
import br.com.fiap.ez.fastfood.application.ports.out.ProductRepository;
import br.com.fiap.ez.fastfood.config.exception.BusinessException;
import br.com.fiap.ez.fastfood.domain.model.Category;
import br.com.fiap.ez.fastfood.domain.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product createProduct(Product product) {
	    if (product.getCategory() == null || product.getCategory().getName() == null) {
	        throw new IllegalArgumentException("Necessário informar a categoria");
	    }
	    String categoryName = product.getCategory().getName().toUpperCase();
	    Category category = categoryRepository.findByName(categoryName)
	            .orElseGet(() -> {
	                Category newCategory = new Category();
	                newCategory.setName(categoryName);
	                return categoryRepository.save(newCategory);
	            });
	    product.setCategory(category);
	    return productRepository.save(product);
	}
	
	@Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }
	
	@Override
    public Product findById(Long id) {
		Product product = productRepository.findById(id);
		
		if(product!=null) {
			return product;
		}else {
			throw new BusinessException("Produto não encontrado");
		}
    }
	
	@Override
    public void deleteProduct(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        productRepository.deleteById(product.getId());
    }

    @Override
    public Product updateProduct(String name, ProductDTO productDTO) {
        Product existingProduct = productRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());

        String categoryName = productDTO.getCategoryName().toUpperCase();
        Category category = categoryRepository.findByName(categoryName)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(categoryName);
                    return categoryRepository.save(newCategory);
                });
        existingProduct.setCategory(category);

        return productRepository.save(existingProduct);
    }
    
    @Override
    public List<Product> findProductsByCategoryName(String categoryName) {
        categoryName = categoryName.toUpperCase();
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryName);
        if (optionalCategory.isEmpty()) {
            return null;
        }
        
        Category category = optionalCategory.get();
        List<Product> products = productRepository.findByCategoryId(category.getId());
        return products.isEmpty() ? null : products;
    }

}
