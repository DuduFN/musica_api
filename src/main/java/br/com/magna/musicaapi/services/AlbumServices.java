package br.com.magna.musicaapi.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magna.musicaapi.dto.album.AlbumDTO;
import br.com.magna.musicaapi.dto.album.AtualizarAlbumDTO;
import br.com.magna.musicaapi.dto.album.MusicaAlbumDTO;
import br.com.magna.musicaapi.entity.album.Album;
import br.com.magna.musicaapi.entity.album.validacoes.ValidaCriacaoAlbumDuplicado;
import br.com.magna.musicaapi.entity.musica.Musica;
import br.com.magna.musicaapi.repository.AlbumRepository;
import br.com.magna.musicaapi.repository.ArtistaRepository;
import br.com.magna.musicaapi.repository.MusicaRepository;
import br.com.magna.musicaapi.repository.historic.AlbumHistRepository;
import br.com.magna.musicaapi.util.conversor.AlbumConversor;

@Service
public class AlbumServices {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	
	@Autowired
	private AlbumHistRepository albumHistRepository;
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Autowired
	private ValidaCriacaoAlbumDuplicado validarAlbum;
	
	@Autowired
	private AlbumConversor conversor;
	
	public Album criar(AlbumDTO dados) {
		validarAlbum.validar(dados);
		Album album = new Album();
		
		album.setId(album.getId());
		album.setNome(dados.getNome());
		album.setArtista(artistaRepository.getReferenceById(dados.getArtista()));
		album.setLancamento(dados.getLancamento());
		album.setTimestamp(LocalDateTime.now().atZone(ZoneId.of("UTC")));
		album.setUser("USUARIO");
		
		albumRepository.save(album);
		albumHistRepository.save(conversor.converterAlbum(album));
		
		return album;
	}
	
	public Album atualizar(AtualizarAlbumDTO dados) {
		Album album = albumRepository.getReferenceById(dados.getId());
		
		album.setNome(dados.getNome());
		album.setArtista(artistaRepository.getReferenceById(dados.getArtista()));
		album.setLancamento(dados.getLancamento());
		album.setTimestamp(LocalDateTime.now().atZone(ZoneId.of("UTC")));
		album.setUser("USUARIO");
		
		albumRepository.save(album);
		albumHistRepository.save(conversor.converterAlbum(album));
		
		return album;
	}
	
	public void removerMusica(MusicaAlbumDTO dados) {
		
		Album album = albumRepository.getReferenceById(dados.albumId());
		Musica novaMusica = musicaRepository.getReferenceById(dados.musicaId());
		
		album.removerMusica(novaMusica);
	}
	
	public void excluir(Long id) {
		albumRepository.deleteById(id);
	}
	
	public Page<Album> listar(Pageable paginacao) {
		return albumRepository.findAll(paginacao);
	}
	
	public Page<Album> pesquisar(Pageable paginacao, String nome) {
		return albumRepository.acharPeloNome(paginacao, nome);
	}
}
