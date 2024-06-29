package org.example.aptechproject.service;

import org.example.aptechproject.dto.ArtistDTO;
import org.example.aptechproject.dto.newDTO.NewArtistDTO;

import java.util.List;

public interface ArtistService {

    List<ArtistDTO> findAll();
    ArtistDTO findById(int id);

    ArtistDTO findByName(String artistName);

    boolean addNew(NewArtistDTO dto);

    boolean deleteArtistById(int id);

    boolean deleteArtistByName(String artistName);
}
