package es.uvt.lbd.wt.enlacesrest.web.exception;

@SuppressWarnings("serial")
public class CredentialsAreNotValidException extends ResourceException {

  public CredentialsAreNotValidException(String errorMsg) {
    super(errorMsg);
  }
}
