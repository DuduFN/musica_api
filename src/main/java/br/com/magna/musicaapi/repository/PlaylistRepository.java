package br.com.magna.musicaapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.magna.musicaapi.entity.playlist.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
	
	@Query("""
			SELECT p FROM Playlist p
			WHERE
			lower(p.nome) LIKE %:nome%
			""")
	Page<Playlist> acharPeloNome(Pageable page, String nome);
}
