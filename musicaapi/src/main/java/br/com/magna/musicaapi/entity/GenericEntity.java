package br.com.magna.musicaapi.entity;

public interface GenericEntity<T, ID> {
	
	public ID getId();
	
	public void setId(ID id);
}
