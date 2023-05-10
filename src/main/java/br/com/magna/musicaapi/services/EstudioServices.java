package br.com.magna.musicaapi.services;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magna.musicaapi.entity.estudio.Estudio;
import br.com.magna.musicaapi.repository.EstudioRepository;
import br.com.magna.musicaapi.repository.historic.EstudioHistRepository;
import br.com.magna.musicaapi.util.conversor.EstudioConversor;

@Service
public class EstudioServices {
	
	@Autowired
	EstudioRepository estudioRepository;
	
	@Autowired
	EstudioHistRepository histRepository;
	
	@Autowired
	EstudioConversor conversor;
	
	public Estudio criar(Estudio dados) {
		Estudio estudio = new Estudio();
		
		estudio.setNome(dados.getNome());
		estudio.setLocal(dados.getLocal());
		estudio.setTimestamp(ZonedDateTime.now());
		estudio.setUser("USUSARIO");
		
		estudioRepository.save(estudio);
		histRepository.save(conversor.converter(estudio));
		return estudio;
	}
	
	public Estudio atualizar(Estudio dados) {
		Estudio estudio = estudioRepository.getReferenceById(dados.getId());
		estudio.setNome(dados.getNome());
		estudio.setLocal(dados.getLocal());
		estudio.setTimestamp(ZonedDateTime.now());
		estudio.setUser("USUSARIO");
		
		estudioRepository.save(estudio);
		histRepository.save(conversor.converter(estudio));
		return estudio;
	}

	public void excluir(Long id) {
		estudioRepository.deleteById(id);
	}
}
