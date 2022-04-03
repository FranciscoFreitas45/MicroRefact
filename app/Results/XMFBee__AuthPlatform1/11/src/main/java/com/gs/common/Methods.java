package com.gs.common;
 import org.springframework.web.context.ContextLoader;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Methods {

 public  String DEFAULT_CODING;

 public  String PATH_UPLOAD;


public int division(double a){
    DecimalFormat df = new DecimalFormat("#");
    return Integer.valueOf(df.format(a));
}


public String createNewFolder(){
    return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
}


public String uploadPath(String type){
    String folder = createNewFolder();
    String rootPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
    String path = rootPath;
    if (type.equals("img")) {
        path += Constants.UPLOAD_IMAGES + folder;
    } else if (type.equals("video")) {
        path += Constants.UPLOAD_VIDEO + folder;
    } else if (type.equals("logo")) {
        path += Constants.UPLOAD_LOGO + folder;
    } else if (type.equals("head")) {
        path += Constants.UPLOAD_HEAD + folder;
    }
    path += "/";
    File file = new File(path);
    if (file.mkdirs()) {
        file.mkdirs();
    }
    return path;
}


public String getRootPath(HttpServletRequest request){
    return request.getServletContext().getRealPath("/");
}


public String createName(String fileName){
    return String.valueOf(System.currentTimeMillis());
}


}