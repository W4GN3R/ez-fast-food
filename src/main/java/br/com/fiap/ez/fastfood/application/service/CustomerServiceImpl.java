package br.com.fiap.ez.fastfood.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.ez.fastfood.application.ports.in.CustomerService;
import br.com.fiap.ez.fastfood.application.ports.out.CustomerRepository;
import br.com.fiap.ez.fastfood.config.exception.BusinessException;
import br.com.fiap.ez.fastfood.domain.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer createCustomer(Customer customer) {
		if (customer != null && isCustomerValid(customer)) {
			Customer existingCustomer = findCustomerByCpf(customer.getCpf());
			if (existingCustomer == null) {
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

		if (existingCustomer != null && isCustomerValid(existingCustomer)) {

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

	/* metodos adicionais */
	private boolean isCustomerValid(Customer customer) {
		if (!customer.getName().equals("string") && !customer.getCpf().equals("string")
				&& !customer.getEmail().equals("string")) {
			return true;
		} else {
			return false;
		}

	}

}
