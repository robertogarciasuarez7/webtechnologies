package es.uvt.lbd.wt.enlacesrest.model.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvt.lbd.wt.enlacesrest.model.domain.Categoria;
import es.uvt.lbd.wt.enlacesrest.model.domain.Enlace;
import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundExceptionCategoria;
import es.uvt.lbd.wt.enlacesrest.model.repository.CategoriaDao;
import es.uvt.lbd.wt.enlacesrest.model.repository.EnlaceDao;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.CategoriaDTO;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.EnlaceDTO;


@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CategoriaService {

	@Autowired private CategoriaDao categoriaDao;
	@Autowired private EnlaceDao enlaceDao;
	

	public List<CategoriaDTO> findAll() {
		List<Categoria> categoria = categoriaDao.findAll();
		List<CategoriaDTO> categoriaDTO = categoria.stream().map(m -> new CategoriaDTO(m)).collect(Collectors.toList());
		return categoriaDTO;
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public CategoriaDTO create(CategoriaDTO categoria) {
		Categoria ca = new Categoria(categoria.getNombre());
		categoriaDao.create(ca);
		return new CategoriaDTO(ca);
	}

	public CategoriaDTO findById(Long id) throws InstanceNotFoundExceptionCategoria {
		Categoria ca = categoriaDao.findById(id);
		if (ca == null)
			throw new InstanceNotFoundExceptionCategoria("La categoría no existe.");
		return new CategoriaDTO(ca);
	}

	public List<EnlaceDTO> findByCategoria(Long id) throws InstanceNotFoundExceptionCategoria{
		Categoria ca = categoriaDao.findById(id);
		if (ca == null)
			throw new InstanceNotFoundExceptionCategoria("La categoría no existe.");
				
		List<Enlace> enlCat = enlaceDao.findByEnlCat(id);
		List<EnlaceDTO> enlacesDto = enlCat.stream().map(m -> new EnlaceDTO(m)).collect(Collectors.toList());
		
		
		return enlacesDto;
	}
	
}
