package es.uvt.lbd.wt.enlacesrest.web.exception;

@SuppressWarnings("serial")
public class RequestBodyNotValidException extends Exception {
	 public RequestBodyNotValidException(String errorMsg) {
	        super(errorMsg);
	    }
}
