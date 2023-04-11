package br.com.magna.musicaapi.entity.compositor;

import java.util.List;

import br.com.magna.musicaapi.dto.compositor.AtualizarCompositorDTO;
import br.com.magna.musicaapi.entity.GenericEntity;
import br.com.magna.musicaapi.entity.musica.Musica;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name="Compositor")
@Table(name="COMPOSITOR")
public class Compositor implements GenericEntity<Compositor, Long>{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_COMPOSITOR")
	private Long id;
	
	@Column(name = "NOME_INTEGRANTE", nullable=false)
	private String nomeIntegrante;
	
	@Column(name = "FUNCAO_INTEGRANTE", nullable=false)
	private String funcaoIntegrante;	
	
	@ManyToMany(mappedBy = "compositor", fetch = FetchType.EAGER)
	private List<Musica> musicas;
	
	public Compositor() {
	}

	public String getNomeIntegrante() {
		return nomeIntegrante;
	}

	public void setNomeIntegrante(String nomeIntegrante) {
		this.nomeIntegrante = nomeIntegrante;
	}

	public String getFuncaoIntegrante() {
		return funcaoIntegrante;
	}

	public void setFuncaoIntegrante(String funcaoIntegrante) {
		this.funcaoIntegrante = funcaoIntegrante;
	}

	public Long getCompositorId() {
		return id;
	}
	
	public void atualizarInformacoes(AtualizarCompositorDTO compositor) {
		if(compositor.nomeIntegrante() != null) {
			this.nomeIntegrante = compositor.nomeIntegrante();
		}
		if(compositor.funcaoIntegrante() != null) {
			this.funcaoIntegrante = compositor.funcaoIntegrante();
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