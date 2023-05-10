package br.com.magna.musicaapi.validacoes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.magna.musicaapi.dto.album.AlbumDTO;
import br.com.magna.musicaapi.dto.artista.ArtistaDTO;
import br.com.magna.musicaapi.dto.artista.ArtistaIntegranteDTO;
import br.com.magna.musicaapi.dto.musica.MusicaDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ValidacoesTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private void criarMusicaValida() {
		List<String> subgeneros = new ArrayList<>();
		subgeneros.add("Hard Rock");
		
		MusicaDTO musica = new MusicaDTO();
		musica.setNome("Musica");
		musica.setArtista(1L);
		musica.setLetra("Letra");
		musica.setDuracao(LocalTime.of(0, 03, 56));
		musica.setAlbum(1L);
		musica.setLancamento(LocalDate.of(1999, 05, 03));
		musica.setGenero("ROCK");
		musica.setSubgenero(subgeneros);
		musica.setEstudio(1L);
		musica.setGravadora(1L);
		
		restTemplate.postForEntity("/musica", musica, String.class);
	}
	
	private void criarAlbumValido() {
		AlbumDTO album = new AlbumDTO();
		album.setNome("nome");
		album.setArtista(3L);
		album.setLancamento(LocalDate.of(1999, 5, 3));
		restTemplate.postForEntity("/album", album, String.class);
	}
	
	private void criarArtistaValido() {
		List<Long> ids = new ArrayList<>();
		ids.add(1L);
		ids.add(2L);
		ids.add(3L);
		
		ArtistaDTO artista = new ArtistaDTO();
		artista.setNome("Nome");
		artista.setIntegrante(ids);
		artista.setDataCriacao(LocalDate.of(1978, 12, 13));
		artista.setPaisOrigem("Brasil");
		restTemplate.postForEntity("/artista", artista, String.class);
	}
	
	@Test
	@DisplayName("Cadastrar - Não pode cadastrar a mesma musica duas vezes")
	void testCadastrarMusicaJaCadastrada()  {
		criarMusicaValida();
		List<String> subgeneros = new ArrayList<>();
		subgeneros.add("Hard Rock");
		MusicaDTO musica = new MusicaDTO();
		musica.setNome("Musica");
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
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assert.assertEquals("Essa musica já foi cadastrada", response.getBody());
	}	
	
	@Test
	@DisplayName("CadastrarAlbum - Não pode cadastrar o mesmo Album duas vezes")
	void testCadastrarAlbumJaCadastrada()  {
		criarAlbumValido();
		AlbumDTO album = new AlbumDTO();
		album.setNome("nome");
		album.setArtista(3L);
		album.setLancamento(LocalDate.of(1999, 5, 3));
		
		ResponseEntity<String> response = restTemplate.postForEntity("/album", album, String.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assert.assertEquals("Esse album já foi cadastrado", response.getBody());
	}	
	
	@Test
	@DisplayName("Adicionar integrante - Não pode adicionar um integrante que já pertence ao artista")
	void testIntegranteJaPerteceAoArtista()  {
		criarArtistaValido();
		
		List<Long> ids = new ArrayList<>();
		ids.add(2L);
		ArtistaIntegranteDTO integrante = new ArtistaIntegranteDTO(4L, ids);
		
		ResponseEntity<String> response = restTemplate.postForEntity("/artista/adicionar/integrante", integrante, String.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assert.assertEquals("O(s) integrante(s) já está no artista", response.getBody());

	}	
	
	@Test
	void validarGeneroTest() {
		List<String> subgeneros = new ArrayList<>();
		subgeneros.add("Hard Rock");
		
		MusicaDTO musica = new MusicaDTO();
		musica.setNome("Sem nome");
		musica.setArtista(1L);
		musica.setLetra("Letra");
		musica.setDuracao(LocalTime.of(0, 03, 56));
		musica.setAlbum(1L);
		musica.setLancamento(LocalDate.of(1999, 05, 03));
		musica.setGenero("RO");
		musica.setSubgenero(subgeneros);
		musica.setEstudio(1L);
		musica.setGravadora(1L);
		
		ResponseEntity<String> response = restTemplate.postForEntity("/musica", musica, String.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assert.assertEquals("Genero não valido", response.getBody());
	}
	
	@Test
	@DisplayName("Mostrar o erro de bad request se algum dado for nulo")
	void validacoesErroTest() {		
		List<String> subgeneros = new ArrayList<>();
		subgeneros.add("Hard Rock");
		
		MusicaDTO musica = new MusicaDTO();
		musica.setNome(null);
		musica.setArtista(1L);
		musica.setLetra("Letra");
		musica.setDuracao(LocalTime.of(0, 03, 56));
		musica.setAlbum(1L);
		musica.setLancamento(LocalDate.of(1999, 05, 03));
		musica.setGenero("ROCK");
		musica.setSubgenero(subgeneros);
		musica.setEstudio(1L);
		musica.setGravadora(1L);
		ResponseEntity<Object> response = restTemplate.postForEntity("/musica", musica, Object.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Mostrar erro de not found 404 se algum ID estiver errado")
	void validacoesErroNotFoudTest() {
		List<String> subgeneros = new ArrayList<>();
		subgeneros.add("Hard Rock");
		
		MusicaDTO musica = new MusicaDTO();
		
		musica.setNome("Musica");
		musica.setArtista(1131L);
		musica.setLetra("Letra");
		musica.setDuracao(LocalTime.of(0, 03, 56));
		musica.setAlbum(1L);
		musica.setLancamento(LocalDate.of(1999, 05, 03));
		musica.setGenero("ROCK");
		musica.setSubgenero(subgeneros);
		musica.setEstudio(1L);
		musica.setGravadora(1L);
		
		ResponseEntity<String> response = restTemplate.postForEntity("/musica", musica, String.class);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}