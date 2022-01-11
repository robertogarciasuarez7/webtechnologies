package es.uvt.lbd.wt.enlacesrest.model.service.dto;

import es.uvt.lbd.wt.enlacesrest.model.domain.Tag;

public class TagDTO {

	private Long id;

	private String nombre;

	public TagDTO() {
		
	}
	
	public TagDTO(String nombre) {
		this.nombre = nombre;
	}

	public TagDTO(Tag c) {
		this.id = c.getId();
		this.nombre = c.getNombre();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
