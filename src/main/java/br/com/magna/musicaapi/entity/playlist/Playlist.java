package br.com.magna.musicaapi.entity.playlist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.magna.musicaapi.entity.GenericEntity;
import br.com.magna.musicaapi.entity.musica.Musica;
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

@Table(name = "TB_PLAYLIST")
@Entity(name = "Playlist")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
public class Playlist extends GenericEntity<Playlist> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_PLAYLIST")
	private Long id;
	
	@Column(name = "NOME", nullable=false)
	private String nome;
	
	@Column(name = "DESCRICAO", nullable=false)
	private String descricao;
	
	@Column(name = "DATA_CRIACAO")
	private LocalDate dataCriacao;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "TB_MUSICA_PLAYLIST",
				joinColumns = @JoinColumn(name = "FK_PLAYLIST"),
				inverseJoinColumns = @JoinColumn(name = "FK_MUSICA"))
	private List<Musica> musica;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Map<String, LocalTime> getMusicas() {
		HashMap<String, LocalTime> listaMusicas = new HashMap<>();
		
		if(this.musica == null) {
			listaMusicas.put("", null);
		} else {
			musica.forEach(i -> listaMusicas.put(i.getNome(),i.getDuracao()));
		}
		
		return listaMusicas;
	}

	public void adicionarMusica(Musica musica) {
			this.musica.add(musica);
	}

	public void removerMusica(Musica musica) {
		if(this.musica.contains(musica)) {
			this.musica.remove(musica);
		}
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