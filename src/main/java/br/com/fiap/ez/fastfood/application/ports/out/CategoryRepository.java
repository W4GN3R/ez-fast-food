package br.com.fiap.ez.fastfood.application.ports.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.ez.fastfood.domain.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
