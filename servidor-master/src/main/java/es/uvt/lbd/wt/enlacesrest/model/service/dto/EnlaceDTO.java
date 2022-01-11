package es.uvt.lbd.wt.enlacesrest.model.service.dto;


import java.util.ArrayList;
import java.util.List;

import es.uvt.lbd.wt.enlacesrest.model.domain.Enlace;

public class EnlaceDTO {
	private Long id;

	private String nombre;

	private String foto;

	private String descripcion;

	private CategoriaDTO categoria;

	private List<TagDTO> tags;

	private String enl;

	private Boolean hasImage = false;

	private Double valoracion;


	public EnlaceDTO() {

	}

	public EnlaceDTO(Enlace m) {
		this.id = m.getId();
		this.nombre = m.getNombre();
		this.descripcion = m.getDescripcion();
		this.enl = m.getEnlace();
		this.valoracion = m.getValoracion();
		if(m.getImagePath() != null) this.hasImage=true;
		if(m.getCategoria() != null)this.categoria = new CategoriaDTO(m.getCategoria());
		this.tags = new ArrayList<>();
	    m.getTags().forEach(t -> {
	      this.tags.add(new TagDTO(t));
	    });
	}

	public EnlaceDTO(String nombre,  String descripcion,  String enl) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.enl = enl;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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

	public Boolean getHasImage() {
		return hasImage;
	}

	public void setHasImage(Boolean hasImage) {
		this.hasImage = hasImage;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public List<TagDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagDTO> tags) {
		this.tags = tags;
	}

	public Double getValoracion() {
		return valoracion;
	}

	public void setValoracion(Double valoracion) {
		this.valoracion = valoracion;
	}

}
