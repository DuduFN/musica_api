package br.com.magna.musicaapi.entity.musica.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magna.musicaapi.dto.musica.MusicaDTO;
import br.com.magna.musicaapi.infra.ValidacaoException;
import br.com.magna.musicaapi.repository.ArtistaRepository;
import br.com.magna.musicaapi.repository.MusicaRepository;

@Component
public class ValidarCadastroMusicaDuplicada {
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	public void validar(MusicaDTO dados) {
		var artista = artistaRepository.getReferenceById(dados.getArtista());
		var musicaJaExiste = musicaRepository.existeMusicaPeloNomeEArtista(dados.getNome(), artista.getNome());
		
		if(musicaJaExiste != null){
			throw new ValidacaoException("Essa musica já foi cadastrada");	
		}
	}
}