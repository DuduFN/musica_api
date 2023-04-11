package br.com.magna.musicaapi.dto.produtor;

import jakarta.validation.constraints.NotBlank;

public record ProdutorDTO(
		@NotBlank String gravadora,
		@NotBlank String estudio,
		@NotBlank String distribuidora,
		@NotBlank String producao
		) {
}
