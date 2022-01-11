package es.uvt.lbd.wt.enlacesrest.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvt.lbd.wt.enlacesrest.model.domain.User;
import es.uvt.lbd.wt.enlacesrest.model.domain.UserAuthority;
import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundUserException;
import es.uvt.lbd.wt.enlacesrest.model.exception.UserEmailExistsException;
import es.uvt.lbd.wt.enlacesrest.model.exception.UserLoginExistsException;
import es.uvt.lbd.wt.enlacesrest.model.repository.UserDao;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.EnlaceDTO;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.UserDTOPrivate;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.UserDTOPublic;
import es.uvt.lbd.wt.enlacesrest.security.SecurityUtils;


@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDao userDAO;

	public List<UserDTOPublic> findAll() {
		return userDAO.findAll().stream().map(user -> new UserDTOPublic(user)).collect(Collectors.toList());
	}

	public UserDTOPublic findById(Long id) throws InstanceNotFoundUserException {
		User user = userDAO.findById(id);
		if (user==null) throw new InstanceNotFoundUserException(id, User.class);
		return new UserDTOPublic(user);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void registerUser(String login, String email, String password) throws UserLoginExistsException,UserEmailExistsException {
		registerUser(login, email, password, false);
	}


	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void registerUser(String login, String email, String password, boolean isAdmin)
			throws UserLoginExistsException,UserEmailExistsException {
		if (userDAO.findByLogin(login) != null) {
			throw new UserLoginExistsException("User login " + login + " already exists");
		}
		
		if(userDAO.findByEmail(email) != null) {
			throw new UserEmailExistsException("User email " + email + " already exists");
		}
		
		User user = new User();
		String encryptedPassword = passwordEncoder.encode(password);
		LocalDate fechaRegistro= LocalDate.now();
			
		user.setLogin(login);
		user.setEmail(email);
		user.setFechaRegistro(fechaRegistro);
		user.setPassword(encryptedPassword);
		user.setAuthority(UserAuthority.USER);
		
		
		if (isAdmin) {
			user.setAuthority(UserAuthority.ADMIN);
		}

		userDAO.create(user);
		
	/*	if (user.getEmail() == null) {
			throw new UserEmailExistsException("User email " + email + " already exists");
		}*/
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public UserDTOPublic update(Long id) {
		User u = userDAO.findById(id);
		if(u.getAuthority()==UserAuthority.USER) {
		u.setAuthority(UserAuthority.ADMIN);
		}else {
			u.setAuthority(UserAuthority.USER);
		}
		userDAO.update(u);
		return new UserDTOPublic(u);
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public UserDTOPrivate getCurrentUserWithAuthority() {
		String currentUserLogin = SecurityUtils.getCurrentUserLogin();
		if (currentUserLogin != null) {
			return new UserDTOPrivate(userDAO.findByLogin(currentUserLogin));
		}
		return null;
	}

	public List<EnlaceDTO> findByFavoritos() {
		User user = userDAO.findByLogin(SecurityUtils.getCurrentUserLogin());
		return user.getEnlaces_favoritos().stream().map(enlacefav -> new EnlaceDTO(enlacefav))
				.collect(Collectors.toList());
	}
	

	public List<EnlaceDTO> findByGuardados() {
		User user = userDAO.findByLogin(SecurityUtils.getCurrentUserLogin());
		return user.getEnlaces_guardados().stream().map(enlacesave -> new EnlaceDTO(enlacesave))
				.collect(Collectors.toList());
	}
	

	public List<EnlaceDTO> findFavsById(Long id) throws InstanceNotFoundUserException {// Para cuando vas a ver el perfil del usuario
		User user = userDAO.findById(id);
		if (user==null) throw new InstanceNotFoundUserException(id, User.class);
		
		return user.getEnlaces_favoritos().stream().map(enlacefav -> new EnlaceDTO(enlacefav))
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id) throws InstanceNotFoundUserException{
		User user = userDAO.findById(id);
		if (user==null) throw new InstanceNotFoundUserException(id, User.class);
		
		 userDAO.delete(id);
	}
}
