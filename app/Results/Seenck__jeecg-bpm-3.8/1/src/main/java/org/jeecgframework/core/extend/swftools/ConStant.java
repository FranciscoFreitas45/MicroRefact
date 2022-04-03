package org.jeecgframework.core.extend.swftools;
 import javax.servlet.http.HttpServletRequest;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
public class ConStant {

 public  String OFFICE_HOME;

 public  String UPLOAD_BASE_DIR;

 public  String SWFTOOLS_BASE_DIR;

 public  String SWFTOOLS_PDF2SWF_PATH;

 public  String SWFTOOLS_PDF2SWF;

 public  String SWFTOOLS_GIF2SWF_PATH;

 public  String SWFTOOLS_PNG2SWF_PATH;

 public  String SWFTOOLS_JPEG2SWF_PATH;

 public  String SWFTOOLS_WAV2SWF_PATH;

 public  String SWFTOOLS_PDFCOMBINE_PATH;

 public  String SWF_STUFFIX;

 public  String SWFTOOLS_HOME;


public String getSWFToolsForLinux(String extend){
    SWFTOOLS_HOME = "";
    if (extend.equals("pdf")) {
        SWFTOOLS_HOME += SWFTOOLS_PDF2SWF;
    }
    if (extend.equals("gif")) {
        SWFTOOLS_HOME += SWFTOOLS_GIF2SWF_PATH;
    }
    if (extend.equals("png")) {
        SWFTOOLS_HOME += SWFTOOLS_PNG2SWF_PATH;
    }
    if (extend.equals("jpeg")) {
        SWFTOOLS_HOME += SWFTOOLS_JPEG2SWF_PATH;
    }
    if (extend.equals("wav")) {
        SWFTOOLS_HOME += SWFTOOLS_WAV2SWF_PATH;
    }
    return SWFTOOLS_HOME;
}


public String getSWFToolsPath(String extend){
    HttpServletRequest request = ContextHolderUtils.getRequest();
    SWFTOOLS_HOME = request.getSession().getServletContext().getRealPath("/") + SWFTOOLS_BASE_DIR + "/";
    if (extend.equals("pdf")) {
        SWFTOOLS_HOME += SWFTOOLS_PDF2SWF_PATH;
    }
    if (extend.equals("gif")) {
        SWFTOOLS_HOME += SWFTOOLS_GIF2SWF_PATH;
    }
    if (extend.equals("png")) {
        SWFTOOLS_HOME += SWFTOOLS_PNG2SWF_PATH;
    }
    if (extend.equals("jpeg")) {
        SWFTOOLS_HOME += SWFTOOLS_JPEG2SWF_PATH;
    }
    if (extend.equals("wav")) {
        SWFTOOLS_HOME += SWFTOOLS_WAV2SWF_PATH;
    }
    return SWFTOOLS_HOME;
}


}