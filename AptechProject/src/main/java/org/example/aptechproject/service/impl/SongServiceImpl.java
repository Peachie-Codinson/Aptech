package org.example.aptechproject.service.impl;

import jakarta.transaction.Transactional;
import org.example.aptechproject.dto.newDTO.NewSongsDTO;
import org.example.aptechproject.dto.SongDTO;
import org.example.aptechproject.ex.AppException;
import org.example.aptechproject.model.Artist;
import org.example.aptechproject.model.Genre;
import org.example.aptechproject.model.Song;
import org.example.aptechproject.repo.ArtistRepository;
import org.example.aptechproject.repo.GenreRepository;
import org.example.aptechproject.repo.SongRepository;
import org.example.aptechproject.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SongServiceImpl implements SongService
{
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<SongDTO> findAll() {
        return songRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SongDTO> findAll(Pageable pageable) {
        Page<Song> songsPage = songRepository.findAll(pageable);
        return songsPage.map(this::toDTO);
    }

    @Override
    public SongDTO findById(int id) {
        return songRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> AppException.notFound("Song id"+id+" not found"));
    }

    @Override
    public List<SongDTO> findByName(String songName) {

        List<Song> songs = songRepository.findSongByName(songName);

        if (songs.isEmpty()) {
            throw AppException.notFound("Song name"+songName+" not found");
        }

        return songs.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean addNew(NewSongsDTO dto) {

        String songName = dto.getSongName();
        String artistName = dto.getArtistName();
        String genreName = dto.getGenreName();

        List<Song> existingSongs = songRepository.findSongsByNameAndArtist(artistName, songName);
        if (!existingSongs.isEmpty()) {
            throw AppException.badRequest("Song with name '" + songName + "' already exists for artist '" + artistName + "'.");
        }


        Artist artist = artistRepository.findArtistByName(artistName)
                .orElseGet(() -> {
                    Artist newArtist = new Artist();
                    newArtist.setArtistName(artistName);
                    return artistRepository.save(newArtist);
                });

        Genre genre = genreRepository.findGenreByName(genreName)
                .orElseGet(() -> {
                    Genre newGenre = new Genre();
                    newGenre.setGenreName(genreName);
                    return genreRepository.save(newGenre);
                });

        Song newSong = new Song();
        newSong.setSongName(songName);
        newSong.setArtist(artist);
        newSong.setGenre(genre);
        newSong.setPrice(dto.getPrice());
        newSong.setImageUrl(dto.getImageUrl());

        songRepository.save(newSong);
        return true;
    }

    @Override
    public boolean deleteSongById(int id) {
        Optional<Song> opSong = songRepository.findById(id);
        if (opSong.isEmpty()) {
            throw AppException.existedRequest("Song's id:" + id + "does not exist");
        }

        songRepository.deleteSongById(id);
        return true;
    }

    @Override
    public List<SongDTO> findSongsByArtist(String artistName) {
        List<Song> songs = songRepository.findSongsByArtist(artistName);
        return songs.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SongDTO> findSongsByGenre(String genreName) {
        List<Song> songs = songRepository.findSongsByGenre(genreName);
        return songs.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long totalSongsCount() {
        return songRepository.countSongs();
    }


    private SongDTO toDTO(Song song) {
        SongDTO dto = new SongDTO();
        dto.setId(song.getId());
        dto.setPrice(song.getPrice());
        dto.setImageUrl(song.getImageUrl());
        dto.setSongName(song.getSongName());
        dto.setGenre(song.getGenre().getGenreName());
        dto.setArtist(song.getArtist().getArtistName());

        return dto;
    }
}
