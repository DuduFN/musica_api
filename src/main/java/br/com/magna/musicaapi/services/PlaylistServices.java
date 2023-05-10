package br.com.magna.musicaapi.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magna.musicaapi.dto.playlist.MusicaPlaylistDTO;
import br.com.magna.musicaapi.entity.musica.Musica;
import br.com.magna.musicaapi.entity.playlist.Playlist;
import br.com.magna.musicaapi.repository.MusicaRepository;
import br.com.magna.musicaapi.repository.PlaylistRepository;
import br.com.magna.musicaapi.repository.historic.PlaylistHistRepository;
import br.com.magna.musicaapi.util.conversor.PlaylistConversor;

@Service
public class PlaylistServices {
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired
	private PlaylistHistRepository histRepository;
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	@Autowired
	private PlaylistConversor conversor;
	
	public Playlist criarPlaylist(Playlist dados) {
		Playlist playlist = new Playlist();	
		
		playlist.setNome(dados.getNome());
		playlist.setDescricao(dados.getDescricao());
		playlist.setDataCriacao(LocalDate.now());
		playlist.setTimestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()));
		playlist.setUser("USUARIO");
		
		playlistRepository.save(playlist);
		histRepository.save(conversor.converterPlaylist(playlist));
		
		return playlist;
	}
	
	public Playlist atualizar(Playlist dados) {
		Playlist playlist = playlistRepository.getReferenceById(dados.getId());
		
		playlist.setNome(dados.getNome());
		playlist.setDescricao(dados.getDescricao());
		playlist.setTimestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()));
		playlist.setUser("USUARIO");
		
		playlistRepository.save(playlist);
		histRepository.save(conversor.converterPlaylist(playlist));
		return playlist;
	}
	
	public void adicionarMusica(MusicaPlaylistDTO dados) {
		
		Playlist playlist = playlistRepository.getReferenceById(dados.playlistId());
		Musica novaMusica = musicaRepository.getReferenceById(dados.musicaId());
		playlist.adicionarMusica(novaMusica);
		
	}
	
	public void removerMusica(MusicaPlaylistDTO dados) {
		
		Playlist playlist = playlistRepository.getReferenceById(dados.playlistId());
		Musica novaMusica = musicaRepository.getReferenceById(dados.musicaId());
		playlist.removerMusica(novaMusica);
		
	}
	
	public void excluir(Long id) {
		musicaRepository.deleteById(id);
	}
	
	public Page<Playlist> listar(Pageable paginacao) {
		return playlistRepository.findAll(paginacao);
	}
	
	public Page<Playlist> pesquisarPeloNome(Pageable paginacao, String nome) {
		return playlistRepository.acharPeloNome(paginacao, nome);
	}
}