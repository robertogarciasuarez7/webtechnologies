package es.uvt.lbd.wt.enlacesrest.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import es.uvt.lbd.wt.enlacesrest.model.exception.NameEnlaceException;
import es.uvt.lbd.wt.enlacesrest.model.exception.UserEmailExistsException;
import es.uvt.lbd.wt.enlacesrest.model.exception.UserLoginExistsException;
import es.uvt.lbd.wt.enlacesrest.model.service.CategoriaService;
import es.uvt.lbd.wt.enlacesrest.model.service.EnlaceService;
import es.uvt.lbd.wt.enlacesrest.model.service.TagService;
import es.uvt.lbd.wt.enlacesrest.model.service.UserService;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.CategoriaDTO;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.EnlaceDTO;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.TagDTO;

@Configuration
public class DatabaseLoader {
	private final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);

	@Autowired
	private UserService userService;
	@Autowired
	private EnlaceService enlaceService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private TagService tagService;

	@PostConstruct
	public void init() throws NameEnlaceException, UserEmailExistsException {
		try {
			if (userService.findAll().size() == 0) {

				userService.registerUser("admin", "admin@admin.es", "admin", true);
				userService.registerUser("user", "user@uvt.ro", "user");
				userService.registerUser("gema", "gema@uvt.ro", "gema");
        userService.registerUser("luismi", "luismi@uvt.ro", "luismi");
				userService.registerUser("roberto", "roberto@uvt.ro", "roberto");


				categoriaService.create(new CategoriaDTO("Comedy"));
				categoriaService.create(new CategoriaDTO("Romance"));
				categoriaService.create(new CategoriaDTO("Scary"));



				enlaceService.create(new EnlaceDTO("The Big Sick",
						"3.4 $",
						"2017"));
				enlaceService.create(new EnlaceDTO("Mean Girls",
						"5.8 $",
						"2004"));
				enlaceService.create(new EnlaceDTO("Pulp Fiction",
						"6.0 $",
						"1994"));
				enlaceService.create(new EnlaceDTO("A Star Is Born",
						"2.5 $",
						"2018"));
				enlaceService.create(new EnlaceDTO("Notting hill",
						"8.1 $",
						"1999"));
				enlaceService.create(new EnlaceDTO("Love Actually",
						"7.15 $",
						"2003"));
				enlaceService.create(new EnlaceDTO("The exorcist",
						"3.4 $",
						"1973"));
				enlaceService.create(new EnlaceDTO("The Ring",
						"4.3 $",
						"2002"));


				tagService.create(new TagDTO("Comedy"));
				tagService.create(new TagDTO("Romance"));
				tagService.create(new TagDTO("Scary"));


			}
		} catch (UserLoginExistsException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
