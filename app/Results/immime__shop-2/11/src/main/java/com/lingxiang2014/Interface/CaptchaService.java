package com.lingxiang2014.Interface;
public interface CaptchaService {

   public boolean isValid(CaptchaType captchaType,String captchaId,String captcha);
}