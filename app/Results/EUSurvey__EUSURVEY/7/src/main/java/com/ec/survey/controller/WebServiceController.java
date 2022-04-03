package com.ec.survey.controller;
 import com.ec.survey.exception.InvalidURLException;
import com.ec.survey.model;
import com.ec.survey.model.administration.LocalPrivilege;
import com.ec.survey.model.administration.User;
import com.ec.survey.model.attendees.Invitation;
import com.ec.survey.model.survey;
import com.ec.survey.model.survey.base.File;
import com.ec.survey.service;
import com.ec.survey.tools.Constants;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.Tools;
import com.ec.survey.tools.Ucs2Utf8;
import com.ec.survey.tools.export.XmlExportCreator;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util;
import java.util.Map.Entry;
import com.ec.survey.Interface.WebserviceService;
import com.ec.survey.Interface.PDFService;
import com.ec.survey.Interface.XmlExportCreator;
import com.ec.survey.DTO.Access;
import com.ec.survey.DTO.WebserviceTask;
import com.ec.survey.DTO.Archive;
import com.ec.survey.DTO.ParticipationGroup;
import com.ec.survey.DTO.Invitation;
import com.ec.survey.DTO.SurveyFilter;
import com.ec.survey.DTO.ArchiveFilter;
import com.ec.survey.DTO.Draft;
import com.ec.survey.DTO.AnswerSet;
import com.ec.survey.DTO.Answer;
import com.ec.survey.DTO.Element;
import com.ec.survey.DTO.ChoiceQuestion;
@Controller
@RequestMapping("/webservice")
public class WebServiceController extends BasicController{

@Resource(name = "webserviceService")
 private  WebserviceService webserviceService;

@Resource(name = "pdfService")
 private  PDFService pdfService;

@Resource(name = "xmlExportCreator")
 private  XmlExportCreator xmlExportCreator;

@Value("${webservice.maxrequestsperday}")
 private  String maxrequestsperday;

 private  String StandardDateString;


@RequestMapping(value = "/getSurveyPublicationStatus/{alias}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/plain;charset=UTF-8")
@ResponseBody
public String getSurveyPublicationStatus(String alias,HttpServletRequest request,HttpServletResponse response){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return getSurveyInfo("SurveyPublicationStatus", credentials.getKey(), credentials.getValue(), alias, request, response);
    }
    return "";
}


@ResponseBody
public String changeSurveyTitle(String login,String pass,String alias,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = getSurvey(alias, user, request, response, true, false);
        if (survey == null)
            return "";
        String newTitle = getBody(request, response);
        if (newTitle == null)
            return "";
        if (newTitle.length() == 0) {
            response.setStatus(412);
            return "";
        }
        survey.setTitle(newTitle);
        surveyService.update(survey, true);
        Translations translations = translationService.getTranslations(survey.getId(), survey.getLanguage().getCode());
        Translation translation = translations.getTranslationsByKey().get(Survey.TITLE);
        translation.setLabel(newTitle);
        translationService.update(translation);
        webserviceService.increaseServiceRequest(user.getId());
        response.setStatus(200);
        return "1";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@ResponseBody
public String removeUsefulLink(String login,String pass,String alias,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = getSurvey(alias, user, request, response, true, false);
        if (survey == null)
            return "";
        String label = request.getHeader(Constants.LABEL);
        if (label == null) {
            response.setStatus(412);
            return "";
        }
        for (String key : survey.getUsefulLinks().keySet()) {
            if (key.endsWith("#" + label)) {
                survey.getUsefulLinks().remove(key);
                surveyService.update(survey, true);
                webserviceService.increaseServiceRequest(user.getId());
                response.setStatus(200);
                return "";
            }
        }
        response.setStatus(412);
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
    }
    return "";
}


@RequestMapping(value = "/prepareResultsPDF/{formid}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/html")
@ResponseBody
public String prepareResultsPDFNoDates(String formid,HttpServletRequest request,HttpServletResponse response,Locale locale){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return createResultsPDF(credentials.getKey(), credentials.getValue(), formid, "0", "0", request, response, locale);
    }
    return "";
}


