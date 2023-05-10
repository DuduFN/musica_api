package br.com.magna.musicaapi.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.magna.musicaapi.dto.playlist.MusicaPlaylistDTO;
import br.com.magna.musicaapi.entity.playlist.Playlist;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PlaylistControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void cadastrarTest() {
		Playlist playlist = new Playlist();
		playlist.setNome(("Nome"));
		playlist.setDescricao(("descricao"));
		ResponseEntity<String> response = restTemplate.postForEntity("/playlist", playlist, String.class);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void atualizarTest() {
			Playlist playlist = new Playlist();
			playlist.setId(1L);
			playlist.setNome(("Nome"));
			playlist.setDescricao(("descricao"));
			ResponseEntity<Playlist> response = restTemplate.exchange("/playlist", HttpMethod.PUT,
					new HttpEntity<>(playlist), Playlist.class);
			Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void excluirTest() {
		restTemplate.delete("/playlist/deletar/3");
		ResponseEntity<Playlist> response = restTemplate.getForEntity("/deletar/3", Playlist.class);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void removerMusicaTest() {
		MusicaPlaylistDTO dados = new MusicaPlaylistDTO(1L, 1L);
		ResponseEntity<String> response = restTemplate.postForEntity("/playlist/remover/musica", dados, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void adicionarMusicaTest() {
		MusicaPlaylistDTO dados = new MusicaPlaylistDTO(1L, 2L);
		ResponseEntity<String> response = restTemplate.postForEntity("/playlist/adicionar/musica", dados, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void pesquisarTest() {
		ResponseEntity<String> response = restTemplate.exchange("/playlist/pesquisa/dormir", 
				HttpMethod.GET,null, String.class);
				Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
				Assert.assertNotNull(response.getBody());
				Assert.assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
	}
	
	@Test
	void listarTest() {
		ResponseEntity<String> response = restTemplate.exchange("/playlist", 
		HttpMethod.GET,null, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
	}

}
