package org.gliderwiki.web.wiki.common.service;
 import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
@Service("apacheFileUtilService")
public class ApacheFileUtilService implements FileService{


@Override
public void copyFile(File oringinFile,File destFile){
    FileUtils.copyFile(oringinFile, destFile);
}


}