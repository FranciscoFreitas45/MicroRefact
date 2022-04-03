package com.ec.survey.DTO;
 import com.ec.survey.exception.InvalidURLException;
import com.ec.survey.exception.MessageException;
import com.ec.survey.model;
import com.ec.survey.model.administration.GlobalPrivilege;
import com.ec.survey.model.administration.LocalPrivilege;
import com.ec.survey.model.administration.User;
import com.ec.survey.model.survey;
import com.ec.survey.model.survey.base.File;
import com.ec.survey.service.ReportingService.ToDo;
import com.ec.survey.tools;
import org.apache.commons.io.IOUtils;
import org.codehaus.plexus.util.FileUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;
import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Collectors.toList;
import com.ec.survey.Interface.SqlQueryService;
import com.ec.survey.Interface.LdapService;
import com.ec.survey.Interface.LdapDBService;
import com.ec.survey.DTO.Translations;
import com.ec.survey.DTO.Skin;
import com.ec.survey.DTO.ImportResult;
import com.ec.survey.DTO.PossibleAnswer;
import com.ec.survey.DTO.DependencyItem;
import com.ec.survey.DTO.Matrix;
import com.ec.survey.DTO.ArchiveFilter;
import com.ec.survey.DTO.Language;
import com.ec.survey.DTO.Translation;
import com.ec.survey.DTO.GalleryQuestion;
import com.ec.survey.DTO.Image;
import com.ec.survey.DTO.Confirmation;
import com.ec.survey.DTO.Download;
import com.ec.survey.DTO.RankingQuestion;
import com.ec.survey.DTO.Status;
import com.ec.survey.DTO.Message;
import com.ec.survey.DTO.AnswerExplanation;
import com.ec.survey.DTO.AnswerComment;
import com.ec.survey.DTO.ScoringItem;
public class SurveyService extends BasicService{

 private  String publicsurveynotification;

 private  String smtpServer;

 private  String smtpPort;

 private  String sender;

 private  String host;

 public  String opcnotify;

 private  String monitoringEmail;

 private  String opcusers;

 private  String opcdepartments;

 protected  SqlQueryService sqlQueryService;

 protected  LdapService ldapService;

