package br.com.magna.musicaapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.magna.musicaapi.entity.musica.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Long>{
	Page<Musica> findAllByAtivoTrue(Pageable paginacao);
	
	@Query("""
			SELECT m FROM Musica m
			WHERE
			lower(m.nome) LIKE %:nome% 
			""")
	Page<Musica> acharMusicaPeloNome(Pageable paginacao, String nome);
	
	@Query("""
			SELECT m FROM Musica m
			WHERE
			m.nome = :nome
			AND
			m.artista = :artista
			""")
	Musica existeMusicaPeloNomeEArtista(String nome, String artista);
}
