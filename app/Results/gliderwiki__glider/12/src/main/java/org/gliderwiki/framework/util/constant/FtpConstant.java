package org.gliderwiki.framework.util.constant;
 public class FtpConstant {

 public  String FTP_TEMP_PATH;

 public  String FTP_TARGET_PATH;

 public  String FTP_THUMB_PATH;

 public  String[] PERMITTED_EXTS;


public boolean isPermittedExt(String fileName){
    boolean permittedExt = false;
    int dotIndex = fileName.lastIndexOf('.');
    String ext = fileName.substring(dotIndex + 1);
    for (int i = 0; i < PERMITTED_EXTS.length; i++) {
        if (ext.equalsIgnoreCase(PERMITTED_EXTS[i])) {
            permittedExt = true;
            break;
        }
    }
    return permittedExt;
}


}