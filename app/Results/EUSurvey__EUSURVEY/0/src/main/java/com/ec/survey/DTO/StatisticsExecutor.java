package com.ec.survey.DTO;
 import java.io.InputStream;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.ec.survey.model.Export;
import com.ec.survey.model.ExportCache;
import com.ec.survey.model.Form;
import com.ec.survey.model.ResultFilter;
import com.ec.survey.model.Export.ExportFormat;
import com.ec.survey.model.Export.ExportState;
import com.ec.survey.model.Export.ExportType;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.service.ExportService;
import com.ec.survey.service.FileService;
import com.ec.survey.service.MailService;
import com.ec.survey.service.SurveyService;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.MailService;
import com.ec.survey.Interface.Survey;
public class StatisticsExecutor implements Runnable{

 private  FileService fileService;

 private  SurveyService surveyService;

 private  ExportService exportService;

 private  MailService mailService;

 protected  MessageSource resources;

 public  ServletContext servletContext;

 private  Survey survey;

 private  String type;

 private  String format;

 private  String email;

 private  String from;

 private  String host;

 private  String hash;

 private  Locale locale;

 private  Logger logger;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public void init(Survey survey,String type,String format,String hash,String email,String from,String host,Locale locale){
    this.survey = survey;
    this.type = type;
    this.format = format;
    this.email = email;
    this.from = from;
    this.host = host;
    this.locale = locale;
    this.hash = hash;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/init"))

.queryParam("survey",survey)
.queryParam("type",type)
.queryParam("format",format)
.queryParam("hash",hash)
.queryParam("email",email)
.queryParam("from",from)
.queryParam("host",host)
.queryParam("locale",locale)
;
restTemplate.put(builder.toUriString(),null);
}


}