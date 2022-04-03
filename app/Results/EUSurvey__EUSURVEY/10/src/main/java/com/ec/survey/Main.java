package com.ec.survey;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.ec.survey.Interface.AnswerService;
import com.ec.survey.Interface.AnswerServiceImpl;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.SurveyServiceImpl;
import com.ec.survey.Interface.ReportingServiceProxy;
import com.ec.survey.Interface.ReportingServiceProxyImpl;
import com.ec.survey.Interface.Survey;
import com.ec.survey.Interface.SurveyImpl;
import com.ec.survey.Interface.ResultFilter;
import com.ec.survey.Interface.ResultFilterImpl;
import com.ec.survey.Interface.Survey;
import com.ec.survey.Interface.SurveyImpl;
import com.ec.survey.Interface.User;
import com.ec.survey.Interface.UserImpl;
import com.ec.survey.Interface.Language;
import com.ec.survey.Interface.LanguageImpl;
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
public AnswerService answerservice(){

return  new AnswerServiceImpl(); 
    }



@Bean
public SurveyService surveyservice(){

return  new SurveyServiceImpl(); 
    }



@Bean
public ReportingServiceProxy reportingserviceproxy(){

return  new ReportingServiceProxyImpl(); 
    }



@Bean
public Survey survey(){

return  new SurveyImpl(); 
    }



@Bean
public ResultFilter resultfilter(){

return  new ResultFilterImpl(); 
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
public Language language(){

return  new LanguageImpl(); 
    }



}