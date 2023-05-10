package br.com.magna.musicaapi.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magna.musicaapi.dto.artista.ArtistaIntegranteDTO;
import br.com.magna.musicaapi.dto.artista.AtualizarArtistaDTO;
import br.com.magna.musicaapi.entity.artista.Artista;
import br.com.magna.musicaapi.entity.artista.validacoes.ValidarSeIntegranteJaPertenceAoArtista;
import br.com.magna.musicaapi.entity.integrante.Integrante;
import br.com.magna.musicaapi.repository.ArtistaRepository;
import br.com.magna.musicaapi.repository.IntegranteRepository;
import br.com.magna.musicaapi.repository.historic.ArtistaHistRepository;
import br.com.magna.musicaapi.util.conversor.ArtistaConversor;

@Service
public class ArtistaServices {
	
	@Autowired
	ArtistaRepository artistaRepository;
	
	@Autowired
	IntegranteRepository integranteRepository;
	
	@Autowired
	ArtistaHistRepository histRepository;
	
	@Autowired
	ValidarSeIntegranteJaPertenceAoArtista validarIntegrante ;
	
	@Autowired 
	ArtistaConversor conversor;
	
	public Artista criar(Artista dados) {
		Artista artista = new Artista();
		
		artista.setId(artista.getId());	
		artista.setNome(dados.getNome());
		artista.setPaisOrigem(dados.getPaisOrigem());
		artista.setDataCriacao(dados.getDataCriacao());
		artista.setIntegrante(dados.getIntegrante());
		artista.setTimestamp(LocalDateTime.now().atZone(ZoneId.of("UTC")));
		artista.setUser("USUSARIO");
		
		artistaRepository.save(artista);
		histRepository.save(conversor.converter(artista));
		return artista;
	}
	
	public Artista atualizar(AtualizarArtistaDTO dados) {
		Artista artista = artistaRepository.getReferenceById(dados.getId());
		
		artista.setNome(dados.getNome());
		artista.setPaisOrigem(dados.getPaisOrigem());
		artista.setDataCriacao(dados.getDataCriacao());
		artista.setTimestamp(LocalDateTime.now().atZone(ZoneId.of("UTC")));
		artista.setUser("USUSARIO");
		
		artistaRepository.save(artista);
		histRepository.save(conversor.converter(artista));
		return artista;
	}
	
	private List<Integrante> relacionarIntegrante(List<Long> integranteIds) {

		List<Integrante> integrantes = new ArrayList<>();

		for (Long id : integranteIds) {
			Integrante integrante = integranteRepository.getReferenceById(id);
			integrantes.add(integrante);
		}
		return integrantes;
	}
	
	public void adicionarIntegrante(ArtistaIntegranteDTO dados) {
		validarIntegrante.validar(dados);
		Artista artista = artistaRepository.getReferenceById(dados.artistaId());		
		artista.setIntegrante(relacionarIntegrante(dados.integranteId()));
	}
	
	public void removerIntegrante(ArtistaIntegranteDTO dados) {
		Artista artista = artistaRepository.getReferenceById(dados.artistaId());		
		artista.removerIntegrante(relacionarIntegrante(dados.integranteId()));
	}
	
	public void excluir(Long id) {
		artistaRepository.deleteById(id);
	}
	
	public Page<Artista> listar(Pageable paginacao) {
		return artistaRepository.findAll(paginacao);
	}
}
