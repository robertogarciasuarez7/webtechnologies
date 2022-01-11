package es.uvt.lbd.wt.enlacesrest.model.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class UserVoteEnlace {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userVoteEnlace_generator")
	@SequenceGenerator(name = "userVoteEnlace_generator", sequenceName = "userVoteEnlace_seq")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="valorado_usuario", nullable =true)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="valorado_enlace", nullable =true)
	private Enlace enlace;
	
	private int valoracion;
	

	public UserVoteEnlace() {
		
	}

	public UserVoteEnlace( User user, Enlace enlace, int valoracion) {
		super();
		this.user = user;
		this.enlace = enlace;
		this.valoracion = valoracion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Enlace getEnlace() {
		return enlace;
	}

	public void setEnlace(Enlace enlace) {
		this.enlace = enlace;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

}
