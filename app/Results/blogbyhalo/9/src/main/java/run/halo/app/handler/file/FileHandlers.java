package run.halo.app.handler.file;
 import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.exception.FileOperationException;
import run.halo.app.exception.RepeatTypeException;
import run.halo.app.model.entity.Attachment;
import run.halo.app.model.enums.AttachmentType;
import run.halo.app.model.support.UploadResult;
@Slf4j
@Component
public class FileHandlers {

 private  ConcurrentHashMap<AttachmentType,FileHandler> fileHandlers;

public FileHandlers(ApplicationContext applicationContext) {
    // Add all file handler
    addFileHandlers(applicationContext.getBeansOfType(FileHandler.class).values());
    log.info("Registered {} file handler(s)", fileHandlers.size());
}
public FileHandler getSupportedType(AttachmentType type){
    FileHandler handler = fileHandlers.getOrDefault(type, fileHandlers.get(AttachmentType.LOCAL));
    if (handler == null) {
        throw new FileOperationException("No available file handlers to operate the file").setErrorData(type);
    }
    return handler;
}


@NonNull
public UploadResult upload(MultipartFile file,AttachmentType attachmentType){
    return getSupportedType(attachmentType).upload(file);
}


@NonNull
public FileHandlers addFileHandlers(Collection<FileHandler> fileHandlers){
    if (!CollectionUtils.isEmpty(fileHandlers)) {
        for (FileHandler handler : fileHandlers) {
            if (this.fileHandlers.containsKey(handler.getAttachmentType())) {
                throw new RepeatTypeException("Same attachment type implements must be unique");
            }
            this.fileHandlers.put(handler.getAttachmentType(), handler);
        }
    }
    return this;
}


public void delete(Attachment attachment){
    Assert.notNull(attachment, "Attachment must not be null");
    getSupportedType(attachment.getType()).delete(attachment.getFileKey());
}


}