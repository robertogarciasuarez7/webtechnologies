package es.uvt.lbd.wt.enlacesrest.model.repository;

import java.util.List;

import es.uvt.lbd.wt.enlacesrest.model.domain.Categoria;


public interface CategoriaDao {

	List<Categoria> findAll();

	void create(Categoria ca);

	Categoria findById(Long id);

}