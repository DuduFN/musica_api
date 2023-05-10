package br.com.magna.musicaapi.controller;

import java.time.LocalDate;

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

import br.com.magna.musicaapi.dto.album.AlbumDTO;
import br.com.magna.musicaapi.dto.album.AtualizarAlbumDTO;
import br.com.magna.musicaapi.dto.album.MusicaAlbumDTO;
import br.com.magna.musicaapi.entity.album.Album;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AlbumControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void cadastrarTest() {
		AlbumDTO album = new AlbumDTO();
		album.setNome("nome");
		album.setArtista(1L);
		album.setLancamento(LocalDate.of(1999, 5, 3));
		
		ResponseEntity<String> response = restTemplate.postForEntity("/album", album, String.class);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void atualizarTest() {
			AtualizarAlbumDTO album = new AtualizarAlbumDTO();
			album.setId(1L);
			album.setNome("nome");
			album.setArtista(3L);
			album.setLancamento(LocalDate.of(1999, 5, 3));
			
			ResponseEntity<String> response = restTemplate.exchange("/album", HttpMethod.PUT,
					new HttpEntity<>(album), String.class);
			Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void listarTest() {
		ResponseEntity<String> response = restTemplate.exchange("/album", 
		HttpMethod.GET,null, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
	}
	
	@Test
	void excluirTest() {
		restTemplate.delete("/album/deletar/3");
		ResponseEntity<Album> response = restTemplate.getForEntity("/deletar/3", Album.class);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void pesquisarTest() {
		ResponseEntity<String> response = restTemplate.exchange("/album/pesquisa/the", 
				HttpMethod.GET,null, String.class);
				Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
				Assert.assertNotNull(response.getBody());
				Assert.assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
	}

	@Test
	void removerMusicaTest() {
		MusicaAlbumDTO dados = new MusicaAlbumDTO(1L, 1L);
		ResponseEntity<String> response = restTemplate.postForEntity("/album/remover", dados, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
}