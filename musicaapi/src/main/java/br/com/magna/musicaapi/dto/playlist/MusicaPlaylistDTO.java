package br.com.magna.musicaapi.dto.playlist;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotNull;

public record MusicaPlaylistDTO (
		
		@NotNull
		@JsonAlias({"palylist_id", "id_playlist"})
		Long playlistId,
		
		@NotNull
		@JsonAlias({"musica_id", "id_musica"}) 
		Long musicaId
		
		){
}
