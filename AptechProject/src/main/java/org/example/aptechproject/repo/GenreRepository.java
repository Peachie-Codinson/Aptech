package org.example.aptechproject.repo;

import org.example.aptechproject.model.Artist;
import org.example.aptechproject.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query("select a from Genre a where a.genreName = :nameParam")
    Optional<Genre> findGenreByName(@Param("nameParam") String name);

    @Modifying
    @Transactional
    @Query("delete from Genre a where a.id = :id")
    void deleteGenreById(@Param("id") int id);
}