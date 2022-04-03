package com.uec.imonitor.common.log;
 import java.io.IOException;
import java.text.MessageFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.uec.imonitor.common.util.CommonUtil;
public class ErrorCodeManager {

 private  Log log;


public String getText(String i18n,String errorCode,Object[] param){
    StringBuffer errorFileNameSb = new StringBuffer("errorcode");
    if (StringUtils.isNoneBlank(i18n)) {
        errorFileNameSb.append("_");
        errorFileNameSb.append(i18n);
    }
    errorFileNameSb.append(".properties");
    String msg = null;
    try {
        msg = CommonUtil.getPropertiesValue(errorFileNameSb.toString(), errorCode);
    } catch (IOException e) {
        log.error(errorFileNameSb.toString() + " file does not exist");
        log.error(e);
    }
    if (StringUtils.isNoneBlank(msg)) {
        msg = MessageFormat.format(msg, param);
    }
    return msg;
}


}