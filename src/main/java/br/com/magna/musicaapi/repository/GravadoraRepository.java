package br.com.magna.musicaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.magna.musicaapi.entity.gravadora.Gravadora;

public interface GravadoraRepository extends JpaRepository<Gravadora, Long> {

}