 protected  LdapDBService ldapDBService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@Transactional
public List<Survey> getPublicSurveysForValidation(String filteralias,String filterowner,String filterrequestdatefrom,String filterrequestdateto){
    Session session = sessionFactory.getCurrentSession();
    String sql = "FROM Survey s WHERE s.isDraft = true AND s.listForm = true AND (s.listFormValidated = false OR s.listFormValidated = null)";
    if (filteralias != null && filteralias.length() > 0) {
        sql += " AND s.shortname like :alias";
    }
    if (filterowner != null && filterowner.length() > 0) {
        sql += " AND s.owner.login like :owner";
    }
    if (filterrequestdatefrom != null && filterrequestdatefrom.length() > 0) {
        sql += " AND s.publicationRequestedDate >= :datefrom";
    }
    if (filterrequestdateto != null && filterrequestdateto.length() > 0) {
        sql += " AND s.publicationRequestedDate <= :dateto";
    }
    Query query = session.createQuery(sql);
    if (filteralias != null && filteralias.length() > 0) {
        query.setString("alias", "%" + filteralias + "%");
    }
    if (filterowner != null && filterowner.length() > 0) {
        query.setString("owner", "%" + filterowner + "%");
    }
    DateTimeFormatter f = DateTimeFormat.forPattern("dd/MM/yyyy");
    if (filterrequestdatefrom != null && filterrequestdatefrom.length() > 0) {
        query.setDate("datefrom", DateTime.parse(filterrequestdatefrom, f).toDate());
    }
    if (filterrequestdateto != null && filterrequestdateto.length() > 0) {
        query.setDate("dateto", Tools.getFollowingDay(DateTime.parse(filterrequestdateto, f).toDate()));
    }
    @SuppressWarnings("unchecked")
    List<Survey> surveys = query.setReadOnly(true).list();
    return surveys;
}


@Transactional(readOnly = true)
public Survey getSurveyByShortname(String shortname,boolean isDraft,User u,HttpServletRequest request,boolean initElements,boolean checkNotArchived,boolean checkNotDeleted,boolean synchronize){
    if (shortname.equals("noform")) {
        SessionInfo info = (SessionInfo) request.getSession().getAttribute("sessioninfo");
        if (info != null) {
            Survey survey = getSurvey(info.getSurvey(), info.getLanguage());
            if (survey != null)
                return survey;
        }
        throw new InvalidURLException();
    }
    Survey survey = surveyService.getSurvey(shortname, isDraft, false, false, false, null, true, checkNotDeleted, true, synchronize);
    if (survey != null) {
        List<String> translations = translationService.getTranslationLanguagesForSurvey(survey.getId());
        survey.setTranslations(translations);
        if (u != null) {
            boolean allowed = false;
            if (!survey.getOwner().getId().equals(u.getId())) {
                if (u.getGlobalPrivileges().get(GlobalPrivilege.FormManagement) < 2) {
                    if (u.getLocalPrivileges().get(LocalPrivilege.FormManagement) < 1) {
                        if (u.getLocalPrivileges().get(LocalPrivilege.AccessDraft) > 0) {
                            allowed = true;
                        }
                        if (u.getLocalPrivileges().get(LocalPrivilege.AccessResults) > 0) {
                            allowed = true;
                        }
                        if (u.getLocalPrivileges().get(LocalPrivilege.ManageInvitations) > 0) {
                            allowed = true;
                        }
                    }
                } else {
                    allowed = true;
                }
            } else {
                allowed = true;
            }
            if (allowed)
                sessionService.updateSessionInfo(survey, u, request);
        }
        if (initElements)
            for (Element element : survey.getElementsRecursive()) {
                element.setSurvey(survey);
            }
        if (checkNotArchived && (survey.getArchived() || survey.getIsDeleted())) {
            throw new InvalidURLException();
        }
        return survey;
    }
    throw new InvalidURLException();
}


@SuppressWarnings("unchecked")
@Transactional(readOnly = true)
public List<Template> getTemplates(Integer ownerId){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Template t WHERE t.owner.id = :id").setInteger("id", ownerId);
    return query.list();
}


public String getSql(SurveyFilter filter,HashMap<String,Object> oQueryParameters,boolean loadpublicationdates){
    StringBuilder sql = new StringBuilder();
    if (filter == null) {
        return sql.toString();
    }
    if (filter.getUid() != null && filter.getUid().length() > 0) {
        sql.append(" AND s.SURVEY_UID like :uid");
        oQueryParameters.put("uid", "%" + filter.getUid().trim() + "%");
    }
    if (filter.getShortname() != null && filter.getShortname().length() > 0) {
        sql.append(" AND s.SURVEYNAME COLLATE UTF8_GENERAL_CI like :shortname");
        oQueryParameters.put(Constants.SHORTNAME, "%" + filter.getShortname().trim() + "%");
    }
    if (filter.getTitle() != null && filter.getTitle().length() > 0) {
        sql.append(" AND s.TITLE COLLATE UTF8_GENERAL_CI like :title");
        oQueryParameters.put("title", "%" + filter.getTitle().trim() + "%");
    }
    if (filter.getAccess() != null && filter.getAccess().length() > 0) {
        sql.append(" AND s.SURVEYSECURITY like :access");
        oQueryParameters.put("access", filter.getAccess());
    }
    if (filter.getType() != null && filter.getType().length() > 0 && !filter.getType().equalsIgnoreCase("all")) {
        switch(filter.getType()) {
            case "quiz":
                sql.append(" AND s.QUIZ = 1");
                break;
            case "standard":
                sql.append(" AND s.QUIZ = 0 AND s.OPC = 0");
                break;
            case "brp":
                sql.append(" AND s.OPC = 1");
                break;
            case "delphi":
                sql.append(" AND s.DELPHI = 1");
                break;
        }
    }
    if (filter.getStatus() != null) {
        boolean unpublished = filter.getStatus().contains("Unpublished");
        boolean published = filter.getStatus().contains("Published");
        if (published && unpublished) {
        // return everything
        } else if (published) {
            sql.append(" AND s.ISPUBLISHED = 1 AND s.ACTIVE = 1");
        } else if (unpublished) {
            sql.append(" AND s.ACTIVE = 0");
        }
    }
    if (filter.getGeneratedFrom() != null) {
        sql.append(" AND s.SURVEY_CREATED >= :generatedFrom");
        oQueryParameters.put("generatedFrom", filter.getGeneratedFrom());
    }
    if (filter.getGeneratedTo() != null) {
        sql.append(" AND s.SURVEY_CREATED < :generatedTo");
        oQueryParameters.put("generatedTo", Tools.getFollowingDay(filter.getGeneratedTo()));
    }
    if (filter.getDeletedFrom() != null) {
        sql.append(" AND s.SURVEY_DELETED >= :deletedFrom");
        oQueryParameters.put("deletedFrom", filter.getDeletedFrom());
    }
    if (filter.getDeletedTo() != null) {
        sql.append(" AND s.SURVEY_DELETED < :deletedTo");
        oQueryParameters.put("deletedTo", Tools.getFollowingDay(filter.getDeletedTo()));
    }
    if (filter.getStartFrom() != null) {
        sql.append(" AND s.SURVEY_START_DATE >= :startFrom");
        oQueryParameters.put("startFrom", filter.getStartFrom());
    }
    if (filter.getStartTo() != null) {
        sql.append(" AND s.SURVEY_START_DATE < :startTo");
        oQueryParameters.put("startTo", Tools.getFollowingDay(filter.getStartTo()));
    }
    if (filter.getEndFrom() != null) {
        sql.append(" AND s.SURVEY_END_DATE >= :endFrom");
        oQueryParameters.put("endFrom", filter.getEndFrom());
    }
    if (filter.getEndTo() != null) {
        sql.append(" AND s.SURVEY_END_DATE < :endTo");
        oQueryParameters.put("endTo", Tools.getFollowingDay(filter.getEndTo()));
    }
    if (filter.getOwner() != null && filter.getOwner().length() > 0 && filter.getUser() != null && (filter.getUser().getLogin().equals(filter.getOwner()) || filter.getUser().getGlobalPrivileges().get(GlobalPrivilege.FormManagement) == 2)) {
        // Searching by owner, checking if current user is owner or has global priviledge
        sql.append(" AND (s.OWNER in (SELECT USER_ID FROM USERS WHERE USER_LOGIN = :ownername OR USER_DISPLAYNAME = :ownername))");
        oQueryParameters.put("ownername", filter.getOwner());
    } else if (filter.getUser() == null) {
        // Searching public surveys
        sql.append(" AND s.LISTFORM = 1 AND s.LISTFORMVALIDATED = 1 AND s.ISPUBLISHED = true AND s.ACTIVE = true AND (s.SURVEYSECURITY = 'open' or s.SURVEYSECURITY = 'openanonymous') AND (s.SURVEY_END_DATE IS NULL OR s.SURVEY_END_DATE > :now)");
        oQueryParameters.put("now", new Date());
    }
    if (filter.getUser() != null) {
        if (filter.getSelector() != null && filter.getSelector().equalsIgnoreCase("any") && filter.getUser().getGlobalPrivileges().get(GlobalPrivilege.FormManagement) > 1) {
        // form administrators can see all surveys
        } else if (filter.getSelector() != null && filter.getSelector().equalsIgnoreCase("my")) {
            // Overriding owner with current user
            sql.append(" AND (s.OWNER = :ownerId)");
            oQueryParameters.put("ownerId", filter.getUser().getId());
        } else if (filter.getSelector() != null && filter.getSelector().equalsIgnoreCase("shared")) {
            // Overriding condition by sharing condition
            if (filter.getUser().getType().equalsIgnoreCase("ECAS")) {
                sql.append(" AND (s.SURVEY_ID in (Select a.SURVEY FROM SURACCESS a WHERE (a.ACCESS_USER = :ownerId OR a.ACCESS_DEPARTMENT IN (SELECT GRPS FROM ECASGROUPS WHERE eg_ID = (SELECT USER_ID FROM ECASUSERS WHERE USER_LOGIN = :login))) AND (a.ACCESS_PRIVILEGES like '%2%' or a.ACCESS_PRIVILEGES like '%1%')))");
                oQueryParameters.put("ownerId", filter.getUser().getId());
                oQueryParameters.put("login", filter.getUser().getLogin());
            } else {
                sql.append(" AND (s.SURVEY_ID in (Select a.SURVEY FROM SURACCESS a WHERE a.ACCESS_USER = :ownerId AND (a.ACCESS_PRIVILEGES like '%2%' or a.ACCESS_PRIVILEGES like '%1%')))");
                oQueryParameters.put("ownerId", filter.getUser().getId());
            }
        } else {
            // Searching for the last case, assuming selector is "all"
            if (filter.getUser().getType().equalsIgnoreCase("ECAS")) {
                sql.append(" AND (s.OWNER = :ownerId OR s.SURVEY_ID in (Select a.SURVEY FROM SURACCESS a WHERE (a.ACCESS_USER = :ownerId OR a.ACCESS_DEPARTMENT IN (SELECT GRPS FROM ECASGROUPS WHERE eg_ID = (SELECT USER_ID FROM ECASUSERS WHERE USER_LOGIN = :login))) AND (a.ACCESS_PRIVILEGES like '%2%' or a.ACCESS_PRIVILEGES like '%1%')))");
                oQueryParameters.put("ownerId", filter.getUser().getId());
                oQueryParameters.put("login", filter.getUser().getLogin());
            } else {
                sql.append(" AND (s.OWNER = :ownerId OR s.SURVEY_ID in (Select a.SURVEY FROM SURACCESS a WHERE a.ACCESS_USER = :ownerId AND (a.ACCESS_PRIVILEGES like '%2%' or a.ACCESS_PRIVILEGES like '%1%')))");
                oQueryParameters.put("ownerId", filter.getUser().getId());
            }
        }
    }
    if (filter.getKeywords() != null && filter.getKeywords().trim().length() > 0) {
        int i = 0;
        sql.append(" AND (");
        for (String word : filter.getKeywords().split(" ")) {
            if (word.trim().length() > 0) {
                String w = "word" + i++;
                if (i > 1) {
                    sql.append(" OR");
                }
                sql.append(" ( s.SURVEYNAME COLLATE UTF8_GENERAL_CI like :").append(w).append(" OR s.TITLE COLLATE UTF8_GENERAL_CI like :").append(w).append(")");
                oQueryParameters.put(w, "%" + word.trim() + "%");
            }
        }
        sql.append(" )");
    }
    if (filter.getLanguages() != null) {
        int i = 0;
        sql.append(" AND (");
        for (String lang : filter.getLanguages()) {
            if (lang.trim().length() > 0) {
                String l = "lang" + i++;
                if (i > 1) {
                    sql.append(" OR");
                }
                sql.append(" ( s.LANGUAGE = :").append(l).append(" or s.SURVEY_ID in (Select distinct t.SURVEY_ID FROM TRANSLATIONS t WHERE t.SURVEY_ACTIVE = 1 AND t.LANGUAGE = :").append(l).append("))");
                oQueryParameters.put(l, lang.trim());
            }
        }
        sql.append(" )");
    }
    if (filter.getSurveys() != null && filter.getSurveys().equalsIgnoreCase("REPORTED")) {
        sql.append(" AND (s.SURVEY_UID IN (SELECT DISTINCT SURABUSE_SURVEY FROM SURABUSE))");
    }
    if (filter.getMinReported() != null && filter.getMinReported() > 0) {
        sql.append(" AND (abu.abuses >= :abuses)");
        oQueryParameters.put("abuses", filter.getMinReported());
    }
    if ((filter.getSurveys() != null && filter.getSurveys().equalsIgnoreCase("FROZEN")) || filter.getFrozen() != null && filter.getFrozen()) {
        sql.append(" AND (s.FROZEN = 1)");
    }
    if (filter.getMinContributions() != null) {
        sql.append(" AND (npa.PUBLISHEDANSWERS > :replies)");
        oQueryParameters.put("replies", filter.getMinContributions());
    }
    if (loadpublicationdates) {
        sql.append(" GROUP BY s.SURVEY_UID");
        boolean having = false;
        if (filter.getPublishedFrom() != null) {
            sql.append(" HAVING STR_TO_DATE(SUBSTRING(GROUP_CONCAT(s.survey_created),-19), '%Y-%m-%d') >= :publishedFrom");
            having = true;
            oQueryParameters.put("publishedFrom", filter.getPublishedFrom());
        }
        if (filter.getPublishedTo() != null) {
            if (having) {
                sql.append(" AND ");
            } else {
                sql.append(" HAVING ");
                having = true;
            }
            sql.append("STR_TO_DATE(SUBSTRING(GROUP_CONCAT(s.survey_created),-19), '%Y-%m-%d') <= :publishedTo");
            oQueryParameters.put("publishedTo", filter.getPublishedTo());
        }
        if (filter.getFirstPublishedFrom() != null) {
            if (having) {
                sql.append(" AND ");
            } else {
                sql.append(" HAVING ");
                having = true;
            }
            sql.append("STR_TO_DATE(SUBSTRING(GROUP_CONCAT(s.survey_created),21, 19), '%Y-%m-%d') >= :firstPublishedFrom");
            oQueryParameters.put("firstPublishedFrom", filter.getFirstPublishedFrom());
        }
        if (filter.getFirstPublishedTo() != null) {
            if (having) {
                sql.append(" AND ");
            } else {
                sql.append(" HAVING ");
            }
            sql.append("STR_TO_DATE(SUBSTRING(GROUP_CONCAT(s.survey_created),21, 19), '%Y-%m-%d') <= :firstPublishedTo");
            oQueryParameters.put("firstPublishedTo", filter.getFirstPublishedTo());
        }
    }
    if (filter.getSortKey() != null && filter.getSortKey().length() > 0) {
        if (filter.getSortKey().equalsIgnoreCase("replies")) {
            sql.append(" ORDER BY npa.PUBLISHEDANSWERS");
            if (filter.getSortOrder() != null && filter.getSortOrder().length() > 0) {
                sql.append(" ").append(filter.getSortOrder().toUpperCase());
            }
        } else if (filter.getSortKey().equalsIgnoreCase("created")) {
            sql.append(" ORDER BY s.SURVEY_CREATED");
            if (filter.getSortOrder() != null && filter.getSortOrder().length() > 0) {
                sql.append(" ").append(filter.getSortOrder().toUpperCase());
            }
        } else if (filter.getSortKey().equalsIgnoreCase("firstPublished")) {
            sql.append(" ORDER BY firstPublished");
            if (filter.getSortOrder() != null && filter.getSortOrder().length() > 0) {
                sql.append(" ").append(filter.getSortOrder().toUpperCase());
            }
        } else if (filter.getSortKey().equalsIgnoreCase("published")) {
            sql.append(" ORDER BY published");
            if (filter.getSortOrder() != null && filter.getSortOrder().length() > 0) {
                sql.append(" ").append(filter.getSortOrder().toUpperCase());
            }
        } else if (filter.getSortKey().equalsIgnoreCase("reported")) {
            sql.append(" ORDER BY reported");
            if (filter.getSortOrder() != null && filter.getSortOrder().length() > 0) {
                sql.append(" ").append(filter.getSortOrder().toUpperCase());
            }
        } else {
            sql.append(" ORDER BY s.").append(filter.getSortKey());
            if (filter.getSortOrder() != null && filter.getSortOrder().length() > 0) {
                sql.append(" ").append(filter.getSortOrder().toUpperCase());
            } else {
                sql.append(" DESC");
            }
        }
    }
    return sql.toString();
}


@Transactional
public List<Integer> getSurveysWithPrivilegesForUser(int userid){
    Session session = sessionFactory.getCurrentSession();
    SQLQuery query = session.createSQLQuery("Select a.SURVEY FROM SURACCESS a WHERE a.ACCESS_USER = :id");
    @SuppressWarnings("rawtypes")
    List surveys = query.setInteger("id", userid).list();
    List<Integer> result = new ArrayList<>();
    for (Object o : surveys) {
        result.add(ConversionTools.getValue(o));
    }
    return result;
}


public String[] getMetaDataForUser(User user,String type){
    String[] result = new String[6];
    List<String> surveyUIDs = getAllPublishedSurveysUIDsForUser(user, true, false, type);
    if (!surveyUIDs.isEmpty()) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT s.SURVEYNAME from SURVEYS s where s.ISDRAFT = 1 and SURVEY_UID IN (" + StringUtils.collectionToCommaDelimitedString(surveyUIDs) + ") ORDER BY s.SURVEY_UPDATED DESC LIMIT 1";
        Query query = session.createSQLQuery(sql);
        String shortname = (String) query.uniqueResult();
        result[0] = shortname != null ? shortname : "";
        sql = "SELECT s.SURVEYNAME FROM ANSWERS_SET ans JOIN SURVEYS s ON ans.SURVEY_ID = s.SURVEY_ID WHERE s.SURVEY_UID IN (" + StringUtils.collectionToCommaDelimitedString(surveyUIDs) + ") ORDER BY ans.ANSWER_SET_DATE DESC LIMIT 1";
        query = session.createSQLQuery(sql);
        String d = (String) query.uniqueResult();
        result[1] = d != null ? d : "";
        sql = "SELECT s.SURVEYNAME, s.DELETED, s.ARCHIVED from SURVEYS s JOIN USERS u ON u.USER_LAST_SURVEY = s.SURVEY_ID where u.USER_ID = :userid";
        query = session.createSQLQuery(sql);
        query.setInteger("userid", user.getId());
        @SuppressWarnings("rawtypes")
        List res = query.list();
        boolean deleted = false;
        boolean archived = false;
        if (res != null && !res.isEmpty()) {
            Object[] a = (Object[]) res.get(0);
            shortname = (String) a[0];
            deleted = (boolean) a[1];
            archived = (boolean) a[2];
        } else {
            shortname = null;
            deleted = true;
        }
        result[2] = shortname != null ? shortname : "";
        result[3] = deleted ? "true" : "false";
        result[4] = archived ? "true" : "false";
    } else {
        // this means that the user has no "existing" survey. Check if there is an
        // archived one
        ArchiveFilter filter = new ArchiveFilter();
        filter.setUserId(user.getId());
        List<Archive> archives = archiveService.getAllArchives(filter, 1, 1, true);
        if (!archives.isEmpty()) {
            result[5] = "true";
        }
    }
    return result;
}


