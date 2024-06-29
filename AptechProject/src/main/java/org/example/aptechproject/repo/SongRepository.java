package org.example.aptechproject.repo;

import org.example.aptechproject.model.Genre;
import org.example.aptechproject.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Integer> {

    @Query("select a from Song a where a.id = :songId")
    Optional<Song> findSongById(@Param("songId") int songId);

    @Query("select a from Song a where a.songName = :nameParam")
    List<Song> findSongByName(@Param("nameParam") String name);

    @Modifying
    @Transactional
    @Query("delete from Song a where a.id = :id")
    void deleteSongById(@Param("id") int id);

    @Query("SELECT s FROM Song s WHERE s.artist.artistName = :artistName")
    List<Song> findSongsByArtist(@Param("artistName") String artistName);

    @Query("SELECT s FROM Song s WHERE s.artist.artistName = :artistName and s.songName=:nameParam")
    List<Song> findSongsByNameAndArtist(@Param("artistName") String artistName, @Param("nameParam") String name);


    @Query("SELECT s FROM Song s WHERE s.genre.genreName = :genreName")
    List<Song> findSongsByGenre(@Param("genreName") String genreName);


    @Query("SELECT COUNT(s) FROM Song s")
    Long countSongs();
}
