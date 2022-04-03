package com.gbcom.system.utils;
 import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
public class ParFileUtils {

 private  Log log;


public String getXmlFromParFile(File parFile){
    String baseName = FilenameUtils.getBaseName(parFile.getName());
    return getContentFromPar(parFile, baseName + ".xml");
}


public ZipEntry[] readEntriesFromZipfile(File parFile){
    org.apache.tools.zip.ZipFile zipFile = new org.apache.tools.zip.ZipFile(parFile);
    java.util.Enumeration e = zipFile.getEntries();
    org.apache.tools.zip.ZipEntry zipEntry = null;
    List<org.apache.tools.zip.ZipEntry> zipEntries = new ArrayList<org.apache.tools.zip.ZipEntry>();
    while (e.hasMoreElements()) {
        zipEntry = (org.apache.tools.zip.ZipEntry) e.nextElement();
        zipEntries.add(zipEntry);
    }
    return zipEntries.toArray(new ZipEntry[zipEntries.size()]);
}


public String getContentFromPar(File parFile,String fileName,String encoding){
    byte[] bytes = readFileContentFromZipfile(parFile, fileName);
    return new String(bytes, encoding);
}


public String getHtmFromParFile(File parFile){
    String baseName = FilenameUtils.getBaseName(parFile.getName());
    return getContentFromPar(parFile, baseName + ".htm");
}


public byte[] readFileContentFromZipfile(File parFile,String fileName){
    org.apache.tools.zip.ZipFile zipFile = new org.apache.tools.zip.ZipFile(parFile);
    try {
        java.util.Enumeration e = zipFile.getEntries();
        org.apache.tools.zip.ZipEntry zipEntry = null;
        while (e.hasMoreElements()) {
            zipEntry = (org.apache.tools.zip.ZipEntry) e.nextElement();
            if (!zipEntry.isDirectory()) {
                if (zipEntry.getName().equals(fileName)) {
                    InputStream in = zipFile.getInputStream(zipEntry);
                    return IOUtils.toByteArray(in);
                }
            }
        }
    } finally {
        zipFile.close();
    }
    return null;
}


public byte[] readFileContentFromByteArray(byte[] bytes,String fileName){
    File tempFile = File.createTempFile("parFile", ".tmp");
    log.debug("temp file name is " + tempFile.getPath());
    FileUtils.writeByteArrayToFile(tempFile, bytes);
    try {
        byte[] ret = readFileContentFromZipfile(tempFile, fileName);
        tempFile.delete();
        return ret;
    } finally {
        tempFile.deleteOnExit();
    }
}


public ZipEntry[] readEntriesFromByteArray(byte[] bytes){
    File tempFile = File.createTempFile("parFile", ".tmp");
    log.debug("temp file name is " + tempFile.getPath());
    FileUtils.writeByteArrayToFile(tempFile, bytes);
    try {
        ZipEntry[] zipEntries = readEntriesFromZipfile(tempFile);
        tempFile.delete();
        return zipEntries;
    } finally {
        tempFile.deleteOnExit();
    }
}


}