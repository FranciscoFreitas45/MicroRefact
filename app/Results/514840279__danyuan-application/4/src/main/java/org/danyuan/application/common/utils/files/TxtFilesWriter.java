package org.danyuan.application.common.utils.files;
 import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class TxtFilesWriter {


public void writeToFile(String data,String filepath){
    try {
        String path = filepath.substring(0, filepath.lastIndexOf("\\") > 0 ? filepath.lastIndexOf("\\") : filepath.lastIndexOf("/") > 0 ? filepath.lastIndexOf("/") : filepath.length());
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(filepath);
        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }
        // true = append file
        FileWriter fileWritter = new FileWriter(filepath);
        fileWritter.write(data + "\r\n");
        fileWritter.flush();
        fileWritter.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


public void appendWriteToFile(String data,String filepath){
    try {
        String path = filepath.substring(0, filepath.lastIndexOf("\\") > 0 ? filepath.lastIndexOf("\\") : filepath.lastIndexOf("/") > 0 ? filepath.lastIndexOf("/") : filepath.length());
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(filepath);
        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }
        // true = append file
        FileWriter fileWritter = new FileWriter(filepath, true);
        fileWritter.write(data + "\r\n");
        fileWritter.flush();
        fileWritter.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}