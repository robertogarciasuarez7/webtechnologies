package es.uvt.lbd.wt.enlacesrest.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundException;
import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundExceptionCategoria;
import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundExceptionTag;
import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundUserException;
import es.uvt.lbd.wt.enlacesrest.model.exception.NameEnlaceException;
import es.uvt.lbd.wt.enlacesrest.model.exception.UserEmailExistsException;
import es.uvt.lbd.wt.enlacesrest.model.exception.UserLoginExistsException;
import es.uvt.lbd.wt.enlacesrest.web.exception.CredentialsAreNotValidException;
import es.uvt.lbd.wt.enlacesrest.web.exception.RequestBodyNotValidException;
import es.uvt.lbd.wt.enlacesrest.web.exception.UserNotExists;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
  private final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

  @ExceptionHandler({NameEnlaceException.class, UserEmailExistsException.class, UserLoginExistsException.class,RequestBodyNotValidException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorDTO badRequestExceptionHandler(Exception e) {
    logger.error(e.getMessage());
    logger.warn(e.getMessage());
    logger.info(e.getMessage());
    logger.debug(e.getMessage(), e);
    return new ErrorDTO(e.getMessage());
  }
  
  @ExceptionHandler({CredentialsAreNotValidException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public ErrorDTO unauthorizedExceptionHandler(Exception e) {
    logger.error(e.getMessage());
    logger.warn(e.getMessage());
    logger.info(e.getMessage());
    logger.debug(e.getMessage(), e);
    return new ErrorDTO(e.getMessage());
  }
  
  @ExceptionHandler({InstanceNotFoundException.class,UserNotExists.class, InstanceNotFoundExceptionTag.class,InstanceNotFoundUserException.class, InstanceNotFoundExceptionCategoria.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorDTO instanceNotFoundExceptionHandler(Exception e) {
    logger.error(e.getMessage());
    logger.warn(e.getMessage());
    logger.info(e.getMessage());
    logger.debug(e.getMessage(), e);
    return new ErrorDTO(e.getMessage());
  }
  
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ErrorDTO internalErrorExceptionHandler(Exception e) {
    logger.error(e.getMessage(), e);
    return new ErrorDTO(e.getMessage());
  }
}
