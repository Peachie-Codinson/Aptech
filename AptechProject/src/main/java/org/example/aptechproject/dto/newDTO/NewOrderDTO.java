package org.example.aptechproject.dto.newDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewOrderDTO {

    @NotNull(message = "User id cannot be null")
    private Integer userId;
}
