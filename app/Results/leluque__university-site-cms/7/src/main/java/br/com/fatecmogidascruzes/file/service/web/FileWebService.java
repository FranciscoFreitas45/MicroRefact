package br.com.fatecmogidascruzes.file.service.web;
 import java.util.UUID;
import br.com.fatecmogidascruzes.exception.DoesNotHaveAccessException;
import br.com.fatecmogidascruzes.exception.InexistentOrDisabledEntity;
import br.com.fatecmogidascruzes.file.FileDTO;
public interface FileWebService {


public FileDTO getImage(UUID fileHash,Integer width,Integer height) throws InexistentOrDisabledEntity
;

public FileDTO getFile(UUID fileHash) throws InexistentOrDisabledEntity
;

}