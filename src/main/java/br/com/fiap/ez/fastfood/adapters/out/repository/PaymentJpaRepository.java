package br.com.fiap.ez.fastfood.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.ez.fastfood.domain.model.Payment;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long>{

}
