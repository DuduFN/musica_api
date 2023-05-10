package br.com.magna.musicaapi.entity.album.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magna.musicaapi.dto.album.AlbumDTO;
import br.com.magna.musicaapi.infra.ValidacaoException;
import br.com.magna.musicaapi.repository.AlbumRepository;
import br.com.magna.musicaapi.repository.ArtistaRepository;

@Component
public class ValidaCriacaoAlbumDuplicado {

	@Autowired
	AlbumRepository albumRepository;
	
	@Autowired
	ArtistaRepository artistaRepository;
	
	public void validar(AlbumDTO dados) {
		var artista = artistaRepository.getReferenceById(dados.getArtista());
		var albumJaExiste = albumRepository.existeAlbumPeloNomeEArtista(dados.getNome(), artista.getNome());
		
		if(albumJaExiste != null){
			throw new ValidacaoException("Esse album já foi cadastrado");
		}
	}
}