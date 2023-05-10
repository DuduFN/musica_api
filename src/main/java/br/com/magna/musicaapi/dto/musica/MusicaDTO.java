package br.com.magna.musicaapi.dto.musica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MusicaDTO {
	
	@NotBlank String nome;
	@NotNull Long artista;
	@NotBlank String letra;
	@NotNull LocalTime duracao;
	@NotNull Long album;
	@NotNull LocalDate lancamento;
	@NotNull String genero;
	@NotNull List<String> subgenero;
	@NotNull Long estudio;
	@NotNull Long gravadora;
	
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
	public String getLetra() {
		return letra;
	}
	public void setLetra(String letra) {
		this.letra = letra;
	}
	public LocalTime getDuracao() {
		return duracao;
	}
	public void setDuracao(LocalTime duracao) {
		this.duracao = duracao;
	}
	public Long getAlbum() {
		return album;
	}
	public void setAlbum(Long album) {
		this.album = album;
	}
	public LocalDate getLancamento() {
		return lancamento;
	}
	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public List<String> getSubgenero() {
		return subgenero;
	}
	public void setSubgenero(List<String> subgenero) {
		this.subgenero = subgenero;
	}
	public Long getEstudio() {
		return estudio;
	}
	public void setEstudio(Long estudio) {
		this.estudio = estudio;
	}
	public Long getGravadora() {
		return gravadora;
	}
	public void setGravadora(Long gravadora) {
		this.gravadora = gravadora;
	}
	
}
