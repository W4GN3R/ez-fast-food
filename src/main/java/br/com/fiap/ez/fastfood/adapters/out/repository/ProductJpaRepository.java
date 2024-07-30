package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.domain.model.Product;

//@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long>{
	List<Product> findByCategoryId(Long categoryId);
    boolean existsByCategoryId(Long categoryId);
    
    @Query(nativeQuery = true, value = "SELECT * FROM PRODUCT WHERE id = :id")
	Product findProductById(@Param("id") Long id);
}
