package es.uvt.lbd.wt.enlacesrest.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvt.lbd.wt.enlacesrest.model.domain.Enlace;
import es.uvt.lbd.wt.enlacesrest.model.domain.UserVoteEnlace;
import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundException;
import es.uvt.lbd.wt.enlacesrest.model.repository.EnlaceDao;
import es.uvt.lbd.wt.enlacesrest.model.repository.UserDao;
import es.uvt.lbd.wt.enlacesrest.model.repository.UserVoteEnlaceDao;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.UserVoteEnlaceDTO;


@Service
@Transactional(readOnly= true, rollbackFor = Exception.class)

public class UserVoteEnlaceService {

	    @Autowired private UserVoteEnlaceDao userVoteEnlaceDAO;
	    @Autowired private UserDao userDAO;
	    @Autowired private EnlaceDao enlaceDAO;
	    
	    public List<UserVoteEnlaceDTO> findAll() {
			List<UserVoteEnlace> userVote = userVoteEnlaceDAO.findAll();
			List<UserVoteEnlaceDTO> UserVoteEnlaceDTO = userVote.stream().map(m -> new UserVoteEnlaceDTO(m)).collect(Collectors.toList());
			return UserVoteEnlaceDTO;
		}
	    
	    @Transactional(readOnly = false, rollbackFor = Exception.class)
	    public UserVoteEnlaceDTO create(UserVoteEnlaceDTO userVote){    
	    	UserVoteEnlace bdUserVote = new UserVoteEnlace();
	        bdUserVote.setUser(userDAO.findById(userVote.getUser().getId()));
	        bdUserVote.setEnlace(enlaceDAO.findById(userVote.getEnlace().getId()));
	        bdUserVote.setValoracion(userVote.getValoracion());
	        userVoteEnlaceDAO.create(bdUserVote);
	        return new UserVoteEnlaceDTO(bdUserVote);
	    }
	    
	    @Transactional(readOnly = false, rollbackFor = Exception.class)
	    public Double averageValoracion(Long id) throws  InstanceNotFoundException{
	    			    	
	        Double total = 0.0;
	        Double resultado = 0.0;
	        Enlace enlace = enlaceDAO.findById(id);
	        if (enlace != null) {
	            List<Integer> enlaces = userVoteEnlaceDAO.enlacesVotados(enlace);
	            for (Integer i : enlaces) {
	                total = total + i;
	            }
	            resultado =  (total / enlaces.size());
	            enlace.setValoracion(resultado);
	            enlaceDAO.update(enlace);
	            return resultado;
	           
	        } throw new InstanceNotFoundException("No se ha encontrado el enlace.");

	    }   
}
