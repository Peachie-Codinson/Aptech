package org.example.aptechproject.repo;

import org.example.aptechproject.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    @Query("select a from Artist  a where a.artistName = :nameParam")
    Optional<Artist> findArtistByName(@Param("nameParam") String name);

    @Modifying
    @Transactional
    @Query("delete from Artist a where a.artistName = :nameParam")
    void deleteArtistByName(@Param("nameParam") String name);

    @Modifying
    @Transactional
    @Query("delete from Artist a where a.id = :id")
    void deleteArtistById(@Param("id") int id);
}
