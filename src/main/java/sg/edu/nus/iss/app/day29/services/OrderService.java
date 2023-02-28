package sg.edu.nus.iss.app.day29.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.nus.iss.app.day29.models.Order;
import sg.edu.nus.iss.app.day29.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    public String insertOrder(Order order) {
		String orderId = UUID.randomUUID().toString().substring(0, 8);
		order.setOrderId(orderId);

		// Save to mongo
		orderRepo.insertOrder(order);

		return orderId;
	}
}
