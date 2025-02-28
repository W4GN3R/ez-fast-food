package br.com.fiap.ez.fastfood.adapters.in.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.dto.CustomerDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.application.ports.in.OrderService;
import br.com.fiap.ez.fastfood.config.exception.BusinessException;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.domain.model.Order;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Operations", description = "Operations related to order")
public class OrderController {

	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	@Operation(summary = "Register a new order (fake checkout)")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Order registered"),
			@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PostMapping(path = "/checkout", produces = "application/json")
	public ResponseEntity<?> registerOrder(@Valid @RequestBody CreateOrderDTO createOrderDTO) {

		try {

			OrderResponseDTO orderResponseDTO = orderService.registerOrder(createOrderDTO);

			return new ResponseEntity<>(orderResponseDTO, HttpStatus.CREATED);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "List all orders")
	@GetMapping(path = "/list-all", produces = "application/json")
	public ResponseEntity<?> listOrders() {
		try {

			List<OrderResponseDTO> ordersDTO = new ArrayList<>();
			ordersDTO = orderService.listAllOrders();
			return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@Operation(summary = "List unfinished orders")

	@GetMapping(path = "/list-orders-in-queue", produces = "application/json")
	public ResponseEntity<?> listUnfinishedOrders() {
		try {
			List<OrderResponseDTO> ordersDTO = orderService.listUnfinishedOrders();
			return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

}
