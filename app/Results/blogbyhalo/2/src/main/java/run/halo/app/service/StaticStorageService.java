package run.halo.app.service;
 import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.model.support.StaticFile;
public interface StaticStorageService {

 private String API_FOLDER_NAME;

 private String STATIC_FOLDER;


public List<StaticFile> listStaticFolder()
;

public void upload(String basePath,MultipartFile file)
;

public void rename(String basePath,String newName)
;

public void save(String path,String content)
;

public void createFolder(String basePath,String folderName)
;

public void delete(String relativePath)
;

}