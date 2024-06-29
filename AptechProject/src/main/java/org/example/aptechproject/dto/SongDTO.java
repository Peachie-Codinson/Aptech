package org.example.aptechproject.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.aptechproject.model.Artist;
import org.example.aptechproject.model.Genre;

import java.util.Set;

@Getter
@Setter
public class SongDTO {

    private Integer id;

    private Double price;

    private String songName;

    private String imageUrl;

    private String artist;

    private String genre;
}
