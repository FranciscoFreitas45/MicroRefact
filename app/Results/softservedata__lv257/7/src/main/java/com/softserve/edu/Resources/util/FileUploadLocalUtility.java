package com.softserve.edu.Resources.util;
 import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
public class FileUploadLocalUtility extends FileUpload{

 private  String ABS_PATH;


public String uploadFile(MultipartFile file,String code,String username){
    // to make sure all the directory exists.
    if (!new File(ABS_PATH).exists()) {
        // create the directories
        new File(ABS_PATH).mkdirs();
    }
    try {
        if (file.getContentType().equals("application/pdf")) {
            file.transferTo(new File(ABS_PATH + code + ".pdf"));
        } else if (file.getContentType().equals("image/jpeg")) {
            file.transferTo(new File(ABS_PATH + code + ".jpg"));
        } else {
            file.transferTo(new File(ABS_PATH + code + ".png"));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return ABS_PATH;
}


}