package es.uvt.lbd.wt.enlacesrest.model.repository;

import java.util.List;

import es.uvt.lbd.wt.enlacesrest.model.domain.Enlace;


public interface EnlaceDao {

	List<Enlace> findAll();

	void create(Enlace en);

	Enlace findById(Long id);
	
	Enlace findByName(String name);

	void update(Enlace enl);

	void delete(Long id);

	List<Enlace> findByEnlCat(Long id);

	List<Enlace> findByEnlTag(Long id);

}
