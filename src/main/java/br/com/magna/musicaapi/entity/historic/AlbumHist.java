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

@Entity(name=("AlbumHist"))
@Table(name="TB_HIST_ALBUM")
public class AlbumHist extends GenericEntity<AlbumHist> {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_ALBUM")
	private Long id;	
	
	@Column(name="NOME", nullable=false)
	private String nome;	
	
	@Column(name="DATA_LANCAMENTO", nullable=false)
	private LocalDate lancamento;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "TB_HIST_MUSICAS_ALBUM",
				joinColumns = @JoinColumn(name = "FK_ALBUM"),
				inverseJoinColumns = @JoinColumn(name = "FK_MUSICA"))
	private List<MusicaHist> musicasHist;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "TB_HIST_ALBUM_MUSICA",
			joinColumns = @JoinColumn(name = "FK_ALBUM"),
			inverseJoinColumns = @JoinColumn(name = "FK_ARTISTA"))
	private List<ArtistaHist> artistaHist = new ArrayList<>();
	

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
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