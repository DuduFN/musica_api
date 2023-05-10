package br.com.magna.musicaapi.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

import br.com.magna.musicaapi.dto.musica.AtualizarMusicaDTO;
import br.com.magna.musicaapi.dto.musica.MusicaDTO;
import br.com.magna.musicaapi.entity.musica.Musica;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class MusicaControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void cadastrarTest() {
		List<String> subgeneros = new ArrayList<>();
		subgeneros.add("Hard Rock");
		
		MusicaDTO musica = new MusicaDTO();
		
		musica.setNome("Nova Musica");
		musica.setArtista(1L);
		musica.setLetra("Letra");
		musica.setDuracao(LocalTime.of(0, 03, 56));
		musica.setAlbum(1L);
		musica.setLancamento(LocalDate.of(1999, 05, 03));
		musica.setGenero("ROCK");
		musica.setSubgenero(subgeneros);
		musica.setEstudio(1L);
		musica.setGravadora(1L);
		
		ResponseEntity<String> response = restTemplate.postForEntity("/musica", musica, String.class);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void atualizarTest() {
			AtualizarMusicaDTO musica = new AtualizarMusicaDTO();
			List<String> subgeneros = new ArrayList<>();
			subgeneros.add("Hard Rock");
			
			musica.setId(1L);
			musica.setNome("Musica");
			musica.setArtista(1L);
			musica.setLetra("Letra");
			musica.setDuracao(LocalTime.of(0, 03, 56));
			musica.setAlbum(1L);
			musica.setLancamento(LocalDate.of(1999, 05, 03));
			musica.setGenero("ROCK");
			musica.setSubgenero(subgeneros);
			musica.setAtivo(true);
			musica.setEstudio(1L);
			musica.setGravadora(1L);
			
			ResponseEntity<String> response = restTemplate.exchange("/musica", HttpMethod.PUT,
					new HttpEntity<>(musica), String.class);
			Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void desativarTest() {
		restTemplate.delete("/musica/desativar/1");
		ResponseEntity<Musica> response = restTemplate.getForEntity("/desativar/1", Musica.class);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void excluirTest() {
		restTemplate.delete("/musica/deletar/3");
		ResponseEntity<Musica> response = restTemplate.getForEntity("/deletar/3", Musica.class);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	

	@ParameterizedTest
	@ValueSource(strings = {"/musica", "/musica/pesquisa/Time", "/musica/1"})
	void listarTest(String endpoints) {
		ResponseEntity<String> response = restTemplate.exchange(endpoints, 
		HttpMethod.GET,null, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
	}
}