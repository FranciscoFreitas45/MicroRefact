package net.shangtech.ssh.core.util;
 import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class FileUtils {

 private  String SAVE_DIR;

 public  List<String> ACCEPT_TYPES;


public String saveStreamToFile(InputStream is,String originalName){
    String extend = originalName.substring(originalName.lastIndexOf("."));
    String date = DateUtils.format(new java.util.Date(), "yyyyMMdd");
    String dateTime = DateUtils.format(new java.util.Date(), "yyyyMMddHHmmss");
    String datePath = date + "/";
    File dir = new File(SAVE_DIR + datePath);
    if (!dir.exists()) {
        dir.mkdirs();
    }
    Random random = new Random();
    String randomName = dateTime + random.nextInt(9) + random.nextInt(9) + random.nextInt(9);
    File target = new File(SAVE_DIR + datePath + randomName + extend);
    while (target.exists()) {
        randomName = dateTime + random.nextInt(9) + random.nextInt(9) + random.nextInt(9);
        target = new File(SAVE_DIR + datePath + randomName + extend);
    }
    OutputStream fout = new FileOutputStream(target);
    byte[] b = new byte[1024];
    int len = 0;
    while ((len = is.read(b)) != -1) {
        fout.write(b, 0, len);
    }
    fout.close();
    is.close();
    String filePath = "upload/" + datePath + randomName + extend;
    return filePath;
}


public String save(File file,String originalName){
    String date = DateUtils.format(new java.util.Date(), "yyyyMMdd");
    String dateTime = DateUtils.format(new java.util.Date(), "yyyyMMddHHmmss");
    String extend = originalName.substring(originalName.lastIndexOf("."));
    String datePath = date + "/";
    File dir = new File(SAVE_DIR + datePath);
    if (!dir.exists()) {
        dir.mkdirs();
    }
    Random random = new Random();
    String randomName = dateTime + random.nextInt(9) + random.nextInt(9) + random.nextInt(9);
    File target = new File(SAVE_DIR + datePath + randomName + extend);
    while (target.exists()) {
        randomName = dateTime + random.nextInt(9) + random.nextInt(9) + random.nextInt(9);
        target = new File(SAVE_DIR + datePath + randomName + extend);
    }
    InputStream is = new FileInputStream(file);
    OutputStream fout = new FileOutputStream(target);
    byte[] b = new byte[1024];
    int len = 0;
    while ((len = is.read(b)) != -1) {
        fout.write(b, 0, len);
    }
    fout.close();
    is.close();
    String filePath = "upload/" + datePath + randomName + extend;
    return filePath;
}


}