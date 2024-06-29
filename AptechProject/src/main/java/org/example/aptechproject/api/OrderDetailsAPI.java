//package org.example.aptechproject.api;
//
//import org.example.aptechproject.dto.OrderDetailsDTO;
//import org.example.aptechproject.dto.newDTO.NewOrderDetailsDTO;
//import org.example.aptechproject.service.OrderDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.lang.reflect.Method;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/OrderDetails")
//public class OrderDetailsAPI {
//
//    @Autowired
//    private OrderDetailService orderDetailService;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public List<OrderDetailsDTO> getOrderDetails() {
//        return orderDetailService.findAll();
//    }
//
//    @GetMapping("/{order_id}")
//    public List<OrderDetailsDTO> getOrderDetailsByOrderId(@PathVariable("order_id") int order_id) {
//        return orderDetailService.findByOrderId(order_id);
//    }
//
//    @PutMapping("/order_id}")
//    public OrderDetailsDTO updateOrderDetails(@RequestBody NewOrderDetailsDTO newOrderDetails) {
//        return orderDetailService.updateOrderDetails(newOrderDetails);
//    }
//
//    @GetMapping("/{order_id}/cost")
//    public Double getOrderDetailsCost(@PathVariable("order_id") int orderId) {
//        return orderDetailService.costByOrderId(orderId);
//    }
//
//    @DeleteMapping("/{order_id}/{song_id}")
//    public boolean deleteOrderDetails(@PathVariable("order_id") int orderId, @PathVariable("song_id") int songId) {
//        return orderDetailService.deleteByOrderAndSongId(orderId, songId);
//    }
//}
