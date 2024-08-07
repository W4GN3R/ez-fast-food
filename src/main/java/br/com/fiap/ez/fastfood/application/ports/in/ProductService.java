package br.com.fiap.ez.fastfood.application.ports.in;

import java.util.List;

import br.com.fiap.ez.fastfood.application.dto.ProductDTO;
import br.com.fiap.ez.fastfood.domain.model.Product;

public interface ProductService {
	
	Product createProduct(ProductDTO productDTO);
	List<Product> listProducts();
	Product findById(Long id);
    void deleteProduct(String name);
    Product updateProduct(String name, ProductDTO productDTO);
    List<Product> findProductsByCategoryName(String categoryName);
}
