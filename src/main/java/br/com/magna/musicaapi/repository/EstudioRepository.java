package br.com.magna.musicaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.magna.musicaapi.entity.estudio.Estudio;

public interface EstudioRepository extends JpaRepository<Estudio, Long>{

}
