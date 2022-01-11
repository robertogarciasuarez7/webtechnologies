package es.uvt.lbd.wt.enlacesrest.model.exception;

import es.uvt.lbd.wt.enlacesrest.web.exception.ResourceException;

@SuppressWarnings("serial")
public class InstanceNotFoundExceptionTag extends ResourceException{
  
	public InstanceNotFoundExceptionTag(String message) {
		  super(message);	}
}
