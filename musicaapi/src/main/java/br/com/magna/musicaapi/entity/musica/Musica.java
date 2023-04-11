package br.com.magna.musicaapi.entity.musica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.magna.musicaapi.dto.musica.AtualizarMusicaDTO;
import br.com.magna.musicaapi.entity.GenericEntity;
import br.com.magna.musicaapi.entity.album.Album;
import br.com.magna.musicaapi.entity.compositor.Compositor;
import br.com.magna.musicaapi.entity.playlist.Playlist;
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

@Entity(name = "Musica")
@Table(name = "MUSICA")
public class Musica implements GenericEntity<Musica, Long>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_MUSICA")
	private Long id;
	
	@Column(name = "NOME", nullable=false)
	private String nome;
	
	@Column(name = "ARTISTA", nullable=false)
	private String artista;
		
	@Column(name = "DURACAO", nullable=false)
	private LocalTime duracao;
	
	@Column(name = "LETRA", columnDefinition="TEXT", nullable=false)
	private String letra;
	
	@Column(name = "ALBUM", nullable=false)
	private String album;
		
	@Column(name = "DATA_LANCAMENTO", nullable=false)
	private LocalDate lancamento;
	
	@Column(name = "GENERO", nullable=false)
	private List<String> genero = new ArrayList<>();
	
	@Column(name = "ATIVO", nullable=false)	
	private Boolean ativo;
	
	@Column(name = "TIMESTAMP", nullable=false)
	private LocalDateTime timestamp;
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "COMPOSITORES_MUSICAS",
				joinColumns = @JoinColumn(name = "MUSICA_PK"),
				inverseJoinColumns = @JoinColumn(name = "COMPOSITOR_PK"))
	private List<Compositor> compositor = new ArrayList<>();


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "PRODUTORES_MUSICAS",
				joinColumns = @JoinColumn(name = "MUSICA_PK"),
				inverseJoinColumns = @JoinColumn(name = "PRODUTOR_PK"))
	private List<Produtor> produtor = new ArrayList<>();

	@ManyToMany(mappedBy = "musicas", fetch = FetchType.EAGER)
	private List<Playlist> playlists;
	
	@ManyToMany(mappedBy = "musicas", fetch = FetchType.EAGER)
	private List<Album> albuns;
	
	public Musica() {
		this.ativo = true;
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

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public LocalDate getLancamento() {
		return lancamento;
	}

	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
	}

	public List<String> getGenero() {
		return genero;
	}

	public void setGenero(List<String> genero) {
		genero.forEach(String::toUpperCase);
		this.genero.addAll(genero);
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void relacionarProdutor(Produtor produtor) {
		if(produtor != null) {
		this.produtor.add(produtor);
		}
	}
	
	public void relacionarCompositor(Compositor compositor) {
		if (compositor != null) {
			this.compositor.add(compositor);
		}
	}

	public void atualizarInformacoes(AtualizarMusicaDTO dadosAtualizar) {
		if (dadosAtualizar.nome() != null) {
			this.nome = dadosAtualizar.nome();
		}
		if (dadosAtualizar.artista() != null) {
			this.artista = dadosAtualizar.artista();
		}
		if (dadosAtualizar.duracao() != null) {
			this.duracao = dadosAtualizar.duracao();
		}
		if (dadosAtualizar.letra() != null) {
			this.letra = dadosAtualizar.letra();
		}
		if (dadosAtualizar.album() != null) {
			this.album = dadosAtualizar.album();
		}
		if (dadosAtualizar.lancamento() != null) {
			this.lancamento = dadosAtualizar.lancamento();
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