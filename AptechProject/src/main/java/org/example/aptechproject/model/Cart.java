package org.example.aptechproject.model;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "t_cart")
@IdClass(CartId.class)
public class Cart {

    @Id
    @Column(name = "song_id", nullable = false)
    private Integer id;

    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "song_id", referencedColumnName = "id", nullable = false)
    private Song songs;

    @Column(name = "count")
    private Integer count;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PostConstruct
    private void init() {
        createdAt = LocalDateTime.now();
        count = 1;
    }
}