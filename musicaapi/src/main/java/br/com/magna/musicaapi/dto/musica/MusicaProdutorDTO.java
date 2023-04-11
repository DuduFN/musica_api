package br.com.magna.musicaapi.dto.musica;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotNull;

public record MusicaProdutorDTO(
		
		@NotNull
		@JsonAlias({"musica_id", "id_musica"})
		Long musicaId,
		
		@NotNull
		@JsonAlias({"produtor_id", "id_produtor"})
		Long produtorId
		
		) {
}
