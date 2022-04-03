package br.com.fatecmogidascruzes.gallery.service.web;
 import java.util.List;
import br.com.fatecmogidascruzes.exception.web.BadRequestException;
import br.com.fatecmogidascruzes.exception.web.ForbiddenException;
import br.com.fatecmogidascruzes.exception.web.InternalErrorException;
public interface AlbumWebService {


public void upload(AlbumPhotosEditDTO albumEditDTO) throws BadRequestException
;

public List<AlbumDTO> getEnabled()
;

public void save(AlbumEditDTO albumEditDTO) throws BadRequestException
;

public List<AlbumDTO> getEnabledForGallery()
;

}