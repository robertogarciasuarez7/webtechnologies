package es.uvt.lbd.wt.enlacesrest.model.exception;

@SuppressWarnings("serial")
public class NameEnlaceException extends Exception {
 
	public NameEnlaceException() {
		super("Nombre no válido");
	}
}
