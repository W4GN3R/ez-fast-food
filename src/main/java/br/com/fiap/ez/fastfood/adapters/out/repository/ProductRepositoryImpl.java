package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.fiap.ez.fastfood.application.ports.out.ProductRepository;
import br.com.fiap.ez.fastfood.domain.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	
	private final ProductJpaRepository productJpaRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public ProductRepositoryImpl(ProductJpaRepository productJpaRepository) {
		this.productJpaRepository = productJpaRepository;
	}
	
	@Override
	public Product save(Product product) {
		return productJpaRepository.save(product);
	}

	@Override
	public List<Product> findAll() {
		return productJpaRepository.findAll();
	}

	@Override
    public Optional<Product> findById(Long id) {
        return productJpaRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        productJpaRepository.deleteById(id);
    }

}
