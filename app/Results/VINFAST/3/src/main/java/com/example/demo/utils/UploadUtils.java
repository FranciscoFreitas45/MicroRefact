package com.example.demo.utils;
 import java.io.File;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class UploadUtils {

@Autowired
 private ServletContext app;


public void uploadProduct(String fileName,MultipartFile multipartFile){
    try {
        if (!multipartFile.isEmpty()) {
            File file = new File(app.getRealPath("/images/" + fileName));
            multipartFile.transferTo(file);
            System.out.println(app.getRealPath("/images/" + fileName));
        }
    } catch (Exception e) {
    }
}


public void uploadUser(String fileName,MultipartFile multipartFile){
    try {
        if (!multipartFile.isEmpty()) {
            File file = new File(app.getRealPath("/avatar/" + fileName));
            multipartFile.transferTo(file);
            System.out.println(app.getRealPath("/avatar/" + fileName));
        }
    } catch (Exception e) {
    }
}


}