package es.uvt.lbd.wt.enlacesrest.web;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundException;
import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundUserException;
import es.uvt.lbd.wt.enlacesrest.model.service.UserService;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.EnlaceDTO;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.UserDTOPublic;
import es.uvt.lbd.wt.enlacesrest.web.exception.IdPathNotMathingIdInEntityException;

@RestController
@RequestMapping("/api/users")

public class UserResource {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public List<UserDTOPublic> findAll() {
		return userService.findAll();
	}

	@GetMapping("/{id}")
	public UserDTOPublic findById(@PathVariable Long id) throws InstanceNotFoundUserException {
		
		 return userService.findById(id);
	}
	
	@GetMapping("/favoritos")
	public List<EnlaceDTO> findByFavoritos() throws InstanceNotFoundException {
		return userService.findByFavoritos();
	}

	@GetMapping("/guardados")
	public List<EnlaceDTO> findByGuardados() throws InstanceNotFoundException {
		return userService.findByGuardados();
	}
	
	@GetMapping("/favoritos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<EnlaceDTO> findFavsById(@PathVariable Long id) throws InstanceNotFoundUserException{
		return userService.findFavsById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	  public void delete(@PathVariable Long id)  throws InstanceNotFoundUserException{
	    userService.delete(id);
	  }
	
	@PutMapping("/{id}")
	public UserDTOPublic update(@PathVariable Long id)
			throws IdPathNotMathingIdInEntityException {

		return userService.update(id);
	}
}