@Transactional(readOnly = true)
public Survey getSurveyByUniqueId(String uid,boolean loadTranslations,boolean draft){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT id FROM Survey s WHERE s.uniqueId = :uid AND s.isDraft = :draft ORDER BY s.id DESC").setString("uid", uid);
    query.setBoolean("draft", draft);
    @SuppressWarnings("unchecked")
    List<Survey> list = query.setReadOnly(true).setMaxResults(1).list();
    if (!list.isEmpty()) {
        Survey survey = getSurvey(ConversionTools.getValue(list.get(0)));
        if (survey != null && loadTranslations) {
            List<String> translations = translationService.getTranslationLanguagesForSurvey(survey.getId());
            survey.setTranslations(translations);
        }
        if (survey != null) {
            synchronizeSurvey(survey, survey.getLanguage().getCode(), true);
        }
        session.setReadOnly(survey, true);
        for (Element e : survey.getElementsRecursive(true)) {
            session.setReadOnly(e, true);
        }
        return survey;
    }
    return null;
}


public String getMySurveysXML(SurveyFilter filter,ArchiveFilter archiveFilter){
    StringBuilder s = new StringBuilder();
    s.append("<?xml version='1.0' encoding='UTF-8' standalone='no' ?>\n");
    s.append("<Surveys user='").append(filter.getUser().getLogin()).append("'>\n");
    if (archiveFilter != null) {
        List<Archive> archives = archiveService.getAllArchives(archiveFilter, 1, 10000, true);
        for (Archive archive : archives) {
            s.append("<Survey uid='").append(archive.getSurveyUID()).append("' alias='").append(archive.getSurveyShortname()).append("'>\n");
            s.append("<Title>").append(ConversionTools.removeHTML(archive.getSurveyTitle())).append("</Title>\n");
            s.append("</Survey>\n");
        }
    }
    SqlPagination pagination = new SqlPagination(1, 10000);
    StringBuilder stringBuilder = new StringBuilder(512);
    stringBuilder.append("SELECT s.SURVEY_UID");
    stringBuilder.append(" , s.SURVEYNAME");
    stringBuilder.append(" , s.TITLE");
    stringBuilder.append(" , s.OWNER");
    stringBuilder.append(" FROM SURVEYS s");
    stringBuilder.append(" LEFT JOIN MV_SURVEYS_NUMBERPUBLISHEDANSWERS npa on s.SURVEY_UID = npa.SURVEYUID ");
    if (filter.getMinReported() != null && filter.getMinReported() > 0) {
        stringBuilder.append(" LEFT JOIN ( SELECT SURABUSE_SURVEY, count(SURABUSE_ID) as abuses FROM SURABUSE GROUP BY SURABUSE_SURVEY) abu ON abu.SURABUSE_SURVEY = s.SURVEY_UID");
    }
    stringBuilder.append(" where");
    if (archiveFilter != null) {
        stringBuilder.append(" (s.ARCHIVED = 1)");
    } else if (filter.getDeleted() != null && filter.getDeleted()) {
        stringBuilder.append(" (s.DELETED = " + (filter.getDeleted() ? "1" : "0") + ")");
    } else if (filter.getFrozen() != null) {
        stringBuilder.append(" (s.FROZEN " + (filter.getFrozen() ? " > 0" : " < 1") + ")");
    } else {
        stringBuilder.append(" (s.ARCHIVED = 0 or s.ARCHIVED is null) and (s.DELETED = 0 or s.DELETED is null)");
    }
    String sql = stringBuilder.toString();
    HashMap<String, Object> parameters = new HashMap<>();
    sql += getSql(filter, parameters, true);
    List<Survey> surveys = new ArrayList<>();
    for (Object[] row : loadSurveysfromDatabase(sql, parameters, pagination)) {
        Survey survey = new Survey();
        survey.setUniqueId((String) row[0]);
        survey.setShortname((String) row[1]);
        survey.setTitle((String) row[2]);
        int ownerId = ConversionTools.getValue(row[3]);
        User owner = administrationService.getUser(ownerId);
        survey.setOwner(owner);
        surveys.add(survey);
    }
    for (Survey survey : surveys) {
        if (filter.getUserDepartment() != null && filter.getUserDepartment().length() > 1) {
            List<String> departments = ldapService.getUserLDAPGroups(survey.getOwner().getLogin());
            if (!departments.contains(filter.getUserDepartment())) {
                continue;
            }
        }
        s.append("<Survey uid='").append(survey.getUniqueId()).append("' alias='").append(survey.getShortname()).append("'>\n");
        s.append("<Title>").append(ConversionTools.removeHTML(survey.getTitle())).append("</Title>\n");
        s.append("</Survey>\n");
    }
    s.append("</Surveys>");
    return s.toString();
}


@Transactional(readOnly = true)
public String[] getSurveyForFile(File file,String contextpath,String surveyuid){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT s.uniqueId, s.shortname FROM Survey s WHERE s.logo.id = :id");
    query.setInteger("id", file.getId());
    @SuppressWarnings("rawtypes")
    List data = query.setMaxResults(1).list();
    if (!data.isEmpty()) {
        Object[] values = (Object[]) data.get(0);
        String[] result = new String[3];
        result[0] = values[0].toString();
        result[1] = values[1].toString();
        result[2] = "logo";
        return result;
    }
    SQLQuery sqlquery = session.createSQLQuery("SELECT e.type, s.SURVEY_UID, s.SURVEYNAME FROM ELEMENTS e JOIN SURVEYS_ELEMENTS se ON se.elements_ID = e.ID JOIN SURVEYS s ON s.SURVEY_ID = se.SURVEYS_SURVEY_ID JOIN ELEMENTS_FILES ef ON ef.ELEMENTS_ID = e.ID WHERE ef.files_FILE_ID = :id");
    sqlquery.setInteger("id", file.getId());
    data = sqlquery.setMaxResults(1).list();
    if (!data.isEmpty()) {
        Object[] values = (Object[]) data.get(0);
        String[] result = new String[3];
        result[0] = values[1].toString();
        result[1] = values[2].toString();
        result[2] = values[0].toString().toLowerCase();
        if (result[2].equalsIgnoreCase("galleryquestion")) {
            result[2] = "image";
        }
        return result;
    }
    if (surveyuid == null) {
        sqlquery = session.createSQLQuery("SELECT e.type, s.SURVEY_UID, s.SURVEYNAME FROM ELEMENTS e JOIN SURVEYS_ELEMENTS se ON se.elements_ID = e.ID JOIN SURVEYS s ON s.SURVEY_ID = se.SURVEYS_SURVEY_ID WHERE URL = :url");
        sqlquery.setString("url", contextpath + "/files/" + file.getUid());
        data = sqlquery.setMaxResults(1).list();
        if (!data.isEmpty()) {
            Object[] values = (Object[]) data.get(0);
            if (values[0].toString().equalsIgnoreCase("IMAGE")) {
                String[] result = new String[3];
                result[0] = values[1].toString();
                result[1] = values[2].toString();
                result[2] = "image";
                return result;
            }
        }
        sqlquery = session.createSQLQuery("SELECT e.type, s.SURVEY_UID, s.SURVEYNAME FROM ELEMENTS e JOIN SURVEYS_ELEMENTS se ON se.elements_ID = e.ID JOIN SURVEYS s ON s.SURVEY_ID = se.SURVEYS_SURVEY_ID WHERE URL LIKE :url");
        sqlquery.setString("url", "%/" + file.getUid());
        data = sqlquery.setMaxResults(1).list();
        if (!data.isEmpty()) {
            Object[] values = (Object[]) data.get(0);
            if (values[0].toString().equalsIgnoreCase("IMAGE")) {
                String[] result = new String[3];
                result[0] = values[1].toString();
                result[1] = values[2].toString();
                result[2] = "image";
                return result;
            }
        }
    } else {
        sqlquery = session.createSQLQuery("SELECT e.type, s.SURVEY_UID, s.SURVEYNAME FROM ELEMENTS e JOIN SURVEYS_ELEMENTS se ON se.elements_ID = e.ID JOIN SURVEYS s ON s.SURVEY_ID = se.SURVEYS_SURVEY_ID WHERE URL = :url");
        sqlquery.setString("url", contextpath + "/files/" + surveyuid + Constants.PATH_DELIMITER + file.getUid());
        data = sqlquery.setMaxResults(1).list();
        if (!data.isEmpty()) {
            Object[] values = (Object[]) data.get(0);
            if (values[0].toString().equalsIgnoreCase("IMAGE")) {
                String[] result = new String[3];
                result[0] = values[1].toString();
                result[1] = values[2].toString();
                result[2] = "image";
                return result;
            }
        }
    }
    sqlquery = session.createSQLQuery("SELECT s.SURVEY_UID, s.SURVEYNAME FROM SURVEYS s JOIN Survey_backgroundDocuments sb ON sb.Survey_SURVEY_ID = s.SURVEY_ID WHERE sb.BACKGROUNDDOCUMENTS LIKE :url");
    sqlquery.setString("url", "%" + file.getUid());
    data = sqlquery.setMaxResults(1).list();
    if (!data.isEmpty()) {
        Object[] values = (Object[]) data.get(0);
        if (values[0].toString().equalsIgnoreCase("IMAGE")) {
            String[] result = new String[3];
            result[0] = values[0].toString();
            result[1] = values[1].toString();
            result[2] = "background document";
            return result;
        }
    }
    return null;
}


