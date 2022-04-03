package com.tech.controllers.methodcontainer;
 import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
public class FileWorkAround {


public boolean fileWorkAroundCall(String filePath,byte[] b,StandardOpenOption openOption){
    File newFile = new File(filePath);
    if (!newFile.getParentFile().exists()) {
        // need check if creation fails?
        newFile.getParentFile().mkdirs();
    }
    // if file exists?
    Files.write(newFile.toPath(), b, openOption);
    return true;
}


}