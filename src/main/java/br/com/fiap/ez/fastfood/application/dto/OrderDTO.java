package br.com.fiap.ez.fastfood.application.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.ez.fastfood.domain.model.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class OrderDTO {


	@JsonProperty("order_id")
	private Long orderId;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("customer_cpf")
	private String customerCpf;

	@JsonProperty("customer_name")
	private String customerName;

	@JsonProperty("order_time")
	private Date orderTime;

	@JsonProperty("completed_time")
	private Date completedTime;

	@JsonProperty("total_price")
	private Double totalPrice;

	@JsonProperty("status")
	private OrderStatus status;

	@JsonProperty("order_items")
	private List<OrderItemDTO> orderItems;

	public OrderDTO() {
		// Default constructor
		this.orderItems = new ArrayList<>();
	}

	
	
	
	public OrderDTO(Long orderId, String customerName, Date orderTime, Date completedTime, Double totalPrice,
			OrderStatus status) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		this.orderTime = orderTime;
		this.completedTime = completedTime;
		this.totalPrice = totalPrice;
		this.status = status;
	}




	public OrderDTO(Long orderId, String customerCpf, String customerName, Date orderTime, Date completedTime,
			Double totalPrice, OrderStatus status) {
		this.orderId = orderId;
		this.customerCpf = customerCpf;
		this.customerName = customerName;
		this.orderTime = orderTime;
		this.completedTime = completedTime;
		this.totalPrice = totalPrice;
		this.status = status;
	}


	public OrderDTO(String customerCpf, String customerName, Date orderTime, Date completedTime, Double totalPrice,
			OrderStatus status, List<OrderItemDTO> orderItems) {
		super();
		this.customerCpf = customerCpf;
		this.customerName = customerName;
		this.orderTime = orderTime;
		this.completedTime = completedTime;
		this.totalPrice = totalPrice;
		this.status = status;
		this.orderItems = orderItems;
	}

	// Getters and setters

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(Date completedTime) {
		this.completedTime = completedTime;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderItemDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}

	public String getCustomerCpf() {
		return customerCpf;
	}

	public void setCustomerCpf(String customerCpf) {
		this.customerCpf = customerCpf;
	}




	public Long getOrderId() {
		return orderId;
	}




	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}




	

}
