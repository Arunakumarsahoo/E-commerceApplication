package com.sahooexpress.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sahooexpress.dto.AuditLog;
import com.sahooexpress.dto.CartItemResponseDTO;
import com.sahooexpress.dto.OrderItemResponseDTO;
import com.sahooexpress.dto.OrderResponseDTO;
import com.sahooexpress.dto.PlaceOrderRequestDTO;
import com.sahooexpress.dto.ProductStockUpdateDTO;
import com.sahooexpress.feignclients.AuditFeignClient;
import com.sahooexpress.feignclients.CartFeignClients;
import com.sahooexpress.feignclients.ProductFeignClients;
import com.sahooexpress.models.Order;
import com.sahooexpress.models.OrderItem;
import com.sahooexpress.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartFeignClients cartFeignClients;
	
	@Autowired
	private ProductFeignClients productFeignClients;
	
	@Autowired
	private AuditFeignClient auditFeignClient;
	
	@Autowired
	private KafkaTemplate<String, AuditLog> kafkaTemplate;

	@Override
	public OrderResponseDTO placeOrder(PlaceOrderRequestDTO request) {
		
		List<CartItemResponseDTO> cartItems = cartFeignClients.getCartByUserId(request.getUserId());
		if(cartItems.isEmpty()) {
			throw new RuntimeException("Cart is Empty");
		}
		
		BigDecimal total =BigDecimal.ZERO; // 7596 + 2000
		List<OrderItem> orderItems = new ArrayList();
		List<ProductStockUpdateDTO> stockUpdates = new ArrayList<>();
		
		for(CartItemResponseDTO item : cartItems) {
			BigDecimal price = productFeignClients.getProduct(item.getProductId()).getPrice();
			BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(item.getQuantity()));
			total = total.add(totalPrice);
			
			OrderItem orderItem = new OrderItem();
			orderItem.setProductId(item.getProductId());
			orderItem.setQuanity(item.getQuantity());
			orderItem.setPrice(price);
			orderItems.add(orderItem);
			
			//stockUpdates.add(new ProductStockUpdateDTO(item.getProductId(),item.getQuantity()));
			stockUpdates.add(new ProductStockUpdateDTO(item.getProductId(),item.getQuantity()));
		}
		
		Order order = new Order();
		order.setUserId(request.getUserId());
		order.setTotalPrice(total);
		order.setStatus("PLACED");
		
		for(OrderItem item : orderItems) {
			item.setOrder(order);
		}
		order.setItems(orderItems);
		
		Order savedOrder = orderRepository.save(order);
		
		productFeignClients.updateProductStock(stockUpdates);
		cartFeignClients.clearUserCart(request.getUserId());
		
		sendAuditEvent(request);
		
		return mapToOrderResponse(savedOrder);
	}

	private void sendAuditEvent(PlaceOrderRequestDTO request) {
		AuditLog auditLog = new AuditLog();
        auditLog.setServiceName("order-service");
        auditLog.setAction("order placed");
        auditLog.setUserId(String.valueOf(request.getUserId()));
        
        for(int i=0;i<=100;i++) {
        	kafkaTemplate.send("audit-event",auditLog);
        }
        
        System.out.println("message published to kafka sucesfully");
        
        //auditFeignClient.publishAuditEvent(auditLog);
	}

	private OrderResponseDTO mapToOrderResponse(Order order) {
		OrderResponseDTO response = new OrderResponseDTO();
		response.setOrderId(order.getId());
		response.setUserId(order.getUserId());
		response.setTotalPrice(order.getTotalPrice());
		response.setStatus(order.getStatus());
		
		var items = order.getItems().stream().map(item -> {
			OrderItemResponseDTO itemDto = new OrderItemResponseDTO();
			itemDto.setProductId(item.getProductId());
			itemDto.setQuanity(item.getQuanity());
			itemDto.setPrice(item.getPrice());
			return itemDto;
			
		}).collect(Collectors.toList());
		
		response.setItems(items);
		return response;
	
   }

	@Override
	public OrderResponseDTO getOrderById(Long orderId) {
		 return orderRepository.findById(orderId)
	                .map(this::mapToOrderResponse)
	                .orElseThrow(() -> new RuntimeException("Order not found"));
	}

	@Override
	public void updateOrderStatus(Long orderId, String status) {
		// Step 1: Fetch the order by ID
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        
     // Step 2: Validate if the status is allowed
        if (!isValidStatus(status)) {
            throw new IllegalArgumentException("Invalid order status: " + status);
        }

        // Step 3: Update the order status
        order.setStatus(status);
        orderRepository.save(order);  // Save the updated order

    }

    private boolean isValidStatus(String status) {
        // Define valid statuses
        List<String> validStatuses = Arrays.asList("PENDING", "CONFIRMED", "SHIPPED", "DELIVERED", "CANCELLED","PLACED");
        return validStatuses.contains(status);
		
	}
	
}
