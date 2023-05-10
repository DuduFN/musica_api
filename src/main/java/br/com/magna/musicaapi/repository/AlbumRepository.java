package br.com.magna.musicaapi.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.magna.musicaapi.entity.album.Album;


public interface AlbumRepository extends JpaRepository<Album, Long>   {
	@Query("""
			SELECT a FROM Album a
			WHERE
			lower(a.nome) LIKE lower(concat('%', :nome,'%'))
			""")
	Page<Album> acharPeloNome(Pageable page, String nome);
	
	@Query("""
			select A.nome, AR.nome
			from Album A
			join A.artista AR
			where A.nome = :nomeAlbum
			and
			AR.nome = :nomeArtista
			""")
	Object existeAlbumPeloNomeEArtista(String nomeAlbum, String nomeArtista);

}