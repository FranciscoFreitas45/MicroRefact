package run.halo.app.service;
 import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.model.dto.BackupDTO;
import run.halo.app.model.dto.post.BasePostDetailDTO;
import run.halo.app.model.params.PostMarkdownParam;
public interface BackupService {

 private  String baseUri;


public void deleteMarkdown(String fileName)
;

@NonNull
public Resource loadFileAsResource(String basePath,String fileName)
;

public List<BackupDTO> listExportedData()
;

@NonNull
public List<BackupDTO> listMarkdowns()
;

@NonNull
public BackupDTO exportMarkdowns(PostMarkdownParam postMarkdownParam)
;

public void importData(MultipartFile file)
;

public void deleteExportedData(String fileName)
;

@NonNull
public BackupDTO exportData()
;

@NonNull
public Optional<BackupDTO> getBackup(Path backupFileName,BackupType type)
;

public BasePostDetailDTO importMarkdown(MultipartFile file)
;

@NonNull
public BackupDTO backupWorkDirectory()
;

public void deleteWorkDirBackup(String fileName)
;

public String getBaseUri(){
    return baseUri;
}
;

@NonNull
public List<BackupDTO> listWorkDirBackups()
;

}