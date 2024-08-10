package br.com.fiap.ez.fastfood.application.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.application.ports.in.PaymentService;
import br.com.fiap.ez.fastfood.application.ports.out.OrderRepository;
import br.com.fiap.ez.fastfood.application.ports.out.PaymentRepository;
import br.com.fiap.ez.fastfood.config.exception.BusinessException;
import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.domain.model.OrderStatus;
import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;

@Service
public class PaymentServiceImpl implements PaymentService {

	private final OrderRepository orderRepository;
	private final PaymentRepository paymentRepository;

	public PaymentServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
		this.orderRepository = orderRepository;
		this.paymentRepository = paymentRepository;
	}

	@Override
	public void makePayment(PaymentDTO paymentDTO, Long id) {
		Order order = orderRepository.findOrderById(id);
		if (order != null) {

			Payment payment = new Payment();
			payment.setOrder(order);
			payment.setPaymentDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
			payment.setPaymentPrice(order.getTotalPrice());
			payment.setPaymentStatus(PaymentStatus.OK);
			
			paymentRepository.save(payment);

	        order.setStatus(OrderStatus.RECEIVED);
	        order.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
	        orderRepository.save(order);

		} else {
			throw new BusinessException("Pedido não existe");
		}

	}

}
