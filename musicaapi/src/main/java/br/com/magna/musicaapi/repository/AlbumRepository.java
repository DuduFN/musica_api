package br.com.magna.musicaapi.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.magna.musicaapi.entity.album.Album;


public interface AlbumRepository extends JpaRepository<Album, Long> {
	
	Page<Album> findAll(Pageable page);
	
	@Query("""
			SELECT a FROM Album a
			WHERE
			a.nome = :nome
			AND
			a.artista = :artista
			""")
	Album existeAlbumPeloNomeEArtista(String nome, String artista);

}