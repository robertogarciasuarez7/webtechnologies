package es.uvt.lbd.wt.enlacesrest.model.exception;

@SuppressWarnings("serial")
public class UserLoginExistsException extends Exception {
  public UserLoginExistsException(String msg) {
    super(msg);
  }
}
