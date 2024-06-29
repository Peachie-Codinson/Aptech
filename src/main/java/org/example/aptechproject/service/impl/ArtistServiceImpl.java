package org.example.aptechproject.service.impl;

import jakarta.transaction.Transactional;
import org.example.aptechproject.dto.ArtistDTO;
import org.example.aptechproject.dto.newDTO.NewArtistDTO;
import org.example.aptechproject.ex.AppException;
import org.example.aptechproject.model.Artist;
import org.example.aptechproject.repo.ArtistRepository;
import org.example.aptechproject.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

import java.util.List;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public List<ArtistDTO> findAll() {
        return artistRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArtistDTO findById(int id) {
        return artistRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> AppException.notFound("Artist's id:" + id + "Does not exist"));
    }

    @Override
    public ArtistDTO findByName(String artistName) {
        return artistRepository.findArtistByName(artistName)
                .map(this::toDTO)
                .orElseThrow(() -> AppException.notFound("Artist's name: " + artistName + " does not exist"));
    }



    @Override
    public boolean addNew(NewArtistDTO dto) {

        String artistName = dto.getArtistName();
        Optional<Artist> opArtist = artistRepository.findArtistByName(dto.getArtistName());
        if (opArtist.isPresent()) {
            throw AppException.existedRequest("Artist " + artistName + "already exist");
        }

        Artist artist = new Artist();
        artist.setArtistName(dto.getArtistName());

        artistRepository.save(artist);

        return true;
    }

    @Override
    public boolean deleteArtistById(int id) {

        Optional<Artist> opArtist = artistRepository.findById(id);
        if (opArtist.isEmpty()) {
            throw AppException.existedRequest("Artist's id:" + id + "does not exist");
        }

        artistRepository.deleteArtistById(id);
        return true;
    }

    @Override
    public boolean deleteArtistByName(String artistName) {
        Optional<Artist> opArtist = artistRepository.findArtistByName(artistName);
        if (opArtist.isEmpty()) {
            throw AppException.existedRequest("Artist " + artistName + " does not exist");
        }

        artistRepository.deleteArtistByName(artistName);
        return true;
    }

    private ArtistDTO toDTO(Artist artist) {
        ArtistDTO dto = new ArtistDTO();
        dto.setId(artist.getId());
        dto.setArtistName(artist.getArtistName());

        return dto;
    }
}
