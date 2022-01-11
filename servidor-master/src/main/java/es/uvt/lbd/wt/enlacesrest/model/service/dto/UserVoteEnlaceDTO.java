package es.uvt.lbd.wt.enlacesrest.model.service.dto;

import es.uvt.lbd.wt.enlacesrest.model.domain.UserVoteEnlace;

public class UserVoteEnlaceDTO {

	private Long id;
	private UserDTOPublic user;
	private EnlaceDTO enlace;
	private int valoracion;
	
	
	public UserVoteEnlaceDTO() {
		
	}

	public UserVoteEnlaceDTO(UserVoteEnlace uservote) {
		this.id = uservote.getId();
		this.user = new UserDTOPublic(uservote.getUser());
		this.enlace = new EnlaceDTO(uservote.getEnlace());
		this.valoracion = uservote.getValoracion();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTOPublic getUser() {
		return user;
	}

	public void setUser(UserDTOPublic user) {
		this.user = user;
	}

	public EnlaceDTO getEnlace() {
		return enlace;
	}

	public void setEnlace(EnlaceDTO enlace) {
		this.enlace = enlace;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	
}
