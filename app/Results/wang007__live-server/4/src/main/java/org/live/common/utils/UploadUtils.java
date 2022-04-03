package org.live.common.utils;
 import java.io.File;
import java.io.IOException;
public class UploadUtils {


public void main(String[] args) throws Exception{
    createFile("c:\\projectDir", "\\upload\\20170403\\haoaoa.txt");
}


public File createFile(String path) throws IOException{
    File file = new File(path);
    File parentFile = file.getParentFile();
    if (parentFile != null && !parentFile.exists()) {
        parentFile.mkdirs();
    }
    if (!file.exists()) {
        file.createNewFile();
    }
    return file;
}


public String getFileSuffix(String fileName){
    // 获取点的位置
    int dotIndex = fileName.lastIndexOf(".");
    return dotIndex == -1 ? "" : fileName.substring(dotIndex);
}


}