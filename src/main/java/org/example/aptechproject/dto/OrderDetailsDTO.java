package org.example.aptechproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsDTO {

    private Integer orderId;
    private Integer songId;
    private Double price;
    private Integer count;
    private Double totalCost;

}
