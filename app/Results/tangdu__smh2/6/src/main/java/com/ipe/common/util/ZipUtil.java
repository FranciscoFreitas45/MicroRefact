package com.ipe.common.util;
 import java.io;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class ZipUtil {


public void zipFiles(String zipfile,OutputStream out){
    File ff = new File(zipfile);
    ZipOutputStream zipOut = new ZipOutputStream(out);
    if (ff.isDirectory()) {
        for (File file : ff.listFiles()) {
            try {
                FileInputStream in = new FileInputStream(file);
                ZipEntry entry = new ZipEntry(file.getName());
                zipOut.putNextEntry(entry);
                int nNumber = 0;
                byte[] buffer = new byte[512];
                while ((nNumber = in.read(buffer)) != -1) {
                    zipOut.write(buffer, 0, nNumber);
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    zipOut.close();
    out.close();
}


}