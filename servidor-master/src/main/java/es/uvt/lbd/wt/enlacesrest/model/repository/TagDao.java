package es.uvt.lbd.wt.enlacesrest.model.repository;

import java.util.List;

import es.uvt.lbd.wt.enlacesrest.model.domain.Tag;

public interface TagDao {

		List<Tag> findAll();

		void create(Tag tag);

		Tag findById(Long id);
		
		Tag findByKeywords(String keywords);

}