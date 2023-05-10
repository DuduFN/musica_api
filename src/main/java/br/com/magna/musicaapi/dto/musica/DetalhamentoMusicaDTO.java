package br.com.magna.musicaapi.dto.musica;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.magna.musicaapi.entity.musica.Musica;

public record DetalhamentoMusicaDTO(
		Long id,
		String nome,
		String artista,
		String letra,
		LocalTime duracao,
		String album,
		LocalDate lancamento
//		String genero
		) {

	public DetalhamentoMusicaDTO(Musica musica) {
		this(
			musica.getId(),
			musica.getNome(),
			musica.getArtista(),
			musica.getLetra(),
			musica.getDuracao(),
			musica.getAlbum(),
			musica.getLancamento()
//			musica.getGenero()
			);
	}
}