@Transactional(readOnly = true)
public Survey getSurveyForUploadedFile(int fileid){
    Session session = sessionFactory.getCurrentSession();
    String sql = "SELECT s.SURVEY_UID FROM SURVEYS s JOIN ANSWERS_SET ans ON ans.SURVEY_ID = s.SURVEY_ID JOIN ANSWERS a on a.AS_ID = ans.ANSWER_SET_ID JOIN ANSWERS_FILES af ON af.ANSWERS_ANSWER_ID = a.ANSWER_ID WHERE af.files_FILE_ID = :fileid";
    SQLQuery query = session.createSQLQuery(sql);
    query.setInteger("fileid", fileid);
    String result = (String) query.uniqueResult();
    if (result == null)
        return null;
    return getSurvey(result, true, false, false, false, null, true, false);
}


@Transactional
public Survey getSurveyWithMissingElements(String uidorshortname,boolean isDraft,boolean checkActive,boolean readReplies,boolean useEagerLoading,String language,boolean readonly,boolean synchronize){
    Survey survey = getSurvey(uidorshortname, isDraft, checkActive, readReplies, useEagerLoading, language, readonly, false, false, synchronize);
    if (survey != null)
        checkAndRecreateMissingElements(survey, null);
    return survey;
}


@Transactional(readOnly = true)
public List<Element> getElements(String[] ids){
    Session session = sessionFactory.getCurrentSession();
    List<Element> result = new ArrayList<>();
    for (String id : ids) {
        Element e = (Element) session.get(Element.class, Integer.parseInt(id));
        result.add(e);
    }
    return result;
}


@Transactional(readOnly = true)
public Map<Element,Integer> getPendingChanges(Survey draftSurvey){
    Map<Element, Integer> result = new HashMap<>();
    Survey publishedSurvey = getSurvey(draftSurvey.getShortname(), false, false, false, false, draftSurvey.getLanguage().getCode(), true, true);
    // Compare elements
    if (draftSurvey != null && publishedSurvey != null) {
        Map<String, Element> publishedElements = publishedSurvey.getElementsByUniqueId();
        for (Element element : draftSurvey.getElements()) {
            if (!publishedElements.containsKey(element.getUniqueId())) {
                // these are new elements
                result.put(element, 0);
            } else {
                Element publishedElement = publishedElements.get(element.getUniqueId());
                if (publishedElement.differsFrom(element)) {
                    // these are modified elements
                    result.put(element, 1);
                }
            }
        }
        // deleted elements
        Map<String, Element> draftElements = draftSurvey.getElementsByUniqueId();
        for (Element element : publishedSurvey.getElements()) {
            if (!draftElements.containsKey(element.getUniqueId())) {
                // these are deleted elements
                result.put(element, 2);
            }
        }
    }
    boolean hasPendingChanges = false;
    if (draftSurvey != null && publishedSurvey != null) {
        if (draftSurvey.getContact() != null && !draftSurvey.getContact().equals(publishedSurvey.getContact()))
            hasPendingChanges = true;
        if (draftSurvey.getMultiPaging() != publishedSurvey.getMultiPaging())
            hasPendingChanges = true;
        if (draftSurvey.getValidatedPerPage() != publishedSurvey.getValidatedPerPage())
            hasPendingChanges = true;
        if (!Objects.equals(draftSurvey.getWcagCompliance(), publishedSurvey.getWcagCompliance()))
            hasPendingChanges = true;
        if (!Tools.isFileEqual(draftSurvey.getLogo(), publishedSurvey.getLogo()))
            hasPendingChanges = true;
        if (!draftSurvey.getLogoInInfo().equals(publishedSurvey.getLogoInInfo()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getLogoText(), publishedSurvey.getLogoText()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getSkin(), publishedSurvey.getSkin()))
            hasPendingChanges = true;
        if (draftSurvey.getSectionNumbering() != publishedSurvey.getSectionNumbering())
            hasPendingChanges = true;
        if (draftSurvey.getQuestionNumbering() != publishedSurvey.getQuestionNumbering())
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getConfirmationPage(), publishedSurvey.getConfirmationPage()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getConfirmationPageLink(), publishedSurvey.getConfirmationPageLink()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getConfirmationLink(), publishedSurvey.getConfirmationLink()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getIsQuiz(), publishedSurvey.getIsQuiz()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getIsOPC(), publishedSurvey.getIsOPC()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getIsDelphi(), publishedSurvey.getIsDelphi()))
            hasPendingChanges = true;
        if (!Tools.isEqualIgnoreEmptyString(draftSurvey.getQuizWelcomeMessage(), publishedSurvey.getQuizWelcomeMessage()))
            hasPendingChanges = true;
        if (!Tools.isEqualIgnoreEmptyString(draftSurvey.getQuizResultsMessage(), publishedSurvey.getQuizResultsMessage()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getShowTotalScore(), publishedSurvey.getShowTotalScore()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getScoresByQuestion(), publishedSurvey.getScoresByQuestion()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getShowQuizIcons(), publishedSurvey.getShowQuizIcons()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getIsUseMaxNumberContribution(), publishedSurvey.getIsUseMaxNumberContribution()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getIsUseMaxNumberContributionLink(), publishedSurvey.getIsUseMaxNumberContributionLink()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getMaxNumberContributionText(), publishedSurvey.getMaxNumberContributionText()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getMaxNumberContributionLink(), publishedSurvey.getMaxNumberContributionLink()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getMaxNumberContribution(), publishedSurvey.getMaxNumberContribution()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getIsDelphiShowAnswersAndStatisticsInstantly(), publishedSurvey.getIsDelphiShowAnswersAndStatisticsInstantly()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getIsDelphiShowAnswers(), publishedSurvey.getIsDelphiShowAnswers()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getMinNumberDelphiStatistics(), publishedSurvey.getMinNumberDelphiStatistics()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getTimeLimit(), publishedSurvey.getTimeLimit()))
            hasPendingChanges = true;
        if (!Tools.isEqual(draftSurvey.getShowCountdown(), publishedSurvey.getShowCountdown()))
            hasPendingChanges = true;
        if (draftSurvey.getSendConfirmationEmail() != publishedSurvey.getSendConfirmationEmail())
            hasPendingChanges = true;
        if (!hasPendingChanges)
            for (String key : draftSurvey.getUsefulLinks().keySet()) {
                if (!publishedSurvey.getUsefulLinks().containsKey(key) || !publishedSurvey.getUsefulLinks().get(key).equals(draftSurvey.getUsefulLinks().get(key))) {
                    hasPendingChanges = true;
                    break;
                }
            }
        if (!hasPendingChanges)
            for (String key : publishedSurvey.getUsefulLinks().keySet()) {
                if (!draftSurvey.getUsefulLinks().containsKey(key) || !draftSurvey.getUsefulLinks().get(key).equals(publishedSurvey.getUsefulLinks().get(key))) {
                    hasPendingChanges = true;
                    break;
                }
            }
        if (!hasPendingChanges)
            for (String key : draftSurvey.getBackgroundDocuments().keySet()) {
                if (!publishedSurvey.getBackgroundDocuments().containsKey(key) || !publishedSurvey.getBackgroundDocuments().get(key).equals(draftSurvey.getBackgroundDocuments().get(key))) {
                    hasPendingChanges = true;
                    break;
                }
            }
        if (!hasPendingChanges)
            for (String key : publishedSurvey.getBackgroundDocuments().keySet()) {
                if (!draftSurvey.getBackgroundDocuments().containsKey(key) || !draftSurvey.getBackgroundDocuments().get(key).equals(publishedSurvey.getBackgroundDocuments().get(key))) {
                    hasPendingChanges = true;
                    break;
                }
            }
        if (hasPendingChanges)
            result.put(new PropertiesElement(), 1);
        // check if the order of elements has changed
        if (!hasPendingChanges && !draftSurvey.getElementsRecursiveUids().equals(publishedSurvey.getElementsRecursiveUids())) {
            hasPendingChanges = true;
            if (hasPendingChanges)
                result.put(new PropertiesElement(true), 1);
        }
        List<Translations> draftTranslations = translationService.getActiveTranslationsForSurvey(draftSurvey.getId());
        List<Translations> publishedTranslations = translationService.getActiveTranslationsForSurvey(publishedSurvey.getId());
        Translations currentTranslations = TranslationsHelper.getTranslations(draftSurvey, false);
        Map<String, Translation> currentKeys = currentTranslations.getTranslationsByKey();
        if (checkTranslations(draftTranslations, publishedTranslations, currentKeys) || checkTranslations(publishedTranslations, draftTranslations, currentKeys)) {
            result.put(new TranslationsElement(), 1);
        }
    }
    return result;
}


public String getNotificationEmailText(Survey survey,String email,Locale locale){
    String notificationemailtext = null;
    if (survey.getSendConfirmationEmail() && email != null && email.contains("@")) {
        String[] args = new String[] { email };
        notificationemailtext = resources.getMessage("message.ConfirmationEmailText", args, "We’ve sent you a confirmation email to your registered email address: {0}.", locale);
    }
    return notificationemailtext;
}


public Map<Integer,String> getUniqueIdsById(Survey publishedSurvey){
    HashMap<Integer, String> oldUniqueIdsById = new HashMap<>();
    for (Element element : publishedSurvey.getElementsRecursive(true)) {
        oldUniqueIdsById.put(element.getId(), element.getUniqueId());
    }
    return oldUniqueIdsById;
}


public Language getLanguage(String code,Session session){
    Query query = session.createQuery("FROM Language l WHERE l.code = :code").setString("code", code);
    @SuppressWarnings("rawtypes")
    List result = query.list();
    if (result.isEmpty()) {
        logger.error("ivalid language code: " + code);
        return null;
    }
    return (Language) result.get(0);
}


