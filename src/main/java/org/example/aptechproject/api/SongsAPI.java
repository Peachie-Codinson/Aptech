package org.example.aptechproject.api;

import jakarta.validation.Valid;
import org.example.aptechproject.dto.newDTO.NewSongsDTO;
import org.example.aptechproject.dto.SongDTO;
import org.example.aptechproject.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/songs")
public class SongsAPI {

    //CRUD ->Admin

    //Client
    //Search -> pagination # of result, current page, # of pages
    //Search by artist, name of songs querydsl


    @Autowired
    private SongService songService;


    @GetMapping("/all")
    public List<SongDTO> getAllSongs() {
        return songService.findAll();
    }

    //http://localhost:8080/api/songs/artist?artistName=Example%20Artist
    @GetMapping("/artist")
    public List<SongDTO> getSongsByArtist(@RequestParam String artistName) {
        return songService.findSongsByArtist(decodeRequestParam(artistName));
    }

    //http://localhost:8080/api/songs/genre?genreName=Example+Genre
    @GetMapping("/genre")
    public List<SongDTO> getSongsByGenre(@RequestParam String genreName) {
        return songService.findSongsByGenre(decodeRequestParam(genreName));
    }

    @GetMapping("/count")
    public Long getSongsCount() {
        return songService.totalSongsCount();
    }

    //http://localhost:8080/api/songs?page=0&size=3
    @GetMapping
    public Page<SongDTO> getAllSongs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return songService.findAll(pageable);
    }


    @GetMapping("/{id}")
    public SongDTO getById(@PathVariable("id") int id) {
        return songService.findById(id);
    }



    @PostMapping
    public Map<String, String> addNew(@RequestBody @Valid NewSongsDTO dto) {
        boolean rs = songService.addNew(dto);

        Map<String, String> map = new HashMap<>();
        map.put("result", "Ket qua " + rs);

        return map;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteById(@PathVariable("id") int id) {

        boolean rs = songService.deleteSongById(id);

        Map<String, String> map = new HashMap<>();
        map.put("result", "Ket qua " + rs);

        return map;
    }

    private String decodeRequestParam(String url) {
        return java.net.URLDecoder.decode(url, StandardCharsets.UTF_8);
    }

}
