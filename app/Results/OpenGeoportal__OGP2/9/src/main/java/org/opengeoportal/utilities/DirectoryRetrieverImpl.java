package org.opengeoportal.utilities;
 import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DirectoryRetrieverImpl implements DirectoryRetriever{

 private  String downloadDirectory;

 protected  Logger logger;

DirectoryRetrieverImpl(String downloadDirectory) {
    this.downloadDirectory = downloadDirectory;
}
public File getDirectory(String directoryName){
    // check permissions
    File tempFile = File.createTempFile("tmp", "tmp");
    File tempDir = tempFile.getParentFile();
    tempFile.delete();
    File theDirectory = new File(tempDir, directoryName);
    if (!theDirectory.exists()) {
        theDirectory.mkdir();
    }
    if (!theDirectory.canRead() || !theDirectory.canWrite()) {
        throw new IOException("Download directory is inaccessible.");
    } else {
        return theDirectory;
    }
}


public File getDownloadDirectory(){
    File theDirectory = null;
    try {
        theDirectory = getDirectory(downloadDirectory);
    } catch (IOException e) {
        logger.error("Unable to retrieve directory ['" + downloadDirectory + "'] in the temp directory.");
    }
    return theDirectory;
}


}