package br.com.magna.musicaapi.util.conversor;

import org.springframework.stereotype.Component;

import br.com.magna.musicaapi.entity.historic.MusicaHist;
import br.com.magna.musicaapi.entity.musica.Musica;

@Component
public class MusicaConversor {
	
	public MusicaHist converter(Musica musica) {
		MusicaHist musicaHist = new MusicaHist();
		
		musicaHist.setDuracao(musica.getDuracao());
		musicaHist.setLancamento(musica.getLancamento());
		musicaHist.setLetra(musica.getLetra());
		musicaHist.setNome(musica.getNome());
		musicaHist.setAtivo(musica.getAtivo());
		musicaHist.setTimestamp(musica.getTimestamp());
		musicaHist.setUser(musica.getUser());
				
		return musicaHist;
	}
	
}
