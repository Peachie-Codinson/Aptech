package org.example.aptechproject.service;

import org.example.aptechproject.dto.OrderDTO;
import org.example.aptechproject.dto.OrderDetailsDTO;
import org.example.aptechproject.dto.newDTO.NewOrderDTO;
import org.example.aptechproject.model.Order;


import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderDTO> findAll();

    OrderDTO getOrderById(int orderId);

    List<OrderDTO> completedOrderByUserId(int userId);

    OrderDTO getCurrentOrder(int userId);

    OrderDTO  checkout(int userId);

    boolean addOrder(NewOrderDTO order);

    boolean deleteOrder(int orderId);


}
