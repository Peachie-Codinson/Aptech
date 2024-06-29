//package org.example.aptechproject.api;
//
//import org.example.aptechproject.dto.OrderDTO;
//import org.example.aptechproject.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/Orders")
//public class OrderAPI {
//
//    @Autowired
//    private OrderService orderService;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public List<OrderDTO> getOrders() {
//        return orderService.findAll();
//    }
//
//    @GetMapping("/{uid}")
//    public OrderDTO getUserCurrentOrder(@PathVariable("uid") int id){
//        return orderService.getCurrentOrder(id);
//    }
//
//    @PostMapping("/{uid}")
//    public OrderDTO checkOutUserCurrentOrder(@PathVariable("uid") int id){
//        return orderService.checkout(id);
//    }
//
//    @GetMapping("/{uid}/history")
//    public List<OrderDTO> getOrderHistory(@PathVariable("uid") int id){
//        return orderService.completedOrderByUserId(id);
//    }
//
//    @DeleteMapping("/{order_id}")
//    public boolean deleteOrder(@PathVariable("order_id") int id){
//        return orderService.deleteOrder(id);
//    }
//
//
//}
