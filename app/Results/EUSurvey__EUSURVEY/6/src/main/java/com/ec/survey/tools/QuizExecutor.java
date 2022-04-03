package com.ec.survey.tools;
 import java.io.InputStream;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.ec.survey.model.AnswerSet;
import com.ec.survey.service.MailService;
import com.ec.survey.service.PDFService;
import com.ec.survey.Interface.MailService;
import com.ec.survey.Interface.AnswerSet;
@Service("quizExecutor")
@Scope("prototype")
public class QuizExecutor implements Runnable{

@Resource(name = "pdfService")
 private  PDFService pdfService;

@Resource(name = "mailService")
 private  MailService mailService;

@Autowired
 public  ServletContext servletContext;

 private  AnswerSet answerSet;

 private  String email;

 private  String from;

 private  String host;

 private  Logger logger;


public void init(AnswerSet answerSet){
    this.answerSet = answerSet;
}


public void run(){
    try {
        java.io.File file = pdfService.createQuizPDF(answerSet);
        java.io.File file2 = pdfService.createAnswerPDF(answerSet);
        if (file != null && this.email != null && this.email.length() > 0) {
            String body = "Dear EUSurvey user,<br /><br />A PDF copy of the results of your quiz participation to survey '<b>" + answerSet.getSurvey().cleanTitle() + "</b>' has been created and is attached to this email.";
            body += "<br /><br />Your EUSurvey team";
            InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/Content/mailtemplateeusurvey.html");
            String text = IOUtils.toString(inputStream, "UTF-8").replace("[CONTENT]", body).replace("[HOST]", host);
            mailService.SendHtmlMail(email, from, from, "Copy of your PDF quiz results", text, file, file2, null, false);
        }
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
    }
}


}