@SuppressWarnings("unchecked")
@Transactional(readOnly = true)
public List<Access> getAccesses(Integer id){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Access a WHERE a.survey.id = :id").setInteger("id", id);
    return query.list();
}


@Transactional(readOnly = true)
public int getNumberOfSurveys(boolean draftSurveys){
    Session session = sessionFactory.getCurrentSession();
    String sql = "SELECT COUNT(DISTINCT SURVEY_UID) FROM SURVEYS WHERE ISDRAFT = " + (draftSurveys ? "1" : "0");
    Query query = session.createSQLQuery(sql);
    return ConversionTools.getValue(query.uniqueResult());
}


@Transactional(readOnly = true)
public Access getAccess(Integer id,Integer userId){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Access a WHERE a.survey.id = :id AND a.user.id = :userId").setInteger("id", id).setInteger("userId", userId);
    @SuppressWarnings("unchecked")
    List<Access> result = query.list();
    if (!result.isEmpty())
        return result.get(0);
    return null;
}


@Transactional(readOnly = true)
public List<Integer> getAllPublishedSurveyVersions(String surveyUid){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createSQLQuery("SELECT SURVEY_ID FROM SURVEYS WHERE SURVEY_UID = :uid AND ISDRAFT=0 ORDER BY SURVEY_ID ASC").setString("uid", surveyUid);
    @SuppressWarnings("rawtypes")
    List res = query.list();
    List<Integer> result = new ArrayList<>();
    for (Object o : res) {
        result.add(ConversionTools.getValue(o));
    }
    return result;
}


public Map<Integer,Language> getLanguageMap(){
    return getLanguages().stream().collect(Collectors.toMap(Language::getId, l -> l));
}


@Transactional(readOnly = true)
public List<String> getAllSurveyUIDs(boolean onlypublished){
    Session session = sessionFactory.getCurrentSession();
    String sql = "SELECT DISTINCT SURVEY_UID FROM SURVEYS";
    if (onlypublished) {
        sql += " WHERE ISPUBLISHED = 1";
    }
    Query query = session.createSQLQuery(sql);
    @SuppressWarnings("unchecked")
    List<String> result = query.list();
    return result;
}


@Transactional(readOnly = true)
public Element getElement(int id){
    Session session = sessionFactory.getCurrentSession();
    return (Element) session.get(Element.class, id);
}


@Transactional
public List<Integer> getSurveysForUser(int userid){
    Session session = sessionFactory.getCurrentSession();
    SQLQuery query = session.createSQLQuery("SELECT SURVEY_ID FROM SURVEYS WHERE OWNER = :id AND ISDRAFT = 1");
    @SuppressWarnings("rawtypes")
    List surveys = query.setInteger("id", userid).list();
    List<Integer> result = new ArrayList<>();
    for (Object o : surveys) {
        result.add(ConversionTools.getValue(o));
    }
    return result;
}


public List<String> getCompletedTranslations(Survey survey){
    return translationService.getTranslationsForSurvey(survey.getId(), false).stream().filter(Translations::getActive).map(t -> t.getLanguage().getCode()).collect(toList());
}


public Map<Integer,String> getAllPublishedSurveysForUser(User user,String sort,String type){
    Session session = sessionFactory.getCurrentSession();
    String ownerwhere = getOwnerWhere(type);
    String sql = "SELECT s.SURVEY_ID, s.SURVEYNAME from SURVEYS s where s.ISDRAFT = 1 and s.ACTIVE = 1 and (" + ownerwhere + ") and (s.ARCHIVED = 0 or s.ARCHIVED is null) and (s.DELETED = 0 or s.DELETED is null) ORDER BY ";
    if (sort.equalsIgnoreCase("created")) {
        sql += "s.SURVEY_CREATED DESC";
    } else if (sort.equalsIgnoreCase(Constants.EDITED)) {
        sql += "s.SURVEY_UPDATED DESC";
    } else {
        sql += "s.SURVEYNAME ASC";
    }
    Query query = session.createSQLQuery(sql);
    query.setInteger("userid", user.getId());
    if (type == null || !type.equalsIgnoreCase("my")) {
        query.setString("login", user.getLogin());
    }
    LinkedHashMap<Integer, String> result = new LinkedHashMap<>();
    @SuppressWarnings("rawtypes")
    List res = query.list();
    for (Object o : res) {
        Object[] a = (Object[]) o;
        result.put(ConversionTools.getValue(a[0]), (String) a[1]);
    }
    return result;
}


public Map<Date,List<String>> getSurveysWithEndDatesForUser(User user,String type){
    Session session = sessionFactory.getCurrentSession();
    String ownerwhere = getOwnerWhere(type);
    String sql = "SELECT s.SURVEYNAME, s.SURVEY_END_DATE FROM SURVEYS s where s.ISDRAFT = 1 and (" + ownerwhere + ") and s.SURVEY_END_DATE is not null and s.SURVEY_END_DATE >= CURDATE() and (s.ARCHIVED = 0 or s.ARCHIVED is null) and (s.DELETED = 0 or s.DELETED is null) and s.AUTOMATICPUBLISHING = 1";
    Query query = session.createSQLQuery(sql);
    query.setInteger("userid", user.getId());
    if (type == null || !type.equalsIgnoreCase("my")) {
        query.setString("login", user.getLogin());
    }
    Map<Date, List<String>> result = new TreeMap<>();
    @SuppressWarnings("rawtypes")
    List res = query.list();
    for (Object o : res) {
        Object[] a = (Object[]) o;
        if (!result.containsKey((Date) a[1])) {
            result.put((Date) a[1], new ArrayList<String>());
        }
        result.get((Date) a[1]).add((String) a[0]);
    }
    return result;
}


@Transactional(readOnly = true)
public List<Survey> getSurveys(SurveyFilter filter,SqlPagination sqlPagination){
    StringBuilder stringBuilder = new StringBuilder(512);
    // 0
    stringBuilder.append("SELECT s.SURVEY_ID");
    // 1
    stringBuilder.append(" ,s.SURVEY_UID");
    // 2
    stringBuilder.append(" ,s.SURVEYNAME");
    // 3
    stringBuilder.append(" ,s.TITLE");
    // 4
    stringBuilder.append(" ,s.SURVEY_CREATED");
    // 5
    stringBuilder.append(" ,s.SURVEY_END_DATE");
    // 6
    stringBuilder.append(" ,s.SURVEY_START_DATE");
    // 7
    stringBuilder.append(" ,s.ISPUBLISHED");
    stringBuilder.append(" ,s.LANGUAGE");
    stringBuilder.append(" ,npa.PUBLISHEDANSWERS as replies");
    stringBuilder.append(" ,s.ACTIVE");
    stringBuilder.append(" ,s.OWNER");
    stringBuilder.append(" ,s.CONTACT");
    stringBuilder.append(" ,(SELECT USER_LOGIN FROM USERS u WHERE u.USER_ID = s.OWNER) as ownerlogin");
    stringBuilder.append(" ,(SELECT USER_DISPLAYNAME FROM USERS u WHERE u.USER_ID = s.OWNER) as ownername");
    stringBuilder.append(" ,s.AUTOMATICPUBLISHING");
    stringBuilder.append(" ,s.CONTACTLABEL");
    stringBuilder.append(" ,s.SURVEYSECURITY");
    stringBuilder.append(" ,s.QUIZ");
    stringBuilder.append(" ,s.OPC");
    stringBuilder.append(" ,s.HASPENDINGCHANGES");
    stringBuilder.append(" ,s.DELPHI");
    stringBuilder.append(" ,s.ECF");
    stringBuilder.append(" from SURVEYS s");
    stringBuilder.append(" LEFT JOIN MV_SURVEYS_NUMBERPUBLISHEDANSWERS npa on s.SURVEY_UID = npa.SURVEYUID");
    stringBuilder.append(" where s.ISDRAFT = 1 and (s.ARCHIVED = 0 or s.ARCHIVED is null) and (s.DELETED = 0 or s.DELETED is null)");
    String sql = stringBuilder.toString();
    HashMap<String, Object> parameters = new HashMap<>();
    sql += getSql(filter, parameters, false);
    List<Survey> surveys = new ArrayList<>();
    Map<Integer, Language> languageMap = getLanguageMap();
    for (Object[] row : loadSurveysfromDatabase(sql, parameters, sqlPagination)) {
        Survey survey = new Survey();
        int rowIndex = 0;
        // 0
        survey.setId(ConversionTools.getValue(row[rowIndex++]));
        // 1
        survey.setUniqueId((String) row[rowIndex++]);
        // 2
        survey.setShortname((String) row[rowIndex++]);
        // 3
        survey.setTitle((String) row[rowIndex++]);
        // 4
        survey.setCreated((Date) row[rowIndex++]);
        // 5
        survey.setEnd((Date) row[rowIndex++]);
        // 6
        survey.setStart((Date) row[rowIndex++]);
        // 7
        survey.setIsPublished((Boolean) row[rowIndex++]);
        // 8
        survey.setLanguage(languageMap.get(ConversionTools.getValue(row[rowIndex++])));
        int mainCount = ConversionTools.getValue(row[rowIndex++]);
        if (this.isReportingDatabaseEnabled()) {
            survey.setNumberOfAnswerSetsPublished(this.reportingService.getCount(false, survey.getUniqueId()));
            if (survey.getNumberOfAnswerSetsPublished() == 0) {
                // 9
                survey.setNumberOfAnswerSetsPublished(mainCount);
            }
        } else {
            // 9
            survey.setNumberOfAnswerSetsPublished(mainCount);
        }
        // 10
        survey.setIsActive((Boolean) row[rowIndex++]);
        User user = new User();
        // 11
        user.setId(ConversionTools.getValue(row[rowIndex++]));
        // 12
        survey.setContact((String) row[rowIndex++]);
        // 13
        user.setLogin((String) row[rowIndex++]);
        // 14
        user.setDisplayName((String) row[rowIndex++]);
        survey.setOwner(user);
        // 15
        survey.setAutomaticPublishing((Boolean) row[rowIndex++]);
        // 16
        survey.setContactLabel((String) row[rowIndex++]);
        // 17
        survey.setSecurity((String) row[rowIndex++]);
        // 18
        survey.setIsQuiz((Boolean) row[rowIndex++]);
        // 19
        survey.setIsOPC((Boolean) row[rowIndex++]);
        // 19 or 20
        survey.setHasPendingChanges((Boolean) row[rowIndex++]);
        // 21
        survey.setIsDelphi((Boolean) row[rowIndex++]);
        survey.setIsECF((Boolean) row[rowIndex]);
        surveys.add(survey);
    }
    return surveys;
}


