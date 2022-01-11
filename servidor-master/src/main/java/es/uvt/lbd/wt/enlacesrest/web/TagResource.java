package es.uvt.lbd.wt.enlacesrest.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundExceptionTag;
import es.uvt.lbd.wt.enlacesrest.model.service.TagService;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.EnlaceDTO;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.TagDTO;

@RestController
@RequestMapping("/api/tags")
public class TagResource {
	
	@Autowired
	private TagService tagService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<TagDTO> findAll() {
		return tagService.findAll();
	}

	@GetMapping("/{id}")
	public TagDTO findById(@PathVariable Long id) throws InstanceNotFoundExceptionTag {
		return tagService.findById(id);
	}
	
	@GetMapping("/enlaces/{keywords}")
	public List<EnlaceDTO> findByKeywords(@PathVariable String keywords){
			return tagService.findByKeywords(keywords);
	}
}
