package br.com.magna.musicaapi.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magna.musicaapi.dto.musica.AtualizarMusicaDTO;
import br.com.magna.musicaapi.dto.musica.MusicaDTO;
import br.com.magna.musicaapi.entity.album.Album;
import br.com.magna.musicaapi.entity.domain.ValidarGenero;
import br.com.magna.musicaapi.entity.musica.Musica;
import br.com.magna.musicaapi.entity.musica.validacoes.ValidarCadastroMusicaDuplicada;
import br.com.magna.musicaapi.repository.AlbumRepository;
import br.com.magna.musicaapi.repository.ArtistaRepository;
import br.com.magna.musicaapi.repository.EstudioRepository;
import br.com.magna.musicaapi.repository.GravadoraRepository;
import br.com.magna.musicaapi.repository.IntegranteRepository;
import br.com.magna.musicaapi.repository.MusicaRepository;
import br.com.magna.musicaapi.repository.historic.MusicaHistRepository;
import br.com.magna.musicaapi.util.conversor.MusicaConversor;

@Service
public class MusicaServices {

	@Autowired
	private ValidarCadastroMusicaDuplicada validarMusica;
	
	@Autowired 
	private ValidarGenero validarGenero;

	@Autowired
	MusicaRepository musicaRepository;

	@Autowired
	MusicaHistRepository histRepository;
	
	@Autowired
	IntegranteRepository integranteRepository;
	
	@Autowired
	ArtistaRepository artistaRepository;
	
	@Autowired
	EstudioRepository estudioRepository;
	
	@Autowired
	GravadoraRepository gravadoraRepository;
	
	@Autowired
	AlbumRepository albumRepository;
	
	@Autowired
	MusicaConversor conversor;
	
	public Musica criar(MusicaDTO dados) {
		validarMusica.validar(dados);
		validarGenero.validar(dados.getGenero());
		
		Musica musica = new Musica();
		Album album = albumRepository.getReferenceById(dados.getAlbum());
		
		musica.setId(musica.getId());
		musica.setNome(dados.getNome());
		musica.setArtista(artistaRepository.getReferenceById(dados.getArtista()));
		musica.setGenero(dados.getGenero());
		musica.setSubgenero(dados.getSubgenero());
		musica.setDuracao(dados.getDuracao());
		musica.setLetra(dados.getLetra());
		musica.setAlbum(album);
		album.setMusica(musica);
		musica.setLancamento(dados.getLancamento());
		musica.setEstudio(estudioRepository.getReferenceById(dados.getEstudio()));
		musica.setGravadora(gravadoraRepository.getReferenceById(dados.getGravadora()));
		musica.setTimestamp(LocalDateTime.now().atZone(ZoneId.of("UTC")));
		musica.setUser("USUSARIO");
		
		musicaRepository.save(musica);
		histRepository.save(conversor.converter(musica));
		return musica;
	}

	public Musica atualizar(AtualizarMusicaDTO dados) {
		var musica = musicaRepository.getReferenceById(dados.getId());
		
		musica.setNome(dados.getNome());
		musica.setArtista(artistaRepository.getReferenceById(dados.getArtista()));
		musica.setDuracao(dados.getDuracao());
		musica.setLetra(dados.getLetra());
		musica.setAlbum(albumRepository.getReferenceById(dados.getAlbum()));
		musica.setLancamento(dados.getLancamento());
		musica.setEstudio(estudioRepository.getReferenceById(dados.getEstudio()));
		musica.setGravadora(gravadoraRepository.getReferenceById(dados.getGravadora()));
		musica.setTimestamp(LocalDateTime.now().atZone(ZoneId.of("UTC")));
		musica.setUser("USUSARIO");
		musica.setAtivo(dados.getAtivo());
		
		musicaRepository.save(musica);
		histRepository.save(conversor.converter(musica));
		return musica;
	}

	public void excluir(Long id) {
		musicaRepository.deleteById(id);
	}
	
	public void desativar(Long id) {
		var musica = musicaRepository.getReferenceById(id);
		musica.setAtivo(false);
	}
	
	public Musica pesquisarPorId(Long id) {
		return musicaRepository.getReferenceById(id);
	}
	
	public Page<Musica> pesquisarTodos(Pageable paginacao){
		return musicaRepository.findAllByAtivoTrue(paginacao);
	}
	
	public Page<Musica> pesquisarPeloNome(Pageable paginacao, String nome) {
		return musicaRepository.acharMusicaPeloNome(paginacao, nome);
	}
}