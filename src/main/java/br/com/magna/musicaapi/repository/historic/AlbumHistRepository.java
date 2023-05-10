package br.com.magna.musicaapi.repository.historic;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.magna.musicaapi.entity.historic.AlbumHist;


public interface AlbumHistRepository extends JpaRepository<AlbumHist, Long>   {
	
}