package org.example.aptechproject.repo;

import org.example.aptechproject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.example.aptechproject.model.Status;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.id = :orderId")
    Optional<Order> findOrderById(@Param("orderId") int orderId);

    @Query("SELECT o FROM Order o WHERE o.userId = :userId")
    List<Order> findOrdersByUserId(@Param("userId") int userId);

    @Query("SELECT o FROM Order o WHERE o.userId = :userId AND o.status = :status ")
    List<Order> findOrderByUserIdAndStatus(@Param("userId") int userId, Status status);

//    @Query("SELECT o FROM Order o WHERE o.userId = :userId AND o.status = 'IN_PROGRESS' ")
//    Optional<Order> findInProgressOrder(@Param("userId") int userId);

//    @Query("SELECT o FROM Order o WHERE o.userId = :userId AND o.status = 'COMPLETED' ")
//    List<Order> findCompletedOrders(@Param("userId") int userId);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = 'COMPLETED' WHERE o.id = :orderId")
    void markOrderAsCompleted(@Param("orderId") int orderId);
}
