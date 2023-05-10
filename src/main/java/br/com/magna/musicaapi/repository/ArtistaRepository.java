package br.com.magna.musicaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.magna.musicaapi.entity.artista.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long>{
	
	@Query("""
			select A.nome, AI.nome
			from Artista A
			join A.integrante AI
			where
			A.id = :artistaId
			and
			AI.id in :integranteId
			""")
	Object integranteJaNoArtista(Long artistaId, List<Long> integranteId);
}