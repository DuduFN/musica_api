package br.com.magna.musicaapi.entity.artista.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magna.musicaapi.dto.artista.ArtistaIntegranteDTO;
import br.com.magna.musicaapi.infra.ValidacaoException;
import br.com.magna.musicaapi.repository.ArtistaRepository;

@Component
public class ValidarSeIntegranteJaPertenceAoArtista {
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	public void validar(ArtistaIntegranteDTO dados) {
		var integranteJaPertence = artistaRepository.integranteJaNoArtista(dados.artistaId(), dados.integranteId());
		
		if(integranteJaPertence != null){
			throw new ValidacaoException("O(s) integrante(s) já está no artista");
		}
	}
}
