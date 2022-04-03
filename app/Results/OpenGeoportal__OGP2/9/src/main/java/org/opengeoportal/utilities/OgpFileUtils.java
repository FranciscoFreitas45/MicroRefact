package org.opengeoportal.utilities;
 import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class OgpFileUtils {

 private  int TEMP_DIR_ATTEMPTS;

 final  Logger logger;


public File createNewFileFromDownload(String fileName,String mimeType,File directory){
    String fileExtension = "";
    String[] fileNameArr = fileName.split("\\.");
    if (fileNameArr.length > 1) {
        String temp = fileNameArr[fileNameArr.length - 1];
        if (temp.length() == 3) {
            // assume this is a file extension
            fileExtension = "." + temp;
            fileName = fileName.substring(0, fileName.indexOf(fileExtension));
        }
    }
    logger.debug(fileName);
    if (fileExtension.isEmpty()) {
        // try to get it from the mime type
        fileExtension = getFileExtensionFromMimeType(mimeType);
    }
    fileName = OgpFileUtils.filterName(fileName);
    directory.mkdirs();
    directory.mkdir();
    File newFile = new File(directory, fileName + fileExtension);
    int i = 1;
    while (newFile.exists()) {
        newFile = new File(directory, fileName + "_" + i + fileExtension);
        i++;
    }
    newFile.createNewFile();
    logger.debug("New file path: " + newFile.getAbsolutePath());
    return newFile;
}


public String getFileExtensionFromMimeType(String contentType){
    String responseContentType = contentType.toLowerCase();
    logger.info("response MIME-Type: " + responseContentType);
    // get info from RequestedLayer object
    if (responseContentType.indexOf(";") > -1) {
        responseContentType = responseContentType.substring(0, responseContentType.indexOf(";"));
    }
    String fileExtension;
    if ((responseContentType.contains("text/xml") || responseContentType.contains("application/xml"))) {
        fileExtension = ".xml";
    } else if ((responseContentType.contains("text/html") || responseContentType.contains("application/html"))) {
        fileExtension = ".html";
    } else if (responseContentType.contains("application/zip")) {
        fileExtension = ".zip";
    } else if (responseContentType.contains("tiff") || responseContentType.contains("geotiff")) {
        fileExtension = ".tif";
    } else if (responseContentType.contains("image/jpeg")) {
        fileExtension = ".jpg";
    } else if (responseContentType.contains("application/vnd.google-earth.kmz")) {
        fileExtension = ".kmz";
    } else if (responseContentType.contains("application/vnd.ogc.se_xml")) {
        fileExtension = "_error.xml";
    } else {
        fileExtension = ".unk";
    }
    return fileExtension;
}


public String filterName(String layerName){
    layerName = layerName.trim();
    // remove the workspace name prefix if it exists
    if (layerName.contains(":")) {
        layerName = layerName.substring(layerName.indexOf(":") + 1);
    }
    // replace periods with underscores
    layerName = layerName.replace(".", "_");
    return layerName;
}


public File createTempDir(){
    File baseDir = new File(System.getProperty("java.io.tmpdir"));
    String baseName = System.currentTimeMillis() + "-";
    for (int counter = 0; counter < TEMP_DIR_ATTEMPTS; counter++) {
        File tempDir = new File(baseDir, baseName + counter);
        if (tempDir.mkdir()) {
            return tempDir;
        }
    }
    throw new IllegalStateException("Failed to create directory within " + TEMP_DIR_ATTEMPTS + " attempts (tried " + baseName + "0 to " + baseName + (TEMP_DIR_ATTEMPTS - 1) + ')');
}


}