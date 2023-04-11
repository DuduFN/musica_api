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

import br.com.magna.musicaapi.dto.compositor.AtualizarCompositorDTO;
import br.com.magna.musicaapi.dto.compositor.CompositorDTO;
import br.com.magna.musicaapi.dto.compositor.DetalhamentoCompositorDTO;
import br.com.magna.musicaapi.entity.compositor.Compositor;
import br.com.magna.musicaapi.repository.CompositorRepository;
import br.com.magna.musicaapi.services.CompositorServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/compositor")
public class CompositorController {
	
	@Autowired
	CompositorRepository compositorRepository;
	
	@Autowired
	CompositorServices compositorServices;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DetalhamentoCompositorDTO> cadastrarCompositor(@RequestBody @Valid CompositorDTO dados, UriComponentsBuilder uriBuilder) {
		Compositor compositor = compositorServices.criarCompositor(dados);
		compositorRepository.save(compositor);
		
		var uri = uriBuilder.path("/musicas/{id}").buildAndExpand(compositor.getCompositorId()).toUri();
		return ResponseEntity.created(uri).body(new DetalhamentoCompositorDTO(compositor));
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<Long> deletarCompositor(@PathVariable Long id){
		compositorRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DetalhamentoCompositorDTO> atualizarCompositor(@RequestBody @Valid AtualizarCompositorDTO dadosAtualizar) {
		
		Compositor compositor = compositorRepository.getReferenceById(dadosAtualizar.id());
		compositor.atualizarInformacoes(dadosAtualizar);
		
		return ResponseEntity.ok(new DetalhamentoCompositorDTO(compositor));
	}

}
