package com.ec.survey;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.ec.survey.Interface.DomainUpdater;
import com.ec.survey.Interface.DomainUpdaterImpl;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.SurveyServiceImpl;
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
import com.ec.survey.Interface.SessionService;
import com.ec.survey.Interface.SessionServiceImpl;
import com.ec.survey.Interface.TranslationService;
import com.ec.survey.Interface.TranslationServiceImpl;
import com.ec.survey.Interface.ActivityService;
import com.ec.survey.Interface.ActivityServiceImpl;
import com.ec.survey.Interface.AdministrationService;
import com.ec.survey.Interface.AdministrationServiceImpl;
import com.ec.survey.Interface.MailService;
import com.ec.survey.Interface.MailServiceImpl;
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
import com.ec.survey.Interface.TranslationService;
import com.ec.survey.Interface.TranslationServiceImpl;
import com.ec.survey.Interface.LdapService;
import com.ec.survey.Interface.LdapServiceImpl;
import com.ec.survey.Interface.LdapDBService;
import com.ec.survey.Interface.LdapDBServiceImpl;
import com.ec.survey.Interface.ArchiveService;
import com.ec.survey.Interface.ArchiveServiceImpl;
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
public DomainUpdater domainupdater(){

return  new DomainUpdaterImpl(); 
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
public SessionService sessionservice(){

return  new SessionServiceImpl(); 
    }



@Bean
public TranslationService translationservice(){

return  new TranslationServiceImpl(); 
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
public ReportingServiceProxy reportingserviceproxy(){

return  new ReportingServiceProxyImpl(); 
    }



@Bean
public ECFService ecfservice(){

return  new ECFServiceImpl(); 
    }



}