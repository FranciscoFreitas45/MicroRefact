package com.ec.survey.DTO;
 import com.ec.survey.service.AnswerService;
import com.ec.survey.service.FileService;
import com.ec.survey.service.MailService;
import com.ec.survey.service.SurveyService;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.InputStream;
import java.util.Date;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.AnswerService;
import com.ec.survey.Interface.FileService;
public class CleanupWorker implements Runnable{

 protected  Logger logger;

 protected  SurveyService surveyService;

 protected  AnswerService answerService;

 protected  FileService fileService;

 protected  MailService mailService;

 protected  SessionFactory sessionFactory;

 public  ServletContext servletContext;

 private  String[] options;

 private  Date pdfbefore;

 private  Date tempbefore;

 private  String email;

 private  String smtpServer;

 private  String smtpPort;

 public  String sender;

 public  String host;

 private  String contextpath;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public void init(String[] options,Date pdfbefore,Date tempbefore,String email){
    this.options = options;
    this.pdfbefore = pdfbefore;
    this.tempbefore = tempbefore;
    this.email = email;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/init"))

.queryParam("options",options)
.queryParam("pdfbefore",pdfbefore)
.queryParam("tempbefore",tempbefore)
.queryParam("email",email)
;
restTemplate.put(builder.toUriString(),null);
}


}