@ResponseBody
public String createResults(String login,String pass,String formid,String showids,String start,String end,HttpServletRequest request,HttpServletResponse response,Locale locale,int type,String contributionType,String filetypes){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = surveyService.getSurvey(formid, true, false, false, false, null, true, true, false, false);
        Survey publishedsurvey = survey == null ? null : surveyService.getSurvey(survey.getUniqueId(), false, false, false, false, null, true, false);
        if (survey == null || publishedsurvey == null || survey.getArchived()) {
            response.setStatus(412);
            return "";
        }
        boolean isAllowed = false;
        if (survey.getOwner().getId().equals(user.getId()) || user.getFormPrivilege() == 2) {
            isAllowed = true;
        } else {
            Access access = surveyService.getAccess(survey.getId(), user.getId());
            if (!(access == null || !access.hasAnyPrivileges() || access.getLocalPrivileges().get(LocalPrivilege.AccessResults) < 1)) {
                isAllowed = true;
            }
        }
        if (!isAllowed) {
            response.setStatus(403);
            return "";
        }
        if (contributionType == null || !(contributionType.equalsIgnoreCase("A") || contributionType.equalsIgnoreCase("N") || contributionType.equalsIgnoreCase("U"))) {
            response.setStatus(412);
            return "";
        }
        boolean addMeta = request.getParameter("meta") != null && request.getParameter("meta").equalsIgnoreCase("true");
        WebserviceTask task = new WebserviceTask(WebserviceTaskType.CreateResults);
        task.setSurveyId(publishedsurvey.getId());
        task.setSurveyUid(publishedsurvey.getUniqueId());
        task.setExportType(type);
        task.setFileTypes(filetypes);
        task.setContributionType(contributionType);
        if (!start.equalsIgnoreCase("0")) {
            try {
                task.setStart(Tools.parseDateString(start, StandardDateString));
            } catch (Exception pe) {
                logger.error(pe.getLocalizedMessage(), pe);
                response.setStatus(412);
                return "";
            }
        }
        if (!end.equalsIgnoreCase("0")) {
            try {
                Date enddate = Tools.parseDateString(end, StandardDateString);
                // the end filter was implemented for the ui and always adds one day,so we have
                // to patch the date
                // we also add one second so that 23:59:59 also includes the timespan between
                // that and 0:00 of the next day
                task.setEnd(Tools.addOneSecond(Tools.getPreviousDay(enddate)));
            } catch (Exception pe) {
                logger.error(pe.getLocalizedMessage(), pe);
                response.setStatus(412);
                return "";
            }
        }
        if (task.getStart() != null && task.getEnd() != null && task.getStart().after(Tools.getFollowingDay(task.getEnd()))) {
            response.setStatus(412);
            return "";
        }
        if (!showids.equalsIgnoreCase("true") && !showids.equalsIgnoreCase("false")) {
            response.setStatus(412);
            return "";
        }
        task.setShowIDs(showids != null && showids.equalsIgnoreCase("true"));
        task.setUser(user);
        task.setCreated(new Date());
        task.setAddMeta(addMeta);
        webserviceService.save(task);
        if (!webserviceService.startTask(task, locale)) {
            response.setStatus(500);
            return "";
        }
        webserviceService.increaseServiceRequest(user.getId());
        response.setStatus(201);
        return task.getId().toString();
    } catch (NumberFormatException e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@ResponseBody
public String restoreSurvey(String login,String pass,String alias,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Archive archive = archiveService.getArchive(user.getId(), alias);
        if (archive == null) {
            response.setStatus(412);
            return "";
        }
        if (!archive.getOwner().equals(user.getName())) {
            response.setStatus(403);
            return "";
        }
        archiveService.restore(archive, user, archive.getSurveyShortname());
        webserviceService.increaseServiceRequest(user.getId());
        response.setStatus(200);
        return "1";
    } catch (NumberFormatException e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@ResponseBody
public String deleteToken(String login,String pass,String groupid,String token,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        ParticipationGroup group = participationService.get(Integer.parseInt(groupid));
        if (group == null) {
            response.setStatus(412);
            return "";
        }
        Survey survey = surveyService.getSurvey(group.getSurveyId(), false, true);
        if (survey == null) {
            survey = surveyService.getSurveyByUniqueId(group.getSurveyUid(), false, true);
        }
        if (survey == null || survey.getArchived()) {
            response.setStatus(412);
            return "";
        }
        boolean isAllowed = false;
        if (survey.getOwner().getId().equals(user.getId()) || user.getFormPrivilege() == 2) {
            isAllowed = true;
        } else {
            Access access = surveyService.getAccess(survey.getId(), user.getId());
            if (!(access == null || !access.hasAnyPrivileges() || access.getLocalPrivileges().get(LocalPrivilege.FormManagement) < 2)) {
                isAllowed = true;
            }
        }
        if (!isAllowed) {
            response.setStatus(403);
            return "";
        }
        // delete invitation
        Invitation invitation = attendeeService.getInvitationByUniqueId(token);
        if (invitation != null && invitation.getParticipationGroupId().equals(group.getId())) {
            invitation.setDeactivated(false);
            attendeeService.delete(invitation);
            webserviceService.increaseServiceRequest(user.getId());
            response.setStatus(204);
            return "";
        } else {
            response.setStatus(412);
            return "";
        }
    } catch (NumberFormatException e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@ResponseBody
public String uploadBackgroundDocument(String login,String pass,String alias,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = getSurvey(alias, user, request, response, true, false);
        if (survey == null)
            return "";
        String label = request.getHeader(Constants.LABEL);
        if (label == null) {
            response.setStatus(412);
            return "";
        }
        String filename = request.getHeader("filename");
        if (filename == null) {
            response.setStatus(412);
            return "";
        }
        InputStream is = request.getInputStream();
        String uid = UUID.randomUUID().toString();
        java.io.File target = fileService.getSurveyFile(survey.getUniqueId(), uid);
        FileOutputStream fos = new FileOutputStream(target);
        IOUtils.copy(is, fos);
        fos.close();
        File f = new File();
        f.setUid(uid);
        f.setName(filename);
        fileService.add(f);
        survey.getBackgroundDocuments().put(label, servletContext.getContextPath() + "/files/" + survey.getUniqueId() + Constants.PATH_DELIMITER + uid);
        surveyService.update(survey, true, true, true, user.getId());
        webserviceService.increaseServiceRequest(user.getId());
        response.setStatus(200);
        return "1";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@RequestMapping(value = "/prepareResultsXML/{formid}/{showids}/{start}/{end}/{type}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/html")
@ResponseBody
public String prepareResultsXML(String formid,String showids,String start,String end,String type,HttpServletRequest request,HttpServletResponse response,Locale locale){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return createResults(credentials.getKey(), credentials.getValue(), formid, showids, start, end, request, response, locale, 1, type, "");
    }
    return "";
}


@ResponseBody
public String changeContact(String login,String pass,String alias,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = getSurvey(alias, user, request, response, true, false);
        if (survey == null)
            return "";
        String newContact = getBody(request, response);
        if (newContact == null)
            return "";
        if (newContact.length() == 0) {
            response.setStatus(412);
            return "";
        }
        survey.setContact(newContact);
        surveyService.update(survey, true);
        webserviceService.increaseServiceRequest(user.getId());
        response.setStatus(200);
        return "1";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@ResponseBody
public String getSurveyInfo(String type,String login,String pass,String alias,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = getSurvey(alias, user, request, response, true, true);
        if (survey == null)
            return "";
        switch(type) {
            case "SurveyPublicationStatus":
                response.setStatus(200);
                webserviceService.increaseServiceRequest(user.getId());
                return survey.getIsPublished() && survey.getIsActive() ? "1" : "0";
            case "SurveyMetaData":
                response.setStatus(200);
                webserviceService.increaseServiceRequest(user.getId());
                return surveyService.getSurveyMetaDataXML(survey);
            default:
                response.setStatus(412);
                break;
        }
        return "";
    } catch (NumberFormatException e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@RequestMapping(value = "/deleteOldExports", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/html")
@ResponseBody
public String deleteOldExports(HttpServletRequest request,HttpServletResponse response,Locale locale){
    User user = getUser(request, response, false);
    if (user != null) {
        if (user.getFormPrivilege() < 2) {
            response.setStatus(403);
            return "";
        }
        WebserviceTask task = new WebserviceTask(WebserviceTaskType.DeleteOldExports);
        task.setUser(user);
        task.setCreated(new Date());
        webserviceService.save(task);
        webserviceService.startTask(task, locale);
        response.setStatus(202);
        return task.getId().toString();
    }
    return "";
}


@ResponseBody
public String createAllResults(String login,String pass,String formid,String start,String end,String type,String xml,String pdf,String uploaded,HttpServletRequest request,HttpServletResponse response,Locale locale){
    String filetypes = "";
    if (xml != null && xml.equalsIgnoreCase("true")) {
        filetypes += "x";
    }
    if (pdf != null && pdf.equalsIgnoreCase("true")) {
        filetypes += "p";
    }
    if (uploaded != null && uploaded.equalsIgnoreCase("true")) {
        filetypes += "u";
    }
    return createResults(login, pass, formid, "false", start, end, request, response, locale, 3, "N", filetypes);
}


@ResponseBody
public String createResultsXML(String login,String pass,String formid,String showids,String start,String end,HttpServletRequest request,HttpServletResponse response,Locale locale){
    return createResults(login, pass, formid, showids, start, end, request, response, locale, 1, "N", "");
}


@ResponseBody
public String removeBackgroundDocument(String login,String pass,String alias,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = getSurvey(alias, user, request, response, true, false);
        if (survey == null)
            return "";
        String label = request.getHeader(Constants.LABEL);
        if (label == null) {
            response.setStatus(412);
            return "";
        }
        boolean found = false;
        String uid = null;
        for (String l : survey.getBackgroundDocuments().keySet()) {
            if (l.equalsIgnoreCase(label)) {
                found = true;
                uid = fileService.getFileUIDFromUrl(survey.getBackgroundDocuments().get(l));
                break;
            }
        }
        if (!found) {
            response.setStatus(412);
            return "";
        }
        survey.getBackgroundDocuments().remove(label);
        surveyService.update(survey, true);
        fileService.deleteIfNotReferenced(uid, survey.getUniqueId());
        webserviceService.increaseServiceRequest(user.getId());
        response.setStatus(200);
        return "1";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@ResponseBody
public String createResultsXMLNoDates(String login,String pass,String formid,String showids,HttpServletRequest request,HttpServletResponse response,Locale locale){
    return createResults(login, pass, formid, showids, "0", "0", request, response, locale);
}


@RequestMapping(value = "/getMySurveys", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = MediaType.APPLICATION_XML_VALUE)
@ResponseBody
public String getMySurveys(String surveyType,String published,String department,String creator,String privileged,String firstPublicationFrom,String firstPublicationTo,String createdFrom,String createdTo,String endFrom,String endTo,String archived,String archivedFrom,String archivedTo,String deleted,String deletedFrom,String deletedTo,String frozen,String minReported,String minContributions,String title,HttpServletRequest request,HttpServletResponse response){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return getMySurveysXml(surveyType, published, department, creator, privileged, firstPublicationFrom, firstPublicationTo, createdFrom, createdTo, endFrom, endTo, archived, archivedFrom, archivedTo, deleted, deletedFrom, deletedTo, frozen, minReported, minContributions, title, request, response);
    }
    return "";
}


@RequestMapping(value = "/prepareResults/{formid}/{showids}/{start}/{end}/{type}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/html")
@ResponseBody
public String prepareResults(String formid,String showids,String start,String end,String type,HttpServletRequest request,HttpServletResponse response,Locale locale){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return createResults(credentials.getKey(), credentials.getValue(), formid, showids, start, end, request, response, locale, 0, type, "");
    }
    return "";
}


public String getMySurveysXml(String surveyType,String published,String department,String creator,String privileged,String firstPublicationFrom,String firstPublicationTo,String createdFrom,String createdTo,String endFrom,String endTo,String archived,String archivedFrom,String archivedTo,String deleted,String deletedFrom,String deletedTo,String frozen,String minReported,String minContributions,String title,HttpServletRequest request,HttpServletResponse response){
    User user = getUser(request, response, true);
    if (user == null)
        return "";
    if (surveyType != null && surveyType.length() > 0) {
        switch(surveyType) {
            case "all":
                break;
            case "standard":
                break;
            case "quiz":
                break;
            case "brp":
                break;
            default:
                response.setStatus(412);
                return "";
        }
    }
    if (!is0or1(published) || !is0or1(creator) || !is0or1(privileged) || !is0or1(archived) || !is0or1(deleted) || !is0or1(frozen)) {
        response.setStatus(412);
        return "";
    }
    if (!is0orDate(firstPublicationFrom) || !is0orDate(firstPublicationTo) || !is0orDate(createdFrom) || !is0orDate(createdTo) || !is0orDate(endFrom) || !is0orDate(endTo) || !is0orDate(archivedFrom) || !is0orDate(archivedTo) || !is0orDate(deletedFrom) || !is0orDate(deletedTo)) {
        response.setStatus(412);
        return "";
    }
    if (minReported != null && !Tools.isInteger(minReported)) {
        response.setStatus(412);
        return "";
    }
    if (minContributions != null && !Tools.isInteger(minContributions)) {
        response.setStatus(412);
        return "";
    }
    webserviceService.increaseServiceRequest(user.getId());
    response.setStatus(200);
    try {
        SurveyFilter filter = new SurveyFilter();
        ArchiveFilter archiveFilter = null;
        filter.setUser(user);
        filter.setUserDepartment(department);
        filter.setType(surveyType);
        filter.setTitle(title);
        if (creator != null && creator.equalsIgnoreCase("1") && (privileged == null || privileged.equalsIgnoreCase("0"))) {
            filter.setSelector("my");
        } else if ((creator == null || creator.equalsIgnoreCase("0")) && privileged != null && privileged.equalsIgnoreCase("1")) {
            filter.setSelector("shared");
        }
        filter.setFirstPublishedFrom(getDate(firstPublicationFrom));
        filter.setFirstPublishedTo(getDate(firstPublicationTo));
        filter.setGeneratedFrom(getDate(createdFrom));
        filter.setGeneratedTo(getDate(createdTo));
        filter.setEndFrom(getDate(endFrom));
        filter.setEndTo(getDate(endTo));
        if (published != null) {
            filter.setStatus(published.equalsIgnoreCase("1") ? "Published;" : "Unpublished;");
        }
        if (archived != null && archived.equalsIgnoreCase("1")) {
            archiveFilter = new ArchiveFilter();
            archiveFilter.setArchivedFrom(getDate(archivedFrom));
            archiveFilter.setArchivedTo(getDate(archivedTo));
            archiveFilter.setCreatedFrom(getDate(createdFrom));
            archiveFilter.setCreatedTo(getDate(createdTo));
            archiveFilter.setTitle(title);
            archiveFilter.setOwner(user.getLogin());
        }
        if (deleted != null) {
            filter.setDeleted(deleted.equalsIgnoreCase("1"));
            if (deleted.equalsIgnoreCase("1")) {
                filter.setDeletedFrom(getDate(deletedFrom));
                filter.setDeletedTo(getDate(deletedTo));
            }
        }
        if (frozen != null) {
            filter.setFrozen(frozen.equalsIgnoreCase("1"));
        }
        if (minReported != null && !minReported.equalsIgnoreCase("0")) {
            filter.setMinReported(Integer.parseInt(minReported));
        }
        if (minContributions != null && !minContributions.equalsIgnoreCase("0")) {
            filter.setMinContributions(Integer.parseInt(minContributions));
        }
        response.setContentType("text/xml");
        return surveyService.getMySurveysXML(filter, archiveFilter);
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@ResponseBody
public String deleteSurvey(String login,String pass,String alias,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = getSurvey(alias, user, request, response, true, false);
        if (survey == null)
            return "";
        surveyService.delete(survey.getId(), false, false);
        webserviceService.increaseServiceRequest(user.getId());
        response.setStatus(200);
        return "1";
    } catch (NumberFormatException e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@RequestMapping(value = "/prepareResultsPDF/{formid}/{start}/{end}/{type}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/html")
@ResponseBody
public String prepareResultsPDF(String formid,String start,String end,String type,HttpServletRequest request,HttpServletResponse response,Locale locale){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return createResults(credentials.getKey(), credentials.getValue(), formid, "false", start, end, request, response, locale, 2, type, "");
    }
    return "";
}


@RequestMapping(value = "/prefill/{shortname}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/html")
@ResponseBody
public String prefill(String shortname,HttpServletRequest request,HttpServletResponse response){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials == null)
        return "";
    User user = getUser(request, response, false);
    if (user == null)
        return "";
    Survey survey = getSurvey(shortname, user, request, response, false, false);
    if (survey == null)
        return "";
    String token = null;
    Map<String, String> values = new HashMap<>();
    HashMap<String, String[]> parameters = new HashMap<>();
    @SuppressWarnings("rawtypes")
    Enumeration en = request.getParameterNames();
    String re = "\\p{C}";
    while (en.hasMoreElements()) {
        String param = (String) en.nextElement();
        String[] values1 = request.getParameterValues(param);
        for (int i = 0; i < values1.length; i++) {
            // convert and replace invalid characters
            String val = Ucs2Utf8.unconvert(values1[i]).replaceAll(re, "");
            values1[i] = val;
        }
        parameters.put(param, values1);
    }
    for (Entry<String, String[]> entry : parameters.entrySet()) {
        if (entry.getKey().equalsIgnoreCase("token")) {
            token = entry.getValue()[0];
        } else {
            if (!values.containsKey(entry.getKey())) {
                values.put(entry.getKey(), entry.getValue()[0]);
            } else {
                response.setStatus(412);
                return "";
            }
        }
    }
    if (token == null) {
        response.setStatus(412);
        return "";
    }
    Invitation invitation = null;
    try {
        invitation = attendeeService.getInvitationByUniqueId(token);
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    }
    Map<Integer, Element> matrixQuestions = survey.getMatrixMap();
    Map<String, Element> matrixQuestionsByAlias = survey.getMatrixMapByAlias();
    if (invitation != null) {
        Draft draft = answerService.getDraftByAnswerUID(token);
        if (draft != null) {
            response.setStatus(412);
            return "";
        }
        draft = new Draft();
        draft.setUniqueId(token);
        AnswerSet answerSet = new AnswerSet();
        answerSet.setUpdateDate(new Date());
        answerSet.setLanguageCode(survey.getLanguage().getCode());
        answerSet.setDate(answerSet.getUpdateDate());
        answerSet.setSurvey(survey);
        answerSet.setSurveyId(survey.getId());
        answerSet.setUniqueCode(token);
        answerSet.setIsDraft(true);
        draft.setAnswerSet(answerSet);
        draft.getAnswerSet().setInvitationId(invitation.getUniqueId());
        Map<String, Element> elementsByAlias = survey.getElementsByAlias();
        for (Entry<String, String> entry : values.entrySet()) {
            String questionalias = entry.getKey();
            if (elementsByAlias.containsKey(questionalias)) {
                Element question = elementsByAlias.get(questionalias);
                if (question instanceof FreeTextQuestion || question instanceof EmailQuestion || question instanceof NumberQuestion || question instanceof RegExQuestion) {
                    Answer answer = new Answer();
                    answer.setAnswerSet(answerSet);
                    answer.setQuestionId(question.getId());
                    answer.setQuestionUniqueId(question.getUniqueId());
                    answer.setValue(entry.getValue());
                    answerSet.addAnswer(answer);
                } else if (question instanceof DateQuestion) {
                    String dateval = entry.getValue();
                    Date date = Tools.parseDateString(dateval, ConversionTools.DateFormat);
                    if (date == null) {
                        response.setStatus(412);
                        return "";
                    }
                    Answer answer = new Answer();
                    answer.setAnswerSet(answerSet);
                    answer.setQuestionId(question.getId());
                    answer.setQuestionUniqueId(question.getUniqueId());
                    answer.setValue(dateval);
                    answerSet.addAnswer(answer);
                } else if (question instanceof TimeQuestion) {
                    String timeval = values.get(questionalias);
                    if (!Tools.isTimeString(timeval)) {
                        response.setStatus(412);
                    }
                    Answer answer = new Answer();
                    answer.setAnswerSet(answerSet);
                    answer.setQuestionId(question.getId());
                    answer.setQuestionUniqueId(question.getUniqueId());
                    answer.setValue(timeval);
                    answerSet.addAnswer(answer);
                } else if (question instanceof ChoiceQuestion) {
                    String[] arrvalues = entry.getValue().split(",");
                    for (String alias : arrvalues) {
                        Integer paid = elementsByAlias.get(alias).getId();
                        Answer answer = new Answer();
                        answer.setAnswerSet(answerSet);
                        answer.setQuestionId(question.getId());
                        answer.setQuestionUniqueId(question.getUniqueId());
                        answer.setValue(paid.toString());
                        answer.setPossibleAnswerId(paid);
                        ChoiceQuestion cq = (ChoiceQuestion) question;
                        answer.setPossibleAnswerUniqueId(cq.getPossibleAnswer(paid).getUniqueId());
                        answerSet.addAnswer(answer);
                    }
                } else if (question instanceof GalleryQuestion) {
                    String[] arrvalues = entry.getValue().split(",");
                    for (String value : arrvalues) {
                        Answer answer = new Answer();
                        answer.setAnswerSet(answerSet);
                        answer.setQuestionId(question.getId());
                        answer.setQuestionUniqueId(question.getUniqueId());
                        answer.setValue(value);
                        answerSet.addAnswer(answer);
                    }
                } else {
                    // this is a matrix
                    if (matrixQuestionsByAlias.containsKey(questionalias)) {
                        // a matrix question
                        String[] arrvalues = entry.getValue().split(",");
                        for (String alias : arrvalues) {
                            Integer paid = elementsByAlias.get(alias).getId();
                            Answer answer = new Answer();
                            answer.setAnswerSet(answerSet);
                            answer.setQuestionId(matrixQuestionsByAlias.get(questionalias).getId());
                            answer.setQuestionUniqueId(matrixQuestionsByAlias.get(questionalias).getUniqueId());
                            answer.setValue(paid.toString());
                            answer.setPossibleAnswerId(paid);
                            Element pa = matrixQuestions.get(paid);
                            if (pa != null) {
                                answer.setPossibleAnswerUniqueId(pa.getUniqueId());
                            }
                            answerSet.addAnswer(answer);
                        }
                    }
                }
            } else if (questionalias.contains("#")) {
                // this is a table
                String[] data = questionalias.split("#");
                int row = Integer.parseInt(data[1]);
                int col = Integer.parseInt(data[2]);
                Table table = (Table) elementsByAlias.get(data[0]);
                Answer answer = new Answer();
                answer.setAnswerSet(answerSet);
                answer.setQuestionId(table.getId());
                answer.setQuestionUniqueId(table.getUniqueId());
                Element tablequestion = table.getQuestions().get(row - 1);
                Element tableanswer = table.getAnswers().get(col - 1);
                answer.setPossibleAnswerUniqueId(tablequestion.getUniqueId() + "#" + tableanswer.getUniqueId());
                answer.setValue(entry.getValue());
                answer.setRow(row);
                answer.setColumn(col);
                answerSet.addAnswer(answer);
            }
        }
        try {
            answerService.saveDraft(draft);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            response.setStatus(412);
            return "";
        }
        response.setStatus(204);
        return "";
    } else {
        response.setStatus(412);
        return "";
    }
}


public Survey getSurvey(String alias,User user,HttpServletRequest request,HttpServletResponse response,boolean draft,boolean readonly){
    Survey survey;
    try {
        survey = surveyService.getSurveyByShortname(alias, draft, null, request, false, false, true, false);
    } catch (InvalidURLException e) {
        survey = null;
    }
    if (survey == null || survey.getArchived()) {
        response.setStatus(412);
        return null;
    }
    if (draft) {
        boolean isAllowed = false;
        if (survey.getOwner().getId().equals(user.getId()) || user.getFormPrivilege() == 2) {
            isAllowed = true;
        } else {
            Access access = surveyService.getAccess(survey.getId(), user.getId());
            if (access != null && access.hasAnyPrivileges()) {
                if (readonly && access.getLocalPrivileges().get(LocalPrivilege.FormManagement) > 0) {
                    isAllowed = true;
                } else if (access.getLocalPrivileges().get(LocalPrivilege.FormManagement) > 1) {
                    isAllowed = true;
                }
            }
        }
        if (!isAllowed) {
            response.setStatus(403);
            return null;
        }
    }
    return survey;
}


@RequestMapping(value = "/prepareResults/{formid}/{showids}/{start}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/html")
@ResponseBody
public String prepareResultsStartOnly(String formid,String showids,String start,HttpServletRequest request,HttpServletResponse response,Locale locale){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return createResultsStartOnly(credentials.getKey(), credentials.getValue(), formid, showids, start, request, response, locale);
    }
    return "";
}


@ResponseBody
public String activateToken(String login,String pass,String groupid,String token,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        ParticipationGroup group = participationService.get(Integer.parseInt(groupid));
        if (group == null) {
            response.setStatus(412);
            return "";
        }
        Survey survey = surveyService.getSurvey(group.getSurveyId(), false, true);
        if (survey == null) {
            survey = surveyService.getSurveyByUniqueId(group.getSurveyUid(), false, true);
        }
        if (survey == null || survey.getArchived()) {
            response.setStatus(412);
            return "";
        }
        boolean isAllowed = false;
        if (survey.getOwner().getId().equals(user.getId()) || user.getFormPrivilege() == 2) {
            isAllowed = true;
        } else {
            Access access = surveyService.getAccess(survey.getId(), user.getId());
            if (!(access == null || !access.hasAnyPrivileges() || access.getLocalPrivileges().get(LocalPrivilege.FormManagement) < 2)) {
                isAllowed = true;
            }
        }
        if (!isAllowed) {
            response.setStatus(403);
            return "";
        }
        // delete invitation
        Invitation invitation = attendeeService.getInvitationByUniqueId(token);
        if (invitation != null && invitation.getParticipationGroupId().equals(group.getId())) {
            invitation.setDeactivated(false);
            attendeeService.update(invitation);
            webserviceService.increaseServiceRequest(user.getId());
            response.setStatus(204);
            return "";
        } else {
            response.setStatus(412);
            return "";
        }
    } catch (NumberFormatException e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@ResponseBody
public String createResultsNoDates(String login,String pass,String formid,String showids,HttpServletRequest request,HttpServletResponse response,Locale locale){
    return createResults(login, pass, formid, showids, "0", "0", request, response, locale);
}


public boolean is0or1(String input){
    return input == null || input.trim().equals("0") || input.trim().equals("1");
}


@ResponseBody
public String createResultsXMLStart(String login,String pass,String formid,String showids,String start,HttpServletRequest request,HttpServletResponse response,Locale locale){
    return createResults(login, pass, formid, showids, start, "0", request, response, locale, 1, "N", "");
}


@ResponseBody
public String getSurveyPDF(String login,String pass,String alias,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = getSurvey(alias, user, request, response, true, false);
        if (survey == null)
            return "";
        Survey published = surveyService.getSurvey(alias, false, false, false, false, null, true, false);
        java.io.File file = null;
        if (published != null) {
            file = pdfService.createSurveyPDF(published, published.getLanguage().getCode(), new java.io.File(archiveFileDir + published.getUniqueId() + ".pdf"));
        } else {
            file = pdfService.createSurveyPDF(survey, survey.getLanguage().getCode(), new java.io.File(archiveFileDir + survey.getUniqueId() + ".pdf"));
        }
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        if (file.getName().endsWith("pdf")) {
            response.setContentType("application/pdf");
        }
        FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
        webserviceService.increaseServiceRequest(user.getId());
        response.setStatus(200);
        return null;
    } catch (NumberFormatException e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@RequestMapping(value = "/prepareResultsXML/{formid}/{showids}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/html")
@ResponseBody
public String prepareResultsXMLNoDates(String formid,String showids,HttpServletRequest request,HttpServletResponse response,Locale locale){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return createResultsXML(credentials.getKey(), credentials.getValue(), formid, showids, "0", "0", request, response, locale);
    }
    return "";
}


@RequestMapping(value = "/prepareResultsPDF/{formid}/{start}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/html")
@ResponseBody
public String prepareResultsPDFNoStart(String formid,String start,HttpServletRequest request,HttpServletResponse response,Locale locale){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return createResultsPDF(credentials.getKey(), credentials.getValue(), formid, start, "0", request, response, locale);
    }
    return "";
}


@ResponseBody
public String archiveSurvey(String login,String pass,String alias,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = getSurvey(alias, user, request, response, true, false);
        if (survey == null)
            return "";
        if (!archiveSurvey(survey, user)) {
            response.setStatus(500);
            return "";
        }
        webserviceService.increaseServiceRequest(user.getId());
        response.setStatus(200);
        return "1";
    } catch (NumberFormatException e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@ResponseBody
public String createTokens(String login,String pass,String groupid,String number,HttpServletRequest request,HttpServletResponse response,Locale locale){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        ParticipationGroup group = participationService.get(Integer.parseInt(groupid));
        if (group == null) {
            response.setStatus(412);
            return "";
        }
        Survey survey = surveyService.getSurvey(group.getSurveyId(), false, true);
        if (survey == null) {
            survey = surveyService.getSurveyByUniqueId(group.getSurveyUid(), false, true);
        }
        if (survey == null || survey.getArchived()) {
            response.setStatus(412);
            return "";
        }
        boolean isAllowed = false;
        if (survey.getOwner().getId().equals(user.getId()) || user.getFormPrivilege() == 2) {
            isAllowed = true;
        } else {
            Access access = surveyService.getAccess(survey.getId(), user.getId());
            if (!(access == null || !access.hasAnyPrivileges() || access.getLocalPrivileges().get(LocalPrivilege.FormManagement) < 2)) {
                isAllowed = true;
            }
        }
        if (!isAllowed) {
            response.setStatus(403);
            return "";
        }
        int num = Integer.parseInt(number);
        if (num > 100000 || num < 1) {
            response.setStatus(416);
            return "";
        }
        // check that with the new tokens the limit of 1 million tokens per guestlist is
        // not violated
        int currentTokens = participationService.getInvitationCount(group.getId());
        int waitingTokens = webserviceService.getWaitingTokens(group);
        if (currentTokens + waitingTokens + num > 1000000) {
            response.setStatus(416);
            return "";
        }
        WebserviceTask task = new WebserviceTask(WebserviceTaskType.CreateTokens);
        task.setSurveyUid(survey.getUniqueId());
        task.setGroupId(Integer.parseInt(groupid));
        task.setNumber(num);
        task.setUser(user);
        task.setCreated(new Date());
        webserviceService.save(task);
        webserviceService.startTask(task, locale);
        webserviceService.increaseServiceRequest(user.getId());
        response.setStatus(202);
        return task.getId().toString();
    } catch (NumberFormatException e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


public boolean is0orDate(String input){
    if (input == null || input.equalsIgnoreCase("0"))
        return true;
    try {
        Tools.parseDateString(input, StandardDateString);
        return true;
    } catch (Exception pe) {
    // ignore
    }
    return false;
}


public User getUser(HttpServletRequest request,HttpServletResponse response,boolean checkrequests){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        User user = null;
        try {
            user = administrationService.getUserForLogin(credentials.getKey(), false);
        } catch (Exception e) {
            response.setStatus(403);
            return null;
        }
        if (administrationService.checkUserPassword(user, credentials.getValue())) {
        // md5 hash replaced
        } else if (!Tools.isPasswordValid(user.getPassword(), credentials.getValue() + user.getPasswordSalt())) {
            response.setStatus(403);
            return null;
        }
        if (!user.getValidated()) {
            response.setStatus(403);
            return null;
        }
        if (checkrequests) {
            ServiceRequest serviceRequest = webserviceService.getServiceRequest(user.getId());
            if (serviceRequest != null && Tools.isToday(serviceRequest.getDate()) && serviceRequest.getCounter() > Integer.parseInt(maxrequestsperday)) {
                response.setStatus(429);
                return null;
            }
        }
        return user;
    }
    return null;
}


@ResponseBody
public String deactivateToken(String login,String pass,String groupid,String token,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        ParticipationGroup group = participationService.get(Integer.parseInt(groupid));
        if (group == null) {
            response.setStatus(412);
            return "";
        }
        Survey survey = surveyService.getSurvey(group.getSurveyId(), false, true);
        if (survey == null) {
            survey = surveyService.getSurveyByUniqueId(group.getSurveyUid(), false, true);
        }
        if (survey == null || survey.getArchived()) {
            response.setStatus(412);
            return "";
        }
        boolean isAllowed = false;
        if (survey.getOwner().getId().equals(user.getId()) || user.getFormPrivilege() == 2) {
            isAllowed = true;
        } else {
            Access access = surveyService.getAccess(survey.getId(), user.getId());
            if (!(access == null || !access.hasAnyPrivileges() || access.getLocalPrivileges().get(LocalPrivilege.FormManagement) < 2)) {
                isAllowed = true;
            }
        }
        if (!isAllowed) {
            response.setStatus(403);
            return "";
        }
        // delete invitation
        Invitation invitation = attendeeService.getInvitationByUniqueId(token);
        if (invitation == null) {
            response.setStatus(412);
            return "";
        }
        if (invitation.getParticipationGroupId().equals(group.getId())) {
            invitation.setDeactivated(true);
            attendeeService.update(invitation);
            webserviceService.increaseServiceRequest(user.getId());
            response.setStatus(200);
            return "";
        } else {
            response.setStatus(412);
            return "";
        }
    } catch (NumberFormatException e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@ResponseBody
public String createResultsStartOnly(String login,String pass,String formid,String showids,String start,HttpServletRequest request,HttpServletResponse response,Locale locale){
    return createResults(login, pass, formid, showids, start, "0", request, response, locale);
}


public KeyValue getLoginAndPassword(HttpServletRequest request,HttpServletResponse response){
    String line = request.getHeader("Authorization");
    if (line != null && line.startsWith("Basic")) {
        String encoded = line.substring("Basic ".length());
        byte[] unencoded = Base64.decodeBase64(encoded.getBytes());
        String credentials = StringUtils.newStringUtf8(unencoded);
        String login = credentials.substring(0, credentials.indexOf(':'));
        String password = credentials.substring(login.length() + 1);
        return new KeyValue(login, password);
    }
    response.setStatus(401);
    response.setHeader("WWW-Authenticate", "Basic realm=\"Please enter your credentials\"");
    return null;
}


@RequestMapping(value = "/prepareResults/{formid}/{showids}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/html")
@ResponseBody
public String prepareResultsNoDates(String formid,String showids,HttpServletRequest request,HttpServletResponse response,Locale locale){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return createResultsNoDates(credentials.getKey(), credentials.getValue(), formid, showids, request, response, locale);
    }
    return "";
}


@ResponseBody
public String createResultFromToken(String login,String pass,String formid,String token,String showids,HttpServletRequest request,HttpServletResponse response,Locale locale){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = surveyService.getSurvey(formid, true, false, false, false, null, true, false);
        Survey publishedsurvey = surveyService.getSurvey(formid, false, false, false, false, null, true, false);
        if (survey == null || publishedsurvey == null || survey.getArchived()) {
            response.setStatus(412);
            return "";
        }
        boolean isAllowed = false;
        if (survey.getOwner().getId().equals(user.getId()) || user.getFormPrivilege() == 2) {
            isAllowed = true;
        } else {
            Access access = surveyService.getAccess(survey.getId(), user.getId());
            if (!(access == null || !access.hasAnyPrivileges() || access.getLocalPrivileges().get(LocalPrivilege.AccessResults) < 1)) {
                isAllowed = true;
            }
        }
        if (!isAllowed) {
            response.setStatus(403);
            return "";
        }
        if (!showids.equalsIgnoreCase("true") && !showids.equalsIgnoreCase("false")) {
            response.setStatus(412);
            return "";
        }
        AnswerSet answerSet = answerService.getByInvitationCode(token);
        if (answerSet == null) {
            response.setStatus(412);
            return "";
        }
        WebserviceTask task = new WebserviceTask(WebserviceTaskType.CreateResult);
        task.setSurveyId(publishedsurvey.getId());
        task.setSurveyUid(publishedsurvey.getUniqueId());
        task.setToken(token);
        task.setShowIDs(showids.equalsIgnoreCase("true"));
        task.setUser(user);
        task.setCreated(new Date());
        webserviceService.save(task);
        webserviceService.startTask(task, locale);
        webserviceService.increaseServiceRequest(user.getId());
        response.setStatus(202);
        return task.getId().toString();
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@ResponseBody
public String createResultsPDFNoStart(String login,String pass,String formid,String start,HttpServletRequest request,HttpServletResponse response,Locale locale){
    return createResults(login, pass, formid, "false", start, "0", request, response, locale, 2, "N", "");
}


@ResponseBody
public String createResultsPDFNoDates(String login,String pass,String formid,HttpServletRequest request,HttpServletResponse response,Locale locale){
    return createResults(login, pass, formid, "false", "0", "0", request, response, locale, 2, "N", "");
}


@ResponseBody
public String createNewTokenList(String login,String pass,String shortname,String active,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = getSurvey(shortname, user, request, response, true, false);
        if (survey == null)
            return "";
        if (!active.equalsIgnoreCase("true") && !active.equalsIgnoreCase("false")) {
            response.setStatus(412);
            return "";
        }
        ParticipationGroup group = new ParticipationGroup(survey.getUniqueId());
        group.setActive(active.equalsIgnoreCase("true"));
        group.setOwnerId(user.getId());
        group.setName("remote" + Tools.formatDate(new Date(), "MM/dd/yyyy HH:mm:ss"));
        group.setSurveyId(survey.getId());
        group.setType(ParticipationGroupType.Token);
        participationService.save(group);
        group.setName("remote " + group.getId());
        participationService.save(group);
        webserviceService.increaseServiceRequest(user.getId());
        response.setStatus(201);
        return group.getId().toString();
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


public Date getDate(String input){
    if (input != null && !input.equalsIgnoreCase("0")) {
        return Tools.parseDateString(input, StandardDateString);
    }
    return null;
}


@ResponseBody
public String publishSurvey(String login,String pass,String alias,HttpServletRequest request,HttpServletResponse response,String start,String end){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = getSurvey(alias, user, request, response, true, false);
        if (survey == null)
            return "";
        Date startdate = null;
        if (start != null) {
            try {
                startdate = Tools.parseDateString(start, StandardDateString);
            } catch (Exception pe) {
                logger.error(pe.getLocalizedMessage(), pe);
                response.setStatus(412);
                return "";
            }
        }
        Date enddate = null;
        if (end != null) {
            try {
                enddate = Tools.parseDateString(end, StandardDateString);
            } catch (Exception pe) {
                logger.error(pe.getLocalizedMessage(), pe);
                response.setStatus(412);
                return "";
            }
        }
        if (survey.getIsPublished() && survey.getIsActive()) {
            response.setStatus(412);
            return "0";
        }
        if (startdate == null && enddate == null) {
            response.setStatus(200);
            Survey published = surveyService.getSurvey(survey.getShortname(), false, false, false, false, null, true, false);
            if (published != null) {
                try {
                    surveyService.applyChanges(survey, true, user.getId(), false);
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            } else {
                surveyService.publish(survey, -1, -1, true, user.getId(), false, false);
            }
            surveyService.activate(survey, true, user.getId());
            webserviceService.increaseServiceRequest(user.getId());
            return "1";
        } else if (startdate != null && enddate != null) {
            if (startdate.after(enddate)) {
                response.setStatus(412);
                return "";
            }
            survey.setStart(startdate);
            survey.setEnd(enddate);
            survey.setAutomaticPublishing(true);
            if (!survey.getIsActive() && survey.getStart().before(new Date())) {
                if (survey.getEnd().after(new Date())) {
                    if (!survey.getIsPublished()) {
                        surveyService.publish(survey, -1, -1, false, user.getId(), false, false);
                    }
                    survey.setIsActive(true);
                    survey.setIsPublished(true);
                }
            } else if (survey.getIsActive() && survey.getStart().after(new Date())) {
                survey.setIsActive(false);
            }
            surveyService.update(survey, true);
            webserviceService.increaseServiceRequest(user.getId());
            response.setStatus(200);
            return "1";
        } else {
            response.setStatus(412);
        }
        return "";
    } catch (NumberFormatException e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@ResponseBody
public String contributionToDraft(String login,String pass,String code,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        AnswerSet answerSet = answerService.get(code);
        if (answerSet == null) {
            response.setStatus(412);
            return "";
        }
        boolean isAllowed = false;
        if (answerSet.getSurvey().getOwner().getId().equals(user.getId()) || user.getFormPrivilege() == 2) {
            isAllowed = true;
        } else {
            Access access = surveyService.getAccess(answerSet.getSurvey().getId(), user.getId());
            if (!(access == null || !access.hasAnyPrivileges() || access.getLocalPrivileges().get(LocalPrivilege.AccessResults) < 2)) {
                isAllowed = true;
            }
        }
        if (!isAllowed) {
            response.setStatus(403);
            return null;
        }
        String uid = answerService.resetContribution(code);
        if (uid == null) {
            response.setStatus(500);
            return "";
        }
        webserviceService.increaseServiceRequest(user.getId());
        response.setStatus(200);
        return uid;
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@RequestMapping(value = "/getSurveyMetadata/{alias}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/xml;charset=UTF-8")
@ResponseBody
public String getSurveyMetadata(String alias,HttpServletRequest request,HttpServletResponse response){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return getSurveyInfo("SurveyMetaData", credentials.getKey(), credentials.getValue(), alias, request, response);
    }
    return "";
}


public String getBody(HttpServletRequest request,HttpServletResponse response){
    StringBuilder jb = new StringBuilder();
    String line = null;
    try {
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) jb.append(line);
    } catch (Exception e) {
        response.setStatus(500);
        return null;
    }
    return jb.toString();
}


@ResponseBody
public String applyChanges(String login,String pass,String alias,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = getSurvey(alias, user, request, response, true, false);
        if (survey == null)
            return "";
        surveyService.applyChanges(survey, false, user.getId(), false);
        response.setStatus(200);
        webserviceService.increaseServiceRequest(user.getId());
        return "1";
    } catch (NumberFormatException e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@RequestMapping(value = "/prepareResultsXML/{formid}/{showids}/{start}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/html")
@ResponseBody
public String prepareResultsXMLStart(String formid,String showids,String start,HttpServletRequest request,HttpServletResponse response,Locale locale){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return createResultsXML(credentials.getKey(), credentials.getValue(), formid, showids, start, "0", request, response, locale);
    }
    return "";
}


@RequestMapping(value = "/publishSurvey/{alias}/{start}/{end}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/html")
@ResponseBody
public String publishSurveyStart(String alias,String start,String end,HttpServletRequest request,HttpServletResponse response){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return publishSurvey(credentials.getKey(), credentials.getValue(), alias, request, response, start, end);
    }
    return "";
}


@ResponseBody
public String getResults(String login,String pass,String taskid,HttpServletRequest request,HttpServletResponse response){
    try {
        response.setContentType("application/xml");
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        WebserviceTask task = webserviceService.get(Integer.parseInt(taskid));
        if (task == null) {
            response.setStatus(412);
            return "";
        }
        if (!task.getUser().getId().equals(user.getId())) {
            response.setStatus(403);
            return "";
        }
        if (task.isDone()) {
            if (task.getResult() == null && task.getError() != null) {
                logger.error(task.getError());
                response.setStatus(500);
                return "";
            }
            String noEmptyResults = request.getParameter("noempty");
            if (noEmptyResults != null && noEmptyResults.equalsIgnoreCase("true") && task.isEmpty()) {
                response.setStatus(200);
                return "";
            }
            File file = fileService.get(task.getResult());
            java.io.File f = null;
            if (task.getSurveyUid() != null) {
                f = fileService.getSurveyExportFile(task.getSurveyUid(), file.getUid());
            } else {
                f = fileService.getUsersFile(user.getId(), file.getUid());
            }
            response.setContentLength((int) f.length());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            if (file.getName().endsWith("pdf")) {
                response.setContentType("application/xml");
            } else if (file.getName().endsWith("xls")) {
                response.setContentType("application/msexcel");
            } else if (file.getName().endsWith("zip")) {
                response.setContentType("application/zip");
            }
            FileCopyUtils.copy(new FileInputStream(f), response.getOutputStream());
            response.setStatus(200);
            return null;
        } else {
            response.setStatus(204);
            return "";
        }
    } catch (NumberFormatException e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@ResponseBody
public String unpublishSurvey(String login,String pass,String alias,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = getSurvey(alias, user, request, response, true, false);
        if (survey == null)
            return "";
        if (!(survey.getIsPublished() && survey.getIsActive())) {
            response.setStatus(412);
            return "0";
        }
        response.setStatus(200);
        surveyService.unpublish(survey, true, user.getId(), false);
        webserviceService.increaseServiceRequest(user.getId());
        return "1";
    } catch (NumberFormatException e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(412);
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@RequestMapping(value = "/prepareResultFromToken/{formid}/{token}/{showids}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/html")
@ResponseBody
public String prepareResultFromToken(String formid,String token,String showids,HttpServletRequest request,HttpServletResponse response,Locale locale){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return createResultFromToken(credentials.getKey(), credentials.getValue(), formid, token, showids, request, response, locale);
    }
    return "";
}


@RequestMapping(value = "/prepareAllResults/{formid}/{start}/{end}/{type}/{xml}/{pdf}/{uploaded}", method = { RequestMethod.GET, RequestMethod.HEAD }, produces = "text/html")
@ResponseBody
public String prepareAllResults(String formid,String start,String end,String type,String xml,String pdf,String uploaded,HttpServletRequest request,HttpServletResponse response,Locale locale){
    KeyValue credentials = getLoginAndPassword(request, response);
    if (credentials != null) {
        return createAllResults(credentials.getKey(), credentials.getValue(), formid, start, end, type, xml, pdf, uploaded, request, response, locale);
    }
    return "";
}


@ResponseBody
public String addUsefulLink(String login,String pass,String alias,HttpServletRequest request,HttpServletResponse response){
    try {
        User user = getUser(request, response, true);
        if (user == null)
            return "";
        Survey survey = getSurvey(alias, user, request, response, true, false);
        if (survey == null)
            return "";
        String label = request.getHeader(Constants.LABEL);
        if (label == null) {
            response.setStatus(412);
            return "";
        }
        String url = request.getHeader("url");
        if (url == null) {
            response.setStatus(412);
            return "";
        }
        survey.getUsefulLinks().put(survey.getUsefulLinks().size() + "#" + label, url);
        surveyService.update(survey, true, true, true, user.getId());
        response.setStatus(200);
        webserviceService.increaseServiceRequest(user.getId());
        return "";
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
        response.setStatus(500);
        return "";
    }
}


@ResponseBody
public String createResultsPDF(String login,String pass,String formid,String start,String end,HttpServletRequest request,HttpServletResponse response,Locale locale){
    return createResults(login, pass, formid, "false", start, end, request, response, locale, 2, "N", "");
}


}