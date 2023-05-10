package br.com.magna.musicaapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.magna.musicaapi.entity.produtor.Produtor;

public interface ProdutorRepository extends JpaRepository<Produtor, Long> {

}
