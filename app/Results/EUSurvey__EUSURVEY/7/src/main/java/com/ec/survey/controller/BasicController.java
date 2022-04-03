package com.ec.survey.controller;
 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Locale;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import com.ec.survey.exception.ForbiddenURLException;
import com.ec.survey.exception.FrozenSurveyException;
import com.ec.survey.exception.InvalidURLException;
import com.ec.survey.exception.MessageException;
import com.ec.survey.exception.NoFormLoadedException;
import com.ec.survey.exception.TooManyFiltersException;
import com.ec.survey.exception.httpexception.ForbiddenException;
import com.ec.survey.exception.httpexception.InternalServerErrorException;
import com.ec.survey.exception.httpexception.NotFoundException;
import com.ec.survey.exception.httpexception.UnauthorizedException;
import com.ec.survey.model.AnswerSet;
import com.ec.survey.model.Archive;
import com.ec.survey.model.Draft;
import com.ec.survey.model.administration.User;
import com.ec.survey.model.survey.Element;
import com.ec.survey.model.survey.Matrix;
import com.ec.survey.model.survey.RatingQuestion;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.service.ActivityService;
import com.ec.survey.service.AdministrationService;
import com.ec.survey.service.AnswerExplanationService;
import com.ec.survey.service.AnswerService;
import com.ec.survey.service.ArchiveService;
import com.ec.survey.service.AttendeeService;
import com.ec.survey.service.ECFService;
import com.ec.survey.service.ExportService;
import com.ec.survey.service.FileService;
import com.ec.survey.service.LdapDBService;
import com.ec.survey.service.LdapService;
import com.ec.survey.service.ParticipationService;
import com.ec.survey.service.ReportingServiceProxy;
import com.ec.survey.service.SessionService;
import com.ec.survey.service.SettingsService;
import com.ec.survey.service.SkinService;
import com.ec.survey.service.SurveyService;
import com.ec.survey.service.SystemService;
import com.ec.survey.service.TranslationService;
import com.ec.survey.tools.ArchiveExecutor;
import com.ec.survey.tools.Constants;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.InvalidXHTMLException;
import com.ec.survey.tools.NotAgreedToTosException;
import com.ec.survey.tools.NotAgreedToPsException;
import com.ec.survey.tools.WeakAuthenticationException;
import com.ec.survey.Interface.AnswerService;
import com.ec.survey.Interface.AnswerExplanationService;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.SystemService;
import com.ec.survey.Interface.AttendeeService;
import com.ec.survey.Interface.ParticipationService;
import com.ec.survey.Interface.ActivityService;
import com.ec.survey.Interface.ExportService;
import com.ec.survey.Interface.AdministrationService;
import com.ec.survey.Interface.SessionService;
import com.ec.survey.Interface.FileService;
import com.ec.survey.Interface.SkinService;
import com.ec.survey.Interface.LdapService;
import com.ec.survey.Interface.LdapDBService;
import com.ec.survey.Interface.ArchiveService;
import com.ec.survey.Interface.SettingsService;
import com.ec.survey.Interface.ReportingServiceProxy;
import com.ec.survey.Interface.ECFService;
import com.ec.survey.DTO.ArchiveExecutor;
@Controller
public class BasicController implements BeanFactoryAware{

