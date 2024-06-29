package org.example.aptechproject.dto.newDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewSongsDTO {

    @NotNull(message = "Song price cannot be null")
    @Min(value = 0, message = "Song price must be greater than 0")
    private Double price;

    @NotBlank(message = "Song name cannot be empty.")
    private String songName;

    @NotBlank(message = "Artist name cannot be empty.")
    private String artistName;

    @NotBlank(message = "Genre name cannot be empty.")
    private String genreName;

    @NotBlank(message = "Image url cannot be empty.")
    private String imageUrl;
}
