package com.lingxiang2014.service.impl;
 import java.awt.image.BufferedImage;
import javax.annotation.Resource;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.lingxiang2014.Setting;
import com.lingxiang2014.Setting.CaptchaType;
import com.lingxiang2014.service.CaptchaService;
import com.lingxiang2014.util.SettingUtils;
@Service("captchaServiceImpl")
public class CaptchaServiceImpl implements CaptchaService{

@Resource(name = "imageCaptchaService")
 private  com.octo.captcha.service.CaptchaService imageCaptchaService;


public BufferedImage buildImage(String captchaId){
    return (BufferedImage) imageCaptchaService.getChallengeForID(captchaId);
}


public boolean isValid(CaptchaType captchaType,String captchaId,String captcha){
    Setting setting = SettingUtils.get();
    if (captchaType == null || ArrayUtils.contains(setting.getCaptchaTypes(), captchaType)) {
        if (StringUtils.isNotEmpty(captchaId) && StringUtils.isNotEmpty(captcha)) {
            try {
                return imageCaptchaService.validateResponseForID(captchaId, captcha.toUpperCase());
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    } else {
        return true;
    }
}


}