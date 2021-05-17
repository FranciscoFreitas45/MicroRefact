import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pt.iscte.hospital.exceptions.ImageSizeException;
import pt.iscte.hospital.exceptions.ImageTypeException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
@Service
public class ImageUploadServiceImpl implements pt.iscte.hospital.services.ImageUploadService,ImageUploadService{

 private  String dirSavePath;

 private  long IMAGE_MAX_SIZE;

 private  String[] IMAGE_TYPES;


public void verifyImage(MultipartFile imageFile){
    String contentType = imageFile.getContentType();
    boolean isImage = false;
    // Check if size too big
    if (imageFile.getSize() > IMAGE_MAX_SIZE) {
        throw new ImageSizeException();
    }
    // Check if image jpg or png
    for (String imageType : IMAGE_TYPES) {
        if (contentType.equals(imageType)) {
            isImage = true;
            break;
        }
    }
    if (!isImage) {
        throw new ImageTypeException();
    }
}


@Override
public String getFileExtension(String imageType){
    HashMap<String, String> imageExtensions = new HashMap<>();
    imageExtensions.put("image/jpeg", ".jpg");
    imageExtensions.put("image/png", ".png");
    return imageExtensions.get(imageType);
}


@Override
public long getImageMaxSize(){
    return IMAGE_MAX_SIZE / 1000000;
}


@Override
public String uploadImage(MultipartFile imageFile,String username){
    // Check if size too big & Check if image jpg or png
    // Throws an error if not a valid image
    verifyImage(imageFile);
    // Sets save path and file extension
    String contentType = imageFile.getContentType();
    // root directory of the project
    String rootDir = System.getProperty("user.dir");
    // save directory path for images
    String savePath = rootDir + "/" + dirSavePath;
    String fileExtension = getFileExtension(contentType);
    String fileName = username + fileExtension;
    // Save image
    imageFile.transferTo(new File(savePath + fileName));
    return fileName;
}


}