package org.example.aptechproject.service.impl;

import jakarta.transaction.Transactional;
import org.example.aptechproject.dto.GenreDTO;
import org.example.aptechproject.dto.newDTO.NewGenreDTO;
import org.example.aptechproject.ex.AppException;
import org.example.aptechproject.model.Genre;
import org.example.aptechproject.repo.GenreRepository;
import org.example.aptechproject.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<GenreDTO> findAll() {
        return genreRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDTO findById(int id) {
        return genreRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> AppException.notFound("Genre's id:" + id + "Does not exist"));
    }

    @Override
    public GenreDTO findGenreByName(String genreName) {
        return genreRepository.findGenreByName(genreName)
                .map(this::toDTO)
                .orElseThrow(() -> AppException.notFound("Genre's name: " + genreName + " does not exist"));
    }

    @Override
    public boolean deleteGenreById(int id) {
        Optional<Genre> opGenre = genreRepository.findById(id);
        if (opGenre.isEmpty()) {
            throw AppException.existedRequest("Genre's id:" + id + "does not exist");
        }

        genreRepository.deleteGenreById(id);
        return true;
    }


    @Override
    public boolean addNew(NewGenreDTO dto) {
        String newGenreName = dto.getGenreName();
        Optional<Genre> opGenre = genreRepository.findGenreByName(newGenreName);
        if (opGenre.isPresent()) {
            throw AppException.existedRequest("Genre " + newGenreName + " already exist");
        }

        Genre genre = new Genre();
        genre.setGenreName(dto.getGenreName());

        genreRepository.save(genre);

        return true;
    }

    private GenreDTO toDTO(Genre genre) {
        GenreDTO dto = new GenreDTO();
        dto.setId(genre.getId());
        dto.setGenreName(genre.getGenreName());

        return dto;
    }
}
