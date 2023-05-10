package br.com.magna.musicaapi.entity.historic;

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

@Table(name = "TB_HIST_PLAYLIST")
@Entity(name = "PlaylistHist")
public class PlaylistHist extends GenericEntity<PlaylistHist> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLAYLIST_PK")
	private Long id;
	
	@Column(name = "NOME", nullable=false)
	private String nome;
	
	@Column(name = "DESCRICAO", nullable=false)
	private String descricao;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "TB_HIST_MUSICAS_PLAYLIST",
				joinColumns = @JoinColumn(name = "FK_PLAYLIST"),
				inverseJoinColumns = @JoinColumn(name = "FK_MUSICA"))
	private List<MusicaHist> musicasHist = new ArrayList<>();

	public void setNome(String nome) {
		this.nome = nome;
	}	

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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