package es.uvt.lbd.wt.enlacesrest.model.service.dto;

import java.time.LocalDate;

import es.uvt.lbd.wt.enlacesrest.model.domain.User;
import es.uvt.lbd.wt.enlacesrest.model.domain.UserAuthority;

public class UserDTOPublic {
	private Long id;
	private String login;
	private LocalDate fechaRegistro;
	private UserAuthority Authority;
	private int numFavoritos;
	private int numGuardados;
	private int numValorados;

	public UserDTOPublic() {
	}

	public UserDTOPublic(User user) {
		this.id = user.getId();
		this.login = user.getLogin();
		this.setFechaRegistro(user.getFechaRegistro());
		this.setAuthority(user.getAuthority());
		this.setNumFavoritos(user.getEnlaces_favoritos().size());
		this.setNumGuardados(user.getEnlaces_guardados().size());
		this.setNumValorados(user.getUserVoteEnlace().size());
	}

	public UserAuthority getAuthority() {
		return Authority;
	}

	public void setAuthority(UserAuthority userAuthority) {
		Authority = userAuthority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public int getNumFavoritos() {
		return numFavoritos;
	}

	public void setNumFavoritos(int numFavoritos) {
		this.numFavoritos = numFavoritos;
	}

	public int getNumGuardados() {
		return numGuardados;
	}

	public void setNumGuardados(int numGuardados) {
		this.numGuardados = numGuardados;
	}

	public int getNumValorados() {
		return numValorados;
	}

	public void setNumValorados(int numValorados) {
		this.numValorados = numValorados;
	}

}
