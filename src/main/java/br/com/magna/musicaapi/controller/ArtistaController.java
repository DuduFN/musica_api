package br.com.magna.musicaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.magna.musicaapi.dto.artista.AtualizarArtistaDTO;
import br.com.magna.musicaapi.entity.artista.Artista;
import br.com.magna.musicaapi.services.ArtistaServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/artista")
public class ArtistaController {
	
	@Autowired
	ArtistaServices artistaServices;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Artista> cadastrarArtista(@RequestBody @Valid Artista dados,
			UriComponentsBuilder uriBuilder) {
		Artista artista = artistaServices.criar(dados);
		var uri = uriBuilder.path("/musicas/{id}").buildAndExpand(artista.getId()).toUri();
		return ResponseEntity.created(uri).body(artista);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<Artista> atualizar(@RequestBody @Valid AtualizarArtistaDTO dadosAtualizar) {
		return ResponseEntity.ok(artistaServices.atualizar(dadosAtualizar));
	}
	
	@GetMapping
	public ResponseEntity<Page<Artista>> listarAlbuns(
			@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		return ResponseEntity.ok(artistaServices.listar(paginacao));
	}
//	@PostMapping("/adicionar/integrante")
//	@Transactional
//	public ResponseEntity<ArtistaIntegranteDTO> adicionarIntegrante(@RequestBody @Valid ArtistaIntegranteDTO dados) {
//		artistaServices.adicionarIntegrante(dados);
//		return ResponseEntity.ok(dados);
//	}
//	
//	@PostMapping("/remover/integrante")
//	@Transactionalq
//	public ResponseEntity<ArtistaIntegranteDTO> removerIntegrante(@RequestBody @Valid ArtistaIntegranteDTO dados) {
//		artistaServices.removerIntegrante(dados);
//		return ResponseEntity.ok(dados);
//	}
//	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<Long> deletarArtista(@PathVariable Long id) {
		artistaServices.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
