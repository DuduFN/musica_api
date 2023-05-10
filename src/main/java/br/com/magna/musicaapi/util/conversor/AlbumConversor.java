package br.com.magna.musicaapi.util.conversor;

import org.springframework.stereotype.Component;

import br.com.magna.musicaapi.entity.album.Album;
import br.com.magna.musicaapi.entity.historic.AlbumHist;

@Component
public class AlbumConversor {
	
	public AlbumHist converterAlbum(Album album) {
		
		AlbumHist albumHist = new AlbumHist();
		
		albumHist.setLancamento(album.getLancamento());
		albumHist.setNome(album.getNome());
		albumHist.setTimestamp(album.getTimestamp());
		albumHist.setUser(album.getUser());
		
		return albumHist;
	}

}
