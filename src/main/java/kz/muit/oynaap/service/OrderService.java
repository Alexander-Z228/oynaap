package kz.muit.oynaap.service;

import kz.muit.oynaap.models.Order;
import kz.muit.oynaap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    public Integer addNewOrder(Order order) {
        Integer order_id = orderRepo.insertOrder(order);
        return order_id;
    }

    public void updateOrderCartItem(Integer cartId, Integer orderId) {
        orderRepo.updateOrderCartItem(orderId, cartId);
    }

    public void updateOrderPayment(Order order) {
        orderRepo.updateOrderPayment(order);
    }

    public List<Order> selectAllUserPaidOrders(String username) {
        return orderRepo.selectAllUserPaidOrders(username);
    }

    public List<Order> selectAllPaidOrders() {
        return orderRepo.selectAllPaidOrders();
    }

}
