package br.com.magna.musicaapi.dto.artista;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotNull;

public record ArtistaIntegranteDTO(
		@NotNull @JsonAlias({"artista_id", "id_artista"}) Long artistaId,
		@NotNull @JsonAlias({"integrante_id", "id_integrante"}) List<Long> integranteId
		) {

}
