package br.com.magna.musicaapi.entity.historic;

import java.time.LocalDate;
import java.time.LocalTime;
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

@Entity(name = "MusicaHist")
@Table(name = "TB_HIST_MUSICA")
public class MusicaHist extends GenericEntity<MusicaHist>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_MUSICA")
	private Long id;
	
	@Column(name = "NOME", nullable=false)
	private String nome;
		
	@Column(name = "DURACAO", nullable=false)
	private LocalTime duracao;
	
	@Column(name = "LETRA", columnDefinition="TEXT", nullable=false)
	private String letra;
		
	@Column(name = "DATA_LANCAMENTO", nullable=false)
	private LocalDate lancamento;
	
	@Column(name = "ATIVO")	
	private Boolean ativo = true;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "TB_HIST_ESTUDIO_MUSICA",
				joinColumns = @JoinColumn(name = "FK_MUSICA"),
				inverseJoinColumns = @JoinColumn(name = "FK_ESTUDIO"))
	private List<EstudioHist> estudioHist = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "TB_HIST_GRAVADORA_MUSICA",
				joinColumns = @JoinColumn(name = "FK_MUSICA"),
				inverseJoinColumns = @JoinColumn(name = "FK_GRAVADORA"))
	private List<GravadoraHist> gravadoraHist = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "TB_HIST_ARTISTA_MUSICA",
			joinColumns = @JoinColumn(name = "FK_MUSICA"),
			inverseJoinColumns = @JoinColumn(name = "FK_ARTISTA"))
	private List<ArtistaHist> artistaHist = new ArrayList<>();

	@ManyToMany(mappedBy = "musicasHist", fetch = FetchType.EAGER)
	private List<PlaylistHist> playlistHist;
	
	@ManyToMany(mappedBy = "musicasHist", fetch = FetchType.EAGER)
	private List<AlbumHist> albumHist;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDuracao(LocalTime duracao) {
		this.duracao = duracao;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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