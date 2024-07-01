package org.example.aptechproject.service.impl;

import jakarta.transaction.Transactional;
import org.example.aptechproject.dto.OrderDTO;
import org.example.aptechproject.dto.newDTO.NewOrderDTO;
import org.example.aptechproject.ex.AppException;
import org.example.aptechproject.model.Order;
import org.example.aptechproject.model.OrderDetail;
import org.example.aptechproject.model.Status;
import org.example.aptechproject.repo.OrderDetailsRepository;
import org.example.aptechproject.repo.OrderRepository;
import org.example.aptechproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(int orderId) {
        return orderRepository.findOrderById(orderId)
                .map(this::toDTO)
                .orElseThrow(() -> AppException.notFound("Order id does not exist"));
    }

    @Override
    public List<OrderDTO> completedOrderByUserId(int userId) {
        return orderRepository.findOrderByUserIdAndStatus(userId, Status.COMPLETED)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getCurrentOrder(int userId) {

        List<Order> currOrder = orderRepository.findOrderByUserIdAndStatus(userId, Status.IN_PROGRESS);
        if (!currOrder.isEmpty()) {
            Order order = currOrder.get(0);
            order.setTotalCost(orderDetailsRepository.findTotalPriceByOrderId(order.getId()));
            orderRepository.save(order);
            return toDTO(order);
        }

        Order order = new Order();
        order.setUserId(userId);

        orderRepository.save(order);

        return toDTO(order);
    }

    @Override
    public OrderDTO checkout(int userId) {

        List<Order> odList = orderRepository.findOrderByUserIdAndStatus(userId, Status.IN_PROGRESS);
        if (odList.isEmpty()) {
            throw AppException.notFound("Order not found");
        }
        Order od = odList.get(0);

        Double total = orderDetailsRepository.findTotalPriceByOrderId(od.getId());

        if (total - 0.0 < 1e7) {
            throw AppException.badRequest("You dont have anything in cart!");
        }

        od.setTotalCost(orderDetailsRepository.findTotalPriceByOrderId(od.getId()));
        od.setStatus(Status.COMPLETED);
        orderRepository.save(od);

        return toDTO(od);
    }

    //add is already automatic
    @Override
    public boolean addOrder(NewOrderDTO order) {
        return false;
    }

    @Override
    public boolean deleteOrder(int orderId) {

        List<OrderDetail> orderDetails = orderDetailsRepository.getAllOrderDetailById(orderId);
        for (OrderDetail od : orderDetails) {
            orderDetailsRepository.deleteOrderDetailById(od.getId());
        }
        orderRepository.deleteById(orderId);
        return true;
    }

    private OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCustomerId(order.getUserId());
        dto.setStatus(String.valueOf(order.getStatus()));
        dto.setTotalCost(orderDetailsRepository.findTotalPriceByOrderId(order.getId()));
        return dto;


    }
}
