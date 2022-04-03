package run.halo.app.handler.file;
 import run.halo.app.model.support.HaloConst.FILE_SEPARATOR;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;
import javax.imageio.ImageReader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.exception.FileOperationException;
import run.halo.app.model.enums.AttachmentType;
import run.halo.app.model.support.UploadResult;
import run.halo.app.utils.ImageUtils;
public interface FileHandler {

 private MediaType IMAGE_TYPE;


@NonNull
public UploadResult upload(MultipartFile file)
;

public void handleImageMetadata(MultipartFile file,UploadResult uploadResult,Supplier<String> thumbnailSupplier){
    if (isImageType(file)) {
        // Handle image
        try (InputStream is = file.getInputStream()) {
            ImageReader image = ImageUtils.getImageReaderFromFile(is, uploadResult.getSuffix());
            uploadResult.setWidth(image.getWidth(0));
            uploadResult.setHeight(image.getHeight(0));
            if (thumbnailSupplier != null) {
                uploadResult.setThumbPath(thumbnailSupplier.get());
            }
        } catch (IOException | OutOfMemoryError e) {
            // ignore IOException and OOM
            LoggerFactory.getLogger(getClass()).warn("Failed to fetch image meta data", e);
        }
    }
    if (StringUtils.isBlank(uploadResult.getThumbPath())) {
        uploadResult.setThumbPath(uploadResult.getFilePath());
    }
}
;

public AttachmentType getAttachmentType()
;

@NonNull
public String normalizeDirectory(String dir){
    Assert.hasText(dir, "Directory full name must not be blank");
    return StringUtils.appendIfMissing(dir, FILE_SEPARATOR);
}
;

public void delete(String key)
;

public boolean isImageType(MultipartFile file){
    String mediaType = file.getContentType();
    return mediaType != null && IMAGE_TYPE.includes(MediaType.valueOf(mediaType));
}
;

}