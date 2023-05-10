package br.com.magna.musicaapi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.magna.musicaapi.entity.gravadora.Gravadora;
import br.com.magna.musicaapi.services.GravadoraServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/gravadora")
public class GravadoraController {
	
	@Autowired
	GravadoraServices gravadoraServices;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Gravadora> cadastrarGravadora(@RequestBody @Valid Gravadora dados,
			UriComponentsBuilder uriBuilder) {
		Gravadora gravadora = gravadoraServices.criar(dados);

		var uri = uriBuilder.path("/gravadora/{id}").buildAndExpand(gravadora.getId()).toUri();
		return ResponseEntity.created(uri).body((gravadora));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<Gravadora> atualizarEstudio(@RequestBody @Valid Gravadora dadosAtualizar) {
		return ResponseEntity.ok((gravadoraServices.atualizar(dadosAtualizar)));
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<Long> deletarEstudio(@PathVariable Long id) {
		gravadoraServices.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