 protected  Logger logger;

@Autowired
 protected  MessageSource resources;

@Resource(name = "answerService")
 protected  AnswerService answerService;

@Resource(name = "answerExplanationService")
 protected  AnswerExplanationService answerExplanationService;

@Resource(name = "surveyService")
 protected  SurveyService surveyService;

@Resource(name = "systemService")
 protected  SystemService systemService;

@Resource(name = "attendeeService")
 protected  AttendeeService attendeeService;

@Resource(name = "participationService")
 protected  ParticipationService participationService;

@Resource(name = "activityService")
 protected  ActivityService activityService;

@Resource(name = "exportService")
 protected  ExportService exportService;

@Resource(name = "administrationService")
 protected  AdministrationService administrationService;

@Resource(name = "sessionService")
 protected  SessionService sessionService;

@Resource(name = "fileService")
 protected  FileService fileService;

@Resource(name = "skinService")
 protected  SkinService skinService;

@Resource(name = "translationService")
 protected  TranslationService translationService;

@Resource(name = "ldapService")
 protected  LdapService ldapService;

@Resource(name = "ldapDBService")
 protected  LdapDBService ldapDBService;

@Resource(name = "archiveService")
 protected  ArchiveService archiveService;

@Resource(name = "taskExecutorLong")
 protected  TaskExecutor taskExecutorLong;

@Resource(name = "taskExecutorLongRestore")
 protected  TaskExecutor taskExecutorLongRestore;

@Resource(name = "settingsService")
 protected  SettingsService settingsService;

@Resource(name = "reportingServiceProxy")
 protected  ReportingServiceProxy reportingService;

@Resource(name = "ecfService")
 protected  ECFService ecfService;

@Value("${captcha.secret}")
 public  String captchasecret;

@Value("${captcha.serverprefix}")
 public  String captchaserverprefix;

@Value("${ui.enableresponsive}")
 public  String enableresponsive;

@Value("${ecaslogout}")
 private  String ecaslogout;

@Value("${showecas}")
 public  String showecas;

@Value("${ecashost}")
 public  String ecashost;

@Value("${sender}")
 public  String sender;

@Value("${captcha.bypass:@null}")
 public  String bypassCaptcha;

@Value("${ui.enablepublicsurveys}")
 public  String enablepublicsurveys;

@Value("${enablereportingdatabase}")
 public  String enablereportingdatabase;

@Value("${casoss}")
 public  String cassOss;

@Value("${contextpath}")
 protected  String contextpath;

@Autowired
 public  ServletContext servletContext;

@Value("${server.prefix}")
 public  String serverPrefix;

@Value("${export.tempFileDir}")
 protected  String tempFileDir;

@Value("${export.fileDir}")
 public  String fileDir;

@Value("${isworkerserver}")
 protected  String isworkerserver;

@Value("${useworkerserver}")
 protected  String useworkerserver;

@Value("${workerserverurl}")
 protected  String workerserverurl;

@Value("${archive.fileDir}")
 protected  String archiveFileDir;

@Value("${oss}")
 protected  String oss;

@Value("${show.privacy}")
 protected  String showPrivacy;

@Value("${opc.redirect}")
 protected  String opcredirect;

