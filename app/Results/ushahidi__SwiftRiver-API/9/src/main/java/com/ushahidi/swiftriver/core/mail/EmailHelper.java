package com.ushahidi.swiftriver.core.mail;
 import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.apache.http.client.utils.URIBuilder;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;
import com.ushahidi.swiftriver.core.model.User;
import com.ushahidi.swiftriver.core.model.UserToken;
import com.ushahidi.swiftriver.core.DTO.User;
public class EmailHelper {

 private  JavaMailSender mailSender;

 private  VelocityEngine velocityEngine;

 private  String senderAddress;

 private  String resetPasswordUrl;

 private  String activateAccountUrl;


public String getEmailBody(EmailType emailType,Map<String,Object> templateParams,String name){
    Map<String, Object> body = new HashMap<String, Object>();
    try {
        URIBuilder uriBuilder = new URIBuilder(getBaseUrl(emailType));
        for (Map.Entry<String, Object> entry : templateParams.entrySet()) {
            uriBuilder.addParameter(entry.getKey(), (String) entry.getValue());
        }
        body.put("name", name);
        body.put("url", uriBuilder.toString());
    } catch (URISyntaxException e) {
        e.printStackTrace();
    }
    String templateLocation = EmailType.getTemplateLocation(emailType);
    return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation, "UTF-8", body);
}


public String getBaseUrl(EmailType emailType){
    switch(emailType) {
        case ACTIVATE_ACCOUNT:
            return activateAccountUrl;
        case RESET_PASSWORD:
            return resetPasswordUrl;
    }
    return null;
}


public void prepare(MimeMessage mimeMessage) throws Exception{
    MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, true);
    mimeHelper.setFrom(senderAddress);
    mimeHelper.setTo(user.getEmail());
    mimeHelper.setReplyTo(senderAddress);
    mimeHelper.setSubject(subject);
    mimeHelper.setText(mailBody, true);
}


public void setVelocityEngine(VelocityEngine velocityEngine){
    this.velocityEngine = velocityEngine;
}


public void setResetPasswordUrl(String resetPasswordUrl){
    this.resetPasswordUrl = resetPasswordUrl;
}


public void setMailSender(JavaMailSender mailSender){
    this.mailSender = mailSender;
}


public void setActivateAccountUrl(String activateAccountUrl){
    this.activateAccountUrl = activateAccountUrl;
}


public void sendAccountActivationEmail(User user,UserToken userToken){
    // Get the mail body
    Map<String, Object> templateParams = new HashMap<String, Object>();
    templateParams.put("email", user.getEmail());
    templateParams.put("token", userToken.getToken());
    final String mailBody = getEmailBody(EmailType.ACTIVATE_ACCOUNT, templateParams, user.getName());
    // Prepare the MIME message
    final String subject = "Welcome to SwiftRiver";
    MimeMessagePreparator preparator = getMimeMessagePreparator(user, subject, mailBody);
    // Send the account activation email
    mailSender.send(preparator);
}


public void sendPasswordResetEmail(User user,UserToken userToken){
    // Get the mail body with all the properties set
    Map<String, Object> templateParams = new HashMap<String, Object>();
    templateParams.put("email", user.getEmail());
    templateParams.put("token", userToken.getToken());
    String mailBody = getEmailBody(EmailType.RESET_PASSWORD, templateParams, user.getName());
    // Prepare the MIME message
    final String subject = "How to reset your SwiftRiver password";
    MimeMessagePreparator preparator = getMimeMessagePreparator(user, subject, mailBody);
    // Send the message
    mailSender.send(preparator);
}


public void setSenderAddress(String senderAddress){
    this.senderAddress = senderAddress;
}


public MimeMessagePreparator getMimeMessagePreparator(User user,String subject,String mailBody){
    MimeMessagePreparator preparator = new MimeMessagePreparator() {

        public void prepare(MimeMessage mimeMessage) throws Exception {
            MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, true);
            mimeHelper.setFrom(senderAddress);
            mimeHelper.setTo(user.getEmail());
            mimeHelper.setReplyTo(senderAddress);
            mimeHelper.setSubject(subject);
            mimeHelper.setText(mailBody, true);
        }
    };
    return preparator;
}


}