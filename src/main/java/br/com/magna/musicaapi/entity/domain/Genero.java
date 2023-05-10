package br.com.magna.musicaapi.entity.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="Genero")
@Table(name="TB_GENERO")
public class Genero{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_GENERO")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
}