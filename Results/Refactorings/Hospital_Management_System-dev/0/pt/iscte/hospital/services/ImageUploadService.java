import org.springframework.web.multipart.MultipartFile;
import pt.iscte.hospital.exceptions.ImageSizeException;
import pt.iscte.hospital.exceptions.ImageTypeException;
import java.io.IOException;
public interface ImageUploadService {


public String getFileExtension(String imageType)


public long getImageMaxSize()


public String uploadImage(MultipartFile imageFile,String username)


}