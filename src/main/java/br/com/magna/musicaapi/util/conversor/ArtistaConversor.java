package br.com.magna.musicaapi.util.conversor;

import org.springframework.stereotype.Component;

import br.com.magna.musicaapi.entity.artista.Artista;
import br.com.magna.musicaapi.entity.historic.ArtistaHist;

@Component
public class ArtistaConversor {
	
	public ArtistaHist converter(Artista artista) {
		ArtistaHist artistaHist = new ArtistaHist();
		artistaHist.setNome(artista.getNome());
		artistaHist.setDataCriacao(artista.getDataCriacao());
		artistaHist.setPaisOrigem(artista.getPaisOrigem());
		artistaHist.setTimestamp(artista.getTimestamp());
		artistaHist.setUser(artista.getUser());
		
		return artistaHist;
	}

}
