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

import br.com.magna.musicaapi.dto.musica.AtualizarMusicaDTO;
import br.com.magna.musicaapi.dto.musica.MusicaDTO;
import br.com.magna.musicaapi.entity.musica.Musica;
import br.com.magna.musicaapi.services.MusicaServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/musica")
public class MusicaController {

	@Autowired
	MusicaServices musicaServices;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Musica> cadastrarMusica(@RequestBody @Valid MusicaDTO dados,
			UriComponentsBuilder uriBuilder) {
		Musica musica = musicaServices.criar(dados);
		var uri = uriBuilder.path("/musica/{id}").buildAndExpand(musica.getId()).toUri();
		return ResponseEntity.created(uri).body(musica);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<Musica> atualizar(@RequestBody @Valid AtualizarMusicaDTO dadosAtualizar) {
		return ResponseEntity.ok(musicaServices.atualizar(dadosAtualizar));
	}

	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<Long> deletarMusica(@PathVariable Long id) {
		musicaServices.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/desativar/{id}")
	@Transactional
	public ResponseEntity<Long> desativarMusica(@PathVariable Long id) {
		musicaServices.desativar(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Musica> mostrarMusicaPorId(@PathVariable Long id) {
		return ResponseEntity.ok(musicaServices.pesquisarPorId(id));
	}

	@GetMapping
	public ResponseEntity<Page<Musica>> listarTodasMusicas(
			@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		return ResponseEntity.ok(musicaServices.pesquisarTodos(paginacao));
	}

	@GetMapping("/pesquisa/{nome}")
	public ResponseEntity<Page<Musica>> pesquisarMusicaPeloNome(
			@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao, @PathVariable String nome) {
		return ResponseEntity.ok(musicaServices.pesquisarPeloNome(paginacao, nome));
	}
}