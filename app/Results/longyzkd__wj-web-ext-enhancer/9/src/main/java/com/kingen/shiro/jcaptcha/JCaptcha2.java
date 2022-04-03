package com.kingen.shiro.jcaptcha;
 import javax.servlet.http.HttpServletRequest;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
public class JCaptcha2 {

 public  ImageCaptchaService captchaService;


public boolean validateResponse(HttpServletRequest request,String userCaptchaResponse){
    if (request.getSession(false) == null)
        return false;
    boolean validated = false;
    try {
        String id = request.getSession().getId();
        validated = captchaService.validateResponseForID(id, userCaptchaResponse).booleanValue();
    } catch (CaptchaServiceException e) {
        e.printStackTrace();
    }
    return validated;
}


}