package org.example.aptechproject.service;

import org.example.aptechproject.dto.newDTO.NewSongsDTO;
import org.example.aptechproject.dto.SongDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SongService {

    List<SongDTO> findAll();

    Page<SongDTO> findAll(Pageable pageable);

    SongDTO findById(int id);

    List<SongDTO> findByName(String genreName);

    boolean addNew(NewSongsDTO dto);

    boolean deleteSongById(int id);



    List<SongDTO> findSongsByArtist(String artistName);

    List<SongDTO> findSongsByGenre(String genreName);

    Long totalSongsCount();
}
