package org.example.aptechproject.model;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @NotNull
    @Lob
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @NotNull
    @Column(name = "total_cost", nullable = false)
    private Double totalCost;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> tOrderDetails = new LinkedHashSet<>();

    @PostConstruct
    private void init() {
        createdOn = LocalDateTime.now();
        status = Status.IN_PROGRESS;
        totalCost = 0.0;
    }

}