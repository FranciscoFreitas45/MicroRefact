package run.halo.app.handler.migrate;
 import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.model.enums.MigrateType;
public interface MigrateHandler {


public boolean supportType(MigrateType type)
;

public void migrate(MultipartFile file)
;

}