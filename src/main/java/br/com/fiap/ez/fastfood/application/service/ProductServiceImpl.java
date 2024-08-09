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
	public Product createProduct(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());

		Category category = categoryRepository.findById(productDTO.getCategoryId())
				.orElseThrow(() -> new BusinessException("Categoria não encontrada"));
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

		if (product != null) {
			return product;
		} else {
			throw new BusinessException("Produto não encontrado");
		}

	}

	@Override
	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id);

		if (product != null) {
			productRepository.deleteById(id);
		} else {
			throw new BusinessException("Produto não encontrado");
		}
	}

	@Override
	public Product updateProduct(Long id, ProductDTO productDTO) {
		Product existingProduct = productRepository.findById(id);
		if (existingProduct != null) {
			existingProduct.setName(productDTO.getName());
			existingProduct.setDescription(productDTO.getDescription());
			existingProduct.setPrice(productDTO.getPrice());

			// Atualizar a categoria apenas com o ID
			Category category = categoryRepository.findById(productDTO.getCategoryId())
					.orElseThrow(() -> new BusinessException("Categoria não encontrada"));
			existingProduct.setCategory(category);

			return productRepository.save(existingProduct);
		} else {
			throw new BusinessException("Produto não encontrado");
		}

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
