package com.ec.survey.tools.export;
 import com.ec.survey.model.Export;
import com.ec.survey.model.ExportCache;
import com.ec.survey.model.Export.ExportState;
import com.ec.survey.model.Form;
import com.ec.survey.service;
import com.ec.survey.tools.Constants;
import com.ec.survey.tools.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import com.ec.survey.Interface.AttendeeService;
import com.ec.survey.Interface.AnswerExplanationService;
import com.ec.survey.Interface.ParticipationService;
import com.ec.survey.Interface.AdministrationService;
import com.ec.survey.Interface.AnswerService;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.PDFService;
import com.ec.survey.Interface.TranslationService;
import com.ec.survey.Interface.MailService;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.ReportingServiceProxy;
import com.ec.survey.Interface.ECFService;
import com.ec.survey.Interface.Form;
public class ExportCreator implements Runnable{

 protected  Logger logger;

@Resource(name = "attendeeService")
 protected  AttendeeService attendeeService;

@Resource(name = "answerExplanationService")
 protected  AnswerExplanationService answerExplanationService;

@Resource(name = "participationService")
 protected  ParticipationService participationService;

@Resource(name = "administrationService")
 protected  AdministrationService administrationService;

@Resource(name = "exportService")
 protected  ExportService exportService;

@Resource(name = "answerService")
 protected  AnswerService answerService;

@Resource(name = "surveyService")
 protected  SurveyService surveyService;

@Resource(name = "pdfService")
 protected  PDFService pdfService;

@Resource(name = "sessionFactory")
 protected  SessionFactory sessionFactory;

@Resource(name = "activityService")
 protected  ActivityService activityService;

@Resource(name = "translationService")
 protected  TranslationService translationService;

@Resource(name = "mailService")
 protected  MailService mailService;

@Resource(name = "fileService")
 protected  FileService fileService;

@Resource(name = "reportingServiceProxy")
 protected  ReportingServiceProxy reportingService;

@Resource(name = "ecfService")
 protected  ECFService ecfService;

@Value("${smtpserver}")
 private  String smtpServer;

@Value("${smtp.port}")
 private  String smtpPort;

@Value("${sender}")
 private  String sender;

@Autowired
 protected  ServletContext servletContext;

 protected  int userId;

 protected  Form form;

 protected  Export export;

 protected  OutputStream outputStream;

 protected  MessageSource resources;

 protected  Locale locale;

 protected  String exportFilePath;

 protected  String uid;

 protected  String host;


public void init(){
}


public void exportStatisticsQuiz()


@Transactional
public void runSync(){
    runBasic(true);
}


public void exportAddressBook()


public void runBasic(boolean sync){
    if (export == null)
        return;
    try {
        innerRunBasic(sync, export);
        export.setValid(true);
        export.setState(ExportState.Finished);
        if (export.getSurvey() != null) {
            activityService.log(310, null, export.getId().toString(), -1, export.getSurvey().getUniqueId());
        }
        if (export.getEmail() != null) {
            // cache it
            ExportCache ec = new ExportCache();
            ec.setSurveyId(form.getSurvey().getId());
            ec.setFilterHash(export.getResultFilter().getHash(export.isAllAnswers()));
            String cacheType = "statexport" + export.getType() + export.getFormat();
            ec.setType(cacheType);
            ec.setUid(uid);
            String name = FileUtils.cleanFilename(String.format("%s_Published_Results_%s.%s", export.getSurveyShortname(), export.getType(), export.getFormat()));
            com.ec.survey.model.survey.base.File f = new com.ec.survey.model.survey.base.File();
            f.setUid(ec.getUid());
            f.setName(name);
            fileService.addNewTransaction(f);
            fileService.saveNewTransaction(ec);
            String link = host + "files/" + uid + Constants.PATH_DELIMITER;
            if (export.getSurvey() != null) {
                link = host + "files/" + export.getSurvey().getUniqueId() + Constants.PATH_DELIMITER + uid + Constants.PATH_DELIMITER;
            }
            String body = "Dear EUSurvey user,<br /><br />The export you requested from the published results of the survey '<b>" + export.getSurvey().cleanTitle() + "</b>' is now finished. You can download it here:<br /><br /> <a href=\"" + link + "\">" + name + "</a><br /><br />Your EUSurvey team";
            String subject = "Copy of your requested export from '" + export.getSurvey().cleanTitleForMailSubject() + "'";
            InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/Content/mailtemplateeusurvey.html");
            String text = IOUtils.toString(inputStream, "UTF-8").replace("[CONTENT]", body).replace("[HOST]", host);
            mailService.SendHtmlMail(export.getEmail(), sender, sender, subject, text, null);
        }
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        if (e instanceof GenericJDBCException) {
            GenericJDBCException ge = (GenericJDBCException) e;
            logger.error("caused by " + ge.getSQL());
        }
        export.setState(ExportState.Failed);
    } finally {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                logger.error(e.getLocalizedMessage(), e);
            }
        }
    }
    exportService.update(export);
}


public void exportECFGlobalResults()


public void exportActivities()


public void initAnswers(){
    form.setStatistics(answerService.getStatisticsOrStartCreator(export.getSurvey(), export.getResultFilter(), true, export.isAllAnswers(), false));
}


@Transactional
public void run(){
    runBasic(true);
}


public void exportContent(boolean sync)


public void exportECFProfileResults()


public void innerRunBasic(boolean sync,Export export){
    switch(export.getType()) {
        case Content:
            exportContent(sync);
            break;
        case Statistics:
            initAnswers();
            exportStatistics();
            break;
        case StatisticsQuiz:
            initAnswers();
            exportStatisticsQuiz();
            break;
        case ECFGlobalResults:
            exportECFGlobalResults();
            break;
        case ECFProfileResults:
            exportECFProfileResults();
            break;
        case ECFOrganizationResults:
            exportECFOrganizationalResults();
            break;
        case AddressBook:
            exportAddressBook();
            break;
        case Activity:
            exportActivities();
            break;
        case Tokens:
            exportTokens();
            break;
        case Files:
            exportContent(sync);
            break;
        case Survey:
            exportContent(sync);
            break;
        default:
            break;
    }
}


public void exportECFOrganizationalResults()


public void exportTokens()


public void exportStatistics()


}