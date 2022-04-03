package com.ec.survey.DTO;
 import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ec.survey.exception.MessageException;
import com.ec.survey.model.ExportCache;
import com.ec.survey.model.Form;
import com.ec.survey.model.ResultFilter;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.model.survey.base.File;
import com.ec.survey.service.AnswerService;
import com.ec.survey.service.FileService;
import com.ec.survey.service.MailService;
import com.ec.survey.service.SurveyService;
import com.ec.survey.tools.export.OdfExportCreator;
import com.ec.survey.tools.export.XlsExportCreator;
import com.ec.survey.Interface.Survey;
import com.ec.survey.Interface.MailService;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.AnswerService;
public class ResultsExecutor implements BeanFactoryAware,Runnable{

 private  Survey survey;

 private  ResultFilter filter;

 private  String email;

 private  String from;

 private  String host;

 private  MailService mailService;

 protected  String deleteexportstimeout;

 public  ServletContext servletContext;

 private  BeanFactory context;

 private  FileService fileService;

 private  SurveyService surveyService;

 private  AnswerService answerService;

 private  String fileDir;

 private  String type;

 private  MessageSource resources;

 private  Locale locale;

 private  String question;

 private  Logger logger;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public void init(Survey survey,ResultFilter filter,String email,String from,String host,String fileDir,String type,MessageSource resources,Locale locale,String question){
    this.survey = survey;
    this.filter = filter;
    this.email = email;
    this.from = from;
    this.host = host;
    this.fileDir = fileDir;
    this.type = type;
    this.resources = resources;
    this.locale = locale;
    this.question = question;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/init"))

.queryParam("survey",survey)
.queryParam("filter",filter)
.queryParam("email",email)
.queryParam("from",from)
.queryParam("host",host)
.queryParam("fileDir",fileDir)
.queryParam("type",type)
.queryParam("resources",resources)
.queryParam("locale",locale)
.queryParam("question",question)
;
restTemplate.put(builder.toUriString(),null);
}


}