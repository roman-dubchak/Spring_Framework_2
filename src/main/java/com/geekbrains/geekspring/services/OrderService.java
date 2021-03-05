package com.geekbrains.geekspring.services;

import com.geekbrains.geekspring.entities.Order;
import com.geekbrains.geekspring.entities.ShoppingCart;
import com.geekbrains.geekspring.entities.User;
import com.geekbrains.geekspring.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderStatusService orderStatusService;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setOrderStatusService(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @Transactional
    public Order makeOrder(ShoppingCart cart, User user) {
        //TODO: домашнее задание
        // Реализовать сохранение покупок, которые пользователь
        // добавил в корзину, в виде заказов, сохраняемых в БД.
        Order order = new Order();
        order.setUser(user);
        order.setPrice(cart.getTotalCost());
//        order.setDeliveryPrice();
//        order.setDeliveryAddress(); создать input items доставки из формы order-filter
//        order.setDeliveryDate();
//        order.setPhoneNumber(user.getPhoneNumber()); // после отпарвялем в orger-result
        order.setStatus(orderStatusService.getStatusById(1L));

        order.setOrderItems(cart.getItems());


        return orderRepository.save(order);
//        return new Order();
    }

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    public Order saveOrder(Order order) {
        //TODO: домашнее задание *
        // Подумать, возможно ли корзину реализовать через сессионный бин.
        // Если возможно и целесообразно, то реализовать это в коде.
        return order;
    }

    public Order changeOrderStatus(Order order, Long statusId) {
        order.setStatus(orderStatusService.getStatusById(statusId));
        return saveOrder(order);
    }
}
