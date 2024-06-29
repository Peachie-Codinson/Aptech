package org.example.aptechproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_order_details")
public class OrderDetail {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "price")
    private Double price;

    @Column(name = "total")
    private Double total;

    @Column(name = "count")
    private Integer count;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @PrePersist
    @PreUpdate
    private void calculateTotal() {
        if (price != null && count != null) {
            total = price * count;
        } else {
            total = 0.0;
        }
    }

    public void setPrice(Double price) {
        this.price = price;
        calculateTotal();
    }

    public void setSong(Song song) {
        this.song = song;
        if (song != null) {
            this.price = song.getPrice();
        }
        calculateTotal();
    }


    public void setCount(Integer count) {
        this.count = count;
        calculateTotal();
    }
}