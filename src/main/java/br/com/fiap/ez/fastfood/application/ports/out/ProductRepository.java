package br.com.fiap.ez.fastfood.application.ports.out;

import java.util.List;

import br.com.fiap.ez.fastfood.domain.model.Product;

public interface ProductRepository {

	Product save(Product product);
	List<Product> findAll();
}
