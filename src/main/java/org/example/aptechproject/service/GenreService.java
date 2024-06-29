package org.example.aptechproject.service;

import org.example.aptechproject.dto.GenreDTO;
import org.example.aptechproject.dto.newDTO.NewGenreDTO;

import java.util.List;

public interface GenreService {
    List<GenreDTO> findAll();
    GenreDTO findById(int id);

    GenreDTO findGenreByName(String genreName);

    boolean deleteGenreById(int id);

    boolean addNew(NewGenreDTO dto);
}