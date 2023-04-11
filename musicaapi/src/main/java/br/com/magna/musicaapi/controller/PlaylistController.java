package br.com.magna.musicaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.magna.musicaapi.dto.playlist.MusicaPlaylistDTO;
import br.com.magna.musicaapi.dto.playlist.PlaylistDTO;
import br.com.magna.musicaapi.entity.playlist.Playlist;
import br.com.magna.musicaapi.repository.PlaylistRepository;
import br.com.magna.musicaapi.services.PlaylistServices;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/playlist")
public class PlaylistController {
	
	@Autowired
	private PlaylistRepository playlistRepository;
		
	@Autowired 
	private PlaylistServices playlistServices;
	
	@PostMapping
	@Transactional
	public void criarPlaylist(@RequestBody @Valid PlaylistDTO dados) {
		Playlist playlist = playlistServices.criarPlaylist(dados);
		playlistRepository.save(playlist);
	}
	
	@PostMapping("/adicionar")
	@Transactional
	public void adicionarMusicasNaPlaylist(@RequestBody MusicaPlaylistDTO dados) {
		playlistServices.adicionarMusica(dados);
	}
	
	@PostMapping("/remover")
	@Transactional
	public void removerMusicasNaPlaylist(@RequestBody MusicaPlaylistDTO dados) {
		playlistServices.removerMusica(dados);
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public void deletarPlaylist(@PathVariable Long id){
		playlistRepository.deleteById(id);
	}
}