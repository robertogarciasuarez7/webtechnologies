package es.uvt.lbd.wt.enlacesrest.model.repository.util;

import org.springframework.web.multipart.MultipartFile;

import es.uvt.lbd.wt.enlacesrest.model.exception.AppException;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.ImageDTO;

public interface ImageService {
	
	String saveImage(MultipartFile file, Long id) throws AppException;

    ImageDTO getImage(String imagePath, Long id) throws AppException;

}
