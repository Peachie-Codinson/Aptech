package org.example.aptechproject.dto.newDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewOrderDetailsDTO {

    @NotNull(message = "Count cannot be negative")
    @Min(0)
    private Integer count;

    @NotNull(message = "Order Id cannot be negative")
    @Min(0)
    private Integer orderId;

    @NotNull(message = "Song Id cannot be negative")
    @Min(0)
    private Integer songId;
}
