package br.com.magna.musicaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.magna.musicaapi.entity.integrante.Integrante;
import br.com.magna.musicaapi.services.IntegranteServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/integrante")
public class IntegranteController {
	
	@Autowired
	IntegranteServices integranteServices;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Integrante> cadastrarIntegrante(@RequestBody @Valid Integrante dados,
			UriComponentsBuilder uriBuilder) {
		Integrante integrante = integranteServices.criarIntegrante(dados);
		
		var uri = uriBuilder.path("/musicas/{id}").buildAndExpand(integrante.getId()).toUri();
		return ResponseEntity.created(uri).body(integrante);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<Integrante> atualizarIntegrante(@RequestBody @Valid Integrante dadosAtualizar) {
		return ResponseEntity.ok(integranteServices.atualizar(dadosAtualizar));
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<Long> deletarIntegrante(@PathVariable Long id){
		integranteServices.excluir(id);
		return ResponseEntity.noContent().build();
	}
}