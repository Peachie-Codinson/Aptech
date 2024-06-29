package org.example.aptechproject.service;

import org.example.aptechproject.dto.OrderDetailsDTO;
import org.example.aptechproject.dto.newDTO.NewArtistDTO;
import org.example.aptechproject.dto.newDTO.NewOrderDetailsDTO;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetailsDTO> findAll();

    List<OrderDetailsDTO> findByOrderId(int orderId);

    OrderDetailsDTO updateOrderDetails(NewOrderDetailsDTO updateDTO);

    boolean addNew(NewOrderDetailsDTO dto);

    boolean deleteById(int id);

    Double costByOrderId(int orderId);

    boolean deleteByOrderAndSongId(int songId, int orderId);

}
