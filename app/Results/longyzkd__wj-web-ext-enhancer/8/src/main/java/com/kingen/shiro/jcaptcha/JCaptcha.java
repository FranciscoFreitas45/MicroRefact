package com.kingen.shiro.jcaptcha;
 import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import javax.servlet.http.HttpServletRequest;
public class JCaptcha {

 public  MyManageableImageCaptchaService captchaService;


public boolean hasCaptcha(HttpServletRequest request,String userCaptchaResponse){
    if (request.getSession(false) == null)
        return false;
    boolean validated = false;
    try {
        String id = request.getSession().getId();
        validated = captchaService.hasCapcha(id, userCaptchaResponse);
    } catch (CaptchaServiceException e) {
        e.printStackTrace();
    }
    return validated;
}


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