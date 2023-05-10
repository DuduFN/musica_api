package br.com.magna.musicaapi.entity.album;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.magna.musicaapi.entity.GenericEntity;
import br.com.magna.musicaapi.entity.artista.Artista;
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

@Entity(name=("Album"))
@Table(name="TB_ALBUM")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
public class Album extends GenericEntity<Album> {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_ALBUM")
	private Long id;	
	
	@Column(name="NOME", nullable=false)
	private String nome;	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "TB_ALBUM_ARTISTA",
			joinColumns = @JoinColumn(name = "FK_ALBUM"),
			inverseJoinColumns = @JoinColumn(name = "FK_ARTISTA"))
	private List<Artista> artista = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "TB_MUSICA_ALBUM",
			joinColumns = @JoinColumn(name = "FK_ALBUM"),
			inverseJoinColumns = @JoinColumn(name = "FK_MUSICA"))
	private List<Musica> musica;
	
	@Column(name="DATA_LANCAMENTO", nullable=false)
	private LocalDate lancamento;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setArtista(Artista artista) {
		this.artista.add(artista);
	}
	
	public List<String> getArtista() {
		List<String> lista= new ArrayList<>();
		artista.forEach(a -> lista.add(a.getNome()));
		return lista;
	}

	public LocalDate getLancamento() {
		return lancamento;
	}

	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
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
	
	public void setMusica(Musica novaMusica) {
			this.musica.add(novaMusica);
	}

	public void removerMusica(Musica novaMusica) {
			musica.remove(novaMusica);
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