@Transactional(readOnly = true)
public int getDBVersion(){
    Session session = sessionFactory.getCurrentSession();
    Query statusQuery = session.createQuery("FROM Status");
    @SuppressWarnings("unchecked")
    List<Status> states = statusQuery.setReadOnly(true).list();
    if (states.isEmpty()) {
        return 0;
    }
    Status status = states.get(0);
    return status.getDbversion();
}


@Transactional(readOnly = true)
public List<Language> getLanguages(){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Language l order by l.code asc");
    @SuppressWarnings("unchecked")
    List<Language> languages = query.list();
    return languages;
}


public List<String> getAllPublishedSurveysUIDsForUser(User user,boolean escapeforsql,boolean includearchived,String type){
    Session session = sessionFactory.getCurrentSession();
    String ownerwhere = getOwnerWhere(type);
    String sql = "SELECT DISTINCT s.SURVEY_UID from SURVEYS s where s.ISDRAFT = 1 and (" + ownerwhere + ") and (s.DELETED = 0 or s.DELETED is null)";
    if (!includearchived) {
        sql += " and (s.ARCHIVED = 0 or s.ARCHIVED is null)";
    }
    Query query = session.createSQLQuery(sql);
    query.setInteger("userid", user.getId());
    if (type == null || !type.equalsIgnoreCase("my")) {
        query.setString("login", user.getLogin());
    }
    List<String> result = new ArrayList<>();
    @SuppressWarnings("rawtypes")
    List res = query.list();
    for (Object o : res) {
        if (escapeforsql) {
            result.add("'" + (String) o + "'");
        } else {
            result.add((String) o);
        }
    }
    return result;
}


@Transactional(readOnly = true)
public List<Survey> getPopularSurveys(SurveyFilter filter){
    filter.setSortKey("replies");
    SqlPagination sqlPagination = new SqlPagination(1, 5);
    return getSurveys(filter, sqlPagination);
}


@Transactional
public Set<String> getRankingQuestionUids(int surveyId){
    Survey survey = getSurvey(surveyId);
    Set<String> result = new HashSet<>();
    for (Element element : survey.getElements()) {
        if (element instanceof RankingQuestion) {
            result.add(element.getUniqueId());
        }
    }
    return result;
}


@Transactional(readOnly = true)
public Survey getSurveyForQuestion(String uid){
    Session session = sessionFactory.getCurrentSession();
    String sql = "SELECT DISTINCT s.SURVEY_UID FROM SURVEYS s JOIN SURVEYS_ELEMENTS se ON se.SURVEYS_SURVEY_ID = s.SURVEY_ID JOIN ELEMENTS e ON e.ID = se.elements_ID WHERE e.ELEM_UID = :uid";
    SQLQuery query = session.createSQLQuery(sql);
    query.setString("uid", uid);
    String result = (String) query.uniqueResult();
    if (result == null)
        return null;
    return getSurvey(result, true, false, false, false, null, true, false);
}


@Transactional(readOnly = true)
public Element getNewestElementByUid(String uid){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createSQLQuery("SELECT MAX(ID) FROM ELEMENTS WHERE ELEM_UID = :uid").setString("uid", uid);
    int elementid = ConversionTools.getValue(query.uniqueResult());
    if (elementid > 0) {
        return getElement(elementid);
    }
    return null;
}


public List<Survey> getAllSurveysForUser(User user){
    Session session = sessionFactory.getCurrentSession();
    String hql = "FROM Survey s WHERE s.isDraft = true AND s.owner.id = :userid";
    Query query = session.createQuery(hql);
    query.setInteger("userid", user.getId());
    @SuppressWarnings("unchecked")
    List<Survey> result = query.list();
    return result;
}


@Transactional(readOnly = true)
public Survey getSurveyInOriginalLanguage(Integer id,String shortname,String uid){
    String code = getLanguageCode(shortname, uid);
    return getSurvey(id, code);
}


@Transactional(readOnly = true)
public int getHighestSurveyId(){
    Session session = sessionFactory.getCurrentSession();
    String sql = "SELECT MAX(s.SURVEY_ID) FROM SURVEYS s";
    Query query = session.createSQLQuery(sql);
    int result = ConversionTools.getValue(query.uniqueResult());
    return result;
}


@Transactional
public Survey getSurvey(String uidorshortname,boolean isDraft,boolean checkActive,boolean readReplies,boolean useEagerLoading,String language,boolean readonly,boolean checkNotDeleted,boolean shortnamefirst,boolean synchronize){
    Session session = sessionFactory.getCurrentSession();
    String sql;
    if (shortnamefirst) {
        sql = "SELECT max(s.id) FROM Survey s WHERE (s.shortname = :uid OR s.uniqueId = :uid) AND s.isDraft = :draft";
    } else {
        sql = "SELECT max(s.id) FROM Survey s WHERE (s.uniqueId = :uid OR s.shortname = :uid) AND s.isDraft = :draft";
    }
    if (checkNotDeleted) {
        sql += " AND (s.isDeleted is null OR s.isDeleted = false)";
    }
    Query query = session.createQuery(sql).setString("uid", uidorshortname).setBoolean("draft", isDraft).setReadOnly(true);
    int id = ConversionTools.getValue(query.uniqueResult());
    if (id > 0) {
        Survey survey = (Survey) session.get(Survey.class, id);
        if (useEagerLoading) {
            initializeSurvey(survey);
        }
        // used e.g. in form runner to check whether disclaimer has to be displayed
        Hibernate.initialize(survey.getOwner().getRoles());
        if (readReplies) {
            survey.setNumberOfAnswerSetsPublished(answerService.getNumberOfAnswerSetsPublished(uidorshortname, survey.getUniqueId()));
        }
        if (survey.getIsActive() && survey.getAutomaticPublishing() && survey.getEnd() != null && survey.getEnd().before(new Date())) {
            survey.setIsActive(false);
            session.update(survey);
        } else if (!survey.getIsActive() && survey.getAutomaticPublishing() && survey.getStart() != null && survey.getStart().before(new Date()) && (survey.getEnd() == null || survey.getEnd().after(new Date()))) {
            survey.setIsActive(true);
            survey.setIsPublished(true);
            session.update(survey);
        }
        if (checkActive && !isDraft) {
            query = session.createQuery(sql).setString("uid", uidorshortname).setBoolean("draft", true);
            id = ConversionTools.getValue(query.uniqueResult());
            if (id == 0)
                return null;
            Survey draft = (Survey) session.get(Survey.class, id);
            if (!draft.getIsActive())
                return null;
        }
        session.setReadOnly(survey, readonly);
        for (Element e : survey.getElementsRecursive(true)) {
            e = (Element) session.merge(e);
            session.setReadOnly(e, readonly);
        }
        if (synchronize) {
            if (language == null) {
                synchronizeSurvey(survey, survey.getLanguage().getCode(), true);
            } else {
                synchronizeSurvey(survey, language, true);
            }
        }
        List<String> translations = translationService.getTranslationLanguagesForSurvey(survey.getId());
        survey.setTranslations(translations);
        return survey;
    }
    return null;
}


@Transactional
public int getAbuseReportsForSurvey(String surveyuid){
    Session session = sessionFactory.getCurrentSession();
    String sql = "SELECT COUNT(DISTINCT SURABUSE_ID) FROM SURABUSE WHERE SURABUSE_SURVEY = :uid";
    Query query = session.createSQLQuery(sql);
    query.setString("uid", surveyuid);
    return ConversionTools.getValue(query.uniqueResult());
}


@Transactional
public Survey getSurveyByUniqueIdToWrite(String uid){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT id FROM Survey s WHERE s.uniqueId = :uid AND s.isDraft = :draft ORDER BY s.id DESC").setString("uid", uid);
    query.setBoolean("draft", true);
    @SuppressWarnings("unchecked")
    List<Survey> list = query.setReadOnly(true).setMaxResults(1).list();
    if (!list.isEmpty()) {
        return getSurvey(ConversionTools.getValue(list.get(0)));
    }
    return null;
}


@Transactional(readOnly = true)
@SuppressWarnings("unchecked")
public List<Survey> getSurveysToStop(){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Survey s WHERE s.isDraft = true AND s.end <= :end AND s.automaticPublishing = true AND s.isPublished = true AND s.isActive = true").setTimestamp("end", new Date());
    return query.list();
}


@SuppressWarnings("unchecked")
@Transactional
public List<Integer> getSurveysMarkedDeleted(){
    Session session = sessionFactory.getCurrentSession();
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MONTH, -3);
    Date threemonthsago = cal.getTime();
    Query query = session.createQuery("SELECT s.id FROM Survey s WHERE s.isDraft = true AND s.isDeleted = true AND s.deleted < :threemonthsago");
    query.setParameter("threemonthsago", threemonthsago);
    return query.list();
}


@Transactional(readOnly = true)
public List<Survey> getSurveysIncludingTranslationLanguages(SurveyFilter filter,SqlPagination sqlPagination,boolean addInvitedAndDrafts,boolean addNumberOfReports){
    List<Survey> surveys = getSurveys(filter, sqlPagination);
    for (Survey survey : surveys) {
        survey.setTranslations(translationService.getTranslationLanguagesForSurvey(survey.getId(), false));
        survey.setCompleteTranslations(this.getCompletedTranslations(survey));
        if (addInvitedAndDrafts) {
            survey.setNumberOfInvitations(participationService.getNumberOfInvitations(survey.getUniqueId()));
            survey.setNumberOfDrafts(answerService.getNumberOfDrafts(survey.getId()));
        } else {
            survey.setNumberOfInvitations(-1);
            survey.setNumberOfDrafts(-1);
        }
        if (addNumberOfReports) {
            survey.setNumberOfReports(surveyService.getAbuseReportsForSurvey(survey.getUniqueId()));
        }
    }
    return surveys;
}


@Transactional(readOnly = true)
public List<Integer> getAllSurveyVersions(int surveyId){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createSQLQuery("SELECT s1.SURVEY_ID FROM SURVEYS s1 JOIN SURVEYS s2 ON s1.SURVEY_UID = s2.SURVEY_UID AND s1.ISDRAFT = s2.ISDRAFT WHERE s2.SURVEY_ID = :id").setInteger("id", surveyId);
    @SuppressWarnings("rawtypes")
    List res = query.list();
    List<Integer> result = new ArrayList<>();
    for (Object o : res) {
        result.add(ConversionTools.getValue(o));
    }
    return result;
}


