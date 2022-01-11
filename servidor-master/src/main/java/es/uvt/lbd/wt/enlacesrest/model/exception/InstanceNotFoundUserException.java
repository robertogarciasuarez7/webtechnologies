package es.uvt.lbd.wt.enlacesrest.model.exception;


import es.uvt.lbd.wt.enlacesrest.model.domain.User;


	@SuppressWarnings("serial")
	public class InstanceNotFoundUserException extends Exception {

	  public InstanceNotFoundUserException(Long id, Class<User> class1) {
	    super("No se ha encontrado el usuario con el id " + id);
	  }

	public InstanceNotFoundUserException(String message) {
		super(message);
	}
}
