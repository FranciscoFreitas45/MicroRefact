package br.com.fatecmogidascruzes.email;
 import java.io.IOException;
import javax.mail.MessagingException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
public interface EmailService {


public void sendRecoveryPasswordEMail(String to,String hash) throws SAXException
;

public void sendContactEmail(String from,String phone,String name,String message) throws SAXException
;

public void sendRequestEMail(String email,String name,String protocol,String requestSituation,String documentType) throws SAXException
;

}