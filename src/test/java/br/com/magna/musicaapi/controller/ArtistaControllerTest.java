package br.com.magna.musicaapi.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.magna.musicaapi.dto.artista.AtualizarArtistaDTO;
import br.com.magna.musicaapi.entity.artista.Artista;
import br.com.magna.musicaapi.entity.integrante.Integrante;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ArtistaControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;	
	
	@Test
	void cadastrarTest() {
		List<Integrante> integrantes = new ArrayList<>();
		
		Integrante integrante = new Integrante();
		integrante.setFuncaoIntegrante("Vocalista");
		integrante.setNomeIntegrante("Bruce");
		integrantes.add(integrante);
		
		Artista artista = new Artista();

		artista.setNome("Nome");
		artista.setIntegrante(integrantes);
		artista.setDataCriacao(LocalDate.of(1978, 12, 13));
		artista.setPaisOrigem("Brasil");
		
		ResponseEntity<Artista> response = restTemplate.postForEntity("/artista", artista, Artista.class);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void atualizarTest() {
			AtualizarArtistaDTO artista = new AtualizarArtistaDTO();
			artista.setId(3L);
			artista.setNome("Nome");
			artista.setDataCriacao(LocalDate.of(1978, 12, 13));
			artista.setPaisOrigem("Brasil");
			
			ResponseEntity<String> response = restTemplate.exchange("/artista", HttpMethod.PUT,
					new HttpEntity<>(artista), String.class);
			Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void excluirTest() {
		restTemplate.delete("/artista/deletar/2");
		ResponseEntity<Artista> response = restTemplate.getForEntity("/artista/2", Artista.class);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
//	@Test
//	void removerIntegranteTest() {
//		ids.add(1L);
//		ids.add(2L);
//		ids.add(3L);
//		ArtistaIntegranteDTO dados = new ArtistaIntegranteDTO(1L, ids);
//		ResponseEntity<String> response = restTemplate.postForEntity("/artista/remover/integrante", dados, String.class);
//		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
//	}
//	
//	@Test
//	void adicionarIntegranteTest() {
//		ids.add(4L);
//		ArtistaIntegranteDTO dados = new ArtistaIntegranteDTO(1L, ids);
//		ResponseEntity<String> response = restTemplate.postForEntity("/artista/adicionar/integrante", dados, String.class);
//		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
//	}
}