package br.com.magna.musicaapi.dto.album;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AtualizarAlbumDTO {
	
	@NotNull Long id;
	@NotBlank String nome;
	@NotNull Long artista;
	@NotNull LocalDate lancamento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getArtista() {
		return artista;
	}
	public void setArtista(Long artista) {
		this.artista = artista;
	}
	public LocalDate getLancamento() {
		return lancamento;
	}
	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
	}
}