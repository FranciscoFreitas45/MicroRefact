package com.ec.survey.DTO;
 import com.ec.survey.exception.ForbiddenException;
import com.ec.survey.exception.ForbiddenURLException;
import com.ec.survey.exception.InternalServerErrorException;
import com.ec.survey.exception.InvalidURLException;
import com.ec.survey.exception.NoFormLoadedException;
import com.ec.survey.model;
import com.ec.survey.model.administration.GlobalPrivilege;
import com.ec.survey.model.administration.LocalPrivilege;
import com.ec.survey.model.administration.User;
import com.ec.survey.model.survey.Element;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.tools.Constants;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.NotAgreedToPsException;
import com.ec.survey.tools.NotAgreedToTosException;
import com.ec.survey.tools.Tools;
import com.ec.survey.tools.WeakAuthenticationException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net;
import java.util.Date;
import java.util.List;
import com.ec.survey.Interface.LdapService;
import com.ec.survey.DTO.UserFilter;
import com.ec.survey.DTO.Form;
public class SessionService extends BasicService{

 private  LdapService ldapService;

 private  String proxyHost;

 private  String proxyPort;

 private  String proxyUser;

 private  String proxyPassword;

 private  String pdfServerPrefix;

 private  String proxyNonProxyHosts;


public String getCaptchaText(HttpServletRequest request){
    return (String) request.getSession().getAttribute("captcha");
}


public User getCurrentUser(HttpServletRequest request,boolean checkTOS,boolean checkWeakAuthentication){
    if (request == null)
        return null;
    User user = (User) request.getSession().getAttribute("USER");
    Boolean weakAuthentication = (Boolean) request.getSession().getAttribute("WEAKAUTHENTICATION");
    if (user != null) {
        Session session = sessionFactory.getCurrentSession();
        user = (User) session.merge(user);
        String weakAuthenticationDisabled = settingsService.get(Setting.WeakAuthenticationDisabled);
        if (weakAuthenticationDisabled.equalsIgnoreCase("true") && checkWeakAuthentication && user.getType().equalsIgnoreCase(User.ECAS) && user.isExternal() && weakAuthentication) {
            throw new WeakAuthenticationException();
        }
        String disabled = settingsService.get(Setting.CreateSurveysForExternalsDisabled);
        if (disabled.equalsIgnoreCase("true") && user.isExternal()) {
            user.setCanCreateSurveys(false);
        }
    }
    if (checkTOS && user != null && !user.isAgreedToPS()) {
        throw new NotAgreedToPsException();
    }
    if (checkTOS && user != null && !user.isAgreedToToS()) {
        throw new NotAgreedToTosException();
    }
    return user;
}


public List<ResultFilter> getAllResultFilter(int surveyid){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM ResultFilter r WHERE r.surveyId = :surveyid ORDER BY r.id DESC").setInteger("surveyid", surveyid);
    @SuppressWarnings("unchecked")
    List<ResultFilter> result = query.list();
    return result;
}


public UserFilter getUserFilter(HttpServletRequest request){
    UserFilter filter = new UserFilter();
    if ("true".equals(request.getParameter("clearFilter"))) {
        return filter;
    }
    filter.setLogin(request.getParameter("login"));
    filter.setEmail(request.getParameter(Constants.EMAIL));
    filter.setComment(request.getParameter("comment"));
    filter.setECaccess(request.getParameter("ECaccess") != null && request.getParameter("ECaccess").equalsIgnoreCase("true"));
    filter.setNoECaccess(request.getParameter("NoECaccess") != null && request.getParameter("NoECaccess").equalsIgnoreCase("true"));
    filter.setECASaccess(request.getParameter("ECASaccess") != null && request.getParameter("ECASaccess").equalsIgnoreCase("true"));
    filter.setNoECASaccess(request.getParameter("NoECASaccess") != null && request.getParameter("NoECASaccess").equalsIgnoreCase("true"));
    filter.setECAS(request.getParameter("ECAS") != null);
    filter.setSystem(request.getParameter("system") != null);
    filter.setLanguages(request.getParameterValues("languages"));
    filter.setBanned(request.getParameter("banned") != null && request.getParameter("banned").equalsIgnoreCase("true"));
    filter.setUnbanned(request.getParameter("unbanned") != null && request.getParameter("unbanned").equalsIgnoreCase("true"));
    String[] roles = request.getParameterValues("roles");
    if (roles != null && roles.length > 0) {
        filter.setRoles(roles);
    }
    String sortKey = request.getParameter("sortkey");
    if (sortKey != null && (sortKey.equalsIgnoreCase("login") || sortKey.equalsIgnoreCase(Constants.EMAIL))) {
        filter.setSortKey(request.getParameter("sortkey"));
        filter.setSortOrder(request.getParameter("sortorder"));
    }
    return filter;
}


public Form getForm(HttpServletRequest request,String shortname,boolean loadReplies,boolean synchronize){
    // first check if a survey id was specified as url parameter
    if (request.getParameter(Constants.SURVEY) != null) {
        String id = request.getParameter(Constants.SURVEY);
        User user = getCurrentUser(request);
        Survey survey = surveyService.getSurvey(Integer.parseInt(id), false, true);
        if (survey != null)
            return checkSurvey(survey, user, request);
    }
    // then check if a shortname was specified as url parameter
    if (shortname != null && !shortname.equalsIgnoreCase("noform")) {
        User user = getCurrentUser(request);
        Survey survey = surveyService.getSurvey(shortname, true, false, loadReplies, false, null, true, synchronize);
        if (survey != null)
            return checkSurvey(survey, user, request);
        throw new InvalidURLException();
    }
    Form form = getFormFromSessionInfo(request);
    if (form != null)
        return form;
    if (request.getParameter(Constants.SHORTNAME) != null) {
        String alias = request.getParameter(Constants.SHORTNAME);
        User user = getCurrentUser(request);
        Survey survey = surveyService.getSurvey(alias, true, false, loadReplies, false, null, true, synchronize);
        if (survey != null)
            return checkSurvey(survey, user, request);
        throw new InvalidURLException();
    }
    throw new NoFormLoadedException();
}


public ResultFilter getLastResultFilter(HttpServletRequest request,int userid,int surveyid){
    if (request == null)
        return null;
    ResultFilter filter = null;
    if (userid > 0 && surveyid > 0) {
        filter = getResultFilter(userid, surveyid);
    } else {
        filter = (ResultFilter) request.getSession().getAttribute("ResultFilter");
    }
    if (filter != null && filter.getDefaultQuestions() != null && filter.getDefaultQuestions()) {
        filter.getVisibleQuestions().clear();
        filter.getExportedQuestions().clear();
        Survey survey = surveyService.getSurvey(filter.getSurveyId());
        for (Element question : survey.getQuestions()) {
            if (question.isUsedInResults()) {
                if (filter.getVisibleQuestions().size() < 20) {
                    filter.getVisibleQuestions().add(question.getId().toString());
                }
                filter.getExportedQuestions().add(question.getId().toString());
            }
        }
    }
    return filter;
}


public String getContextPath(){
    return servletContext.getContextPath();
}


public Form getFormOrNull(HttpServletRequest request,String shortname,boolean loadReplies){
    try {
        return getForm(request, shortname, loadReplies, false);
    } catch (Exception ex) {
        logger.debug(ex);
    }
    return null;
}


public Form getFormFromSessionInfo(HttpServletRequest request){
    SessionInfo info = (SessionInfo) request.getSession().getAttribute("sessioninfo");
    if (info != null) {
        Survey survey = surveyService.getSurvey(info.getSurvey(), info.getLanguage());
        Form form = new Form(survey, translationService.getTranslationsForSurvey(survey.getId(), true), survey.getLanguage(), resources, contextpath);
        form.setNumberOfAnswerSets(answerService.getNumberOfAnswerSets(survey, new ResultFilter()));
        String languageCode = info.getLanguage();
        if (request.getParameter("slang") != null && request.getParameter("slang").trim().length() > 0) {
            languageCode = request.getParameter("slang").trim();
            form.setLanguage(surveyService.getLanguage(languageCode));
        } else if (form.getLanguage() != null) {
            languageCode = form.getLanguage().getCode();
        }
        if (info.getLanguage() != null && !survey.getLanguage().getCode().equalsIgnoreCase(languageCode)) {
            survey = surveyService.getSurvey(survey.getId(), languageCode);
            form.setSurvey(survey);
            info.setLanguage(languageCode);
            request.getSession().setAttribute("sessioninfo", info);
        }
        return form;
    }
    return null;
}


public String getCheckExport(HttpServletRequest request){
    if (request == null)
        return null;
    if (request.getSession() == null)
        return null;
    return (String) request.getSession().getAttribute("CHECK_EXPORT");
}


public ActivityFilter getLastActivityFilter(HttpServletRequest request){
    if (request == null)
        return null;
    return (ActivityFilter) request.getSession().getAttribute("ActivityFilter");
}


public ResultFilter getResultFilter(int userid,int surveyid){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM ResultFilter r WHERE r.userId = :userid and r.surveyId = :surveyid ORDER BY r.id DESC").setInteger("userid", userid).setInteger("surveyid", surveyid);
    @SuppressWarnings("unchecked")
    List<ResultFilter> result = query.list();
    if (!result.isEmpty()) {
        return answerService.initialize(result.get(0));
    }
    return null;
}


public String getPdfServerPrefix(){
    return pdfServerPrefix;
}


public SurveyFilter getSurveyFilter(HttpServletRequest request,boolean forms){
    SurveyFilter filter = new SurveyFilter();
    filter.setUser(getCurrentUser(request));
    if (request.getParameter("clearFilter") != null && "true".equals(request.getParameter("clearFilter"))) {
        if (forms)
            request.getSession().removeAttribute("SURVEYFILTER");
        return filter;
    }
    if (request.getParameter("itemsPerPage") != null) {
        filter.setShortname(request.getParameter("name"));
        filter.setTitle(request.getParameter("title"));
        filter.setGeneratedFrom(ConversionTools.getDate(request.getParameter("generatedFrom")));
        filter.setGeneratedTo(ConversionTools.getDate(request.getParameter("generatedTo")));
        filter.setStartFrom(ConversionTools.getDate(request.getParameter("startFrom")));
        filter.setStartTo(ConversionTools.getDate(request.getParameter("startTo")));
        filter.setEndFrom(ConversionTools.getDate(request.getParameter("endFrom")));
        filter.setEndTo(ConversionTools.getDate(request.getParameter("endTo")));
        filter.setAccess(request.getParameter("access"));
        boolean filterDraft = request.getParameter("statusDraft") != null && request.getParameter("statusDraft").equalsIgnoreCase("Draft");
        boolean filterUnpublished = request.getParameter("statusUnpublished") != null && request.getParameter("statusUnpublished").equalsIgnoreCase("Unpublished");
        boolean filterPublished = request.getParameter("statusPublished") != null && request.getParameter("statusPublished").equalsIgnoreCase("Published");
        boolean filterOwn = request.getParameter("surveysOwn") != null && request.getParameter("surveysOwn").equalsIgnoreCase("own");
        boolean filterShared = request.getParameter("surveysShared") != null && request.getParameter("surveysShared").equalsIgnoreCase("shared");
        boolean filterAny = request.getParameter("surveysShared") != null && request.getParameter("surveysShared").equalsIgnoreCase("any");
        String status = "";
        if (filterDraft)
            status += "Draft;";
        if (filterUnpublished)
            status += "Unpublished;";
        if (filterPublished)
            status += "Published;";
        filter.setStatus(status);
        filter.setKeywords(request.getParameter("keywords"));
        filter.setLanguages(request.getParameterValues("languages"));
        filter.setOwner(request.getParameter("owner"));
        if (filterAny) {
            filter.setSelector("any");
        } else if (filterOwn && filterShared) {
            filter.setSelector("all");
        } else if (filterOwn) {
            filter.setSelector("my");
        } else if (filterShared) {
            filter.setSelector("shared");
        } else {
            filter.setSelector("all");
        }
        if (request.getParameter("sortkey") == null) {
            filter.setSortKey("survey_created");
            filter.setSortOrder("DESC");
        } else {
            // check for validity
            String sortkey = request.getParameter("sortkey");
            if (sortkey.equalsIgnoreCase("surveyname") || sortkey.equalsIgnoreCase("survey_created") || sortkey.equalsIgnoreCase("survey_end_date") || sortkey.equalsIgnoreCase("replies")) {
                filter.setSortKey(sortkey);
                if (request.getParameter("sortorder") != null && request.getParameter("sortorder").equalsIgnoreCase("asc")) {
                    filter.setSortOrder("ASC");
                } else {
                    filter.setSortOrder("DESC");
                }
            }
        }
        if (forms)
            request.getSession().setAttribute("SURVEYFILTER", filter);
    } else {
        if (forms && request.getSession().getAttribute("SURVEYFILTER") != null) {
            filter = (SurveyFilter) request.getSession().getAttribute("SURVEYFILTER");
        }
    }
    return filter;
}


@Override
public PasswordAuthentication getPasswordAuthentication(){
    return new PasswordAuthentication(proxyUser, proxyPassword.toCharArray());
}


}