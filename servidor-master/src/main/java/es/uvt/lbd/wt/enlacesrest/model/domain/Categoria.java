package es.uvt.lbd.wt.enlacesrest.model.domain;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_generator")
	@SequenceGenerator(name = "categoria_generator", sequenceName = "categoria_seq")
	private Long id;
	
	private String nombre;
	
	@OneToMany(mappedBy="categoria")
	private Set<Enlace> enlaces;
	
	public Categoria() {
		
	}

	public Categoria(Categoria categoria) {
		this.id = categoria.getId();
		this.nombre = categoria.getNombre();
	}
	
	public Categoria(String nombre) {
		this.nombre= nombre;
	}

	public Categoria(Long id, String nombre) {
		
		this.id = id;
		this.nombre = nombre;
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

	public Set<Enlace> getEnlaces() {
		return enlaces;
	}

	public void setEnlaces(Set<Enlace> enlaces) {
		this.enlaces = enlaces;
	}
	
}
