package org.example.aptechproject.api;

import jakarta.validation.Valid;
import org.example.aptechproject.dto.ArtistDTO;
import org.example.aptechproject.dto.newDTO.NewArtistDTO;
import org.example.aptechproject.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/artist")
public class ArtistAPI {

    @Autowired
    private ArtistService artistService;

    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<ArtistDTO> getAllArtists() {
        return artistService.findAll();
    }

    @GetMapping("/id/{id}")
    public ArtistDTO getById(@PathVariable("id") int id) {
        return artistService.findById(id);
    }

    @GetMapping("/name/{name}")
    public ArtistDTO getByName(@PathVariable("name") String name) {
        return artistService.findByName(name);
    }

    @PostMapping
    public Map<String, String> addNew(@RequestBody @Valid NewArtistDTO dto) {
        boolean rs = artistService.addNew(dto);

        Map<String, String> map = new HashMap<>();
        map.put("result", "Ket qua " + rs);

        return map;
    }

    @DeleteMapping("/id/{id}")
    public Map<String, String> deleteById(@PathVariable("id") int id) {

        boolean rs = artistService.deleteArtistById(id);

        Map<String, String> map = new HashMap<>();
        map.put("result", "Ket qua " + rs);

        return map;
    }

    @DeleteMapping("/name/{name}")
    public Map<String, String> deleteById(@PathVariable("name") String name) {

        boolean rs = artistService.deleteArtistByName(name);

        Map<String, String> map = new HashMap<>();
        map.put("result", "Ket qua " + rs);

        return map;
    }
}
