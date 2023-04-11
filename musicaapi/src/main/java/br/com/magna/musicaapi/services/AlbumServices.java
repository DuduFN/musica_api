package br.com.magna.musicaapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magna.musicaapi.dto.album.AlbumDTO;
import br.com.magna.musicaapi.dto.album.MusicaAlbumDTO;
import br.com.magna.musicaapi.entity.album.Album;
import br.com.magna.musicaapi.entity.album.validacoes.ValidaCriacaoAlbumDuplicado;
import br.com.magna.musicaapi.entity.album.validacoes.ValidarSeMusicaPertenceAoAlbum;
import br.com.magna.musicaapi.entity.musica.Musica;
import br.com.magna.musicaapi.repository.AlbumRepository;
import br.com.magna.musicaapi.repository.MusicaRepository;

@Service
public class AlbumServices {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	@Autowired
	private ValidarSeMusicaPertenceAoAlbum validarMusica;
	
	@Autowired
	private ValidaCriacaoAlbumDuplicado validarAlbum;
	
	public Album criarAlbum(AlbumDTO dados) {
		validarAlbum.validar(dados);
		Album album = new Album();
		
		album.setNome(dados.nome());
		album.setArtista(dados.artista());
		album.setLancamento(dados.lancamento());
		
		return album;
	}
	
	public void adicionarMusica(MusicaAlbumDTO dados) {
		validarMusica.validar(dados);
		
		Album album = albumRepository.getReferenceById(dados.albumId());
		Musica novaMusica = musicaRepository.getReferenceById(dados.musicaId());
		album.adicionarMusica(novaMusica);
		
	}
	
	public void removerMusica(MusicaAlbumDTO dados) {
		
		Album album = albumRepository.getReferenceById(dados.albumId());
		Musica novaMusica = musicaRepository.getReferenceById(dados.musicaId());
		album.removerMusica(novaMusica);
	}
}
