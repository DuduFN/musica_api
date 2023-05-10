package br.com.magna.musicaapi.entity.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magna.musicaapi.infra.ValidacaoException;
import br.com.magna.musicaapi.repository.GeneroRepository;

@Component
public class ValidarGenero {
	
	@Autowired
	GeneroRepository generoRepository;
	
	public void validar(String genero) {
		String generos = generoRepository.acharNomeGenero(genero.toUpperCase());
		if(generos == null) {
			throw new ValidacaoException("Genero não valido");
		}
	}
}
