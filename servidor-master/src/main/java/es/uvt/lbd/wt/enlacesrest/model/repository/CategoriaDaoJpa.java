package es.uvt.lbd.wt.enlacesrest.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.uvt.lbd.wt.enlacesrest.model.domain.Categoria;
import es.uvt.lbd.wt.enlacesrest.model.repository.util.GenericDaoJpa;


@Repository
public class  CategoriaDaoJpa extends GenericDaoJpa implements CategoriaDao {

	@Override
	public List<Categoria> findAll() {
		return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
	}
	
	@Override
	public void create(Categoria ca) {
		entityManager.persist(ca);

	}

	@Override
	public Categoria findById(Long id) {
		return entityManager.find(Categoria.class, id);

	}
	
}