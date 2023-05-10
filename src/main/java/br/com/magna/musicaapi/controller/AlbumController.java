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

import br.com.magna.musicaapi.dto.album.AlbumDTO;
import br.com.magna.musicaapi.dto.album.AtualizarAlbumDTO;
import br.com.magna.musicaapi.dto.album.MusicaAlbumDTO;
import br.com.magna.musicaapi.entity.album.Album;
import br.com.magna.musicaapi.services.AlbumServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/album")
public class AlbumController {

	@Autowired
	private AlbumServices albumServices;

	@PostMapping
	@Transactional
	public ResponseEntity<Album> criarAlbum(@RequestBody @Valid AlbumDTO dados,
			UriComponentsBuilder uriBuilder) {
		
		Album album = albumServices.criar(dados);
		
		var uri = uriBuilder.path("/album/{id}").buildAndExpand(album.getId()).toUri();
		return ResponseEntity.created(uri).body(album);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<Album> atualizar(@RequestBody @Valid AtualizarAlbumDTO dadosAtualizar) {
		return ResponseEntity.ok(albumServices.atualizar(dadosAtualizar));
	}
	
	@GetMapping
	public ResponseEntity<Page<Album>> listarAlbuns(
			@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		return ResponseEntity.ok(albumServices.listar(paginacao));
	}

	@PostMapping("/remover")
	@Transactional
	public  ResponseEntity<MusicaAlbumDTO> removerMusicasAlbum(@RequestBody @Valid MusicaAlbumDTO dados) {
		albumServices.removerMusica(dados);
		return ResponseEntity.ok(dados);
	}

	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<Long> deletarAlbum(@PathVariable Long id) {
		albumServices.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/pesquisa/{nome}")
	public ResponseEntity<Page<Album>> pesquisarPeloNome(
			@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao, @PathVariable String nome) {
		return ResponseEntity.ok(albumServices.pesquisar(paginacao, nome));
	}
}