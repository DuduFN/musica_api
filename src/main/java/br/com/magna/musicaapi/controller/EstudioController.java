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

import br.com.magna.musicaapi.entity.estudio.Estudio;
import br.com.magna.musicaapi.services.EstudioServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/estudio")
public class EstudioController {
	
	@Autowired
	EstudioServices estudioServices;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Estudio> cadastrarEstudio(@RequestBody @Valid Estudio dados,
			UriComponentsBuilder uriBuilder) {
		Estudio estudio = estudioServices.criar(dados);
		var uri = uriBuilder.path("/gravadora/{id}").buildAndExpand(estudio.getId()).toUri();
		return ResponseEntity.created(uri).body((estudio));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<Estudio> atualizarEstudio(@RequestBody @Valid Estudio dadosAtualizar) {
		return ResponseEntity.ok((estudioServices.atualizar(dadosAtualizar)));
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<Long> deletarEstudio(@PathVariable Long id) {
		estudioServices.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
