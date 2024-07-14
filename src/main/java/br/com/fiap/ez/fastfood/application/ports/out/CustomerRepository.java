package br.com.fiap.ez.fastfood.application.ports.out;

import br.com.fiap.ez.fastfood.domain.model.Customer;
import java.util.List;


public interface CustomerRepository {
	
	Customer save(Customer customer);
    List <Customer> findAll();
	Customer removeByCpf(String cpf);
	Customer findCustomerByCpf (String cpf);
	Customer updateCustomerByCpf(Customer customer);
	
	
}
