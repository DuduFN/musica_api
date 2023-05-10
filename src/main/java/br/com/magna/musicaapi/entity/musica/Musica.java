package br.com.magna.musicaapi.entity.musica;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.magna.musicaapi.entity.GenericEntity;
import br.com.magna.musicaapi.entity.album.Album;
import br.com.magna.musicaapi.entity.artista.Artista;
import br.com.magna.musicaapi.entity.estudio.Estudio;
import br.com.magna.musicaapi.entity.gravadora.Gravadora;
import br.com.magna.musicaapi.entity.playlist.Playlist;
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

@Entity(name = "Musica")
@Table(name = "TB_MUSICA")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
public class Musica extends GenericEntity<Musica>{
	
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
	
	private String genero;
	
	@Column(name="SUBGENERO")
	private List<String> subgenero= new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "TB_ESTUDIO_MUSICA",
				joinColumns = @JoinColumn(name = "FK_MUSICA"),
				inverseJoinColumns = @JoinColumn(name = "FK_ESTUDIO"))
	private List<Estudio> estudio = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "TB_GRAVADORA_MUSICA",
				joinColumns = @JoinColumn(name = "FK_MUSICA"),
				inverseJoinColumns = @JoinColumn(name = "FK_GRAVADORA"))
	private List<Gravadora> gravadora = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "TB_ARTISTA_MUSICA",
			joinColumns = @JoinColumn(name = "FK_MUSICA"),
			inverseJoinColumns = @JoinColumn(name = "FK_ARTISTA"))
	private List<Artista> artista = new ArrayList<>();
	

	@ManyToMany(mappedBy = "musica", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Playlist> playlists;
	
	@ManyToMany(mappedBy = "musica", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Album> album = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalTime getDuracao() {
		return duracao;
	}

	public void setDuracao(LocalTime duracao) {
		this.duracao = duracao;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public LocalDate getLancamento() {
		return lancamento;
	}

	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public Boolean getAtivo() {
		return this.ativo;
	}
	
	public List<String> getSubgenero() {
		return subgenero;
	}

	public void setSubgenero(List<String> subgenero) {
		this.subgenero = subgenero;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public List<String> getAlbum() {
		List<String> listaAlbum = new ArrayList<>();
		album.forEach(a -> listaAlbum.add(a.getNome()));
		return listaAlbum;
	}
	
	public void setAlbum(Album album) {
		this.album.add(album);
	}
	
	public List<String> getArtista() {
		List<String> listaArtista = new ArrayList<>();
		artista.forEach(a -> listaArtista.add(a.getNome()));
		return listaArtista;
	}
	
	public void setArtista(Artista artista) {
		this.artista.add(artista);
	}

	public List<String> getEstudio() {
		List<String> listaEstudio = new ArrayList<>();
		estudio.forEach(a -> listaEstudio.add(a.getNome()));
		return listaEstudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio.add(estudio);
	}

	public List<String> getGravadora() {
		List<String> listaGravadora = new ArrayList<>();
		gravadora.forEach(a -> listaGravadora.add(a.getNome()));
		return listaGravadora;
	}

	public void setGravadora(Gravadora gravadora) {
		this.gravadora.add(gravadora);
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