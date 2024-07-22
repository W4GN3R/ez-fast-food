package br.com.fiap.ez.fastfood.application.ports.in;

import java.util.List;

import br.com.fiap.ez.fastfood.domain.model.Product;

public interface ProductService {
	
	Product createProduct(Product product);
	List<Product> listProducts();
	Product findById(Long id);
    void deleteProduct(Long id);
    Product updateProduct(Long id, Product product);
    List<Product> findProductsByCategoryName(String categoryName);
}
