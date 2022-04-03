package br.com.fatecmogidascruzes.Interface;
public interface EmailService {

   public void sendRecoveryPasswordEMail(String to,String hash);
}