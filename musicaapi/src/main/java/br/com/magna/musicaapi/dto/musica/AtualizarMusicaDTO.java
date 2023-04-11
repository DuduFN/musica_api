package br.com.magna.musicaapi.dto.musica;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public record AtualizarMusicaDTO(
		
		@NotNull Long id,
		String nome,
		String artista,
		String letra,
		LocalTime duracao,
		String album,
		LocalDate lancamento,
		String genero
		
		) {
}
