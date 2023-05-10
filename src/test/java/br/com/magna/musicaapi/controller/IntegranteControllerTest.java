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

import br.com.magna.musicaapi.entity.integrante.Integrante;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class IntegranteControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void cadastrarTest() {
		Integrante integrante = new Integrante();
		integrante.setFuncaoIntegrante("Vocalista");
		integrante.setNomeIntegrante("Bruce");
		
		ResponseEntity<String> response = restTemplate.postForEntity("/integrante", integrante, String.class);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void excluirTest() {
		restTemplate.delete("/integrante/deletar/2");
		ResponseEntity<Integrante> response = restTemplate.getForEntity("/integrante/2", Integrante.class);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void atualizarTest() {
			Integrante integrate = new Integrante();
			integrate.setId(1L);
			integrate.setNomeIntegrante("Tiao");
			integrate.setFuncaoIntegrante("tecladista");
			ResponseEntity<Integrante> response = restTemplate.exchange("/integrante", HttpMethod.PUT,
					new HttpEntity<>(integrate), Integrante.class);
			Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
}