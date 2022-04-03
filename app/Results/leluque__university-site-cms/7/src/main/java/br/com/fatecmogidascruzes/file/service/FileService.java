package br.com.fatecmogidascruzes.file.service;
 import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import br.com.fatecmogidascruzes.file.File;
import br.com.fatecmogidascruzes.service.BaseService;
public interface FileService extends BaseService<File, Long>{


public byte[] loadBytes(Long id,int width,int height)
;

public File saveFile(MultipartFile file)
;

public File saveImage(MultipartFile multipartFile,String alternativeDescription) throws IOException
;

}