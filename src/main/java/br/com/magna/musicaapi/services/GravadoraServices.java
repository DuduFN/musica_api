package br.com.magna.musicaapi.services;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magna.musicaapi.entity.gravadora.Gravadora;
import br.com.magna.musicaapi.repository.GravadoraRepository;
import br.com.magna.musicaapi.repository.historic.GravadoraHistRepository;
import br.com.magna.musicaapi.util.conversor.GravadoraConversor;

@Service
public class GravadoraServices {

	@Autowired
	GravadoraRepository gravadoraRepository;
	
	@Autowired
	GravadoraHistRepository histRepository;
	
	@Autowired
	GravadoraConversor conversor;
	
	public Gravadora criar(Gravadora dados) {
		Gravadora gravadora = new Gravadora();
		
		gravadora.setId(gravadora.getId());
		gravadora.setNome(dados.getNome());
		gravadora.setDistribuidora(dados.getDistribuidora());
		gravadora.setTimestamp(ZonedDateTime.now());
		gravadora.setUser("USUSARIO");
		
		gravadoraRepository.save(gravadora);
		histRepository.save(conversor.converter(gravadora));
		return gravadora;
	}
	
	public Gravadora atualizar(Gravadora dados) {
		Gravadora gravadora = gravadoraRepository.getReferenceById(dados.getId());
		gravadora.setNome(dados.getNome());
		gravadora.setDistribuidora(dados.getDistribuidora());
		gravadora.setTimestamp(ZonedDateTime.now());
		gravadora.setUser("USUSARIO");
		
		gravadoraRepository.save(gravadora);
		histRepository.save(conversor.converter(gravadora));
		return gravadora;
	}
	
	public void excluir(Long id) {
		gravadoraRepository.deleteById(id);
	}
}
