package br.com.magna.musicaapi.entity.estudio;

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

@Entity(name="Estudio")
@Table(name="TB_ESTUDIO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
public class Estudio extends GenericEntity<Estudio> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_ESTUDIO")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="LOCAL")
	private String local;
	
	@ManyToMany(mappedBy = "estudio", fetch = FetchType.EAGER)
	private List<Musica> musicas;
	
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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