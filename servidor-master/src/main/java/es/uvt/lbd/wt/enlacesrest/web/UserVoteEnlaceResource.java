package es.uvt.lbd.wt.enlacesrest.web;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundException;
import es.uvt.lbd.wt.enlacesrest.model.service.UserVoteEnlaceService;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.UserVoteEnlaceDTO;
import es.uvt.lbd.wt.enlacesrest.web.exception.RequestBodyNotValidException;


@RestController
@RequestMapping("/api/uservotes")
public class UserVoteEnlaceResource {

	@Autowired
	private UserVoteEnlaceService userVoteService;
	
	@GetMapping
	public List<UserVoteEnlaceDTO> findAll() {
		return userVoteService.findAll();
	}

	@PostMapping
	public UserVoteEnlaceDTO create(@RequestBody @Valid UserVoteEnlaceDTO userVote)
			throws RequestBodyNotValidException {
		return userVoteService.create(userVote);
	}

	@GetMapping("/enlace/{id}")
	public Double averageRate(@PathVariable Long id) throws InstanceNotFoundException {
		return userVoteService.averageValoracion(id);
	}

}
