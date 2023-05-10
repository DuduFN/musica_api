package br.com.magna.musicaapi.util.conversor;

import org.springframework.stereotype.Component;

import br.com.magna.musicaapi.entity.estudio.Estudio;
import br.com.magna.musicaapi.entity.historic.EstudioHist;

@Component
public class EstudioConversor {

	public EstudioHist converter(Estudio estudio) {
		EstudioHist estudioHist = new EstudioHist();
		estudioHist.setNome(estudio.getNome());
		estudioHist.setLocal(estudio.getLocal());
		
		return estudioHist;
	}
}
