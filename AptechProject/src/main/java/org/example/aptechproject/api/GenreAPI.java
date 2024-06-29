package org.example.aptechproject.api;

import jakarta.validation.Valid;
import org.example.aptechproject.dto.GenreDTO;
import org.example.aptechproject.dto.newDTO.NewGenreDTO;
import org.example.aptechproject.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/genre")
public class GenreAPI {

    @Autowired
    private GenreService genreService;

    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/all")
    public List<GenreDTO> getAllGenres() {
        return genreService.findAll();
    }

//    @GetMapping("/{id}")
//    public GenreDTO getById(@PathVariable("id") int id) {
//        return genreService.findById(id);
//    }

    @GetMapping("/{name}")
    public GenreDTO getGenreByName(@PathVariable("name")  String name) {
        return genreService.findGenreByName(name);
    }

    @PostMapping
    public Map<String, String> addNew(@RequestBody @Valid NewGenreDTO dto) {
        boolean rs = genreService.addNew(dto);

        Map<String, String> map = new HashMap<>();
        map.put("result", "Ket qua " + rs);

        return map;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteById(@PathVariable("id") int id) {

        boolean rs = genreService.deleteGenreById(id);

        Map<String, String> map = new HashMap<>();
        map.put("result", "Ket qua " + rs);

        return map;
    }
}