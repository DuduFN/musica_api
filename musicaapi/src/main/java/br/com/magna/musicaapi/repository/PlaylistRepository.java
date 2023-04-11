package br.com.magna.musicaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.magna.musicaapi.entity.playlist.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
