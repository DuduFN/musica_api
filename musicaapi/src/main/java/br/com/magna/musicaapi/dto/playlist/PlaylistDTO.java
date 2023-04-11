package br.com.magna.musicaapi.dto.playlist;

import jakarta.validation.constraints.NotNull;

public record PlaylistDTO(
		
		@NotNull String nome,
		String descricao
		
		) {
}