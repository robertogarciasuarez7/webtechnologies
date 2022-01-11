package es.uvt.lbd.wt.enlacesrest.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundExceptionCategoria;
import es.uvt.lbd.wt.enlacesrest.model.service.CategoriaService;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.CategoriaDTO;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.EnlaceDTO;


@RestController
@RequestMapping("/api/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CategoriaDTO> findAll() {
		return categoriaService.findAll();
	}

	@GetMapping("/{id}")
	public CategoriaDTO findById(@PathVariable Long id) throws InstanceNotFoundExceptionCategoria {
		return categoriaService.findById(id);
	}

	@GetMapping("/{id}/enlaces")
	@ResponseStatus(HttpStatus.OK)
	public List<EnlaceDTO> findByCategoria(@PathVariable Long id) throws InstanceNotFoundExceptionCategoria{
		return categoriaService.findByCategoria(id);
	}

}
