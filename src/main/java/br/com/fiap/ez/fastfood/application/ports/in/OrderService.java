package br.com.fiap.ez.fastfood.application.ports.in;

import java.util.List;

import br.com.fiap.ez.fastfood.application.dto.OrderDTO;
import br.com.fiap.ez.fastfood.domain.model.Order;

public interface OrderService {

	OrderDTO registerOrder (OrderDTO orderDTO);
	//Order registerOrder (Order order);
	Order findOrderById(Long id);
	List<Order> listOrders();
	Order updateOrderStatus(Long orderId, String status);
	List<OrderDTO> listUnfinishedOrders();
}
