package es.uvt.lbd.wt.enlacesrest.web.exception;

@SuppressWarnings("serial")
public class ResourceException extends Exception {
  public ResourceException(String errorMsg) {
    super(errorMsg);
  }
}
