package br.com.magna.musicaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.magna.musicaapi.dto.playlist.MusicaPlaylistDTO;
import br.com.magna.musicaapi.entity.playlist.Playlist;
import br.com.magna.musicaapi.services.PlaylistServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

	@Autowired
	private PlaylistServices playlistServices;

	@PostMapping
	@Transactional
	public ResponseEntity<Playlist> criarPlaylist(@RequestBody @Valid Playlist dados,
			UriComponentsBuilder uriBuilder) {
		
		Playlist playlist = playlistServices.criarPlaylist(dados);
		
		var uri = uriBuilder.path("/playlist/{id}").buildAndExpand(playlist.getId()).toUri();
		return ResponseEntity.created(uri).body(playlist);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<Playlist> atualizar(@RequestBody @Valid Playlist dadosAtualizar) {
		return ResponseEntity.ok(playlistServices.atualizar(dadosAtualizar));
	}
	
	@GetMapping
	public ResponseEntity<Page<Playlist>> listarPlaylists(
			@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		return ResponseEntity.ok(playlistServices.listar(paginacao));
	}

	@PostMapping("/adicionar/musica")
	@Transactional
	public void adicionarMusicasPlaylist(@RequestBody @Valid MusicaPlaylistDTO dados) {
		playlistServices.adicionarMusica(dados);
	}

	@PostMapping("/remover/musica")
	@Transactional
	public void removerMusicasPlaylist(@RequestBody @Valid MusicaPlaylistDTO dados) {
		playlistServices.removerMusica(dados);
	}

	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<Long> deletarPlaylist(@PathVariable Long id) {
		playlistServices.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/pesquisa/{nome}")
	public ResponseEntity<Page<Playlist>> listarPeloNome(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao, @PathVariable String nome) {
		return ResponseEntity.ok(playlistServices.pesquisarPeloNome(paginacao, nome));
	}

}