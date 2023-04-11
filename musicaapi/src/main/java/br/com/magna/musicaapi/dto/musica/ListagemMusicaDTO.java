package br.com.magna.musicaapi.dto.musica;

import java.time.LocalTime;

import br.com.magna.musicaapi.entity.musica.Musica;

public record ListagemMusicaDTO(
		Long id,
		String nome,
		String artista,
		LocalTime duracao,
		String album
//		String genero
		) {
	
	public ListagemMusicaDTO(Musica musica) {
		this(
			musica.getId(),
			musica.getNome(),
			musica.getArtista(),
			musica.getDuracao(),
			musica.getAlbum()
//			musica.getGenero()
			);
	}
}
