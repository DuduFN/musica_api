package br.com.magna.musicaapi.entity.album;

import java.time.LocalDate;
import java.util.List;

import br.com.magna.musicaapi.dto.musica.ListagemMusicaDTO;
import br.com.magna.musicaapi.entity.GenericEntity;
import br.com.magna.musicaapi.entity.musica.Musica;
import br.com.magna.musicaapi.entity.produtor.Produtor;
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

@Entity(name=("Album"))
@Table(name="ALBUM")
public class Album implements GenericEntity<Album, Long> {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_ALBUM")
	private Long id;	
	
	@Column(name="NOME", nullable=false)
	private String nome;	
	
	@Column(name="ARTISTA", nullable=false)
	private String artista;
	
	@Column(name="DATA_LANCAMENTO", nullable=false)
	private LocalDate lancamento;
	
	@ManyToMany(mappedBy="albuns")
	private List<Produtor> produtor;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "MUSICAS_ALBUM",
				joinColumns = @JoinColumn(name = "ALBUM_PK"),
				inverseJoinColumns = @JoinColumn(name = "MUSICA_PK"))
	private List<Musica> musicas; 
	
	public Album() {
	}

	public Long getAlbumId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public LocalDate getLancamento() {
		return lancamento;
	}

	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
	}
	
	public List<ListagemMusicaDTO> getMusicas() {
		return musicas.stream().map(ListagemMusicaDTO::new).toList();
	}
	
	public void adicionarMusica(Musica novaMusica) {
		if(novaMusica != null) {
			this.musicas.add(novaMusica);
		}
	}

	public void removerMusica(Musica novaMusica) {
		if(this.musicas.contains(novaMusica)){
			musicas.remove(novaMusica);
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