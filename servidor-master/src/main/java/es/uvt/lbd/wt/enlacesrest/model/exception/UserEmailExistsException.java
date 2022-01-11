package es.uvt.lbd.wt.enlacesrest.model.exception;

@SuppressWarnings("serial")
public class UserEmailExistsException extends Exception {
  public UserEmailExistsException(String msg) {
    super(msg);
  }
}