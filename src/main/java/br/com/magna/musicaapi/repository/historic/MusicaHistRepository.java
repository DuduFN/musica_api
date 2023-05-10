package br.com.magna.musicaapi.repository.historic;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.magna.musicaapi.entity.historic.MusicaHist;

public interface MusicaHistRepository extends JpaRepository<MusicaHist, Long>{

}
