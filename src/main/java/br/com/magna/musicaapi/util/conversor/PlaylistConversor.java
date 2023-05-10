package br.com.magna.musicaapi.util.conversor;

import org.springframework.stereotype.Component;

import br.com.magna.musicaapi.entity.historic.PlaylistHist;
import br.com.magna.musicaapi.entity.playlist.Playlist;

@Component
public class PlaylistConversor {
	
	public PlaylistHist converterPlaylist(Playlist playlist) {
		
		PlaylistHist playlistHist = new PlaylistHist();
		
		playlistHist.setNome(playlist.getNome());
		playlistHist.setDescricao(playlist.getDescricao());
		playlistHist.setTimestamp(playlist.getTimestamp());
		playlistHist.setUser(playlist.getUser());
		
		return playlistHist;
	}

}