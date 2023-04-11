package br.com.magna.musicaapi.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magna.musicaapi.dto.musica.CadastroMusicaDTO;
import br.com.magna.musicaapi.dto.musica.MusicaCompositorDTO;
import br.com.magna.musicaapi.dto.musica.MusicaProdutorDTO;
import br.com.magna.musicaapi.entity.compositor.Compositor;
import br.com.magna.musicaapi.entity.musica.Musica;
import br.com.magna.musicaapi.entity.musica.ValidarCadastroMusicaDuplicada;
import br.com.magna.musicaapi.entity.produtor.Produtor;
import br.com.magna.musicaapi.repository.CompositorRepository;
import br.com.magna.musicaapi.repository.MusicaRepository;
import br.com.magna.musicaapi.repository.ProdutorRepository;

@Service
public class MusicaServices {
	
	@Autowired
	private ValidarCadastroMusicaDuplicada validarMusica;
	
	@Autowired
	MusicaRepository musicaRepository;
	
	@Autowired
	ProdutorRepository produtorRepository;
	
	@Autowired
	CompositorRepository compositorRepository;
	
	public Musica criarMusica(CadastroMusicaDTO dados) {
		validarMusica.validar(dados);
		Musica musica = new Musica();
		
		musica.setNome(dados.nome());
		musica.setArtista(dados.artista());
		musica.setDuracao(dados.duracao());
		musica.setLetra(dados.letra());
		musica.setAlbum(dados.album());
		musica.setLancamento(dados.lancamento());
		musica.setGenero(dados.genero());
		musica.setTimestamp(LocalDateTime.now());
		
		return musica;
	}

	public void relacionarProdutor(MusicaProdutorDTO dados) {
		Musica musica = musicaRepository.getReferenceById(dados.musicaId());
		Produtor produtor = produtorRepository.getReferenceById(dados.produtorId());
		musica.relacionarProdutor(produtor);
	}
	
	public void relacionarCompositor(MusicaCompositorDTO dados) {
		Musica musica = musicaRepository.getReferenceById(dados.musicaId());
		Compositor compositor = compositorRepository.getReferenceById(dados.compositorId());
		musica.relacionarCompositor(compositor);
	}
}
