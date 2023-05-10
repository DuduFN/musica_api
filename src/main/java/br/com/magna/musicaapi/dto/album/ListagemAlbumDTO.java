package br.com.magna.musicaapi.dto.album;

import java.util.List;

import br.com.magna.musicaapi.dto.musica.ListagemMusicaDTO;
import br.com.magna.musicaapi.entity.album.Album;

public record ListagemAlbumDTO(
		Long id,
		String nome,
		String artista,
		List<ListagemMusicaDTO> musicas
		) {
	
	public ListagemAlbumDTO(Album album) {
		this(	
			album.getAlbumId(),	
			album.getNome(),
			album.getArtista(),
			album.getMusicas()
			);
	}
}
