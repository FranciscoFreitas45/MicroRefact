package cn.maxcj.core.util;
 import cn.maxcj.config.properties.GunsProperties;
import cn.stylefeng.roses.core.util.SpringContextHolder;
public class KaptchaUtil {


public Boolean getKaptchaOnOff(){
    return SpringContextHolder.getBean(GunsProperties.class).getKaptchaOpen();
}


}