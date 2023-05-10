package br.com.magna.musicaapi.services;

import org.springframework.stereotype.Service;

import br.com.magna.musicaapi.dto.produtor.ProdutorDTO;
import br.com.magna.musicaapi.entity.produtor.Produtor;

@Service
public class ProdutorServices {
	
	public Produtor criarProdutor(ProdutorDTO dados) {
		Produtor produtor = new Produtor();
		
		produtor.setGravadora(dados.gravadora());
		produtor.setEstudio(dados.estudio());
		produtor.setDistribuidora(dados.distribuidora());
		produtor.setProducao(dados.producao());
		
		return produtor;
	}
}
