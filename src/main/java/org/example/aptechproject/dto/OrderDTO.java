package org.example.aptechproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private int id;
    private int customerId;
    private String status;
    private Double totalCost;
}
