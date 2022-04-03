package com.easyshopping.Interface;
public interface CaptchaService {

   public boolean isValid(CaptchaType captchaType,String captchaId,String captcha);
}