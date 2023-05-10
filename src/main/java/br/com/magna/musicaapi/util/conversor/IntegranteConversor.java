package br.com.magna.musicaapi.util.conversor;

import org.springframework.stereotype.Component;

import br.com.magna.musicaapi.entity.historic.IntegranteHist;
import br.com.magna.musicaapi.entity.integrante.Integrante;

@Component
public class IntegranteConversor {
	
	public IntegranteHist converterIntegrante(Integrante integrante) {
		IntegranteHist integranteHist = new IntegranteHist();
		
		integranteHist.setFuncaoIntegrante(integrante.getFuncaoIntegrante());
		integranteHist.setNomeIntegrante(integrante.getNomeIntegrante());
		integranteHist.setTimestamp(integrante.getTimestamp());
		integranteHist.setUser(integrante.getUser());
		
		return integranteHist;
	}

}
