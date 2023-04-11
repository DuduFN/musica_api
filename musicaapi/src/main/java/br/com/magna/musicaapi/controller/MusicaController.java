package br.com.magna.musicaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import br.com.magna.musicaapi.dto.musica.CadastroMusicaDTO;
import br.com.magna.musicaapi.dto.musica.DetalhamentoMusicaDTO;
import br.com.magna.musicaapi.dto.musica.ListagemMusicaDTO;
import br.com.magna.musicaapi.dto.musica.MusicaCompositorDTO;
import br.com.magna.musicaapi.dto.musica.MusicaProdutorDTO;
import br.com.magna.musicaapi.entity.musica.Musica;
import br.com.magna.musicaapi.repository.MusicaRepository;
import br.com.magna.musicaapi.services.MusicaServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/musicas")
public class MusicaController {
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	@Autowired
	MusicaServices musicaServices;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DetalhamentoMusicaDTO> cadastrarMusica(@RequestBody @Valid CadastroMusicaDTO dados, UriComponentsBuilder uriBuilder) {
		Musica musica = musicaServices.criarMusica(dados);
		musicaRepository.save(musica);
		var uri = uriBuilder.path("/musicas/{id}").buildAndExpand(musica.getId()).toUri();
		return ResponseEntity.created(uri).body(new DetalhamentoMusicaDTO(musica));
	}
	
	@PostMapping("/produtor")
	@Transactional
	public void relacionarProdutorComMusica(@RequestBody  @Valid MusicaProdutorDTO dados) {
		musicaServices.relacionarProdutor(dados);
	}
	
	@PostMapping("/compositor")
	@Transactional
	public void relacionarCompositorComMusica(@RequestBody @Valid MusicaCompositorDTO dados) {
		musicaServices.relacionarCompositor(dados);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DetalhamentoMusicaDTO> atualizar(@RequestBody @Valid AtualizarMusicaDTO dadosAtualizar) {
		var musica = musicaRepository.getReferenceById(dadosAtualizar.id());
		musica.atualizarInformacoes(dadosAtualizar);
		return ResponseEntity.ok(new DetalhamentoMusicaDTO(musica));
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<Long> deletarMusica(@PathVariable Long id){
		musicaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/desativar/{id}")
	@Transactional
	public ResponseEntity<Long> desativarMusica(@PathVariable Long id){
		var musica = musicaRepository.getReferenceById(id);
		musica.setAtivo(false);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhamentoMusicaDTO> mostrarMusicaPorId(@PathVariable Long id){
		var musica = musicaRepository.getReferenceById(id);
		return ResponseEntity.ok(new DetalhamentoMusicaDTO(musica));
	}

	@GetMapping
	public ResponseEntity<Page<ListagemMusicaDTO>> listarTodasMusicas(Pageable paginacao){
		var page = musicaRepository.findAllByAtivoTrue(paginacao).map(ListagemMusicaDTO::new);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/pesquisa/{nome}")
	public ResponseEntity<Page<ListagemMusicaDTO>> pesquisarMusicaPeloNome(Pageable paginacao, @PathVariable String nome) {
		var page = musicaRepository.acharMusicaPeloNome(paginacao, nome).map(ListagemMusicaDTO::new);
		return ResponseEntity.ok(page);
	}
}