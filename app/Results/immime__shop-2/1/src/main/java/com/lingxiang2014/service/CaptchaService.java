package com.lingxiang2014.service;
 import java.awt.image.BufferedImage;
import com.lingxiang2014.Setting.CaptchaType;
public interface CaptchaService {


public BufferedImage buildImage(String captchaId)
;

public boolean isValid(CaptchaType captchaType,String captchaId,String captcha)
;

}