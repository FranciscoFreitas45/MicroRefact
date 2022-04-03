package com.ec.survey;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.ec.survey.Interface.WebserviceService;
import com.ec.survey.Interface.WebserviceServiceImpl;
import com.ec.survey.Interface.PDFService;
import com.ec.survey.Interface.PDFServiceImpl;
import com.ec.survey.Interface.XmlExportCreator;
import com.ec.survey.Interface.XmlExportCreatorImpl;
import com.ec.survey.Interface.MicrosoftTranslationService;
import com.ec.survey.Interface.MicrosoftTranslationServiceImpl;
import com.ec.survey.Interface.AnswerService;
import com.ec.survey.Interface.AnswerServiceImpl;
import com.ec.survey.Interface.AnswerExplanationService;
import com.ec.survey.Interface.AnswerExplanationServiceImpl;
import com.ec.survey.Interface.ParticipationService;
import com.ec.survey.Interface.ParticipationServiceImpl;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.SurveyServiceImpl;
import com.ec.survey.Interface.ExportService;
import com.ec.survey.Interface.ExportServiceImpl;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.FileServiceImpl;
import com.ec.survey.Interface.SkinService;
import com.ec.survey.Interface.SkinServiceImpl;
import com.ec.survey.Interface.SessionService;
import com.ec.survey.Interface.SessionServiceImpl;
import com.ec.survey.Interface.ActivityService;
import com.ec.survey.Interface.ActivityServiceImpl;
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
import com.ec.survey.Interface.ActivityService;
import com.ec.survey.Interface.ActivityServiceImpl;
import com.ec.survey.Interface.ExportService;
import com.ec.survey.Interface.ExportServiceImpl;
import com.ec.survey.Interface.AdministrationService;
import com.ec.survey.Interface.AdministrationServiceImpl;
import com.ec.survey.Interface.SessionService;
import com.ec.survey.Interface.SessionServiceImpl;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.FileServiceImpl;
import com.ec.survey.Interface.SkinService;
import com.ec.survey.Interface.SkinServiceImpl;
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
public WebserviceService webserviceservice(){

return  new WebserviceServiceImpl(); 
    }



@Bean
public PDFService pdfservice(){

return  new PDFServiceImpl(); 
    }



@Bean
public XmlExportCreator xmlexportcreator(){

return  new XmlExportCreatorImpl(); 
    }



@Bean
public MicrosoftTranslationService microsofttranslationservice(){

return  new MicrosoftTranslationServiceImpl(); 
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
public ExportService exportservice(){

return  new ExportServiceImpl(); 
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
public ActivityService activityservice(){

return  new ActivityServiceImpl(); 
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
public ActivityService activityservice(){

return  new ActivityServiceImpl(); 
    }



@Bean
public ExportService exportservice(){

return  new ExportServiceImpl(); 
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