package br.com.magna.musicaapi.entity.historic;

import java.util.List;

import br.com.magna.musicaapi.entity.GenericEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name="EstudioHist")
@Table(name="TB_HIST_ESTUDIO")
public class EstudioHist extends GenericEntity<EstudioHist> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_ESTUDIO")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="LOCAL")
	private String local;
	
	@ManyToMany(mappedBy = "estudioHist", fetch = FetchType.EAGER)
	private List<MusicaHist> musicas;
	
	public void setLocal(String local) {
		this.local = local;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
}
