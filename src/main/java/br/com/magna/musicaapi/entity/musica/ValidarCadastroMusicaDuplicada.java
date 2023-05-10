package br.com.magna.musicaapi.entity.musica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magna.musicaapi.dto.musica.CadastroMusicaDTO;
import br.com.magna.musicaapi.infra.ValidacaoException;
import br.com.magna.musicaapi.repository.MusicaRepository;

@Component
public class ValidarCadastroMusicaDuplicada {
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	public void validar(CadastroMusicaDTO dados) {
		var musicaJaExiste = musicaRepository.existeMusicaPeloNomeEArtista(dados.nome(), dados.artista());
		if(musicaJaExiste != null){
			throw new ValidacaoException("Essa musica já foi cadastrada");
		}
	}
}