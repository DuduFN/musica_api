package br.com.magna.musicaapi.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magna.musicaapi.entity.integrante.Integrante;
import br.com.magna.musicaapi.repository.IntegranteRepository;
import br.com.magna.musicaapi.repository.historic.IntegranteHistRepository;
import br.com.magna.musicaapi.util.conversor.IntegranteConversor;

@Service
public class IntegranteServices {
	
	@Autowired
	IntegranteRepository integranteRepository;
	
	@Autowired
	IntegranteHistRepository histRepository;
	
	@Autowired
	IntegranteConversor conversor;
	
	public Integrante criarIntegrante(Integrante integranteDados) {
		Integrante integrante = new Integrante();
		
		integrante.setId(integrante.getId());
		integrante.setNomeIntegrante(integranteDados.getNomeIntegrante());
		integrante.setFuncaoIntegrante(integranteDados.getFuncaoIntegrante());
		integrante.setTimestamp(LocalDateTime.now().atZone(ZoneId.of("UTC")));
		integrante.setUser("USUARIO");
		
		integranteRepository.save(integrante);
		histRepository.save(conversor.converterIntegrante(integrante));
		
		return integrante;
	}
	
	public Integrante atualizar(Integrante dadosAtualizar) {
		Integrante integrante = integranteRepository.getReferenceById(dadosAtualizar.getId());
		
		integrante.setNomeIntegrante(dadosAtualizar.getNomeIntegrante());
		integrante.setFuncaoIntegrante(dadosAtualizar.getFuncaoIntegrante());
		integrante.setTimestamp(LocalDateTime.now().atZone(ZoneId.of("UTC")));
		integrante.setUser("USUARIO");
		
		integranteRepository.save(integrante);
		histRepository.save(conversor.converterIntegrante(integrante));
		return integrante;
	}
	
	public void excluir(Long id) {
		integranteRepository.deleteById(id);
	}
}
