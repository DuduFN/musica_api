package br.com.magna.musicaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.magna.musicaapi.entity.domain.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long>{
	
	@Query("""
			select G.nome from Genero G
			where
			G.nome = :nomeGenero
			""")
	String acharNomeGenero(@Param("nomeGenero")String nomeGenero);	
}