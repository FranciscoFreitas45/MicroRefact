package br.com.fatecmogidascruzes.Interface;
public interface ReCaptchaService {

   public String verifyRecaptcha(String ip,String recaptchaResponse);
}