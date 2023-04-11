 package br.com.magna.musicaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.magna.musicaapi.dto.album.AlbumDTO;
import br.com.magna.musicaapi.dto.album.DetalhamentoAlbumDTO;
import br.com.magna.musicaapi.dto.album.ListagemAlbumDTO;
import br.com.magna.musicaapi.dto.album.MusicaAlbumDTO;
import br.com.magna.musicaapi.entity.album.Album;
import br.com.magna.musicaapi.repository.AlbumRepository;
import br.com.magna.musicaapi.services.AlbumServices;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/album")
public class AlbumController {
	
	@Autowired
	private AlbumRepository albumRepository;
		
	@Autowired
	private AlbumServices albumServices;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DetalhamentoAlbumDTO> criarAlbum(@RequestBody @Valid AlbumDTO dados, UriComponentsBuilder uriBuilder) {
		Album album = albumServices.criarAlbum(dados);
		albumRepository.save(album);
		
		var uri = uriBuilder.path("/album/{id}").buildAndExpand(album.getAlbumId()).toUri();
		return ResponseEntity.created(uri).body(new DetalhamentoAlbumDTO(album));
	}
	
	@PostMapping("/adicionar")
	@Transactional
	public void adicionarMusicasAlbum(@RequestBody @Valid MusicaAlbumDTO dados) {
		albumServices.adicionarMusica(dados);
	}
	
	@PostMapping("/remover")
	@Transactional
	public void removerMusicasAlbum(@RequestBody @Valid MusicaAlbumDTO dados) {
		albumServices.removerMusica(dados);
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<Long> deletarAlbum (@PathVariable Long id){
		albumRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<Page<ListagemAlbumDTO>> listar(Pageable paginacao){
		var page = albumRepository.findAll(paginacao).map(ListagemAlbumDTO::new);
		return ResponseEntity.ok(page) ;
	}
}