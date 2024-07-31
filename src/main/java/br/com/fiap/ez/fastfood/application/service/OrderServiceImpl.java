package br.com.fiap.ez.fastfood.application.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.com.fiap.ez.fastfood.application.dto.OrderDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.application.dto.ProductDTO;
import br.com.fiap.ez.fastfood.application.ports.in.OrderService;
import br.com.fiap.ez.fastfood.application.ports.out.CustomerRepository;
import br.com.fiap.ez.fastfood.application.ports.out.OrderRepository;
import br.com.fiap.ez.fastfood.application.ports.out.PaymentRepository;
import br.com.fiap.ez.fastfood.application.ports.out.ProductRepository;
import br.com.fiap.ez.fastfood.config.exception.BusinessException;
import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.domain.model.OrderStatus;
import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;
import br.com.fiap.ez.fastfood.domain.model.Product;
import br.com.fiap.ez.fastfood.domain.model.OrderItem;

import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final PaymentRepository paymentRepository;
	private final CustomerRepository customerRepository;
	

	

	public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository,
			PaymentRepository paymentRepository, CustomerRepository customerRepository) {
		super();
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.paymentRepository = paymentRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional
	public OrderDTO registerOrder(OrderDTO orderDTO) {

		Order registeredOrder = new Order();
		Customer customer =  customerRepository.findCustomerByCpf(orderDTO.getCustomerCpf());
		
		if(customer!= null) {
			
			registeredOrder.setCustomer(customer);
		}
		registeredOrder.setCustomerName(orderDTO.getCustomerName());
		registeredOrder.setOrderTime(orderDTO.getOrderTime());
		registeredOrder.setCompletedTime(orderDTO.getCompletedTime());

		
		registeredOrder.setStatus(orderDTO.getStatus());

		List<OrderItem> orderItems = new ArrayList<>();

		if (orderDTO.getOrderItems() != null && !orderDTO.getOrderItems().isEmpty()) {
			

			for (OrderItemDTO item : orderDTO.getOrderItems()) {

				OrderItem orderItem = new OrderItem();
				Product product = new Product();

				product = productRepository.findById(item.getProductId());

				if (product != null) {
					orderItem.setProduct(product);
				} else {
					throw new BusinessException("Produto não existe");
				}

				orderItem.setQuantity(item.getQuantity());
				orderItem.setPrice(product.getPrice() * item.getQuantity());
				orderItem.setOrder(registeredOrder);
				orderItems.add(orderItem);
			}

			registeredOrder.setOrderItems(orderItems);
			registeredOrder.calculateAndSetTotalPrice();
			orderDTO.setTotalPrice(registeredOrder.getTotalPrice());
			//registeredOrder.setStatus(OrderStatus.RECEIVED);
			orderRepository.save(registeredOrder);
			
			registerPayment(registeredOrder);
			return orderDTO;

		} else {
			throw new BusinessException("Lista de pedidos vazia");
		}

	}

	@Override
	public List<Order> listOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order updateOrderStatus(Long orderId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> listUnfinishedOrders() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void registerPayment(Order order) {
		
		Payment payment = new Payment();
		payment.setOrder(order);
		payment.setCustomer(order.getCustomer());
		payment.setPaymentPrice(order.getTotalPrice());
		payment.setPaymentStatus(PaymentStatus.PENDING);
		
		paymentRepository.save(payment);
		
	}

	@Override
	public Order findOrderById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * private OrderDTO convertToOrderDTO(Order order) { List<OrderItemDTO>
	 * orderItemsDTO = new ArrayList<>(); for (OrderItem item :
	 * order.getOrderItems()) { OrderItemDTO itemDTO = new OrderItemDTO(
	 * item.getProduct().getId(), item.getProduct().getName(), item.getQuantity(),
	 * item.getPrice() ); orderItemsDTO.add(itemDTO); }
	 * 
	 * OrderDTO orderDTO = new OrderDTO( order.getOrderId(), order.getCustomerCpf(),
	 * order.getCustomerName(), order.getOrderTime(), order.getCompletedTime(),
	 * order.getTotalPrice(), order.getStatus(), orderItemsDTO );
	 * 
	 * orderDTO.setWaitedTime(calculateOrderWaitedTime(order.getOrderTime()));
	 * return orderDTO; }
	 */

}
