package es.uvt.lbd.wt.enlacesrest.model.repository;

import java.util.List;

import es.uvt.lbd.wt.enlacesrest.model.domain.Enlace;
import es.uvt.lbd.wt.enlacesrest.model.domain.UserVoteEnlace;


public interface UserVoteEnlaceDao {

	void create(UserVoteEnlace uservote);
	
	List<Integer> enlacesVotados(Enlace enlace);

	List<UserVoteEnlace> findAll();

}
