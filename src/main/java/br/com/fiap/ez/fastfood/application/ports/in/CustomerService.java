package br.com.fiap.ez.fastfood.application.ports.in;

import java.util.List;
import java.util.Optional;

import br.com.fiap.ez.fastfood.domain.model.Customer;

public interface CustomerService {

	Customer createCustomer(Customer customer);
	Customer updateCustomer(String cpf, Customer updateCustomer);
    List<Customer> listCustomers();
    Customer deleteCustomerByCpf(String cpf);
    Customer findCustomerByCpf (String cpf);
    Customer authenticate(String cpf, String password);
}
