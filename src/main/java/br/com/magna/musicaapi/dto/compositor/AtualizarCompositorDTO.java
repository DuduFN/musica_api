package br.com.magna.musicaapi.dto.compositor;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotNull;

public record AtualizarCompositorDTO(
		
		@NotNull Long id,
		@JsonAlias({"nome_integrante", "integrante_nome"}) String nomeIntegrante,
		@JsonAlias({"funcao_integrante", "integrante_funcao"}) String funcaoIntegrante
		
		) {
}
