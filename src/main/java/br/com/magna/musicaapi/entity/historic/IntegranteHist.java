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

@Entity(name="IntegranteHist")
@Table(name="TB_HIST_INTEGRANTE")
public class IntegranteHist extends GenericEntity<IntegranteHist>{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_INTEGRANTE")
	private Long id;
	
	@Column(name = "NOME_INTEGRANTE", nullable=false)
	private String nomeIntegrante;
	
	@Column(name = "FUNCAO_INTEGRANTE", nullable=false)
	private String funcaoIntegrante;
	
	@ManyToMany(mappedBy = "integranteHist", fetch = FetchType.EAGER)
	private List<ArtistaHist> artista;

	public void setNomeIntegrante(String nomeIntegrante) {
		this.nomeIntegrante = nomeIntegrante;
	}

	public void setFuncaoIntegrante(String funcaoIntegrante) {
		this.funcaoIntegrante = funcaoIntegrante;
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