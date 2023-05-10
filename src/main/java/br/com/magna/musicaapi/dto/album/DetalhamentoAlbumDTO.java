package br.com.magna.musicaapi.dto.album;

import java.time.LocalDate;

import br.com.magna.musicaapi.entity.album.Album;

public record DetalhamentoAlbumDTO(
		Long id,
		String nome,
		String artista,
		LocalDate lancamento
		) {
	
	public DetalhamentoAlbumDTO(Album album) {
		this(	
			album.getAlbumId(),	
			album.getNome(),
			album.getArtista(),
			album.getLancamento()
			);
	}
}
