package br.com.fiap.ez.fastfood.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.ez.fastfood.domain.model.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long>{

}
