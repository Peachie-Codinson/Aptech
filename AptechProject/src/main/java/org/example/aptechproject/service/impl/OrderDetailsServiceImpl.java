package org.example.aptechproject.service.impl;

import jakarta.transaction.Transactional;
import org.example.aptechproject.dto.OrderDetailsDTO;
import org.example.aptechproject.dto.newDTO.NewOrderDetailsDTO;
import org.example.aptechproject.ex.AppException;
import org.example.aptechproject.model.Order;
import org.example.aptechproject.model.OrderDetail;
import org.example.aptechproject.model.Song;
import org.example.aptechproject.repo.OrderDetailsRepository;
import org.example.aptechproject.repo.OrderRepository;
import org.example.aptechproject.repo.SongRepository;
import org.example.aptechproject.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderDetailsDTO> findAll() {
        return orderDetailsRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailsDTO> findByOrderId(int orderId) {

        orderRepository.findOrderById(orderId)
                .orElseThrow(() -> AppException.notFound("Order with ID " + orderId + " not found"));

        return orderDetailsRepository.getAllOrderDetailById(orderId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public OrderDetailsDTO updateOrderDetails(NewOrderDetailsDTO updateDTO) {

        OrderDetail od = orderDetailsRepository.findByOrderAndSongId(updateDTO.getOrderId(), updateDTO.getSongId())
                .orElseThrow(() -> AppException.notFound("Order detail not found"));

        int newCount = updateDTO.getCount();
        if(newCount>0){
            od.setCount(updateDTO.getCount());

            od.setPrice(od.getSong().getPrice());
            OrderDetail newOD = orderDetailsRepository.save(od);
            return toDTO(newOD);
        }
        else{
            deleteById(updateDTO.getOrderId());
            return null;
        }
    }

    @Override
    public boolean addNew(NewOrderDetailsDTO dto) {

        Order order = orderRepository.findOrderById(dto.getOrderId())
                .orElseThrow(() -> AppException.notFound("Order with ID " + dto.getOrderId() + " not found"));

        Song song = songRepository.findSongById(dto.getSongId())
                .orElseThrow(() -> AppException.notFound("Song with ID " + dto.getSongId() + " not found"));


        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setSong(song);
        orderDetail.setPrice(song.getPrice());
        orderDetail.setCount(dto.getCount());

        orderDetailsRepository.save(orderDetail);

        return true;
    }

    @Override
    public boolean deleteById(int id) {
        OrderDetail od = orderDetailsRepository.findByOrderDetailId(id)
                .orElseThrow(() -> AppException.notFound("Order detail not found"));
        orderDetailsRepository.deleteOrderDetailById(od.getId());
        return true;
    }

    @Override
    public Double costByOrderId(int orderId) {
        return orderDetailsRepository.findTotalPriceByOrderId(orderId);
    }

    @Override
    public boolean deleteByOrderAndSongId(int songId, int orderId) {
        OrderDetail od = orderDetailsRepository.findByOrderAndSongId(orderId, songId)
                .orElseThrow(() -> AppException.notFound("Order detail not found"));
        orderDetailsRepository.deleteOrderDetailById(od.getId());
        return true;
    }

    private OrderDetailsDTO toDTO(OrderDetail od) {
        OrderDetailsDTO dto = new OrderDetailsDTO();
        dto.setOrderId(od.getOrder().getId());
        dto.setSongId(od.getSong().getId());
        dto.setPrice(od.getPrice());
        dto.setCount(od.getCount());
        dto.setTotalCost(od.getTotal());

        return dto;
    }
}
