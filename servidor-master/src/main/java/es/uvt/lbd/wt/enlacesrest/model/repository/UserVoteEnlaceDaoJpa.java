package es.uvt.lbd.wt.enlacesrest.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.uvt.lbd.wt.enlacesrest.model.domain.Enlace;
import es.uvt.lbd.wt.enlacesrest.model.domain.UserVoteEnlace;
import es.uvt.lbd.wt.enlacesrest.model.repository.util.GenericDaoJpa;

@Repository
public class UserVoteEnlaceDaoJpa extends GenericDaoJpa implements UserVoteEnlaceDao {

	@Override
	public void create(UserVoteEnlace uservote) {
		entityManager.persist(uservote);		
	}
		
	@Override
	public List<Integer> enlacesVotados(Enlace enlace) {
		return entityManager.createQuery("Select valoracion from UserVoteEnlace ve where ve.enlace =: enlace", Integer.class).setParameter("enlace", enlace).getResultList();
	}

	@Override
	public List<UserVoteEnlace> findAll() {
		return entityManager.createQuery("from UserVoteEnlace", UserVoteEnlace.class).getResultList();		
	}

}
