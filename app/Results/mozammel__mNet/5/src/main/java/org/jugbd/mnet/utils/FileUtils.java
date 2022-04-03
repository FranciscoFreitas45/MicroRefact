package org.jugbd.mnet.utils;
 import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;
public class FileUtils {

 public  int FILE_NAME_MAX_SIZE;

 private  Map<String,String> extensionContentTypeMap;


public boolean isValidFile(MultipartFile file,String[] fileTypeList){
    String fileName = file.getOriginalFilename();
    String extension = getExtensionInLowerCase(fileName);
    for (String validFileType : fileTypeList) {
        if (extension.equalsIgnoreCase(validFileType)) {
            return true;
        }
    }
    return false;
}


public String getExtensionFromContentType(String contentType){
    String key = "";
    for (Map.Entry<String, String> entry : extensionContentTypeMap.entrySet()) {
        if ((entry.getValue().equalsIgnoreCase(contentType))) {
            key = entry.getKey();
        }
    }
    return key;
}


public String getExtensionInLowerCase(String fileName){
    return FilenameUtils.getExtension(fileName).toLowerCase();
}


public String getContentType(String extension){
    return extensionContentTypeMap.get(extension.toLowerCase());
}


public String getFilteredFileName(String fileName){
    String extension = getExtensionInLowerCase(fileName);
    String baseName = FilenameUtils.getBaseName(fileName);
    baseName = baseName.replaceAll(" ", "-");
    return StringUtils.getTrimmedString(baseName, FILE_NAME_MAX_SIZE - extension.length()) + "." + extension;
}


}