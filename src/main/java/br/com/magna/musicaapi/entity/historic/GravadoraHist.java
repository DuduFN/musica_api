package br.com.magna.musicaapi.entity.historic;

import java.util.List;

import br.com.magna.musicaapi.entity.GenericEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name="GravadoraHist")
@Table(name="TB_HIST_GRAVADORA")
public class GravadoraHist extends GenericEntity<GravadoraHist> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_GRAVADORA")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="DISTRIBUIDORA")
	private String distribuidora;
	
	@ManyToMany(mappedBy = "gravadoraHist", fetch = FetchType.EAGER)
	private List<MusicaHist> musicas;

	public void setNome(String nome) {
		this.nome = nome;
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
