package br.com.fiap.ez.fastfood.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.ez.fastfood.application.ports.in.CustomerService;
import br.com.fiap.ez.fastfood.application.ports.out.CustomerRepository;
import br.com.fiap.ez.fastfood.config.exception.BusinessException;
import br.com.fiap.ez.fastfood.domain.model.Customer;


@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	//private final PasswordEncoder passwordEncoder;

	@Autowired
	/*public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
		this.customerRepository = customerRepository;
		this.passwordEncoder = passwordEncoder;
	}*/
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer createCustomer(Customer customer) {
		if (customer != null && customer.isCustomerValid(customer)) {
			Customer existingCustomer = findCustomerByCpf(customer.getCpf());
			if (existingCustomer == null) {
				//customer.setPassword(passwordEncoder.encode(customer.getPassword()));
				return customerRepository.save(customer);
			} else {
				throw new BusinessException("Cliente já cadastrado");
			}

		} else {
			throw new IllegalArgumentException("Dados inválidos");
		}
	}

	@Override
	public List<Customer> listCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer deleteCustomerByCpf(String cpf) {

		Customer customer = findCustomerByCpf(cpf);

		if (customer != null) {
			return customerRepository.removeByCpf(cpf);
		} else {
			throw new BusinessException("Cliente não encontrado");
		}
	}

	@Transactional
	public Customer updateCustomer(String cpf, Customer updateCustomer) {

		Customer existingCustomer = findCustomerByCpf(cpf);

		if (existingCustomer != null && updateCustomer.isCustomerValid(existingCustomer)) {

			existingCustomer.setName(updateCustomer.getName());

			existingCustomer.setEmail(updateCustomer.getEmail());

			existingCustomer.setCpf(updateCustomer.getCpf());

			return customerRepository.updateCustomerByCpf(existingCustomer);
		} else {
			throw new IllegalArgumentException("Dados inválidos.");
		}
	}

	@Override
	public Customer findCustomerByCpf(String cpf) {

		Customer findCustomer = customerRepository.findCustomerByCpf(cpf);

		if (findCustomer != null) {
			return findCustomer;
		} else {
			return null;
		}
	}

	/**
	 * Metodo de autenticacao (cpf e senha) com seguranca implementada
	 * Comentado para utilizacao futura
	 *
	 */
	@Override
	/*public Customer authenticate(String cpf, String password) {
		Customer customer = customerRepository.findCustomerByCpf(cpf);

		if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {

			return customer;
		}else {
			throw new BusinessException("CPF ou senha errada.");
		}
	}*/
	
	public Customer authenticate(String cpf) {
		Customer customer = customerRepository.findCustomerByCpf(cpf);
		if (customer != null) {
			return customer;
		}else {
			throw new BusinessException("CPF ou senha errada.");
		}
	}

}