 protected  BeanFactory context;


@ExceptionHandler(InvalidXHTMLException.class)
public ModelAndView handleInvalidXHTMLException(InvalidXHTMLException e,Locale locale,HttpServletRequest request){
    logger.error(e.getLocalizedMessage(), e);
    ModelAndView model = new ModelAndView(Constants.VIEW_ERROR_GENERIC);
    String message = resources.getMessage("label.InvalidXHTMLPost", null, "The data you submitted contains invalid XHTML content. Please remove it before saving.", locale);
    message += ": " + e.getMessage();
    model.addObject(Constants.MESSAGE, message);
    model.addObject("contextpath", contextpath);
    return model;
}


public boolean isReportingDatabaseEnabled(){
    return enablereportingdatabase != null && enablereportingdatabase.equalsIgnoreCase("true");
}


public boolean checkCaptcha(HttpServletRequest request){
    URLConnection connection = null;
    try {
        if (!isByPassCaptcha()) {
            String captcha = settingsService.get("captcha");
            if (captcha.equalsIgnoreCase("recaptcha")) {
                sessionService.initializeProxy();
                String str = request.getParameter("g-recaptcha-response");
                URL url = new URL("https://www.google.com/recaptcha/api/siteverify?secret=" + captchasecret + "&response=" + str);
                connection = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) if (inputLine.contains("success")) {
                    in.close();
                    return inputLine.contains("true");
                }
                in.close();
            }
            if (captcha.equalsIgnoreCase("internal")) {
                String str = request.getParameter("internal_captcha_response");
                if (str == null) {
                    str = request.getParameter("g-recaptcha-response");
                }
                return sessionService.getCaptchaText(request).equals(str);
            }
            if (captcha.equalsIgnoreCase("eucaptcha")) {
                String str = request.getParameter("internal_captcha_response");
                if (str == null) {
                    str = request.getParameter("g-recaptcha-response");
                }
                String token = request.getParameter("captcha_token");
                String id = request.getParameter("captcha_id");
                String useaudio = request.getParameter("captcha_useaudio");
                if (token == null) {
                    String challenge = request.getParameter("recaptcha_challenge_field");
                    if (challenge != null && challenge.contains("|")) {
                        String[] pair = challenge.split("\\|");
                        id = pair[0];
                        token = pair[1];
                        useaudio = pair[2];
                    }
                }
                if (str == null || id == null || token == null) {
                    return false;
                }
                sessionService.initializeProxy();
                URL url = new URL(captchaserverprefix + "validateCaptcha/" + id);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("jwtString", token);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                String postData = "captchaAnswer=" + str + "&useAudio=" + ("true".equalsIgnoreCase(useaudio));
                byte[] postDataBytes = postData.getBytes("UTF-8");
                conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                for (int c; (c = in.read()) >= 0; ) {
                    sb.append((char) c);
                }
                String response = sb.toString();
                in.close();
                return response.equalsIgnoreCase("{\"responseCaptcha\":\"success\"}");
            }
            return false;
        } else {
            return true;
        }
    } catch (NullPointerException npe) {
    // this happens when the captcha was not displayed. We can ignore it here as
    // that is handled on the page itself
    } catch (IOException ioe) {
    // this happens when the eucaptcha returns "unsuccessful"
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
    }
    return false;
}


@ExceptionHandler({ java.net.SocketException.class, ClientAbortException.class })
public void handleClientAbortException(Exception e,Locale locale,HttpServletRequest request){
    logger.info(e.getLocalizedMessage(), e);
}


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@ExceptionHandler(InternalServerErrorException.class)
public void handleInternalServerErrorException(InternalServerErrorException e){
    logger.error(e.getLocalizedMessage(), e);
// nothing else to do
}


@ExceptionHandler(NotAgreedToTosException.class)
public ModelAndView handleNotAgreedToTosException(Exception e,HttpServletRequest request){
    ModelAndView model = new ModelAndView("redirect:/auth/tos");
    model.addObject("contextpath", contextpath);
    return model;
}


public boolean isShowEcas(){
    return showecas != null && showecas.equalsIgnoreCase("true");
}


public boolean isAjax(HttpServletRequest request){
    return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
}


public ModelAndView testDraftAlreadySubmittedByUniqueCode(String uniqueAnswerSet,Locale locale){
    if (surveyService.answerSetExists(uniqueAnswerSet, false, true)) {
        ModelAndView model = new ModelAndView(Constants.VIEW_ERROR_GENERIC);
        model.addObject(Constants.MESSAGE, resources.getMessage("error.AnswerAlreadySubmitted", null, "This answer was already submitted.", locale));
        return model;
    }
    return null;
}


@ExceptionHandler(InvalidURLException.class)
public ModelAndView handleInvalidURLException(Exception e,HttpServletRequest request){
    logger.info(e.getLocalizedMessage(), e);
    ModelAndView model = new ModelAndView("redirect:/errors/404.html");
    model.addObject("is404", true);
    model.addObject("contextpath", contextpath);
    return model;
}


@ExceptionHandler(Exception.class)
public ModelAndView handleException(Exception e,Locale locale,HttpServletRequest request,HttpServletResponse response){
    logger.error(e.getLocalizedMessage(), e);
    if (e instanceof IllegalArgumentException) {
        logger.error("caused by URL: " + request.getRequestURL().toString() + "?" + request.getQueryString());
    }
    if (!response.getOutputStream().isReady()) {
        logger.error("Exception thrown after outputstream was closed, caused by URL: " + request.getRequestURL().toString() + "?" + request.getQueryString());
    // return null;
    }
    ModelAndView model;
    model = new ModelAndView("redirect:/errors/500.html");
    model.addObject("contextpath", contextpath);
    return model;
}


