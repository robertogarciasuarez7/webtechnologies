package es.uvt.lbd.wt.enlacesrest.model.service.dto;

import es.uvt.lbd.wt.enlacesrest.model.domain.Categoria;


public class CategoriaDTO {
	private Long id;

	private String nombre;

	public CategoriaDTO() {
		
	}
	
	public CategoriaDTO(String nombre) {
		this.nombre = nombre;
	}

	public CategoriaDTO(Categoria c) {
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
