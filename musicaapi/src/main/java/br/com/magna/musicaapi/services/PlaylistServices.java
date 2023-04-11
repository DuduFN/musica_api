package br.com.magna.musicaapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magna.musicaapi.dto.playlist.MusicaPlaylistDTO;
import br.com.magna.musicaapi.dto.playlist.PlaylistDTO;
import br.com.magna.musicaapi.entity.musica.Musica;
import br.com.magna.musicaapi.entity.playlist.Playlist;
import br.com.magna.musicaapi.repository.MusicaRepository;
import br.com.magna.musicaapi.repository.PlaylistRepository;

@Service
public class PlaylistServices {
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	
	public Playlist criarPlaylist(PlaylistDTO dados) {
		Playlist playlist = new Playlist();	
		playlist.setNome(dados.nome());
		playlist.setDescricao(dados.descricao());
		return playlist;
	}
	
	public void adicionarMusica(MusicaPlaylistDTO dados) {
		
		Playlist playlist = playlistRepository.getReferenceById(dados.playlistId());
		Musica novaMusica = musicaRepository.getReferenceById(dados.musicaId());
		playlist.adicionarMusica(novaMusica);
		
	}
	
	public void removerMusica(MusicaPlaylistDTO dados) {
		
		Playlist playlist = playlistRepository.getReferenceById(dados.playlistId());
		Musica novaMusica = musicaRepository.getReferenceById(dados.musicaId());
		playlist.removerMusica(novaMusica);
		
	}
}