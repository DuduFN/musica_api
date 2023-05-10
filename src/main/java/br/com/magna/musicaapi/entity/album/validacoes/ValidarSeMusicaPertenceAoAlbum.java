package br.com.magna.musicaapi.entity.album.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magna.musicaapi.dto.album.MusicaAlbumDTO;
import br.com.magna.musicaapi.entity.album.Album;
import br.com.magna.musicaapi.entity.musica.Musica;
import br.com.magna.musicaapi.infra.ValidacaoException;
import br.com.magna.musicaapi.repository.AlbumRepository;
import br.com.magna.musicaapi.repository.MusicaRepository;

@Component
public class ValidarSeMusicaPertenceAoAlbum {

	@Autowired
	MusicaRepository musicaRepository;
	
	@Autowired
	AlbumRepository albumRepository;
	
	public void validar(MusicaAlbumDTO dados) {
		
		Musica musica = musicaRepository.getReferenceById(dados.musicaId());
		Album album = albumRepository.getReferenceById(dados.albumId());
		
		if(!musica.getArtista().equalsIgnoreCase(album.getArtista())) {
			throw new ValidacaoException("A musica deve ser do mesmo artista");
		}
		
		if(!musica.getAlbum().equalsIgnoreCase(album.getNome())) {
			throw new ValidacaoException("A musica deve pertencer ao album");
		}
	}
}
