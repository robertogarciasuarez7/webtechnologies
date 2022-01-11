package es.uvt.lbd.wt.enlacesrest.model.domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_generator")
	@SequenceGenerator(name = "tag_generator", sequenceName = "tag_seq")

	private Long id;
	private String nombre;
	
	@ManyToMany(mappedBy="tags")
	private Set<Enlace> enlaces = new HashSet<>();

	public Tag() {

	}
	
	public Tag(String nombre) {
		this.nombre=nombre;
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