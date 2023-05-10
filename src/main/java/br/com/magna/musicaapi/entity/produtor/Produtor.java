package br.com.magna.musicaapi.entity.produtor;

import java.util.List;

import br.com.magna.musicaapi.dto.produtor.AtualizarProdutorDTO;
import br.com.magna.musicaapi.entity.GenericEntity;
import br.com.magna.musicaapi.entity.album.Album;
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

@Entity(name="Produtor")
@Table(name="PRODUTOR")
public class Produtor implements GenericEntity<Produtor, Long> {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_PRODUTOR", nullable=false)
	private Long id;	
	
	@Column(name="GRAVDORA", nullable=false)
	private String gravadora;	
	
	@Column(name="ESTUDIO", nullable=false)
	private String estudio;
	
	@Column(name="DISTRIBUIDORA", nullable=false)
	private String distribuidora; 
	
	@Column(name="PRODUCAO", nullable=false)
	private String producao;
	
	
	@ManyToMany(mappedBy = "produtor", fetch = FetchType.EAGER)
	private List<Musica> musicas;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "produtores_albuns",
				joinColumns = @JoinColumn(name = "produtor_id"),
				inverseJoinColumns = @JoinColumn(name = "album_id"))
	private List<Album> albuns;

	public Produtor() {
	}
	
	public String getGravadora() {
		return gravadora;
	}

	public void setGravadora(String gravadora) {
		this.gravadora = gravadora;
	}

	public String getEstudio() {
		return estudio;
	}

	public void setEstudio(String estudio) {
		this.estudio = estudio;
	}
	
	public String getDistribuidora() {
		return distribuidora;
	}
	
	public void setDistribuidora(String distribuidora) {
		this.distribuidora = distribuidora;
	}

	public String getProducao() {
		return producao;
	}

	public void setProducao(String producao) {
		this.producao = producao;
	}

	public Long getProdutorId() {
		return id;
	}

	public void atualizarInformacoes(AtualizarProdutorDTO produtor) {
		if(produtor.gravadora()!= null) {
			this.gravadora = produtor.gravadora();
		}
		if(produtor.estudio()!= null) {
			this.estudio = produtor.estudio();
		}
		if(produtor.distribuidora()!= null) {
			this.distribuidora = produtor.distribuidora();
		}
		if(produtor.producao()!= null) {
			this.producao = produtor.producao();
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