package com.ec.survey.DTO;
 import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Locale;
import javax.annotation.Resource;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ec.survey.exception.MessageException;
import com.ec.survey.model.Archive;
import com.ec.survey.model.Export;
import com.ec.survey.model.ResultFilter;
import com.ec.survey.model.Export.ExportFormat;
import com.ec.survey.model.Export.ExportState;
import com.ec.survey.model.Export.ExportType;
import com.ec.survey.model.Form;
import com.ec.survey.model.administration.User;
import com.ec.survey.model.survey.Element;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.service.AnswerService;
import com.ec.survey.service.ArchiveService;
import com.ec.survey.service.ExportService;
import com.ec.survey.service.FileService;
import com.ec.survey.service.MailService;
import com.ec.survey.service.PDFService;
import com.ec.survey.service.SessionService;
import com.ec.survey.service.SurveyService;
import com.ec.survey.service.TranslationService;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.AnswerService;
import com.ec.survey.Interface.ArchiveService;
import com.ec.survey.Interface.SessionService;
import com.ec.survey.Interface.MailService;
import com.ec.survey.Interface.PDFService;
import com.ec.survey.Interface.TranslationService;
import com.ec.survey.Interface.Archive;
import com.ec.survey.Interface.Survey;
import com.ec.survey.Interface.User;
import com.ec.survey.Interface.Survey;
import com.ec.survey.Interface.Form;
public class ArchiveExecutor implements Runnable{

 private  FileService fileService;

 private  SurveyService surveyService;

 private  ExportService exportService;

 private  AnswerService answerService;

 private  ArchiveService archiveService;

 private  SessionService sessionService;

 private  MailService mailService;

 private  PDFService pdfService;

 private  SessionFactory sessionFactory;

 private  TranslationService translationService;

 protected  MessageSource resources;

 private  Logger logger;

 private  Archive archive;

 private  Survey survey;

 private  User user;

 private  Export export;

 private  Export exportstats;

 private  Export exportstatspdf;

 private  Survey published;

 private  Form form;

 private  java.io.File target;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public void init(Archive archive,Survey survey,User user){
    this.archive = archive;
    this.survey = survey;
    this.user = user;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/init"))

.queryParam("archive",archive)
.queryParam("survey",survey)
.queryParam("user",user)
;
restTemplate.put(builder.toUriString(),null);
}


@Transactional
public boolean prepare(){
    if (survey == null) {
        logger.error("survey is null");
        return false;
    }
    if (survey.getLanguage() == null) {
        logger.error("survey.language is null");
        return false;
    }
    published = surveyService.getSurvey(survey.getShortname(), false, false, false, true, survey.getLanguage().getCode(), true, false);
    if (published != null) {
        form = new Form();
        form.setSurvey(published);
        ResultFilter resultFilter = new ResultFilter();
        resultFilter.setSurveyId(published.getId());
        for (Element element : published.getElementsRecursive(false)) {
            resultFilter.getVisibleQuestions().add(element.getId().toString());
            resultFilter.getExportedQuestions().add(element.getId().toString());
            if (element.isDelphiElement()) {
                resultFilter.getVisibleExplanations().add(element.getId().toString());
                resultFilter.getExportedExplanations().add(element.getId().toString());
                resultFilter.getVisibleDiscussions().add(element.getId().toString());
                resultFilter.getExportedDiscussions().add(element.getId().toString());
            }
        }
        export.setDate(new Date());
        export.setState(ExportState.Pending);
        export.setUserId(user.getId());
        export.setName("archiveXLS");
        export.setType(ExportType.Content);
        export.setFormat(ExportFormat.xls);
        export.setSurvey(published);
        export.setResultFilter(resultFilter);
        exportService.prepareExport(null, export);
        exportstats.setDate(new Date());
        exportstats.setState(ExportState.Pending);
        exportstats.setUserId(user.getId());
        exportstats.setName("archiveStats");
        exportstats.setType(ExportType.Statistics);
        exportstats.setFormat(ExportFormat.xls);
        exportstats.setSurvey(published);
        exportstats.setResultFilter(resultFilter);
        exportService.prepareExport(null, exportstats);
        exportstatspdf.setDate(new Date());
        exportstatspdf.setState(ExportState.Pending);
        exportstatspdf.setUserId(user.getId());
        exportstatspdf.setName("archiveStats");
        exportstatspdf.setType(ExportType.Statistics);
        exportstatspdf.setFormat(ExportFormat.pdf);
        exportstatspdf.setSurvey(published);
        exportstatspdf.setResultFilter(resultFilter);
        exportService.prepareExport(null, exportstatspdf);
    }
    surveyService.markAsArchived(survey.getUniqueId());
    return true;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/prepare"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}