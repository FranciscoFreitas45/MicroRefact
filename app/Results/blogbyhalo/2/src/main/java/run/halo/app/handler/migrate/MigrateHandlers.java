package run.halo.app.handler.migrate;
 import java.util.Collection;
import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.exception.FileOperationException;
import run.halo.app.model.enums.MigrateType;
@Slf4j
@Component
public class MigrateHandlers {

 private  Collection<MigrateHandler> migrateHandlers;

public MigrateHandlers(ApplicationContext applicationContext) {
    // Add all migrate handler
    addMigrateHandlers(applicationContext.getBeansOfType(MigrateHandler.class).values());
}
@NonNull
public void upload(MultipartFile file,MigrateType migrateType){
    Assert.notNull(file, "Multipart file must not be null");
    Assert.notNull(migrateType, "Migrate type must not be null");
    for (MigrateHandler migrateHandler : migrateHandlers) {
        if (migrateHandler.supportType(migrateType)) {
            migrateHandler.migrate(file);
            return;
        }
    }
    throw new FileOperationException("No available migrate handler to migrate the file").setErrorData(migrateType);
}


@NonNull
public MigrateHandlers addMigrateHandlers(Collection<MigrateHandler> migrateHandlers){
    if (!CollectionUtils.isEmpty(migrateHandlers)) {
        this.migrateHandlers.addAll(migrateHandlers);
    }
    return this;
}


}