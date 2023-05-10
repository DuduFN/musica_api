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

import br.com.magna.musicaapi.dto.produtor.AtualizarProdutorDTO;
import br.com.magna.musicaapi.dto.produtor.DetalhamentoProdutorDTO;
import br.com.magna.musicaapi.dto.produtor.ProdutorDTO;
import br.com.magna.musicaapi.entity.produtor.Produtor;
import br.com.magna.musicaapi.repository.ProdutorRepository;
import br.com.magna.musicaapi.services.ProdutorServices;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/produtor")
public class ProdutorController {
	
	
	@Autowired
	ProdutorRepository produtorRepository;
	
	@Autowired
	ProdutorServices produtoServices;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DetalhamentoProdutorDTO> cadastrarProdutor(@RequestBody @Valid ProdutorDTO dados, UriComponentsBuilder uriBuilder) {
		Produtor produtor = produtoServices.criarProdutor(dados);
		produtorRepository.save(produtor);
		
		var uri = uriBuilder.path("/produtor/{id}").buildAndExpand(produtor.getProdutorId()).toUri();
		return ResponseEntity.created(uri).body(new DetalhamentoProdutorDTO(produtor));
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<Long> deletarProdutor(@PathVariable Long id){
		produtorRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DetalhamentoProdutorDTO> atualizarCompositor(@RequestBody @Valid AtualizarProdutorDTO dadosAtualizar) {
		Produtor produtor = produtorRepository.getReferenceById(dadosAtualizar.id());
		produtor.atualizarInformacoes(dadosAtualizar);
		return ResponseEntity.ok(new DetalhamentoProdutorDTO(produtor));
	}
}
