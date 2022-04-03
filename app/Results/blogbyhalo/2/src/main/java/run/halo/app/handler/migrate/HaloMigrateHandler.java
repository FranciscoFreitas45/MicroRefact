package run.halo.app.handler.migrate;
 import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.model.enums.MigrateType;
import run.halo.app.service.BackupService;
@Component
public class HaloMigrateHandler implements MigrateHandler{

 private  BackupService backupService;

public HaloMigrateHandler(BackupService backupService) {
    this.backupService = backupService;
}
@Override
public boolean supportType(MigrateType type){
    return MigrateType.HALO.equals(type);
}


@Override
public void migrate(MultipartFile file){
    try {
        backupService.importData(file);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}