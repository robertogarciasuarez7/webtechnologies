package es.uvt.lbd.wt.enlacesrest.model.domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class Enlace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enlace_generator")
	@SequenceGenerator(name = "enlace_generator", sequenceName = "enlace_seq")
	private Long id;
	
	@Column(nullable=false,unique=true)
	private String nombre;
	private String descripcion;
	private String enl;
	//nombre original de la imagenes
	private String imagePath;
	private Double valoracion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="enl_cat", nullable =true )
	private Categoria categoria;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="enl_tag", nullable =true)
	private Set<Tag> tags = new HashSet<>();
	

	public Enlace() {
	}
	
	public Enlace(Double valoracion) {
		this.valoracion = valoracion;
	}

	public Enlace(String nombre, String descripcion, String enl) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.enl=enl;
	}
	
	public Enlace(String nombre, String descripcion, String enl, Double valoracion) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.enl=enl;
		this.valoracion=valoracion;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEnl() {
		return enl;
	}

	public void setEnl(String enl) {
		this.enl = enl;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getEnlace() {
		return enl;
	}

	public void setEnlace(String enl) {
		this.enl = enl;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Double getValoracion() {
		return valoracion;
	}

	public void setValoracion(Double valoracion) {
		this.valoracion = valoracion;
	}
	
}
