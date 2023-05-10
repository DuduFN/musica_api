package br.com.magna.musicaapi.entity.gravadora;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.magna.musicaapi.entity.GenericEntity;
import br.com.magna.musicaapi.entity.musica.Musica;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name="Gravadora")
@Table(name="TB_GRAVADORA")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
public class Gravadora extends GenericEntity<Gravadora> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_GRAVADORA")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="DISTRIBUIDORA")
	private String distribuidora;
	
	@ManyToMany(mappedBy = "gravadora", fetch = FetchType.EAGER)
	private List<Musica> musicas;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDistribuidora() {
		return distribuidora;
	}

	public void setDistribuidora(String distribuidora) {
		this.distribuidora = distribuidora;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
