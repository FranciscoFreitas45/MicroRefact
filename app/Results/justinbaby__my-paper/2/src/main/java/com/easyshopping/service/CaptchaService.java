package com.easyshopping.service;
 import java.awt.image.BufferedImage;
import com.easyshopping.Setting.CaptchaType;
public interface CaptchaService {


public BufferedImage buildImage(String captchaId)
;

public boolean isValid(CaptchaType captchaType,String captchaId,String captcha)
;

}