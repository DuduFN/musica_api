package br.com.magna.musicaapi.entity;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class GenericEntity<T> {
	
	@Column(name="USER_APP")
	private String user;
	
	@Column(name = "TIMESTAMP")
	private ZonedDateTime timestamp;
		
	public ZonedDateTime getTimestamp() {
		return this.timestamp;
	}
	
	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public abstract Long getId();
	
	public abstract void setId(Long id);
}
