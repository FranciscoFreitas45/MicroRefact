package com.kingen.shiro.jcaptcha;
 import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;
public class CaptchaService {

 private  ImageCaptchaService instance;

// 不允许构造实例
private CaptchaService() {
}
public ImageCaptchaService getInstance(){
    return instance;
}


}