@ExceptionHandler(TooManyFiltersException.class)
public ModelAndView handleTooManyFiltersException(Exception e,HttpServletRequest request,Locale locale){
    logger.error(e.getLocalizedMessage(), e);
    ModelAndView model = new ModelAndView(Constants.VIEW_ERROR_GENERIC);
    String message = resources.getMessage("error.TooManyFilters", null, "You used too many search filters. Please use at most 3 filters at the same time", locale);
    model.addObject(Constants.MESSAGE, message);
    model.addObject("contextpath", contextpath);
    return model;
}


public boolean isShowPrivacy(){
    return (!StringUtils.isEmpty(showPrivacy) && showPrivacy.equalsIgnoreCase("true"));
}


public Survey editSave(Survey survey,HttpServletRequest request){
    int counter = 1;
    while (true) {
        try {
            survey = surveyService.editSave(survey, request);
            return survey;
        } catch (org.hibernate.exception.LockAcquisitionException | org.springframework.dao.CannotAcquireLockException ex) {
            logger.info("lock on survey table catched; retry counter: " + counter);
            counter++;
            if (counter > 60) {
                logger.error(ex.getLocalizedMessage(), ex);
                throw ex;
            }
            Thread.sleep(1000);
        }
    }
}


@ExceptionHandler(com.ec.survey.tools.Bad2faCredentialsException.class)
public ModelAndView handleBad2faCredentialsException(Exception e,HttpServletRequest request){
    logger.info(e.getLocalizedMessage(), e);
    ModelAndView model = new ModelAndView("redirect:/errors/2fa.html");
    model.addObject("contextpath", contextpath);
    return model;
}


@ExceptionHandler({ DataException.class })
public void handleHibernateException(Exception e,Locale locale,HttpServletRequest request){
    logger.error(e.getLocalizedMessage(), e);
    if (e.getCause() != null) {
        logger.error(e.getCause().getLocalizedMessage(), e.getCause());
    }
}


@ExceptionHandler(com.ec.survey.tools.FrozenCredentialsException.class)
public ModelAndView handleFrozenCredentialsException(Exception e,HttpServletRequest request){
    logger.info(e.getLocalizedMessage(), e);
    ModelAndView model = new ModelAndView("redirect:/errors/frozen.html");
    model.addObject("contextpath", contextpath);
    return model;
}


@ResponseStatus(HttpStatus.UNAUTHORIZED)
@ExceptionHandler(UnauthorizedException.class)
public void handleUnauthorizedException(UnauthorizedException e){
// nothing else to do
}


@ResponseStatus(HttpStatus.NOT_FOUND)
@ExceptionHandler(NotFoundException.class)
public void handleNotFoundException(NotFoundException e){
// nothing else to do
}


@ResponseStatus(HttpStatus.FORBIDDEN)
@ExceptionHandler(ForbiddenException.class)
public void handleForbiddenException(ForbiddenException e){
// nothing else to do
}


@ExceptionHandler(NoFormLoadedException.class)
public ModelAndView handleNoFormLoadedException(Exception e,Locale locale,HttpServletRequest request){
    logger.error(e.getLocalizedMessage(), e);
    ModelAndView model = new ModelAndView(Constants.VIEW_ERROR_GENERIC);
    String message = resources.getMessage("error.NoFormLoadedNew", null, "You have to load a survey before using this page!", locale);
    model.addObject(Constants.MESSAGE, message);
    model.addObject("contextpath", contextpath);
    return model;
}


@Override
public void setBeanFactory(BeanFactory beanFactory){
    context = beanFactory;
}


public boolean isCasOss(){
    return cassOss != null && cassOss.equalsIgnoreCase("true");
}


