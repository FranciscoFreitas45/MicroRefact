package com.ec.survey;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.ec.survey.Interface.SqlQueryService;
import com.ec.survey.Interface.SqlQueryServiceImpl;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.FileServiceImpl;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.SurveyServiceImpl;
import com.ec.survey.Interface.AnswerService;
import com.ec.survey.Interface.AnswerServiceImpl;
import com.ec.survey.Interface.ArchiveService;
import com.ec.survey.Interface.ArchiveServiceImpl;
import com.ec.survey.Interface.SessionService;
import com.ec.survey.Interface.SessionServiceImpl;
import com.ec.survey.Interface.MailService;
import com.ec.survey.Interface.MailServiceImpl;
import com.ec.survey.Interface.PDFService;
import com.ec.survey.Interface.PDFServiceImpl;
import com.ec.survey.Interface.TranslationService;
import com.ec.survey.Interface.TranslationServiceImpl;
import com.ec.survey.Interface.Archive;
import com.ec.survey.Interface.ArchiveImpl;
import com.ec.survey.Interface.Survey;
import com.ec.survey.Interface.SurveyImpl;
import com.ec.survey.Interface.User;
import com.ec.survey.Interface.UserImpl;
import com.ec.survey.Interface.Survey;
import com.ec.survey.Interface.SurveyImpl;
import com.ec.survey.Interface.Form;
import com.ec.survey.Interface.FormImpl;
import com.ec.survey.Interface.MailService;
import com.ec.survey.Interface.MailServiceImpl;
import com.ec.survey.Interface.AttendeeService;
import com.ec.survey.Interface.AttendeeServiceImpl;
import com.ec.survey.Interface.AnswerExplanationService;
import com.ec.survey.Interface.AnswerExplanationServiceImpl;
import com.ec.survey.Interface.ParticipationService;
import com.ec.survey.Interface.ParticipationServiceImpl;
import com.ec.survey.Interface.AdministrationService;
import com.ec.survey.Interface.AdministrationServiceImpl;
import com.ec.survey.Interface.AnswerService;
import com.ec.survey.Interface.AnswerServiceImpl;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.SurveyServiceImpl;
import com.ec.survey.Interface.PDFService;
import com.ec.survey.Interface.PDFServiceImpl;
import com.ec.survey.Interface.TranslationService;
import com.ec.survey.Interface.TranslationServiceImpl;
import com.ec.survey.Interface.MailService;
import com.ec.survey.Interface.MailServiceImpl;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.FileServiceImpl;
import com.ec.survey.Interface.ReportingServiceProxy;
import com.ec.survey.Interface.ReportingServiceProxyImpl;
import com.ec.survey.Interface.ECFService;
import com.ec.survey.Interface.ECFServiceImpl;
import com.ec.survey.Interface.Form;
import com.ec.survey.Interface.FormImpl;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.FileServiceImpl;
import com.ec.survey.Interface.ParticipationService;
import com.ec.survey.Interface.ParticipationServiceImpl;
import com.ec.survey.Interface.AnswerService;
import com.ec.survey.Interface.AnswerServiceImpl;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.SurveyServiceImpl;
import com.ec.survey.Interface.PDFService;
import com.ec.survey.Interface.PDFServiceImpl;
import com.ec.survey.Interface.SqlQueryService;
import com.ec.survey.Interface.SqlQueryServiceImpl;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.FileServiceImpl;
import com.ec.survey.Interface.AnswerService;
import com.ec.survey.Interface.AnswerServiceImpl;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.SurveyServiceImpl;
import com.ec.survey.Interface.ParticipationService;
import com.ec.survey.Interface.ParticipationServiceImpl;
import com.ec.survey.Interface.TranslationService;
import com.ec.survey.Interface.TranslationServiceImpl;
import com.ec.survey.Interface.PDFService;
import com.ec.survey.Interface.PDFServiceImpl;
import com.ec.survey.Interface.Survey;
import com.ec.survey.Interface.SurveyImpl;
import com.ec.survey.Interface.MailService;
import com.ec.survey.Interface.MailServiceImpl;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.FileServiceImpl;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.SurveyServiceImpl;
import com.ec.survey.Interface.AnswerService;
import com.ec.survey.Interface.AnswerServiceImpl;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.SurveyServiceImpl;
import com.ec.survey.Interface.AdministrationService;
import com.ec.survey.Interface.AdministrationServiceImpl;
import com.ec.survey.Interface.ArchiveService;
import com.ec.survey.Interface.ArchiveServiceImpl;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.FileServiceImpl;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.SurveyServiceImpl;
import com.ec.survey.Interface.MailService;
import com.ec.survey.Interface.MailServiceImpl;
import com.ec.survey.Interface.Survey;
import com.ec.survey.Interface.SurveyImpl;
import com.ec.survey.Interface.ParticipationService;
import com.ec.survey.Interface.ParticipationServiceImpl;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.FileServiceImpl;
import com.ec.survey.Interface.AttendeeService;
import com.ec.survey.Interface.AttendeeServiceImpl;
import com.ec.survey.Interface.AnswerService;
import com.ec.survey.Interface.AnswerServiceImpl;
import com.ec.survey.Interface.AnswerExplanationService;
import com.ec.survey.Interface.AnswerExplanationServiceImpl;
import com.ec.survey.Interface.ParticipationService;
import com.ec.survey.Interface.ParticipationServiceImpl;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.SurveyServiceImpl;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.FileServiceImpl;
import com.ec.survey.Interface.SkinService;
import com.ec.survey.Interface.SkinServiceImpl;
import com.ec.survey.Interface.SessionService;
import com.ec.survey.Interface.SessionServiceImpl;
import com.ec.survey.Interface.TranslationService;
import com.ec.survey.Interface.TranslationServiceImpl;
import com.ec.survey.Interface.AdministrationService;
import com.ec.survey.Interface.AdministrationServiceImpl;
import com.ec.survey.Interface.MailService;
import com.ec.survey.Interface.MailServiceImpl;
import com.ec.survey.Interface.SystemService;
import com.ec.survey.Interface.SystemServiceImpl;
import com.ec.survey.Interface.SettingsService;
import com.ec.survey.Interface.SettingsServiceImpl;
import com.ec.survey.Interface.ArchiveService;
import com.ec.survey.Interface.ArchiveServiceImpl;
import com.ec.survey.Interface.ReportingServiceProxy;
import com.ec.survey.Interface.ReportingServiceProxyImpl;
import com.ec.survey.Interface.ECFService;
import com.ec.survey.Interface.ECFServiceImpl;
import com.ec.survey.Interface.AnswerService;
import com.ec.survey.Interface.AnswerServiceImpl;
import com.ec.survey.Interface.AnswerExplanationService;
import com.ec.survey.Interface.AnswerExplanationServiceImpl;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.SurveyServiceImpl;
import com.ec.survey.Interface.SystemService;
import com.ec.survey.Interface.SystemServiceImpl;
import com.ec.survey.Interface.AttendeeService;
import com.ec.survey.Interface.AttendeeServiceImpl;
import com.ec.survey.Interface.ParticipationService;
import com.ec.survey.Interface.ParticipationServiceImpl;
import com.ec.survey.Interface.AdministrationService;
import com.ec.survey.Interface.AdministrationServiceImpl;
import com.ec.survey.Interface.SessionService;
import com.ec.survey.Interface.SessionServiceImpl;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.FileServiceImpl;
import com.ec.survey.Interface.SkinService;
import com.ec.survey.Interface.SkinServiceImpl;
import com.ec.survey.Interface.TranslationService;
import com.ec.survey.Interface.TranslationServiceImpl;
import com.ec.survey.Interface.LdapService;
import com.ec.survey.Interface.LdapServiceImpl;
import com.ec.survey.Interface.LdapDBService;
import com.ec.survey.Interface.LdapDBServiceImpl;
import com.ec.survey.Interface.ArchiveService;
import com.ec.survey.Interface.ArchiveServiceImpl;
import com.ec.survey.Interface.SettingsService;
import com.ec.survey.Interface.SettingsServiceImpl;
import com.ec.survey.Interface.ReportingServiceProxy;
import com.ec.survey.Interface.ReportingServiceProxyImpl;
import com.ec.survey.Interface.ECFService;
import com.ec.survey.Interface.ECFServiceImpl;
@SpringBootApplication
public class Main {


@Bean
public RestTemplate restTemplate(){
 
 return new RestTemplate();

  }



public static void main(String[] args){

SpringApplication.run(Main.class,args);

   }



@Bean
public SqlQueryService sqlqueryservice(){

return  new SqlQueryServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public SurveyService surveyservice(){

return  new SurveyServiceImpl(); 
    }



@Bean
public AnswerService answerservice(){

return  new AnswerServiceImpl(); 
    }



@Bean
public ArchiveService archiveservice(){

return  new ArchiveServiceImpl(); 
    }



@Bean
public SessionService sessionservice(){

return  new SessionServiceImpl(); 
    }



@Bean
public MailService mailservice(){

return  new MailServiceImpl(); 
    }



@Bean
public PDFService pdfservice(){

return  new PDFServiceImpl(); 
    }



@Bean
public TranslationService translationservice(){

return  new TranslationServiceImpl(); 
    }



@Bean
public Archive archive(){

return  new ArchiveImpl(); 
    }



@Bean
public Survey survey(){

return  new SurveyImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public Survey survey(){

return  new SurveyImpl(); 
    }



@Bean
public Form form(){

return  new FormImpl(); 
    }



@Bean
public MailService mailservice(){

return  new MailServiceImpl(); 
    }



@Bean
public AttendeeService attendeeservice(){

return  new AttendeeServiceImpl(); 
    }



@Bean
public AnswerExplanationService answerexplanationservice(){

return  new AnswerExplanationServiceImpl(); 
    }



@Bean
public ParticipationService participationservice(){

return  new ParticipationServiceImpl(); 
    }



@Bean
public AdministrationService administrationservice(){

return  new AdministrationServiceImpl(); 
    }



@Bean
public AnswerService answerservice(){

return  new AnswerServiceImpl(); 
    }



@Bean
public SurveyService surveyservice(){

return  new SurveyServiceImpl(); 
    }



@Bean
public PDFService pdfservice(){

return  new PDFServiceImpl(); 
    }



@Bean
public TranslationService translationservice(){

return  new TranslationServiceImpl(); 
    }



@Bean
public MailService mailservice(){

return  new MailServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public ReportingServiceProxy reportingserviceproxy(){

return  new ReportingServiceProxyImpl(); 
    }



@Bean
public ECFService ecfservice(){

return  new ECFServiceImpl(); 
    }



@Bean
public Form form(){

return  new FormImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public ParticipationService participationservice(){

return  new ParticipationServiceImpl(); 
    }



@Bean
public AnswerService answerservice(){

return  new AnswerServiceImpl(); 
    }



@Bean
public SurveyService surveyservice(){

return  new SurveyServiceImpl(); 
    }



@Bean
public PDFService pdfservice(){

return  new PDFServiceImpl(); 
    }



@Bean
public SqlQueryService sqlqueryservice(){

return  new SqlQueryServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public AnswerService answerservice(){

return  new AnswerServiceImpl(); 
    }



@Bean
public SurveyService surveyservice(){

return  new SurveyServiceImpl(); 
    }



@Bean
public ParticipationService participationservice(){

return  new ParticipationServiceImpl(); 
    }



@Bean
public TranslationService translationservice(){

return  new TranslationServiceImpl(); 
    }



@Bean
public PDFService pdfservice(){

return  new PDFServiceImpl(); 
    }



@Bean
public Survey survey(){

return  new SurveyImpl(); 
    }



@Bean
public MailService mailservice(){

return  new MailServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public SurveyService surveyservice(){

return  new SurveyServiceImpl(); 
    }



@Bean
public AnswerService answerservice(){

return  new AnswerServiceImpl(); 
    }



@Bean
public SurveyService surveyservice(){

return  new SurveyServiceImpl(); 
    }



@Bean
public AdministrationService administrationservice(){

return  new AdministrationServiceImpl(); 
    }



@Bean
public ArchiveService archiveservice(){

return  new ArchiveServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public SurveyService surveyservice(){

return  new SurveyServiceImpl(); 
    }



@Bean
public MailService mailservice(){

return  new MailServiceImpl(); 
    }



@Bean
public Survey survey(){

return  new SurveyImpl(); 
    }



@Bean
public ParticipationService participationservice(){

return  new ParticipationServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public AttendeeService attendeeservice(){

return  new AttendeeServiceImpl(); 
    }



@Bean
public AnswerService answerservice(){

return  new AnswerServiceImpl(); 
    }



@Bean
public AnswerExplanationService answerexplanationservice(){

return  new AnswerExplanationServiceImpl(); 
    }



@Bean
public ParticipationService participationservice(){

return  new ParticipationServiceImpl(); 
    }



@Bean
public SurveyService surveyservice(){

return  new SurveyServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public SkinService skinservice(){

return  new SkinServiceImpl(); 
    }



@Bean
public SessionService sessionservice(){

return  new SessionServiceImpl(); 
    }



@Bean
public TranslationService translationservice(){

return  new TranslationServiceImpl(); 
    }



@Bean
public AdministrationService administrationservice(){

return  new AdministrationServiceImpl(); 
    }



@Bean
public MailService mailservice(){

return  new MailServiceImpl(); 
    }



@Bean
public SystemService systemservice(){

return  new SystemServiceImpl(); 
    }



@Bean
public SettingsService settingsservice(){

return  new SettingsServiceImpl(); 
    }



@Bean
public ArchiveService archiveservice(){

return  new ArchiveServiceImpl(); 
    }



@Bean
public ReportingServiceProxy reportingserviceproxy(){

return  new ReportingServiceProxyImpl(); 
    }



@Bean
public ECFService ecfservice(){

return  new ECFServiceImpl(); 
    }



@Bean
public AnswerService answerservice(){

return  new AnswerServiceImpl(); 
    }



@Bean
public AnswerExplanationService answerexplanationservice(){

return  new AnswerExplanationServiceImpl(); 
    }



@Bean
public SurveyService surveyservice(){

return  new SurveyServiceImpl(); 
    }



@Bean
public SystemService systemservice(){

return  new SystemServiceImpl(); 
    }



@Bean
public AttendeeService attendeeservice(){

return  new AttendeeServiceImpl(); 
    }



@Bean
public ParticipationService participationservice(){

return  new ParticipationServiceImpl(); 
    }



@Bean
public AdministrationService administrationservice(){

return  new AdministrationServiceImpl(); 
    }



@Bean
public SessionService sessionservice(){

return  new SessionServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public SkinService skinservice(){

return  new SkinServiceImpl(); 
    }



@Bean
public TranslationService translationservice(){

return  new TranslationServiceImpl(); 
    }



@Bean
public LdapService ldapservice(){

return  new LdapServiceImpl(); 
    }



@Bean
public LdapDBService ldapdbservice(){

return  new LdapDBServiceImpl(); 
    }



@Bean
public ArchiveService archiveservice(){

return  new ArchiveServiceImpl(); 
    }



@Bean
public SettingsService settingsservice(){

return  new SettingsServiceImpl(); 
    }



@Bean
public ReportingServiceProxy reportingserviceproxy(){

return  new ReportingServiceProxyImpl(); 
    }



@Bean
public ECFService ecfservice(){

return  new ECFServiceImpl(); 
    }



}