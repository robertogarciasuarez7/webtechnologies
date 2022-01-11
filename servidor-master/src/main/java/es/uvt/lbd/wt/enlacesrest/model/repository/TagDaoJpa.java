package es.uvt.lbd.wt.enlacesrest.model.repository;

import java.util.List;


import org.springframework.stereotype.Repository;

import es.uvt.lbd.wt.enlacesrest.model.domain.Tag;
import es.uvt.lbd.wt.enlacesrest.model.repository.util.GenericDaoJpa;

@Repository
public class TagDaoJpa extends GenericDaoJpa implements TagDao {

	@Override
	public List<Tag> findAll() {
		return entityManager.createQuery("from Tag", Tag.class).getResultList();
	}

	@Override
	public void create(Tag tag) {
		entityManager.persist(tag);

	}

	@Override
	public Tag findById(Long id) {
		return entityManager.find(Tag.class, id);

	}

	@Override
	public Tag findByKeywords(String keywords) {
		return entityManager.createQuery("from Tag tag where tag.nombre like :keywords", Tag.class).setParameter("keywords", "%" + keywords + "%").getSingleResult();
	}
	
}
