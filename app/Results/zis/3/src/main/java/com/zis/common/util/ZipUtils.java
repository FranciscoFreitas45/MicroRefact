package com.zis.common.util;
 import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class ZipUtils {

 public  String EXT;

 private  String BASE_DIR;

 private  String PATH;

 private  int BUFFER;


public void compressDir(File dir,ZipOutputStream zos,String basePath){
    File[] files = dir.listFiles();
    // 构建空目录
    if (files.length < 1) {
        ZipEntry entry = new ZipEntry(basePath + dir.getName() + PATH);
        zos.putNextEntry(entry);
        zos.closeEntry();
    }
    for (File file : files) {
        // 递归压缩
        compress(file, zos, basePath + dir.getName() + PATH);
    }
}


public void compress(String srcPath,String destPath){
    File srcFile = new File(srcPath);
    compress(srcFile, destPath);
}


public void compressFile(File file,ZipOutputStream zos,String dir){
    /**
     * 压缩包内文件名定义
     *
     * <pre>
     * 如果有多级目录，那么这里就需要给出包含目录的文件名
     * 如果用WinRAR打开压缩包，中文名将显示为乱码
     * </pre>
     */
    ZipEntry entry = new ZipEntry(dir + file.getName());
    zos.putNextEntry(entry);
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
    int count;
    byte[] data = new byte[BUFFER];
    while ((count = bis.read(data, 0, BUFFER)) != -1) {
        zos.write(data, 0, count);
    }
    bis.close();
    zos.closeEntry();
}


}