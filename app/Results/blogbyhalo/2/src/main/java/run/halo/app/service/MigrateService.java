package run.halo.app.service;
 import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.model.enums.MigrateType;
public interface MigrateService {


public void migrate(MultipartFile file,MigrateType migrateType)
;

}