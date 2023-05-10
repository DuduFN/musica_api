package br.com.magna.musicaapi.dto.produtor;

import jakarta.validation.constraints.NotNull;

public record AtualizarProdutorDTO(
		
		@NotNull Long id,
		String gravadora,
		String estudio,
		String distribuidora,
		String producao
		
		) {
}
