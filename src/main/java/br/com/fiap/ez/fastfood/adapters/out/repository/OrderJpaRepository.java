package br.com.fiap.ez.fastfood.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import br.com.fiap.ez.fastfood.domain.model.Order;


public interface OrderJpaRepository  extends JpaRepository<Order, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM EZ_FASTFOOD.ORDER WHERE id = :id")
	Order findOrderById(@Param("id") Long id);

}
