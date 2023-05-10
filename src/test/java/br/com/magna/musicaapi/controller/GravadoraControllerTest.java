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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.magna.musicaapi.entity.gravadora.Gravadora;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class GravadoraControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void cadastrarTest() {
		Gravadora dados = new Gravadora();
		dados.setNome("Nome");
		dados.setDistribuidora("distribuidora");
		ResponseEntity<String> response = restTemplate.postForEntity("/gravadora", dados, String.class);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void atualizarTest() {
			Gravadora dados = new Gravadora();
			dados.setId(1L);
			dados.setNome("Nome");
			dados.setDistribuidora("distribuidora");
			ResponseEntity<Gravadora> response = restTemplate.exchange("/gravadora", HttpMethod.PUT,
					new HttpEntity<>(dados), Gravadora.class);
			Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void excluirTest() {
		restTemplate.delete("/gravadora/deletar/2");
		ResponseEntity<Gravadora> response = restTemplate.getForEntity("/gravadora/2", Gravadora.class);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}