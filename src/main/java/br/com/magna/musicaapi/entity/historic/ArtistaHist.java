package br.com.magna.musicaapi.entity.historic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.magna.musicaapi.entity.GenericEntity;
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

@Entity(name="ArtistaHist")
@Table(name="TB_HIST_ARTISTA")
public class ArtistaHist extends GenericEntity<ArtistaHist>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_ARTISTA")
	private Long id;
	 
	@Column(name="NOME")
	private String nome;
	
	@Column(name="DATA_CRIACAO")
	private LocalDate dataCriacao;
	
	@Column(name="PAIS_ORIGEM")
	private String paisOrigem;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "TB_HIST_INTEGRANTES_ARTISTA",
				joinColumns = @JoinColumn(name = "FK_ARTISTA"),
				inverseJoinColumns = @JoinColumn(name = "FK_INTEGRANTE"))
	List<IntegranteHist> integranteHist = new ArrayList<>();
	
	@ManyToMany(mappedBy = "artistaHist", fetch = FetchType.EAGER)
	private List<MusicaHist> musicas;
	
	@ManyToMany(mappedBy = "artistaHist", fetch = FetchType.EAGER)
	private List<AlbumHist> album;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
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
