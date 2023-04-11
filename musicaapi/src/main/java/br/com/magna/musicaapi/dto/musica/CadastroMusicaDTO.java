package br.com.magna.musicaapi.dto.musica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroMusicaDTO(
		
		@NotBlank String nome,
		@NotBlank String artista,
		@NotBlank String letra,
		@NotNull LocalTime duracao,
		@NotBlank String album,
		@NotNull LocalDate lancamento,
		@NotNull List<String> genero		
		) {
}
