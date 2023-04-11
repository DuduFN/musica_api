package br.com.magna.musicaapi.dto.album;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlbumDTO (
		
		@NotBlank String nome, 
		@NotBlank String artista,
		@NotNull LocalDate lancamento
		
		) {
}
