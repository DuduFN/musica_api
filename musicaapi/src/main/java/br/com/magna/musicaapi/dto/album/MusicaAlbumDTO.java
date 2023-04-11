package br.com.magna.musicaapi.dto.album;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotNull;

public record MusicaAlbumDTO (
		
		@NotNull @JsonAlias({"album_id", "id_album"}) Long albumId,
		
		@NotNull @JsonAlias({"musica_id", "id_musica"}) Long musicaId
		
		){
}
