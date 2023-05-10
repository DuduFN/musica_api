package br.com.magna.musicaapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.magna.musicaapi.entity.musica.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Long>{
	Page<Musica> findAllByAtivoTrue(Pageable paginacao);
	
	@Query("""
			select M from Musica M
			where
			lower(M.nome) like lower(concat('%', :nome, '%'))
			""")
	Page<Musica> acharMusicaPeloNome(Pageable paginacao, @Param("nome") String nome);
	
	@Query("""
			select M.nome, MA.nome
			from Musica M
			join M.artista MA
			where M.nome = :nomeMusica
			and
			MA.nome = :nomeArtista
			""")
	Object existeMusicaPeloNomeEArtista(String nomeMusica, String nomeArtista);
	
	/*
	 * 		select tm.nome, ta.nome
			from tb_musica tm
			join tb_artista_musica tam on tam.fk_musica = tm.pk_musica
			join tb_artista ta on ta.pk_artista = tam.fk_artista
	 */
}