@Transactional(readOnly = true)
public List<Survey> getSurveysToNotify(){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Survey s WHERE s.isDraft = true AND s.notified = false AND s.automaticPublishing = true AND s.end != null AND s.notificationValue != null AND s.notificationUnit != null AND s.isActive = true");
    @SuppressWarnings("unchecked")
    List<Survey> surveys = query.list();
    Calendar today = Calendar.getInstance();
    today.setTime(new Date());
    List<Survey> result = new ArrayList<>();
    for (Survey survey : surveys) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(survey.getEnd());
        int val = 0;
        try {
            val = -1 * Integer.parseInt(survey.getNotificationValue());
            if (survey.getNotificationUnit().equalsIgnoreCase("0")) {
                cal.add(Calendar.HOUR_OF_DAY, val);
            } else if (survey.getNotificationUnit().equalsIgnoreCase("1")) {
                cal.add(Calendar.DAY_OF_MONTH, val);
            } else if (survey.getNotificationUnit().equalsIgnoreCase("2")) {
                cal.add(Calendar.WEEK_OF_YEAR, val);
            } else if (survey.getNotificationUnit().equalsIgnoreCase("3")) {
                cal.add(Calendar.MONTH, val);
            }
        } catch (NumberFormatException e) {
            logger.error("invalid notification value found for survey " + survey.getId());
        }
        // check if it is time to notify
        if (today.after(cal)) {
            logger.info("found survey to notify: " + survey.getUniqueId() + " ending at " + survey.getEnd() + " " + survey.getNotificationValue() + " " + survey.getNotificationUnit());
            result.add(survey);
        }
    }
    return result;
}


public boolean getIsSurveyPublished(String shortname){
    Survey s = this.getSurvey(shortname, false, false, false, false, null, true, false);
    return s != null;
}


@Transactional(readOnly = true)
public Survey getSurveyReadOnly(int id,boolean loadTranslations,boolean setSurvey){
    return getSurvey(id, loadTranslations, true, true, setSurvey);
}


public String getSurveyMetaDataXML(Survey survey){
    int results = 0;
    if (this.isReportingDatabaseEnabled()) {
        results = reportingService.getCount(false, survey.getUniqueId());
    } else {
        results = surveyService.getNumberPublishedAnswersFromMaterializedView(survey.getUniqueId());
    }
    StringBuilder s = new StringBuilder();
    User owner = survey.getOwner();
    EcasHelper.readData(owner, this.ldapService);
    s.append("<?xml version='1.0' encoding='UTF-8' standalone='no' ?>\n");
    s.append("<Survey id='").append(survey.getId()).append("' alias='").append(survey.getShortname()).append("'>\n");
    s.append("<SurveyType>").append(survey.getIsQuiz() ? "Quiz" : (survey.getIsOPC() ? "BRP Public Consultation" : (survey.getIsDelphi() ? "Delphi" : "Standard"))).append("</SurveyType>\n");
    s.append("<Title>").append(survey.getTitle()).append("</Title>\n");
    s.append("<PivotLanguage>").append(survey.getLanguage().getCode().toUpperCase()).append("</PivotLanguage>\n");
    s.append("<Owner>").append(owner.getGivenName()).append(" ").append(owner.getSurName()).append("</Owner>\n");
    s.append("<OwnerDepartment>").append(owner.getDepartment()).append("</OwnerDepartment>\n");
    s.append("<Contact>").append(survey.getContact()).append("</Contact>\n");
    s.append("<Status>").append(survey.getIsPublished() && survey.getIsActive() ? "published" : "unpublished").append("</Status>\n");
    Survey published = surveyService.getSurvey(survey.getShortname(), false, false, false, false, null, true, false);
    s.append("<PendingChanges>").append(published != null && surveyService.getPendingChanges(survey).size() > 0 ? "yes" : "no").append("</PendingChanges>\n");
    s.append("<Start>").append(survey.getStart() == null ? "Unset" : ConversionTools.getFullString4Webservice(survey.getStart())).append("</Start>\n");
    s.append("<End>").append(survey.getEnd() == null ? "Unset" : ConversionTools.getFullString4Webservice(survey.getEnd())).append("</End>\n");
    s.append("<Results>").append(results).append("</Results>\n");
    s.append("<AutomaticPublishing>").append(survey.getAutomaticPublishing() ? "yes" : "no").append("</AutomaticPublishing>\n");
    s.append("<UseFulLinks>");
    for (String label : survey.getAdvancedUsefulLinks().keySet()) {
        s.append("<UseFulLink>\n");
        s.append("<url>").append(survey.getAdvancedUsefulLinks().get(label)).append("</url>\n");
        s.append("<label>").append(label).append("</label>\n");
        s.append("</UseFulLink>\n");
    }
    s.append("</UseFulLinks>\n");
    s.append("<BackgroundDocuments>");
    for (String label : survey.getBackgroundDocumentsAlphabetical().keySet()) {
        s.append("<BackgroundDocument>\n");
        s.append("<url>").append(survey.getBackgroundDocumentsAlphabetical().get(label)).append("</url>\n");
        s.append("<label>").append(label).append("</label>\n");
        s.append("</BackgroundDocument>\n");
    }
    s.append("</BackgroundDocuments>\n");
    s.append("<Security>");
    if (survey.getSecurity().startsWith("open")) {
        s.append("open");
    } else {
        s.append("secured");
        if (survey.getPassword() != null && survey.getPassword().length() > 0) {
            s.append(";PW:").append(survey.getPassword());
        }
        if (survey.getEcasSecurity()) {
            if (survey.getEcasMode() != null && survey.getEcasMode().equalsIgnoreCase("all")) {
                s.append(";EULogin_all");
            } else {
                s.append(";EULogin_internal");
            }
        }
    }
    s.append("</Security>\n");
    s.append("<Privacy>").append(survey.getSecurity().endsWith("anonymous") ? "yes" : "no").append("</Privacy>\n");
    s.append("<Visibility>").append(survey.getListForm() ? "public" : "private").append("</Visibility>\n");
    s.append("<Captcha>").append(survey.getCaptcha() ? "yes" : "no").append("</Captcha>\n");
    s.append("<Contribution>").append(survey.getChangeContribution() ? "yes" : "no").append("</Contribution>\n");
    s.append("<DownloadContribution>").append(survey.getDownloadContribution() ? "yes" : "no").append("</DownloadContribution>\n");
    s.append("<Draft>").append(survey.getSaveAsDraft() ? "yes" : "no").append("</Draft>\n");
    s.append("<Skin>").append(survey.getSkin() != null ? survey.getSkin().getName() : "no skin").append("</Skin>\n");
    s.append("<PublishedResults>");
    if (survey.getPublication() != null && (survey.getPublication().isShowContent() || survey.getPublication().isShowStatistics())) {
        boolean first = true;
        if (survey.getPublication().isShowContent()) {
            s.append("Answers");
            first = false;
        }
        if (survey.getPublication().isShowStatistics()) {
            if (!first)
                s.append(Constants.PATH_DELIMITER);
            s.append("Statistics");
            first = false;
        }
        if (survey.getPublication().isShowSearch()) {
            if (!first)
                s.append(Constants.PATH_DELIMITER);
            s.append("Search");
        }
    }
    s.append("</PublishedResults>\n");
    s.append("<ConfirmationPage>");
    if (survey.getConfirmationPageLink() != null && survey.getConfirmationPageLink()) {
        s.append(survey.getConfirmationLink());
    } else {
        s.append(survey.getConfirmationPage());
    }
    s.append("</ConfirmationPage>\n");
    s.append("<UnavailabilityPage>");
    if (survey.getEscapePageLink() != null && survey.getEscapePageLink()) {
        s.append(survey.getEscapeLink());
    } else {
        s.append(survey.getEscapePage());
    }
    s.append("</UnavailabilityPage>\n");
    s.append("</Survey>");
    return s.toString();
}


@Transactional(readOnly = true)
public List<String> getLanguageCodes(){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("Select l.code FROM Language l");
    @SuppressWarnings("unchecked")
    List<String> codes = query.list();
    return codes;
}


@Transactional(readOnly = true)
public Element getParentForChildQuestion(Integer id){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createSQLQuery("SELECT childElements_ID FROM ELEMENTS WHERE ID = :id").setInteger("id", id);
    int parentid = ConversionTools.getValue(query.uniqueResult());
    // questions could have been deleted from the parent -> get newest version of
    // the parent
    if (parentid > 0) {
        query = session.createSQLQuery("SELECT MAX(ID) FROM ELEMENTS WHERE ELEM_UID IN (SELECT ELEM_UID FROM ELEMENTS WHERE ID = :id)").setInteger("id", parentid);
        parentid = ConversionTools.getValue(query.uniqueResult());
    }
    return parentid > 0 ? getElement(parentid) : null;
}


public Map<Integer,Integer> getSourceIdsById(Survey publishedSurvey){
    HashMap<Integer, Integer> oldSourceIdsById = new HashMap<>();
    for (Element element : publishedSurvey.getElementsRecursive()) {
        oldSourceIdsById.put(element.getId(), element.getSourceId());
    }
    return oldSourceIdsById;
}


@Transactional(readOnly = true)
@SuppressWarnings("unchecked")
public List<Survey> getSurveysToStart(){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Survey s WHERE s.isDraft = true AND s.start <= :start AND ((s.end is not null AND s.end > :start) OR (s.end is null)) AND s.automaticPublishing = true AND s.isActive = false").setTimestamp("start", new Date());
    return query.list();
}


@Transactional(readOnly = true)
public String getLanguageCode(String shortname,String uid){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT s.language.code FROM Survey s WHERE s.uniqueId = :uid AND s.isDraft = true").setString("uid", uid);
    String result = (String) query.uniqueResult();
    if (result == null) {
        query = session.createQuery("SELECT s.language.code FROM Survey s WHERE s.shortname = :name AND s.isDraft = true").setString("name", shortname);
        result = (String) query.uniqueResult();
    }
    return result;
}


