package br.com.fiap.ez.fastfood.application.ports.out;

import java.util.List;
import java.util.Optional;

import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.domain.model.Product;

public interface ProductRepository {

	Product save(Product product);
	List<Product> findAll();
	Optional<Product> findById(Long id);
    void deleteById(Long id);
}
