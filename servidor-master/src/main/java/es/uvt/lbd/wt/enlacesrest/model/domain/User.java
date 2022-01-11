package es.uvt.lbd.wt.enlacesrest.model.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "theUser")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name = "user_generator", sequenceName = "user_seq")
	private Long id;

	@Column(unique = true)
	private String login;

	@Column(unique = true)
	private String email;
	
	@Column
	private LocalDate fechaRegistro;

	private String password;

	@Enumerated(EnumType.STRING)
	private UserAuthority authority;

	@ManyToMany(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(name = "favorito", joinColumns = @JoinColumn(name = "id_user"), // user
			inverseJoinColumns = @JoinColumn(name = "id_enlace")) // enlace
	Set<Enlace> enlaces_favoritos = new HashSet<>(); // colección que queremos guardar

	@ManyToMany(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(name = "guardado", joinColumns = @JoinColumn(name = "id_user"), // user
			inverseJoinColumns = @JoinColumn(name = "id_enlace")) // enlace
	Set<Enlace> enlaces_guardados = new HashSet<>(); // colección que queremos guardar

	@OneToMany(mappedBy="user")// nome do atributo da outra clase non da columna da taboa
	 Set<UserVoteEnlace> userVoteEnlace = new HashSet<>();

	public User() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserAuthority getAuthority() {
		return authority;
	}

	public void setAuthority(UserAuthority authority) {
		this.authority = authority;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Set<Enlace> getEnlaces_favoritos() {
		return enlaces_favoritos;
	}

	public void setEnlaces_favoritos(Set<Enlace> enlaces_favoritos) {
		this.enlaces_favoritos = enlaces_favoritos;
	}
	
	public Set<Enlace> getEnlaces_guardados() {
		return enlaces_guardados;
	}

	public void setEnlaces_guardados(Set<Enlace> enlaces_guardados) {
		this.enlaces_guardados = enlaces_guardados;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserVoteEnlace> getUserVoteEnlace() {
		return userVoteEnlace;
	}

	public void setUserVoteEnlace(Set<UserVoteEnlace> userVoteEnlace) {
		this.userVoteEnlace = userVoteEnlace;
	}
}
