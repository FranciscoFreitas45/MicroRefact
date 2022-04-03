package cn.maxcj.DTO;
 import cn.stylefeng.roses.core.util.ToolUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.io.File;
import cn.stylefeng.roses.core.util.ToolUtil.getTempPath;
public class GunsProperties {

 public  String PREFIX;

 private  Boolean kaptchaOpen;

 private  Boolean swaggerOpen;

 private  String fileUploadPath;

 private  Boolean haveCreatePath;

 private  Boolean springSessionOpen;

 private  Integer sessionInvalidateTime;

 private  Integer sessionValidationInterval;


public Boolean getKaptchaOpen(){
    return kaptchaOpen;
}


public Boolean getSpringSessionOpen(){
    return springSessionOpen;
}


public Integer getSessionInvalidateTime(){
    return sessionInvalidateTime;
}


public String getFileUploadPath(){
    // 如果没有写文件上传路径,保存到临时目录
    if (ToolUtil.isEmpty(fileUploadPath)) {
        return getTempPath();
    } else {
        // 判断有没有结尾符,没有得加上
        if (!fileUploadPath.endsWith(File.separator)) {
            fileUploadPath = fileUploadPath + File.separator;
        }
        // 判断目录存不存在,不存在得加上
        if (!haveCreatePath) {
            File file = new File(fileUploadPath);
            file.mkdirs();
            haveCreatePath = true;
        }
        return fileUploadPath;
    }
}


public Boolean getSwaggerOpen(){
    return swaggerOpen;
}


public Integer getSessionValidationInterval(){
    return sessionValidationInterval;
}


}