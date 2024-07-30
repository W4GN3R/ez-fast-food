package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.ez.fastfood.domain.model.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long>{
	List<Product> findByCategoryId(Long categoryId);
    boolean existsByCategoryId(Long categoryId);
    Optional<Product> findByName(String name);
}
