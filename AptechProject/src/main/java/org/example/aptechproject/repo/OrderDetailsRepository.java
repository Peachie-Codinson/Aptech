package org.example.aptechproject.repo;

import org.example.aptechproject.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Integer> {

    @Query("SELECT od FROM OrderDetail od WHERE od.order.id = :orderId")
    List<OrderDetail> getAllOrderDetailById(@Param("orderId") int orderId);

    @Query("SELECT od FROM OrderDetail od WHERE od.order.id = :orderId and od.song.id = :songId")
    Optional<OrderDetail> findByOrderAndSongId(@Param("orderId") int orderId,@Param("songId") int songId);

    @Query("SELECT od FROM OrderDetail od WHERE od.id = :orderId")
    Optional<OrderDetail> findByOrderDetailId(@Param("orderId") int ODId);

    @Modifying
    @Transactional
    @Query("delete from OrderDetail od where od.id = :id")
    void deleteOrderDetailById(@Param("id") int id);

    @Query("SELECT SUM(od.price) FROM OrderDetail od WHERE od.order.id = :orderId")
    Double findTotalPriceByOrderId(int orderId);
}
