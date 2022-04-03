package sn.service;
 import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sn.api.response.AbstractResponse;
import sn.api.response.FileUploadResponse;
import sn.api.response.ServiceResponse;
import javax.mail.Multipart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Map;
import sn.api.response.AbstractResponse.createErrorResponse;
import sn.Interface.AccountService;
@Slf4j
@Service
public class StorageService {

@Autowired
 private  Cloudinary cloudinary;

@Autowired
 private  AccountService accountService;

@Value("${storage.root.location}")
 private  String uploadPath;


public ResponseEntity<ServiceResponse<AbstractResponse>> uploadFile(MultipartFile file,String type){
    if (!Strings.isNotEmpty(type) || !type.equals("IMAGE")) {
        log.error("type [{}] is incorrect", type);
        return createErrorResponse("incorrect type", "file type must be \"image\" or \"IMAGE\"");
    }
    try {
        File uploadedFile = convertMultiPartToFile(file);
        Map uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
        FileUploadResponse response = FileUploadResponse.builder().id((String) uploadResult.get("secure_url")).ownerId(accountService.findCurrentUser().getId()).fileName((String) uploadResult.get("original_filename")).relativeFilePath((String) uploadResult.get("secure_url")).rawFileURL((String) uploadResult.get("secure_url")).fileFormat((String) uploadResult.get("format")).bytes((Integer) uploadResult.get("bytes")).fileType(type).createdAt(Instant.parse(((String) uploadResult.get("created_at"))).getEpochSecond()).build();
        FileSystemUtils.deleteRecursively(Paths.get(uploadPath));
        return ResponseEntity.status(HttpStatus.OK).body(new ServiceResponse<>(response));
    } catch (IOException | NullPointerException exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        ServiceResponse<AbstractResponse> serviceResponse = new ServiceResponse<>();
        serviceResponse.setError("Catch some error when upload.");
        return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
    }
}


public File convertMultiPartToFile(MultipartFile file){
    Path rootLocation = Paths.get(uploadPath);
    File uploadDir = new File(rootLocation.toUri());
    if (!uploadDir.exists()) {
        log.warn("create temp upload file directory");
        uploadDir.mkdirs();
    }
    File convFile = rootLocation.resolve(StringUtils.cleanPath(file.getOriginalFilename())).toFile();
    FileOutputStream fileOutputStream = new FileOutputStream(convFile);
    fileOutputStream.write(file.getBytes());
    fileOutputStream.close();
    return convFile;
}


}