public List<Survey> getSurveysIncludingPublicationDates(SurveyFilter filter,SqlPagination sqlPagination){
    StringBuilder stringBuilder = new StringBuilder(1024);
    // 0
    stringBuilder.append("SELECT s.SURVEY_ID");
    // 1
    stringBuilder.append(", s.SURVEY_UID");
    // 2
    stringBuilder.append(", s.SURVEYNAME");
    // 3
    stringBuilder.append(", s.TITLE");
    // 4
    stringBuilder.append(", s.OWNER");
    // 5
    stringBuilder.append(", (SELECT USER_LOGIN FROM USERS u WHERE u.USER_ID = s.OWNER) as ownerlogin");
    // 6
    stringBuilder.append(", (SELECT USER_DISPLAYNAME FROM USERS u WHERE u.USER_ID = s.OWNER) as ownername");
    // 8
    stringBuilder.append(", s.SURVEYSECURITY");
    // 9
    stringBuilder.append(", s.ACTIVE");
    // 10
    stringBuilder.append(", s.FROZEN");
    stringBuilder.append(// 11
    ", (SELECT MIN(SURVEY_CREATED) FROM SURVEYS WHERE ISDRAFT = 0 AND SURVEY_UID = s.SURVEY_UID) as firstPublished");
    stringBuilder.append(// 12
    ", (SELECT MAX(SURVEY_CREATED) FROM SURVEYS WHERE ISDRAFT = 0 AND SURVEY_UID = s.SURVEY_UID) as published");
    stringBuilder.append(" ,s.ISPUBLISHED");
    if (!this.isReportingDatabaseEnabled()) {
        // 7
        stringBuilder.append(", npa.PUBLISHEDANSWERS as replies");
    }
    // 13
    stringBuilder.append(", s.SURVEY_DELETED");
    // 14
    stringBuilder.append(", s.SURVEY_CREATED");
    stringBuilder.append(// 15
    ", (SELECT COUNT(DISTINCT SURABUSE_ID) FROM SURABUSE WHERE SURABUSE_SURVEY = s.SURVEY_UID) as reported");
    stringBuilder.append(" from SURVEYS s");
    stringBuilder.append(" LEFT JOIN MV_SURVEYS_NUMBERPUBLISHEDANSWERS npa on s.SURVEY_UID = npa.SURVEYUID");
    stringBuilder.append(" where s.ISDRAFT = 1 AND ");
    String sql = stringBuilder.toString();
    if (filter.getSurveys() != null && filter.getSurveys().equalsIgnoreCase("ARCHIVED")) {
        sql += "(s.ARCHIVED = 1)";
    } else if (filter.getSurveys() != null && filter.getSurveys().equalsIgnoreCase("DELETED")) {
        sql += "(s.DELETED = 1)";
    } else if (filter.getSurveys() != null && filter.getSurveys().equalsIgnoreCase("REPORTED")) {
        sql += "(s.SURVEY_ID > 0)";
    // handled inside getSql
    } else {
        sql += "(s.ARCHIVED = 0 or s.ARCHIVED is null) and (s.DELETED = 0 or s.DELETED is null)";
    }
    HashMap<String, Object> parameters = new HashMap<>();
    sql += getSql(filter, parameters, true);
    List<Survey> surveys = new ArrayList<>();
    for (Object[] row : loadSurveysfromDatabase(sql, parameters, sqlPagination)) {
        Survey survey = new Survey();
        int columnNum = 0;
        survey.setId(ConversionTools.getValue(row[columnNum++]));
        survey.setUniqueId((String) row[columnNum++]);
        survey.setShortname((String) row[columnNum++]);
        survey.setTitle((String) row[columnNum++]);
        User user = new User();
        user.setId(ConversionTools.getValue(row[columnNum++]));
        user.setLogin((String) row[columnNum++]);
        user.setDisplayName((String) row[columnNum++]);
        survey.setOwner(user);
        survey.setSecurity((String) row[columnNum++]);
        survey.setIsActive((Boolean) row[columnNum++]);
        survey.setIsFrozen((Boolean) row[columnNum++]);
        survey.setFirstPublished((Date) row[columnNum++]);
        survey.setPublished((Date) row[columnNum++]);
        survey.setIsPublished((Boolean) row[columnNum++]);
        if (this.isReportingDatabaseEnabled()) {
            survey.setNumberOfAnswerSetsPublished(this.reportingService.getCount(false, survey.getUniqueId()));
        } else {
            survey.setNumberOfAnswerSetsPublished(ConversionTools.getValue(row[columnNum++]));
        }
        survey.setDeleted((Date) row[columnNum++]);
        survey.setCreated((Date) row[columnNum++]);
        survey.setNumberOfReports(ConversionTools.getValue(row[columnNum]));
        surveys.add(survey);
    }
    return surveys;
}


@Transactional(readOnly = true)
public Survey getSurveyByAlias(String alias,boolean draft){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT id FROM Survey s WHERE s.shortname = :alias AND s.isDraft = :draft ORDER BY s.id DESC").setString("alias", alias);
    query.setBoolean("draft", draft);
    @SuppressWarnings("unchecked")
    List<Survey> list = query.setReadOnly(true).setMaxResults(1).list();
    if (list.size() > 0) {
        Survey survey = getSurvey(ConversionTools.getValue(list.get(0)));
        if (survey != null) {
            synchronizeSurvey(survey, survey.getLanguage().getCode(), true);
        }
        session.setReadOnly(survey, true);
        for (Element e : survey.getElementsRecursive(true)) {
            session.setReadOnly(e, true);
        }
        return survey;
    }
    return null;
}


@Transactional(readOnly = true)
public Template getTemplate(int id){
    Session session = sessionFactory.getCurrentSession();
    return (Template) session.get(Template.class, id);
}


@Transactional(readOnly = true)
public Access getGroupAccess(Integer id,String groupName){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Access a WHERE a.survey.id = :id AND a.department = :groupName").setInteger("id", id).setString("groupName", groupName);
    @SuppressWarnings("unchecked")
    List<Access> result = query.list();
    if (!result.isEmpty())
        return result.get(0);
    return null;
}


@Transactional(readOnly = true)
public Map<String,String> getLanguageNamesByCode(){
    List<Language> languages = getLanguages();
    Map<String, String> result = new HashMap<>();
    for (Language language : languages) {
        if (!result.containsKey(language.getCode())) {
            result.put(language.getCode(), language.getEnglishName());
        }
    }
    return result;
}


public String getOwnerWhere(String type){
    String ownerwhere;
    if (type != null && type.equalsIgnoreCase("my")) {
        ownerwhere = "s.OWNER = :userid";
    } else if (type != null && type.equalsIgnoreCase("shared")) {
        ownerwhere = "s.SURVEY_ID in (Select a.SURVEY FROM SURACCESS a WHERE (a.ACCESS_USER = :userid OR a.ACCESS_DEPARTMENT IN (SELECT GRPS FROM ECASGROUPS WHERE eg_ID = (SELECT USER_ID FROM ECASUSERS WHERE USER_LOGIN = :login))) AND (a.ACCESS_PRIVILEGES like '%2%' or a.ACCESS_PRIVILEGES like '%1%'))";
    } else {
        ownerwhere = "s.OWNER = :userid OR s.SURVEY_ID in (Select a.SURVEY FROM SURACCESS a WHERE (a.ACCESS_USER = :userid OR a.ACCESS_DEPARTMENT IN (SELECT GRPS FROM ECASGROUPS WHERE eg_ID = (SELECT USER_ID FROM ECASUSERS WHERE USER_LOGIN = :login))) AND (a.ACCESS_PRIVILEGES like '%2%' or a.ACCESS_PRIVILEGES like '%1%'))";
    }
    return ownerwhere;
}


public int[] getSurveyStatisticsForUser(User user,String type){
    int[] result = new int[5];
    // published
    // unpublished
    // archived
    // pending changes
    Session session = sessionFactory.getCurrentSession();
    String ownerwhere = getOwnerWhere(type);
    String sql = "SELECT s.SURVEY_ID, s.ACTIVE, s.ISPUBLISHED, s.ARCHIVED, s.HASPENDINGCHANGES from SURVEYS s where s.ISDRAFT = 1 and (" + ownerwhere + ") and (s.DELETED = 0 or s.DELETED is null)";
    Query query = session.createSQLQuery(sql);
    query.setInteger("userid", user.getId());
    if (type == null || !type.equalsIgnoreCase("my")) {
        query.setString("login", user.getLogin());
    }
    @SuppressWarnings("rawtypes")
    List res = query.list();
    for (Object o : res) {
        Object[] a = (Object[]) o;
        boolean active = (boolean) a[1];
        boolean archived = a[3] != null && (boolean) a[3];
        boolean pending = (boolean) a[4];
        if (archived) {
            result[2]++;
        } else {
            if (active) {
                result[0]++;
            } else {
                result[1]++;
            }
            if (pending)
                result[3]++;
        }
        result[4]++;
    }
    List<Archive> archives = archiveService.getArchivesForUser(user.getId());
    result[2] += archives.size();
    result[4] += archives.size();
    return result;
}


@Override
public String getFileDir(){
    return fileDir;
}


@Transactional(readOnly = true)
public int getNumberPublishedAnswersFromMaterializedView(String uid){
    Session session = sessionFactory.getCurrentSession();
    String sql = "SELECT npa.PUBLISHEDANSWERS FROM MV_SURVEYS_NUMBERPUBLISHEDANSWERS npa WHERE npa.SURVEYUID = :uid";
    SQLQuery query = session.createSQLQuery(sql);
    query.setString("uid", uid);
    List<?> res = (List<?>) query.list();
    if (!res.isEmpty())
        return ConversionTools.getValue(res.get(0));
    return 0;
}


@Transactional(readOnly = true)
public List<Survey> getAllECFSurveys(){
    Session session = sessionFactory.getCurrentSession();
    String sql = "FROM Survey s WHERE s.isECF = true";
    Query query = session.createQuery(sql);
    @SuppressWarnings("unchecked")
    List<Survey> result = query.list();
    return result;
}


@Transactional
public void save(Language objLang){
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(objLang);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))

.queryParam("objLang",objLang)
;
restTemplate.put(builder.toUriString(),null);
}


@Transactional
public void saveLanguages(List<Language> langs){
    Session session = sessionFactory.getCurrentSession();
    for (Language l : langs) {
        try {
            session.save(l);
        } catch (Exception e) {
            logger.error("language " + l.getEnglishName() + " could not be imported");
        }
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveLanguages"))

.queryParam("langs",langs)
;
restTemplate.put(builder.toUriString(),null);
}


}