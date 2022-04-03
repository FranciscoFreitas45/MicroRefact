package br.com.fatecmogidascruzes.Interface;
public interface EmailService {

   public void sendRequestEMail(String email,String name,String protocol,String requestSituation,String documentType);
}