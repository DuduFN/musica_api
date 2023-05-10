package br.com.magna.musicaapi.dto.musica;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotNull;

public record MusicaCompositorDTO(
		
		@NotNull
		@JsonAlias({"musica_id", "id_musica"})
		Long musicaId,
		
		@NotNull
		@JsonAlias({"compositor_id", "id_compositor"})
		Long compositorId
		
		) {
}
