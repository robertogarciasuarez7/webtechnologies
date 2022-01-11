package es.uvt.lbd.wt.enlacesrest.model.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import es.uvt.lbd.wt.enlacesrest.model.domain.Enlace;
import es.uvt.lbd.wt.enlacesrest.model.domain.User;
import es.uvt.lbd.wt.enlacesrest.model.domain.UserVoteEnlace;
import es.uvt.lbd.wt.enlacesrest.model.exception.AppException;
import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundException;
import es.uvt.lbd.wt.enlacesrest.model.exception.NameEnlaceException;
import es.uvt.lbd.wt.enlacesrest.model.repository.CategoriaDao;
import es.uvt.lbd.wt.enlacesrest.model.repository.EnlaceDao;
import es.uvt.lbd.wt.enlacesrest.model.repository.TagDao;
import es.uvt.lbd.wt.enlacesrest.model.repository.UserDao;
import es.uvt.lbd.wt.enlacesrest.model.repository.UserVoteEnlaceDao;
import es.uvt.lbd.wt.enlacesrest.model.repository.util.ImageService;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.EnlaceDTO;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.ImageDTO;
import es.uvt.lbd.wt.enlacesrest.security.SecurityUtils;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class EnlaceService {

	@Autowired
	private EnlaceDao enlaceDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CategoriaDao categoriaDao;
	@Autowired
	private TagDao tagDao;
	@Autowired
	private UserVoteEnlaceDao userVoteEnlaceDao;
	@Autowired
	private ImageService imageService;

	public List<EnlaceDTO> findAll() {
		List<Enlace> enlace = enlaceDao.findAll();
		List<EnlaceDTO> enlaceDTO = enlace.stream().map(m -> new EnlaceDTO(m)).collect(Collectors.toList());
		return enlaceDTO;
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public EnlaceDTO create(EnlaceDTO enlace) throws NameEnlaceException {
		Enlace en = new Enlace(enlace.getNombre(), enlace.getDescripcion(), enlace.getEnl());
		if (enlaceDao.findByName(en.getNombre()) != null) {
			throw new NameEnlaceException();
		}
		if (enlace.getCategoria() != null) {
			en.setCategoria(categoriaDao.findById(enlace.getCategoria().getId()));
		}
		if (enlace.getTags() != null) {
			enlace.getTags().forEach(tag -> {
				en.getTags().add(tagDao.findById(tag.getId()));
			});
		}
		en.setValoracion(0.0);
		enlaceDao.create(en);
		return new EnlaceDTO(en);
	}

	public EnlaceDTO findById(Long id) throws InstanceNotFoundException {
		Enlace enlace = enlaceDao.findById(id);
		if (enlace == null)
			throw new InstanceNotFoundException("No se ha encontrado el enlace.");
		return new EnlaceDTO(enlace);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public EnlaceDTO update(EnlaceDTO enlace) {
		Enlace enl = enlaceDao.findById(enlace.getId());

		enl.setDescripcion(enlace.getDescripcion());
		enl.setEnlace(enlace.getEnl());
		enl.setNombre(enlace.getNombre());

		if( enlace.getCategoria() != null) {
			if (enlace.getCategoria().getId() != null) {
				enl.setCategoria(categoriaDao.findById(enlace.getCategoria().getId()));
			}
		}
		enl.getTags().clear();
		if (enlace.getTags() != null) {
			enlace.getTags().forEach(tag -> {
				enl.getTags().add(tagDao.findById(tag.getId()));
			});
		}
			enlaceDao.update(enl);
			return new EnlaceDTO(enl);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void saveEnlaceImageById(Long id, MultipartFile file) throws InstanceNotFoundException, AppException{
		Enlace enlace = enlaceDao.findById(id);
		if (enlace == null)
			throw new InstanceNotFoundException("No se ha encontrado la imagen.");

		String filePath = imageService.saveImage(file, enlace.getId()); // guardar imagen
		enlace.setImagePath(filePath); // asociar nombre al enlace
		enlaceDao.update(enlace);

	}

	public ImageDTO getEnlaceImageById(Long id) throws InstanceNotFoundException, AppException {
		Enlace enlace = enlaceDao.findById(id);
		if (enlace == null)
			throw new InstanceNotFoundException("No se ha encontrado la imagen.");

		return imageService.getImage(enlace.getImagePath(), enlace.getId());

	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id) {
		enlaceDao.delete(id);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean addFavorito(Long id) {
		Enlace enl = enlaceDao.findById(id);
		User user = userDao.findByLogin(SecurityUtils.getCurrentUserLogin());
		user.getEnlaces_favoritos().add(enl);
		return true;
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean removeFavorito(Long id) {
		Enlace enl = enlaceDao.findById(id);
		User user = userDao.findByLogin(SecurityUtils.getCurrentUserLogin());
		user.getEnlaces_favoritos().remove(enl);
		return true;
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean favoritoCheck(Long id) {
		Enlace enl = enlaceDao.findById(id);
		User user = userDao.findByLogin(SecurityUtils.getCurrentUserLogin());
		Set<Enlace> enlacefav = user.getEnlaces_favoritos();
		if (enlacefav.contains(enl)) {
			return true;
		} else {
			return false;
		}
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean addGuardado(Long id) {
		Enlace enl = enlaceDao.findById(id);
		User user = userDao.findByLogin(SecurityUtils.getCurrentUserLogin());
		user.getEnlaces_guardados().add(enl);
		return true;
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean removeGuardado(Long id) {
		Enlace enl = enlaceDao.findById(id);
		User user = userDao.findByLogin(SecurityUtils.getCurrentUserLogin());
		user.getEnlaces_guardados().remove(enl);
		return true;
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean guardadoCheck(Long id) {
		Enlace enl = enlaceDao.findById(id);
		User user = userDao.findByLogin(SecurityUtils.getCurrentUserLogin());
		Set<Enlace> enlacesave = user.getEnlaces_guardados();
		if (enlacesave.contains(enl)) {
			return true;
		} else {
			return false;
		}
	}

	 @Transactional(readOnly = false, rollbackFor = Exception.class)
		public boolean valoradoCheck(Long id) {
			Enlace enl = enlaceDao.findById(id);
			User user = userDao.findByLogin(SecurityUtils.getCurrentUserLogin());
			List<UserVoteEnlace> votos = userVoteEnlaceDao.findAll();

			for (UserVoteEnlace v : votos) {
				if((v.getEnlace().getId() == enl.getId()) && (v.getUser().getId() == user.getId()))
					return true;
			}
			return false;
		}
}
