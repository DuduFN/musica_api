package br.com.magna.musicaapi.entity.integrante;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.magna.musicaapi.entity.GenericEntity;
import br.com.magna.musicaapi.entity.artista.Artista;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name="Integrante")
@Table(name="TB_INTEGRANTE")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
public class Integrante extends GenericEntity<Integrante>{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_INTEGRANTE")
	private Long id;
	
	@Column(name = "NOME", nullable=false)
	private String nome;
	
	@Column(name = "FUNCAO", nullable=false)
	private String funcaoIntegrante;	
	
	@ManyToMany(mappedBy = "integrante", fetch = FetchType.EAGER)
	private List<Artista> artista;

	public String getNomeIntegrante() {
		return nome;
	}

	public void setNomeIntegrante(String nomeIntegrante) {
		this.nome = nomeIntegrante;
	}

	public String getFuncaoIntegrante() {
		return funcaoIntegrante;
	}

	public void setFuncaoIntegrante(String funcaoIntegrante) {
		this.funcaoIntegrante = funcaoIntegrante;
	}
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
}