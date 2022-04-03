package com.ec.survey.service;
 import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.ec.survey.tools.ConversionTools;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import com.ec.survey.Interface.AnswerExplanationService;
import com.ec.survey.Interface.ParticipationService;
import com.ec.survey.Interface.ExportService;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.SkinService;
import com.ec.survey.Interface.TranslationService;
import com.ec.survey.Interface.ActivityService;
import com.ec.survey.Interface.AdministrationService;
import com.ec.survey.Interface.MailService;
import com.ec.survey.Interface.SystemService;
import com.ec.survey.Interface.SettingsService;
import com.ec.survey.Interface.ArchiveService;
import com.ec.survey.Interface.ReportingServiceProxy;
import com.ec.survey.Interface.ECFService;
@Service
public class BasicService implements BeanFactoryAware{

@Resource(name = "sessionFactory")
 protected  SessionFactory sessionFactory;

@Resource(name = "answerService")
 protected  AnswerService answerService;

@Resource
 protected  AnswerExplanationService answerExplanationService;

@Resource(name = "participationService")
 protected  ParticipationService participationService;

@Resource(name = "surveyService")
 protected  SurveyService surveyService;

@Resource(name = "exportService")
 protected  ExportService exportService;

@Resource(name = "fileService")
 protected  FileService fileService;

@Resource(name = "skinService")
 protected  SkinService skinService;

@Resource(name = "sessionService")
 protected  SessionService sessionService;

@Resource(name = "translationService")
 protected  TranslationService translationService;

@Resource(name = "activityService")
 protected  ActivityService activityService;

@Resource(name = "administrationService")
 protected  AdministrationService administrationService;

@Resource(name = "mailService")
 protected  MailService mailService;

@Resource(name = "systemService")
 protected  SystemService systemService;

@Resource(name = "settingsService")
 protected  SettingsService settingsService;

@Resource(name = "archiveService")
 protected  ArchiveService archiveService;

@Resource(name = "reportingServiceProxy")
 protected  ReportingServiceProxy reportingService;

@Resource(name = "ecfService")
 protected  ECFService ecfService;

@Autowired
 protected  MessageSource resources;

@Value("${export.tempFileDir}")
 protected  String tempFileDir;

@Value("${export.fileDir}")
 protected  String fileDir;

@Value("${archive.fileDir}")
 protected  String archiveFileDir;

@Value("${filesystem.surveys}")
 protected  String surveysDir;

@Value("${filesystem.users}")
 protected  String usersDir;

@Value("${filesystem.archive}")
 protected  String archiveDir;

@Value("${export.poolSize}")
 private  String poolSize;

@Value("${export.timeout}")
 protected  String exporttimeout;

@Value("${mail.mailPoolSize}")
 private  String mailPoolSize;

@Value("${pdfserver.prefix}")
 protected  String pdfhost;

@Value("${server.prefix}")
 protected  String serverPrefix;

@Value("${isworkerserver}")
 protected  String isworkerserver;

@Value("${useworkerserver}")
 protected  String useworkerserver;

@Value("${workerserverurl}")
 protected  String workerserverurl;

@Value("${contextpath}")
 protected  String contextpath;

@Value("${oss}")
 protected  String oss;

@Autowired
 protected  ServletContext servletContext;

@Value("${enablereportingdatabase}")
 protected  String enablereportingdatabase;

@Value("${ui.enabledelphi}")
 protected  String enableDelphi;

 private  ExecutorService pool;

 private  ExecutorService pdfpool;

 private  ExecutorService answerpool;

 private  ExecutorService mailPool;

 private  ExecutorService tokenPool;

 protected  Logger logger;

 protected  BeanFactory context;

 private  LinkedBlockingQueue<Runnable> taskQueue;

 protected  List<Runnable> running;


public boolean isDelphiEnabled(){
    return (!StringUtils.isEmpty(enableDelphi) && enableDelphi.equalsIgnoreCase("true"));
}


@Override
public void beforeExecute(Thread t,Runnable r){
    super.beforeExecute(t, r);
    running.add(r);
}


public boolean isReportingDatabaseEnabled(){
    return enablereportingdatabase != null && enablereportingdatabase.equalsIgnoreCase("true");
}


public Thread newThread(Runnable r){
    Thread t = new Thread(r);
    t.setPriority(Thread.MIN_PRIORITY);
    return t;
}


public ExecutorService getTokenPool(){
    if (tokenPool == null) {
        tokenPool = Executors.newFixedThreadPool(1, new MyThreadFactory());
    }
    return tokenPool;
}


public String getFileUIDFromUrl(String url){
    return url.substring(url.lastIndexOf('/') + 1);
}


public ExecutorService getPDFPool(){
    if (pdfpool == null) {
        pdfpool = Executors.newFixedThreadPool(ConversionTools.getInt(poolSize), new MyThreadFactory());
    }
    return pdfpool;
}


@Override
public void setBeanFactory(BeanFactory beanFactory){
    context = beanFactory;
}


public String getFileDir(){
    return fileDir;
}


public ExecutorService getMailPool(){
    if (mailPool == null) {
        mailPool = Executors.newFixedThreadPool(ConversionTools.getInt(mailPoolSize), new MyThreadFactory());
    }
    return mailPool;
}


@Override
public void afterExecute(Runnable r,Throwable t){
    super.afterExecute(r, t);
    running.remove(r);
}


public ExecutorService getPool(){
    if (pool == null) {
        pool = Executors.newFixedThreadPool(ConversionTools.getInt(poolSize), new MyThreadFactory());
    }
    return pool;
}


public boolean isOss(){
    return (!StringUtils.isEmpty(oss) && oss.equalsIgnoreCase("true"));
}


public Executor getAnswerPool(){
    if (answerpool == null) {
        answerpool = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, taskQueue, Executors.defaultThreadFactory()) {

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                running.add(r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                running.remove(r);
            }
        };
    }
    return answerpool;
}


}