@ExceptionHandler(ForbiddenURLException.class)
public ModelAndView handleForbiddenURLException(Exception e,HttpServletRequest request){
    logger.info(e.getLocalizedMessage(), e);
    ModelAndView model = new ModelAndView("redirect:/errors/403.html");
    model.addObject("contextpath", contextpath);
    return model;
}


@ExceptionHandler(FrozenSurveyException.class)
public ModelAndView handleFrozenSurveyException(Exception e,HttpServletRequest request,Locale locale){
    logger.error(e.getLocalizedMessage(), e);
    ModelAndView model = new ModelAndView(Constants.VIEW_ERROR_GENERIC);
    String message = resources.getMessage("error.FrozenSurvey", null, "This survey has been blocked due to an infringement to our policy. We are sorry for the inconvenience this may cause. Please try again later.", locale);
    model.addObject(Constants.MESSAGE, message);
    model.addObject("contextpath", contextpath);
    String uisessiontimeout = settingsService.get("uisessiontimeout");
    model.getModelMap().addAttribute("uisessiontimeout", uisessiontimeout);
    return model;
}


public ModelAndView testDraftAlreadySubmitted(Draft draft,Locale locale){
    if (draft != null) {
        String uniqueAnswerSet = draft.getAnswerSet().getUniqueCode();
        ModelAndView err = testDraftAlreadySubmittedByUniqueCode(uniqueAnswerSet, locale);
        if (err != null)
            return err;
    }
    return null;
}


@ExceptionHandler(WeakAuthenticationException.class)
public ModelAndView handleWeakAuthenticationException(Exception e,HttpServletRequest request,Locale locale){
    logger.error(e.getLocalizedMessage(), e);
    ModelAndView model = new ModelAndView("redirect:/errors/weak.html");
    model.addObject("contextpath", contextpath);
    return model;
}


public void saveAnswerSet(AnswerSet answerSet,String fileDir,String draftid,int userid,HttpServletRequest request){
    boolean saved = false;
    int counter = 1;
    boolean existingAnswerSet = answerSet.getId() != null && answerSet.getId() > 0;
    String oldvalues = "";
    if (existingAnswerSet && answerSet.getSurvey().getIsDraft() && activityService.isLogEnabled(406)) {
        oldvalues = answerService.serializeOriginal(answerSet.getId());
    }
    while (!saved) {
        try {
            answerService.internalSaveAnswerSet(answerSet, fileDir, draftid, true, true);
            sessionService.ClearUniqueCodeForForm(request, answerSet.getSurvey().getId());
            if (answerSet.getId() != null) {
                if (existingAnswerSet) {
                    String newvalues = answerSet.serialize();
                    if (answerSet.getSurvey().getIsDraft()) {
                        activityService.log(406, answerSet.getUniqueCode() + ":" + oldvalues, answerSet.getUniqueCode() + ":" + newvalues, userid, answerSet.getSurvey().getUniqueId());
                    } else {
                        activityService.log(403, null, answerSet.getUniqueCode(), userid, answerSet.getSurvey().getUniqueId());
                    }
                } else {
                    if (answerSet.getSurvey().getIsDraft()) {
                        activityService.log(404, null, answerSet.getUniqueCode(), -1, answerSet.getSurvey().getUniqueId());
                    } else {
                        activityService.log(401, null, answerSet.getUniqueCode(), -1, answerSet.getSurvey().getUniqueId());
                    }
                }
            }
            saved = true;
        } catch (org.hibernate.exception.LockAcquisitionException | org.springframework.dao.CannotAcquireLockException ex) {
            logger.info("lock on answerSet table catched; retry counter: " + counter);
            counter++;
            if (counter > 60) {
                logger.error(ex.getLocalizedMessage(), ex);
                throw ex;
            }
            Thread.sleep(1000);
        }
    }
}


@ExceptionHandler(NotAgreedToPsException.class)
public ModelAndView handleNotAgreedToPsException(Exception e,HttpServletRequest request){
    ModelAndView model = new ModelAndView("redirect:/auth/ps");
    model.addObject("contextpath", contextpath);
    return model;
}


