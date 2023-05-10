package br.com.magna.musicaapi.util.conversor;

import org.springframework.stereotype.Component;

import br.com.magna.musicaapi.entity.gravadora.Gravadora;
import br.com.magna.musicaapi.entity.historic.GravadoraHist;

@Component
public class GravadoraConversor {

	public GravadoraHist converter(Gravadora gravadora) {
		GravadoraHist gravadoraHist = new GravadoraHist();
		
		gravadoraHist.setNome(gravadora.getNome());
		gravadoraHist.setDistribuidora(gravadora.getDistribuidora());
		gravadoraHist.setTimestamp(gravadora.getTimestamp());
		gravadoraHist.setUser(gravadora.getUser());
		
		return gravadoraHist;
	}
	
}
