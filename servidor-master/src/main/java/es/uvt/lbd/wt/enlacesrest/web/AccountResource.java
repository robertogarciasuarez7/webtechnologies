package es.uvt.lbd.wt.enlacesrest.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uvt.lbd.wt.enlacesrest.model.exception.UserEmailExistsException;
import es.uvt.lbd.wt.enlacesrest.model.exception.UserLoginExistsException;
import es.uvt.lbd.wt.enlacesrest.model.service.UserService;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.LoginDTO;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.UserDTOPrivate;
import es.uvt.lbd.wt.enlacesrest.security.JWTConfigurer;
import es.uvt.lbd.wt.enlacesrest.security.JWTToken;
import es.uvt.lbd.wt.enlacesrest.security.TokenProvider;
import es.uvt.lbd.wt.enlacesrest.web.exception.CredentialsAreNotValidException;

/**
 * Este controlador va por separado que el UserResource porque se encarga de
 * tareas relacionadas con la autenticación, registro, etc.
 *
 * <p>
 * También permite a cada usuario logueado en la aplicación obtener información
 * de su cuenta
 */
@RestController
@RequestMapping("/api")
public class AccountResource {
	private final Logger logger = LoggerFactory.getLogger(AccountResource.class);

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@PostMapping("/authenticate")
	public JWTToken authenticate(@Valid @RequestBody LoginDTO loginDTO) throws CredentialsAreNotValidException {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginDTO.getLogin(), loginDTO.getPassword());
		try {
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = tokenProvider.createToken(authentication);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
			return new JWTToken(jwt);
		} catch (AuthenticationException e) {
			logger.warn(e.getMessage(), e);
			throw new CredentialsAreNotValidException(e.getMessage());
		}
	}

	@GetMapping("/account")
	public UserDTOPrivate getAccount() {
		return userService.getCurrentUserWithAuthority();
	}

	@PostMapping("/register")
	public void registerAccount(@Valid @RequestBody UserDTOPrivate account)
			throws UserLoginExistsException, UserEmailExistsException {
		userService.registerUser(account.getLogin(), account.getEmail(), account.getPassword());
	}
}
