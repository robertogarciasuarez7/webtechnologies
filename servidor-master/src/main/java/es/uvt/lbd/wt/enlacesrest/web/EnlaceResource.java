package es.uvt.lbd.wt.enlacesrest.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.uvt.lbd.wt.enlacesrest.model.exception.AppException;
import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundException;
import es.uvt.lbd.wt.enlacesrest.model.exception.NameEnlaceException;
import es.uvt.lbd.wt.enlacesrest.model.service.EnlaceService;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.EnlaceDTO;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.ImageDTO;
import es.uvt.lbd.wt.enlacesrest.web.exception.IdPathNotMathingIdInEntityException;

@RestController
@RequestMapping("/api/enlaces")

public class EnlaceResource {

	@Autowired
	private EnlaceService enlaceService;

	@GetMapping
	public List<EnlaceDTO> findAll() {
		return enlaceService.findAll();
	}

	@GetMapping("/{id}")
	public EnlaceDTO findById(@PathVariable Long id) throws InstanceNotFoundException {
		return enlaceService.findById(id);
	}

	@GetMapping("/{id}/image")
	@ResponseStatus(HttpStatus.OK)
	public void getEnlaceImageById(@PathVariable Long id, HttpServletResponse response)
			throws AppException, InstanceNotFoundException {
		ImageDTO image = enlaceService.getEnlaceImageById(id);

		try {
			response.setContentType(image.getMediaType());
			response.setHeader("Content-disposition", "filename=" + image.getFilename()); // visualizar la imagen
			IOUtils.copy(image.getInputStream(), response.getOutputStream());// envio de los types concretos de la
																				// imagen
		} catch (IOException e) {
			e.printStackTrace();
			throw new AppException("Hubo un problema con el envio de la imagenes");
		}
	}

	@PostMapping
	public EnlaceDTO create(@RequestBody EnlaceDTO enlace) throws NameEnlaceException {
		return enlaceService.create(enlace);
	}

	@PostMapping("/{id}/image")
	@ResponseStatus(HttpStatus.OK)
	public void saveEnlaceImageById(@PathVariable Long id, @RequestParam MultipartFile file,
			HttpServletResponse response) throws InstanceNotFoundException, AppException {
		enlaceService.saveEnlaceImageById(id, file);
	} // nos envia el fichero @Request

	@PutMapping("/{id}")
	public EnlaceDTO update(@PathVariable Long id, @RequestBody EnlaceDTO enlace)
			throws IdPathNotMathingIdInEntityException {

		if (id != enlace.getId()) {
			throw new IdPathNotMathingIdInEntityException();
		}
		return enlaceService.update(enlace);
	}

	@GetMapping("/valoradocheck/{id}")
	@ResponseStatus(HttpStatus.OK)
	public boolean valoradoCheck(@PathVariable Long id) {
		return enlaceService.valoradoCheck(id);
	}

	@GetMapping("/favoritocheck/{id}")
	@ResponseStatus(HttpStatus.OK)
	public boolean favoritoCheck(@PathVariable Long id) {
		return enlaceService.favoritoCheck(id);
	}

	@PutMapping("/favorito/{id}")
	@ResponseStatus(HttpStatus.OK)
	public boolean addFavorito(@PathVariable Long id) throws InstanceNotFoundException {
		return enlaceService.addFavorito(id);
	}

	@PutMapping("/unfavorito/{id}")
	@ResponseStatus(HttpStatus.OK)
	public boolean removeFavorito(@PathVariable Long id) throws InstanceNotFoundException {
		return enlaceService.removeFavorito(id);
	}

	@GetMapping("/savecheck/{id}")
	@ResponseStatus(HttpStatus.OK)
	public boolean guardadoCheck(@PathVariable Long id) {
		return enlaceService.guardadoCheck(id);
	}

	@PutMapping("/save/{id}")
	@ResponseStatus(HttpStatus.OK)
	public boolean addGuardado(@PathVariable Long id) throws InstanceNotFoundException {

		return enlaceService.addGuardado(id);
	}

	@PutMapping("/unsave/{id}")
	@ResponseStatus(HttpStatus.OK)
	public boolean removeGuardado(@PathVariable Long id) throws InstanceNotFoundException {

		return enlaceService.removeGuardado(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		enlaceService.delete(id);
	}

}
