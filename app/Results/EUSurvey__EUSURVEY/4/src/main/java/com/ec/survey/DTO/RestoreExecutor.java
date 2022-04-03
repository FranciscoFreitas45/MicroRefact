package com.ec.survey.DTO;
 import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ec.survey.model.Archive;
import com.ec.survey.model.administration.User;
import com.ec.survey.service.AdministrationService;
import com.ec.survey.service.ArchiveService;
import com.ec.survey.service.FileService;
import com.ec.survey.service.SurveyService;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.SurveyService;
public class RestoreExecutor implements Runnable{

 private  FileService fileService;

 private  SurveyService surveyService;

 private  ArchiveService archiveService;

 private  AdministrationService administrationService;

 private  Archive archive;

 private  String alias;

 private  User user;

 private  Logger logger;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public void init(Archive archive,String alias,User user){
    this.archive = archive;
    this.alias = alias;
    this.user = user;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/init"))

.queryParam("archive",archive)
.queryParam("alias",alias)
.queryParam("user",user)
;
restTemplate.put(builder.toUriString(),null);
}


@Transactional
public void prepare(){
    archiveService.markRestoring(archive);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/prepare"))

;
restTemplate.put(builder.toUriString(),null);
}


}