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

import br.com.magna.musicaapi.entity.estudio.Estudio;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class EstudioControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void cadastrarTest() {
		Estudio dados = new Estudio();
		dados.setNome("Nome");
		dados.setLocal("local");
		ResponseEntity<String> response = restTemplate.postForEntity("/estudio", dados, String.class);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void atualizarTest() {
			Estudio dados = new Estudio();
			dados.setId(1L);
			dados.setNome("NovoNome");
			dados.setLocal("NovoLocal");
			ResponseEntity<Estudio> response = restTemplate.exchange("/estudio", HttpMethod.PUT,
					new HttpEntity<>(dados), Estudio.class);
			Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void excluirTest() {
		restTemplate.delete("/estudio/deletar/2");
		ResponseEntity<Estudio> response = restTemplate.getForEntity("/deletar/2", Estudio.class);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}
