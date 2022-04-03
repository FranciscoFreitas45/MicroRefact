package de.metas.ui.web.upload;
 import java.io.IOException;
import java.time.format.DateTimeFormatter;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.MImage;
import org.compiere.util.Env;
import org.compiere.util.MimeType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.common.annotations.VisibleForTesting;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.util.FileUtils;
import de.metas.util.time.SystemTime;
import lombok.NonNull;
@Service
public class WebuiImageService {

 private  DateTimeFormatter DATE_FORMAT;


@VisibleForTesting
public String normalizeUploadFilename(String name,String contentType){
    final String fileExtension = MimeType.getExtensionByType(contentType);
    final String nameNormalized;
    if (Check.isEmpty(name, true) || // HARDCODED: this happens when the image is taken from webcam
    "blob".equals(name)) {
        nameNormalized = DATE_FORMAT.format(SystemTime.asZonedDateTime());
    } else {
        nameNormalized = name.trim();
    }
    return FileUtils.changeFileExtension(nameNormalized, fileExtension);
}


public WebuiImageId uploadImage(MultipartFile file){
    final String name = file.getOriginalFilename();
    final byte[] data = file.getBytes();
    final String contentType = file.getContentType();
    final String filenameNorm = normalizeUploadFilename(name, contentType);
    final MImage adImage = new MImage(Env.getCtx(), 0, ITrx.TRXNAME_None);
    adImage.setName(filenameNorm);
    adImage.setBinaryData(data);
    // TODO: introduce adImage.setTemporary(true);
    InterfaceWrapperHelper.save(adImage);
    return WebuiImageId.ofRepoId(adImage.getAD_Image_ID());
}


public WebuiImage getWebuiImage(WebuiImageId imageId,int maxWidth,int maxHeight){
    final MImage adImage = MImage.get(Env.getCtx(), imageId.getRepoId());
    if (adImage == null || adImage.getAD_Image_ID() <= 0) {
        throw new EntityNotFoundException("Image id not found: " + imageId);
    }
    return WebuiImage.of(adImage, maxWidth, maxHeight);
}


}