package br.com.magna.musicaapi.entity.album.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magna.musicaapi.dto.album.AlbumDTO;
import br.com.magna.musicaapi.infra.ValidacaoException;
import br.com.magna.musicaapi.repository.AlbumRepository;

@Component
public class ValidaCriacaoAlbumDuplicado {

	@Autowired
	AlbumRepository albumRepository;
	
	public void validar(AlbumDTO dados) {
		var albumJaExiste = albumRepository.existeAlbumPeloNomeEArtista(dados.nome(), dados.artista());
		if(albumJaExiste != null){
			throw new ValidacaoException("Esse album já foi cadastrado");
		}
	}
}
