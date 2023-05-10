package br.com.magna.musicaapi.entity.artista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.magna.musicaapi.entity.GenericEntity;
import br.com.magna.musicaapi.entity.album.Album;
import br.com.magna.musicaapi.entity.integrante.Integrante;
import br.com.magna.musicaapi.entity.musica.Musica;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name = "Artista")
@Table(name = "TB_ARTISTA")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Artista extends GenericEntity<Artista> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_ARTISTA")
	private Long id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "DATA_CRIACAO")
	private LocalDate dataCriacao;

	@Column(name = "PAIS_ORIGEM")
	private String paisOrigem;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "TB_INTEGRANTE_ARTISTA", joinColumns = @JoinColumn(name = "FK_ARTISTA"), inverseJoinColumns = @JoinColumn(name = "FK_INTEGRANTE"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	List<Integrante> integrante = new ArrayList<>();

	@ManyToMany(mappedBy = "artista", fetch = FetchType.EAGER)
	private List<Musica> musicas;

	@ManyToMany(mappedBy = "artista", fetch = FetchType.EAGER)
	private List<Album> album;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}
	
	public List<Integrante> getIntegrante(){
		return integrante;
	}

	public void setIntegrante(List<Integrante> intregrante) {
		this.integrante.addAll(intregrante);
	}

	public void removerIntegrante(List<Integrante> integrante) {
		this.integrante.removeAll(integrante);
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
