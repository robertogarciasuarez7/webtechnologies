package es.uvt.lbd.wt.enlacesrest.model.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import es.uvt.lbd.wt.enlacesrest.model.domain.Enlace;
import es.uvt.lbd.wt.enlacesrest.model.repository.util.GenericDaoJpa;


@Repository
public class EnlaceDaoJpa extends GenericDaoJpa implements EnlaceDao {

	@Override
	public List<Enlace> findAll() {
		return entityManager.createQuery("from Enlace e order by e.valoracion DESC", Enlace.class).getResultList();
	}

	@Override
	public void create(Enlace en) {
		entityManager.persist(en);

	}

	@Override
	public Enlace findById(Long id) {
		return entityManager.find(Enlace.class, id);

	}

	@Override
	public void update(Enlace en) {
			entityManager.merge(en);
	}

	@Override
	public void delete(Long id) {
		Enlace enlace = findById(id);
		if (enlace != null)
			delete(enlace);
	}

	private void delete(Enlace enlace) {
		entityManager.remove(enlace);
	}

	@Override
	public List<Enlace> findByEnlCat(Long id) {
		return entityManager.createQuery("from Enlace enl where enl.categoria.id =" + id, Enlace.class).getResultList();
	}

	@Override
	public List<Enlace> findByEnlTag(Long id) {
		return entityManager.createQuery(
				"from Enlace enl where enl.id IN (SELECT enl.id FROM Enlace enl LEFT OUTER JOIN  enl.tags t where t.id ="
						+ id + ")",
				Enlace.class).getResultList();
	}

	@Override
	public Enlace findByName(String nombre) {
		TypedQuery<Enlace> query = entityManager.createQuery("from Enlace e where e.nombre = :nombre", Enlace.class)
				.setParameter("nombre", nombre);
		return DataAccessUtils.singleResult(query.getResultList());
	}

}