public boolean isOss(){
    return (!StringUtils.isEmpty(oss) && oss.equalsIgnoreCase("true"));
}


public ModelAndView basicwelcome(HttpServletRequest request){
    ModelAndView model = new ModelAndView("home/welcome");
    model.addObject("page", "welcome");
    model.addObject("ecasurl", ecashost);
    model.addObject("serviceurl", serverPrefix + "auth/ecaslogin");
    model.addObject("continueWithoutJavascript", true);
    if (isShowEcas())
        model.addObject("showecas", true);
    // CASOSS
    if (isCasOss())
        model.addObject("casoss", true);
    if (request.getParameter("ecaslogout") != null) {
        model.addObject("ECASLOGOUT", ecaslogout);
    }
    return model;
}


public boolean answerSetContainsAnswerForQuestion(AnswerSet answerSet,Element question){
    if (question instanceof Matrix) {
        return !answerSet.getMatrixAnswers((Matrix) question).isEmpty();
    }
    if (question instanceof RatingQuestion) {
        for (Element childQuestion : ((RatingQuestion) question).getChildElements()) {
            if (!answerSet.getAnswers(childQuestion.getId(), childQuestion.getUniqueId()).isEmpty()) {
                return true;
            }
        }
        return false;
    }
    return !answerSet.getAnswers(question.getId(), question.getUniqueId()).isEmpty();
}


@ExceptionHandler(MessageException.class)
public ModelAndView handleMessageException(Exception e,Locale locale,HttpServletRequest request){
    logger.error(e.getMessage(), e);
    ModelAndView model = new ModelAndView(Constants.VIEW_ERROR_GENERIC);
    model.addObject(Constants.MESSAGE, e.getMessage());
    model.addObject("contextpath", contextpath);
    return model;
}


public boolean archiveSurvey(Survey survey,User u){
    Archive archive = new Archive();
    archive.setArchived(new Date());
    archive.setCreated(survey.getCreated());
    String title = ConversionTools.removeHTML(survey.getTitle(), true).replace("\"", "'");
    if (title.length() > 250)
        title = title.substring(0, 250) + "...";
    archive.setSurveyTitle(title);
    archive.setSurveyUID(survey.getUniqueId());
    archive.setReplies(answerService.getNumberOfAnswerSetsPublished(survey.getShortname(), survey.getUniqueId()));
    archive.setSurveyHasUploadedFiles(survey.getHasUploadElement());
    archive.setSurveyShortname(survey.getShortname());
    archive.setOwner(survey.getOwner().getName());
    archive.setUserId(u.getId());
    StringBuilder langs = new StringBuilder();
    if (survey.getTranslations() != null) {
        for (String s : survey.getTranslations()) {
            langs.append(s);
        }
    }
    archive.setLanguages(langs.toString());
    archiveService.add(archive);
    if (useworkerserver.equalsIgnoreCase("true") && isworkerserver.equalsIgnoreCase("false")) {
        logger.info("calling worker server for archiving survey " + survey.getId());
        URL workerurl = new URL(workerserverurl + "worker/startArchive/" + archive.getId());
        try {
            URLConnection wc = workerurl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(wc.getInputStream()));
            String inputLine;
            StringBuilder result = new StringBuilder();
            while ((inputLine = in.readLine()) != null) result.append(inputLine);
            in.close();
            if (!result.toString().equals("OK")) {
                logger.error("calling worker server for archiving survey " + survey.getId() + " returned " + result);
                return false;
            }
            surveyService.removeFromSessionCache(survey);
            return true;
        } catch (ConnectException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
    }
    ArchiveExecutor export = (ArchiveExecutor) context.getBean("archiveExecutor");
    export.init(archive, survey, u);
    export.prepare();
    taskExecutorLong.execute(export);
    return true;
}


public boolean isByPassCaptcha(){
    return bypassCaptcha != null && bypassCaptcha.equalsIgnoreCase("true");
}


}