package org.example.aptechproject.dto.newDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewArtistDTO {

    @NotBlank(message = "Artist name cannot be empty.")
    private String artistName;
}
