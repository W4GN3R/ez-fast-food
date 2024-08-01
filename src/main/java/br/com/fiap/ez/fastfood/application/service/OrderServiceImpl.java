package br.com.fiap.ez.fastfood.application.service;

import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.time.format.DateTimeFormatter;

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
		Customer customer = customerRepository.findCustomerByCpf(orderDTO.getCustomerCpf());

		if (customer != null) {

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
			registeredOrder.setOrderTime(new Date());
			registeredOrder.setOrderItems(orderItems);
			registeredOrder.calculateAndSetTotalPrice();
			orderDTO.setTotalPrice(registeredOrder.getTotalPrice());
			// registeredOrder.setStatus(OrderStatus.RECEIVED);
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
	public List<OrderDTO> listUnfinishedOrders() {
		List<Order> orderList= orderRepository.listUnfinishedOrders();
		List<OrderDTO> orderDTOList = new ArrayList<>();
		
		for (Order order: orderList) {
			OrderDTO orderDTO = new OrderDTO();
			
			orderDTO.setCompletedTime(order.getCompletedTime());
			orderDTO.setCustomerCpf(order.getCustomer() != null ? order.getCustomer().getCpf() : null);
			orderDTO.setCustomerName(order.getCustomerName());
			orderDTO.setOrderId(order.getId());
			
			List<OrderItemDTO> orderItemDTOs = new ArrayList<>();		
			for (OrderItem orderItem : order.getOrderItems()) {
			    OrderItemDTO orderItemDTO = new OrderItemDTO();
			    orderItemDTO.setProductId(orderItem.getProduct().getId());
			    orderItemDTO.setQuantity(orderItem.getQuantity());
			    orderItemDTOs.add(orderItemDTO);
			}
			orderDTO.setOrderItems(orderItemDTOs);
			orderDTO.setOrderTime(order.getOrderTime());
			orderDTO.setStatus(order.getStatus());
			orderDTO.setTotalPrice(order.getTotalPrice());
			orderDTO.setWaitedTime(calculateOrderWaitedTime(order));
			
			orderDTOList.add(orderDTO);
			
		}
		return orderDTOList;
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


	public String calculateOrderWaitedTime(Order order) {
		LocalDateTime orderLocalDateTime = convertToLocalDateTime(order.getOrderTime());
		LocalDateTime now = LocalDateTime.now();

		Duration duration = Duration.between(orderLocalDateTime, now);
		long hours = duration.toHours();
		long minutes = duration.toMinutes() % 60;

		return String.format("%02dh%02d", hours, minutes);
	}

	private LocalDateTime convertToLocalDateTime(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
}
