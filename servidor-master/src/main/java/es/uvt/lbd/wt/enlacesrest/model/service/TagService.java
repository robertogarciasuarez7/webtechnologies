package es.uvt.lbd.wt.enlacesrest.model.service;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvt.lbd.wt.enlacesrest.model.domain.Enlace;
import es.uvt.lbd.wt.enlacesrest.model.domain.Tag;
import es.uvt.lbd.wt.enlacesrest.model.exception.InstanceNotFoundExceptionTag;
import es.uvt.lbd.wt.enlacesrest.model.repository.EnlaceDao;
import es.uvt.lbd.wt.enlacesrest.model.repository.TagDao;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.EnlaceDTO;
import es.uvt.lbd.wt.enlacesrest.model.service.dto.TagDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TagService {

	@Autowired
	private TagDao tagDao;
	@Autowired
	private EnlaceDao enlaceDao;

	public List<TagDTO> findAll() {
		List<Tag> tag = tagDao.findAll();
		List<TagDTO> tagDTO = tag.stream().map(m -> new TagDTO(m)).collect(Collectors.toList());
		return tagDTO;
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public TagDTO create(TagDTO tag) {
		Tag ca = new Tag(tag.getNombre());
		tagDao.create(ca);
		return new TagDTO(ca);
	}

	public TagDTO findById(Long id) throws InstanceNotFoundExceptionTag {
		Tag ca = tagDao.findById(id);
		if (ca == null)
			throw new InstanceNotFoundExceptionTag("Tag no existe.");
		return new TagDTO(ca);
	}

	public List<EnlaceDTO> findByKeywords(String keywords) {
		Tag tag=tagDao.findByKeywords(keywords);
		Long Idtag=tag.getId();
		
		if(Idtag != null) {
			List<Enlace> enlTag = enlaceDao.findByEnlTag(Idtag);
			List<EnlaceDTO> enlacesDto = enlTag.stream().map(m -> new EnlaceDTO(m)).collect(Collectors.toList());
			return enlacesDto;

		}else {
			List<EnlaceDTO> enlacesDto = new ArrayList<EnlaceDTO>();
			return enlacesDto;
		}
				
	}

}
