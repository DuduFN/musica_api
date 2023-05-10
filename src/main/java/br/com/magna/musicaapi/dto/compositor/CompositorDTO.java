package br.com.magna.musicaapi.dto.compositor;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;

public record CompositorDTO(
		
		@NotBlank @JsonAlias({"nome_integrante", "integrante_nome"}) String nomeIntegrante,
		@NotBlank @JsonAlias({"funcao_integrante", "integrante_funcao"}) String funcaoIntegrante
		
		) {
}
