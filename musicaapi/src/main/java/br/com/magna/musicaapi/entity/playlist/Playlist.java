package br.com.magna.musicaapi.entity.playlist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

@Table(name = "PLAYLIST")
@Entity(name = "Playlist")
public class Playlist implements GenericEntity<Playlist, Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLAYLIST_PK")
	private Long id;
	
	@Column(name = "NOME", nullable=false)
	private String nome;
	
	@Column(name = "DESCRICAO", nullable=false)
	private String descricao;
	
	@Column(name = "DATA_CRIACAO") //ADICIONAR O LOCAL DATE NOW NO SERVICE
	private LocalDate dataCriacao;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "MUSICAS_PLAYLIST",
				joinColumns = @JoinColumn(name = "PLAYLIST_PK"),
				inverseJoinColumns = @JoinColumn(name = "MUSICA_PK"))
	private List<Musica> musicas = new ArrayList<>();

	public Long getPlaylistId() {
		return id;
	}
	
	public Playlist() {
		this.dataCriacao = LocalDate.now();
	}

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

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void adicionarMusica(Musica musica) {
		if (musica != null) {
			this.musicas.add(musica);
		}
	}

	public void removerMusica(Musica musica) {
		if(this.musicas.contains(musica)) {
			this.musicas.remove(musica);
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