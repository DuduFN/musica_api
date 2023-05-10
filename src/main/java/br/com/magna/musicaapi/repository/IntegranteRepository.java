package br.com.magna.musicaapi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.magna.musicaapi.entity.integrante.Integrante;

public interface IntegranteRepository extends JpaRepository<Integrante, Long> {
	
	@Query("""
			select I from Integrante I
			where I in :integrante
			""")
	List<Integrante> acharIntegranteValido(List<Integrante> integrante);

}
