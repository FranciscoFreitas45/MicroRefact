package com.ec.survey.service;
 import com.ec.survey.exception.MessageException;
import com.ec.survey.exception.SmtpServerNotConfiguredException;
import com.ec.survey.exception.TooManyFiltersException;
import com.ec.survey.model;
import com.ec.survey.model.ResultFilter.ResultFilterSortKey;
import com.ec.survey.model.administration.Role;
import com.ec.survey.model.administration.User;
import com.ec.survey.model.attendees.Attendee;
import com.ec.survey.model.attendees.Attribute;
import com.ec.survey.model.attendees.AttributeName;
import com.ec.survey.model.attendees.Invitation;
import com.ec.survey.model.delphi.DelphiMedian;
import com.ec.survey.model.survey;
import com.ec.survey.model.survey.base.File;
import com.ec.survey.service.ReportingService.ToDo;
import com.ec.survey.tools.Constants;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.FileUtils;
import com.ec.survey.tools.InvalidEmailException;
import com.ec.survey.tools.MathUtils;
import com.ec.survey.tools.NotAgreedToPsException;
import com.ec.survey.tools.NotAgreedToTosException;
import com.ec.survey.tools.SurveyHelper;
import com.ec.survey.tools.Tools;
import com.ec.survey.tools.WeakAuthenticationException;
import com.ec.survey.tools.export.StatisticsCreator;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util;
import java.util.Map.Entry;
import com.ec.survey.Interface.AttendeeService;
import com.ec.survey.Interface.SqlQueryService;
import com.ec.survey.Interface.ValidCodesService;
import com.ec.survey.DTO.ResultFilter;
import com.ec.survey.DTO.RatingQuestion;
import com.ec.survey.DTO.MatrixOrTable;
import com.ec.survey.DTO.Section;
import com.ec.survey.DTO.FreeTextQuestion;
import com.ec.survey.DTO.Attendee;
import com.ec.survey.DTO.Question;
import com.ec.survey.DTO.AttributeName;
import com.ec.survey.DTO.Attribute;
import com.ec.survey.DTO.ChoiceQuestion;
import com.ec.survey.DTO.Role;
import com.ec.survey.DTO.Invitation;
import com.ec.survey.DTO.ParticipationGroup;
import com.ec.survey.DTO.NumberQuestion;
import com.ec.survey.DTO.DelphiMedian;
import com.ec.survey.DTO.StatisticsCreator;
import com.ec.survey.DTO.Statistics;
@Service("answerService")
public class AnswerService extends BasicService{

@Resource(name = "attendeeService")
 private  AttendeeService attendeeService;

@Autowired
 private  SqlQueryService sqlQueryService;

@Resource(name = "validCodesService")
 private  ValidCodesService validCodesService;


@Transactional
public List<String> deleteAnswers(List<String> answerSetsUIDS,int surveyid){
    List<String> deletedAnswerSets = new ArrayList<>();
    if (answerSetsUIDS == null || answerSetsUIDS.isEmpty()) {
        return deletedAnswerSets;
    }
    Session session = sessionFactory.getCurrentSession();
    StringBuilder sql = new StringBuilder("From AnswerSet a where a.uniqueCode in (");
    int counter = 0;
    for (String uniqueCode : answerSetsUIDS) {
        if (counter > 0) {
            sql.append(", ");
        }
        sql.append("'").append(uniqueCode).append("'");
        counter++;
    }
    sql.append(")");
    Query query = session.createQuery(sql.toString());
    @SuppressWarnings("unchecked")
    List<AnswerSet> answerSets = query.list();
    Survey survey = surveyService.getSurvey(surveyid, false, true);
    if (survey != null && survey.getIsDelphi()) {
        answerSets.forEach(set -> answerExplanationService.deleteExplanationByAnswerSet(set));
    }
    for (AnswerSet answerSet : answerSets) {
        try {
            this.deleteAnswer(answerSet, false);
            deletedAnswerSets.add(answerSet.getUniqueCode());
        } catch (IOException | MessageException e) {
            e.printStackTrace();
        }
    }
    this.deleteStatisticsForSurvey(surveyid);
    return deletedAnswerSets;
}


public String getDraftForEcasLogin(Survey survey,HttpServletRequest request){
    Session session = sessionFactory.getCurrentSession();
    String sql = "SELECT d.DRAFT_UID FROM DRAFTS d JOIN ANSWERS_SET a ON d.answerSet_ANSWER_SET_ID = a.ANSWER_SET_ID WHERE (a.RESPONDER_EMAIL = :email or a.RESPONDER_EMAIL = :email2) AND a.SURVEY_ID IN (:ids)";
    SQLQuery query = session.createSQLQuery(sql);
    User user = sessionService.getCurrentUser(request, false, false);
    if (user == null)
        return null;
    List<Integer> ids = surveyService.getAllSurveyVersions(survey.getId());
    query.setString(Constants.EMAIL, user.getEmail());
    query.setString("email2", Tools.md5hash(user.getEmail()));
    query.setParameterList("ids", ids);
    @SuppressWarnings("unchecked")
    List<String> result = query.list();
    if (!result.isEmpty()) {
        // if multiple contributions are allowed there can be several drafts for the same user
        // we skip those that have already been submitted
        for (String draftUid : result) {
            Draft draft = answerService.getDraft(draftUid);
            String answerUid = draft.getAnswerSet().getUniqueCode();
            AnswerSet answerSet = answerService.get(answerUid);
            if (answerSet == null) {
                return draftUid;
            }
        }
    }
    return null;
}


@Transactional
public void deleteStatisticsRequest(StatisticsRequest statisticsRequest){
    Session session = sessionFactory.getCurrentSession();
    session.delete(statisticsRequest);
}


@Transactional
public Statistics getStatistics(int requestid){
    StatisticsRequest statisticsRequest = getStatisticRequest(requestid);
    if (statisticsRequest == null)
        return null;
    Statistics statistics = this.getStatisticsForFilterHash(statisticsRequest.getSurveyId(), statisticsRequest.getFilter().getHash(statisticsRequest.isAllanswers()), false);
    if (statistics != null) {
        this.deleteStatisticsRequest(statisticsRequest);
    }
    return statistics;
}


public int getNumberOfDraftsInner(List<Integer> allVersions){
    Session session = sessionFactory.getCurrentSession();
    if (allVersions.isEmpty()) {
        return 0;
    }
    String query = "SELECT count(*) FROM ANSWERS_SET ans WHERE ans.SURVEY_ID IN (" + StringUtils.collectionToCommaDelimitedString(allVersions) + ") AND ans.ISDRAFT = 1 AND ans.UNIQUECODE NOT IN (SELECT UNIQUECODE FROM ANSWERS_SET ans2 WHERE SURVEY_ID IN (" + StringUtils.collectionToCommaDelimitedString(allVersions) + ") AND ans2.ISDRAFT = 0)";
    Query q = session.createSQLQuery(query);
    return ConversionTools.getValue(q.uniqueResult());
}


@Transactional
public WrongAttempts getWrongAttempts(String ip){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT a FROM WrongAttempts a WHERE a.ip = :ip").setString("ip", ip);
    @SuppressWarnings("unchecked")
    List<WrongAttempts> list = query.list();
    if (list.isEmpty()) {
        return null;
    }
    if (list.size() > 1) {
        throw new MessageException("Multiple WrongAttempts found for ip " + ip);
    }
    WrongAttempts result = list.get(0);
    if (result.getLockDate() != null) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -1);
        Date yesterday = cal.getTime();
        if (result.getLockDate().before(yesterday)) {
            result.setCounter(0);
            result.setlockDate(null);
            session.update(result);
        }
    }
    return result;
}


public void internalParseForCompletionRates(String uniqueCode,String questionUid,List<String> listOfAnswer,Map<String,Integer> answersByQuestion,Map<String,List<String>> sectionsByQuestion,Map<String,String> parentByQuestion,Map<String,Map<String,List<String>>> questionUidsPerAnswerAndSection){
    if (parentByQuestion.containsKey(questionUid)) {
        questionUid = parentByQuestion.get(questionUid);
    }
    if (answersByQuestion.containsKey(questionUid) && !listOfAnswer.contains(questionUid)) {
        listOfAnswer.add(questionUid);
        answersByQuestion.put(questionUid, answersByQuestion.get(questionUid) + 1);
    }
    if (sectionsByQuestion.containsKey(questionUid)) {
        List<String> sections = sectionsByQuestion.get(questionUid);
        for (String section : sections) {
            if (!questionUidsPerAnswerAndSection.containsKey(section)) {
                questionUidsPerAnswerAndSection.put(section, new HashMap<String, List<String>>());
            }
            Map<String, List<String>> questionUidsPerAnswer = questionUidsPerAnswerAndSection.get(section);
            if (!questionUidsPerAnswer.containsKey(uniqueCode)) {
                questionUidsPerAnswer.put(uniqueCode, new ArrayList<String>());
            }
            if (!questionUidsPerAnswer.get(uniqueCode).contains(questionUid)) {
                questionUidsPerAnswer.get(uniqueCode).add(questionUid);
            }
        }
    }
}


@Transactional(readOnly = true)
public boolean getHasPublishedAnswers(String uid){
    Session session = sessionFactory.getCurrentSession();
    SQLQuery query = session.createSQLQuery("SELECT ANSWERS_SET.ANSWER_SET_ID FROM ANSWERS_SET inner join SURVEYS s on ANSWERS_SET.SURVEY_ID = s.SURVEY_ID where ANSWERS_SET.ISDRAFT = 0 AND s.SURVEY_UID = :uid AND s.ISDRAFT = 0 LIMIT 1");
    query.setString("uid", uid);
    @SuppressWarnings("rawtypes")
    List result = query.list();
    return !result.isEmpty();
}


@Transactional(readOnly = true)
public Date getNewestTestAnswerDate(int surveyId){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT max(a.updateDate) FROM AnswerSet a WHERE a.surveyId = " + surveyId + " AND a.isDraft = 0");
    return (Date) query.uniqueResult();
}


@Transactional(readOnly = true)
public List<AnswerSet> getDraftAnswers(int surveyId,ResultFilter filter,SqlPagination sqlPagination,boolean loadDraftIds,boolean initFiles){
    Session session = sessionFactory.getCurrentSession();
    HashMap<String, Object> parameters = new HashMap<>();
    boolean useSurveysTable = false;
    boolean useDraftSurveysTable = false;
    String sql;
    StringBuilder where = new StringBuilder();
    if (filter.getUser().indexOf(';') > 0) {
        where.append("ans.RESPONDER_EMAIL IN (:emails)");
    } else {
        where.append("(ans.RESPONDER_EMAIL = :email)");
    }
    if (filter.getUpdatedFrom() != null) {
        where.append(" AND ans.ANSWER_SET_UPDATE >= :updateDateFrom");
        parameters.put("updateDateFrom", filter.getUpdatedFrom());
    }
    if (filter.getUpdatedTo() != null) {
        where.append(" AND ans.ANSWER_SET_UPDATE < :updateDateTo");
        parameters.put("updateDateTo", Tools.getFollowingDay(filter.getUpdatedTo()));
    }
    if (filter.getSurveyShortname() != null && filter.getSurveyShortname().length() > 0) {
        useSurveysTable = true;
        where.append(" AND s.SURVEYNAME LIKE :surveyAlias");
        parameters.put("surveyAlias", "%" + filter.getSurveyShortname().trim() + "%");
    }
    if (filter.getSurveyTitle() != null && filter.getSurveyTitle().length() > 0) {
        useSurveysTable = true;
        where.append(" AND s.TITLESORT LIKE :surveyTitle");
        parameters.put("surveyTitle", "%" + filter.getSurveyTitle().trim() + "%");
    }
    if (filter.getSurveyStatus() != null && filter.getSurveyStatus().length() > 0 && !filter.getSurveyStatus().equalsIgnoreCase("All")) {
        useSurveysTable = true;
        useDraftSurveysTable = true;
        where.append(" AND d.ACTIVE = :surveyActive");
        parameters.put("surveyActive", filter.getSurveyStatus().equalsIgnoreCase("Published") ? 1 : 0);
    }
    if (filter.getSurveyEndDateFrom() != null) {
        useSurveysTable = true;
        where.append(" AND s.SURVEY_END_DATE >= :endFrom");
        parameters.put("endFrom", filter.getSurveyEndDateFrom());
    }
    if (filter.getSurveyEndDateTo() != null) {
        useSurveysTable = true;
        where.append(" AND s.SURVEY_END_DATE <= :endTo");
        parameters.put("endTo", filter.getSurveyEndDateTo());
    }
    String joinSurveys = "";
    if (useSurveysTable)
        joinSurveys = " JOIN SURVEYS s ON ans.SURVEY_ID = s.SURVEY_ID";
    if (useDraftSurveysTable)
        joinSurveys += " JOIN SURVEYS d ON d.SURVEY_UID = s.SURVEY_UID AND d.ISDRAFT = 1";
    sql = "SELECT ans.ANSWER_SET_ID FROM ANSWERS_SET ans " + joinSurveys + " WHERE " + where.toString() + " AND  ans.ISDRAFT = 1 AND ans.UNIQUECODE NOT IN (SELECT ans.UNIQUECODE FROM ANSWERS_SET ans WHERE " + where.toString() + " AND ans.ISDRAFT = 0) ORDER BY ans.ANSWER_SET_UPDATE DESC";
    if (filter.getUser().indexOf(';') > 0) {
        parameters.put("emails", filter.getUser().trim().split(";"));
    } else {
        parameters.put(Constants.EMAIL, filter.getUser());
    }
    SQLQuery query = session.createSQLQuery(sql);
    sqlQueryService.setParameters(query, parameters);
    @SuppressWarnings("rawtypes")
    List res = query.setFirstResult(sqlPagination.getFirstResult()).setMaxResults(sqlPagination.getMaxResult()).list();
    List<AnswerSet> result = new ArrayList<>();
    for (Object o : res) {
        Integer i = ConversionTools.getValue(o);
        AnswerSet answerSet = (AnswerSet) session.get(AnswerSet.class, i);
        result.add(answerSet);
    }
    return result;
}


@Transactional(readOnly = false)
public String resetContribution(String code){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT a FROM AnswerSet a WHERE a.isDraft = false AND a.uniqueCode = :uniqueCode order by date DESC").setString(Constants.UNIQUECODE, code);
    @SuppressWarnings("unchecked")
    List<AnswerSet> list1 = query.list();
    if (list1.isEmpty()) {
        return null;
    }
    AnswerSet answerSet = list1.get(0);
    if (answerSet != null) {
        query = session.createQuery("FROM Draft d WHERE d.answerSet.uniqueCode = :uniqueCode order by answerSet.date DESC").setString(Constants.UNIQUECODE, code);
        @SuppressWarnings("unchecked")
        List<Draft> list = query.list();
        if (answerSet.getInvitationId() != null) {
            attendeeService.decreaseInvitationAnswer(answerSet.getInvitationId());
        }
        if (list.isEmpty()) {
            // there is no draft
            Draft draft = new Draft();
            String uniqueCode = UUID.randomUUID().toString();
            draft.setUniqueId(uniqueCode);
            answerSet.setIsDraft(true);
            draft.setAnswerSet(answerSet);
            session.saveOrUpdate(draft);
            reportingService.addToDo(ToDo.DELETEDCONTRIBUTION, answerSet.getSurvey().getUniqueId(), answerSet.getUniqueCode());
            return draft.getUniqueId();
        } else {
            Draft draft = list.get(0);
            if (draft != null) {
                String uid = draft.getUniqueId();
                session.delete(draft);
                session.flush();
                draft = new Draft();
                draft.setUniqueId(uid);
                answerSet.setIsDraft(true);
                draft.setAnswerSet(answerSet);
                session.saveOrUpdate(draft);
                reportingService.addToDo(ToDo.DELETEDCONTRIBUTION, answerSet.getSurvey().getUniqueId(), answerSet.getUniqueCode());
                return uid;
            }
        }
    }
    return null;
}


public String getSql(String prefix,int surveyId,ResultFilter filter,Map<String,Object> values,boolean searchallsurveys){
    if (prefix == null || prefix.length() == 0) {
        prefix = "SELECT DISTINCT ans.ANSWER_SET_ID";
    }
    StringBuilder sql = new StringBuilder(prefix + " FROM ANSWERS a1");
    StringBuilder where;
    int joincounter = 0;
    boolean useSurveysTable = false;
    boolean useDraftSurveysTable = false;
    boolean usePublicationsTable = false;
    String joinSurveys = "";
    if (surveyId > -1) {
        where = new StringBuilder(" ans.SURVEY_ID = :surveyId AND ans.ISDRAFT = false");
        List<Integer> allVersions = surveyService.getAllSurveyVersions(surveyId);
        if (searchallsurveys && allVersions.size() > 1) {
            where = new StringBuilder(" ans.SURVEY_ID IN (" + StringUtils.collectionToCommaDelimitedString(allVersions) + ") AND ans.ISDRAFT = false");
        } else {
            values.put("surveyId", surveyId);
        }
    } else {
        if (filter.getNoTestAnswers()) {
            useSurveysTable = true;
            where = new StringBuilder("s.ISDRAFT = 0");
        } else {
            where = new StringBuilder("ans.SURVEY_ID > 0");
        }
        if (filter.getSurveyUid() != null && filter.getSurveyUid().length() > 0) {
            useSurveysTable = true;
            where.append(" AND s.SURVEY_UID = :surveyUID");
            values.put("surveyUID", filter.getSurveyUid().trim());
        }
        if (filter.getSurveyShortname() != null && filter.getSurveyShortname().length() > 0) {
            useSurveysTable = true;
            where.append(" AND s.SURVEYNAME LIKE :surveyAlias");
            values.put("surveyAlias", "%" + filter.getSurveyShortname().trim() + "%");
        }
        if (filter.getSurveyTitle() != null && filter.getSurveyTitle().length() > 0) {
            useSurveysTable = true;
            where.append(" AND s.TITLESORT LIKE :surveyTitle");
            values.put("surveyTitle", "%" + filter.getSurveyTitle().trim() + "%");
        }
        if (filter.getSurveyStatus() != null && filter.getSurveyStatus().length() > 0 && !filter.getSurveyStatus().equalsIgnoreCase("All")) {
            useSurveysTable = true;
            useDraftSurveysTable = true;
            where.append(" AND d.ACTIVE = :surveyActive");
            values.put("surveyActive", filter.getSurveyStatus().equalsIgnoreCase("Published") ? 1 : 0);
        }
        if (filter.getSurveyEndDateFrom() != null) {
            useSurveysTable = true;
            where.append(" AND s.SURVEY_END_DATE >= :endFrom");
            values.put("endFrom", filter.getSurveyEndDateFrom());
        }
        if (filter.getSurveyEndDateTo() != null) {
            useSurveysTable = true;
            where.append(" AND s.SURVEY_END_DATE <= :endTo");
            values.put("endTo", filter.getSurveyEndDateTo());
        }
        if (filter.getSurveyPublishedResults() != null && filter.getSurveyPublishedResults().length() > 0 && !filter.getSurveyPublishedResults().equalsIgnoreCase("All")) {
            usePublicationsTable = true;
            useSurveysTable = true;
            where.append(" AND (p.PUB_CONT = 1 || p.PUB_STAT = 1)");
        }
    }
    if (filter != null) {
        if (filter.getFilterValues() != null && filter.getFilterValues().size() > 3) {
            throw new TooManyFiltersException("too many result filters");
        }
        if (filter.getStatus() != null && filter.getStatus().length() > 0 && !filter.getStatus().equalsIgnoreCase("All")) {
            where.append(" AND ans.ISDRAFT = :status");
            values.put("status", filter.getStatus().equalsIgnoreCase("Submitted") ? 0 : 1);
        }
        if (filter.getInvitation() != null && filter.getInvitation().length() > 0) {
            where.append(" AND ans.ANSWER_SET_INVID = :invitationId");
            values.put("invitationId", filter.getInvitation().trim());
        }
        if (filter.getCaseId() != null && filter.getCaseId().length() > 0) {
            where.append(" AND ans.UNIQUECODE = :uniqueCode");
            values.put(Constants.UNIQUECODE, filter.getCaseId().trim());
        }
        if (filter.getAnsweredECFProfileUID() != null && filter.getAnsweredECFProfileUID().length() > 0) {
            where.append(" AND ans.ECF_PROFILE_UID = :profileUid");
            values.put("profileUid", filter.getAnsweredECFProfileUID());
        }
        if (filter.getDraftId() != null && filter.getDraftId().length() > 0) {
            where.append(" AND d.DRAFT_UID = :draftId");
            values.put("draftId", filter.getDraftId().trim());
        }
        if (filter.getUser() != null && filter.getUser().length() > 0) {
            if (filter.getUser().indexOf(';') > 0) {
                where.append(" AND ans.RESPONDER_EMAIL IN (:emails)");
                values.put("emails", filter.getUser().trim().split(";"));
            } else {
                where.append(" AND ans.RESPONDER_EMAIL = :email");
                values.put(Constants.EMAIL, filter.getUser().trim());
            }
        }
        if (filter.getCreatedOrUpdated() != null && filter.getCreatedOrUpdated() && filter.getGeneratedFrom() != null && filter.getGeneratedTo() != null && filter.getUpdatedFrom() != null && filter.getUpdatedTo() != null) {
            where.append(" AND ((ans.ANSWER_SET_DATE >= :generatedFrom AND ans.ANSWER_SET_DATE < :generatedTo) OR (ans.ANSWER_SET_UPDATE >= :updateDateFrom AND ans.ANSWER_SET_UPDATE < :updateDateTo))");
            values.put("generatedFrom", filter.getGeneratedFrom());
            values.put("generatedTo", Tools.getFollowingDay(filter.getGeneratedTo()));
            values.put("updateDateFrom", filter.getUpdatedFrom());
            values.put("updateDateTo", Tools.getFollowingDay(filter.getUpdatedTo()));
        } else {
            if (filter.getGeneratedFrom() != null) {
                where.append(" AND ans.ANSWER_SET_DATE >= :generatedFrom");
                values.put("generatedFrom", filter.getGeneratedFrom());
            }
            if (filter.getGeneratedTo() != null) {
                where.append(" AND ans.ANSWER_SET_DATE < :generatedTo");
                values.put("generatedTo", Tools.getFollowingDay(filter.getGeneratedTo()));
            }
            if (filter.getUpdatedFrom() != null) {
                where.append(" AND ans.ANSWER_SET_UPDATE >= :updateDateFrom");
                values.put("updateDateFrom", filter.getUpdatedFrom());
            }
            if (filter.getUpdatedTo() != null) {
                where.append(" AND ans.ANSWER_SET_UPDATE < :updateDateTo");
                values.put("updateDateTo", Tools.getFollowingDay(filter.getUpdatedTo()));
            }
            if (filter.getOnlyReallyUpdated() != null && filter.getOnlyReallyUpdated()) {
                where.append(" AND ans.ANSWER_SET_DATE != ans.ANSWER_SET_UPDATE");
            }
        }
        if (filter.getLanguages() != null && !filter.getLanguages().isEmpty()) {
            int i = 0;
            where.append(" AND (");
            for (String lang : filter.getLanguages()) {
                if (lang.trim().length() > 0) {
                    String l = "lang" + i++;
                    if (i > 1) {
                        where.append(" OR");
                    }
                    where.append(" ans.ANSWER_SET_LANG like :").append(l);
                    values.put(l, lang.trim());
                }
            }
            where.append(" )");
        }
        Map<String, String> filterValues = filter.getFilterValues();
        if (filterValues != null && filterValues.size() > 0) {
            Set<String> rankingQuestionUids = surveyId > -1 ? surveyService.getRankingQuestionUids(surveyId) : new HashSet<>();
            int i = 0;
            for (Entry<String, String> item : filterValues.entrySet()) {
                String questionIdAndUid = item.getKey();
                String questionId = questionIdAndUid.substring(0, questionIdAndUid.indexOf('|'));
                String questionUid = questionIdAndUid.substring(questionIdAndUid.indexOf('|') + 1);
                String answersasstring = item.getValue();
                String[] answers = StringUtils.delimitedListToStringArray(answersasstring, ";");
                if (answersasstring.replace(";", "").trim().length() > 0 && answers.length > 0) {
                    where.append(" AND (");
                    boolean first = true;
                    joincounter++;
                    if (joincounter > 1) {
                        sql.append(" JOIN ANSWERS a").append(joincounter).append(" ON a1.AS_ID = a").append(joincounter).append(".AS_ID");
                    }
                    for (String answer : answers) if (answer.trim().length() > 0) {
                        if (first) {
                            where.append(" (");
                        } else {
                            where.append(" OR (");
                        }
                        if (questionUid.endsWith("from")) {
                            String answerPart = "(STR_TO_DATE(a" + joincounter + ".VALUE,'%d/%m/%Y') >= STR_TO_DATE(:answer" + i + ",'%d/%m/%Y'))";
                            questionUid = questionUid.replace("from", "");
                            where.append(" (a").append(joincounter).append(".QUESTION_UID = :questionUid").append(i).append(" AND ").append(answerPart).append(")");
                            values.put("questionUid" + i, questionUid);
                            values.put(Constants.ANSWER + i, answer);
                        } else if (questionUid.endsWith("to")) {
                            String answerPart = "(STR_TO_DATE(a" + joincounter + ".VALUE,'%d/%m/%Y') <= STR_TO_DATE(:answer" + i + ",'%d/%m/%Y'))";
                            questionUid = questionUid.replace("to", "");
                            where.append(" (a").append(joincounter).append(".QUESTION_UID = :questionUid").append(i).append(" AND ").append(answerPart).append(")");
                            values.put("questionUid" + i, questionUid);
                            values.put(Constants.ANSWER + i, answer);
                        } else {
                            String answerPart = "a" + joincounter + ".VALUE like :answer" + i;
                            if (answer.contains("|")) {
                                String answerUid = answer.substring(answer.indexOf('|') + 1);
                                answerPart = "(a" + joincounter + ".PA_UID like :answerUid" + i + ")";
                                values.put("answerUid" + i, answerUid);
                            } else {
                                if (answer.contains(Constants.PATH_DELIMITER)) {
                                    answerPart = "a" + joincounter + ".VALUE LIKE :answer" + i;
                                    values.put(Constants.ANSWER + i, "%" + answer + "%");
                                } else {
                                    // the filter on ranking questions is basically the first element in the sorted list
                                    if (rankingQuestionUids.contains(questionUid)) {
                                        values.put(Constants.ANSWER + i, answer + "%");
                                    } else {
                                        values.put(Constants.ANSWER + i, "%" + answer + "%");
                                    }
                                }
                            }
                            if (questionId.contains("-")) {
                                String[] data = questionId.split("-");
                                if (questionUid.length() > 0) {
                                    where.append(" (a").append(joincounter).append(".ANSWER_ROW = :row").append(i).append(" AND a").append(joincounter).append(".ANSWER_COL = :col").append(i).append(" AND (a").append(joincounter).append(".QUESTION_ID = :questionId").append(i).append(" OR a").append(joincounter).append(".QUESTION_UID = :questionUid").append(i).append(") AND ").append(answerPart).append(")");
                                    values.put("questionUid" + i, questionUid);
                                } else {
                                    where.append(" (a").append(joincounter).append(".ANSWER_ROW = :row").append(i).append(" AND a").append(joincounter).append(".ANSWER_COL = :col").append(i).append(" AND a").append(joincounter).append(".QUESTION_ID = :questionId").append(i).append(" AND ").append(answerPart).append(")");
                                }
                                values.put("questionId" + i, data[0]);
                                values.put("row" + i, data[1]);
                                values.put("col" + i, data[2]);
                            } else {
                                if (questionUid.length() > 0) {
                                    where.append(" (a").append(joincounter).append(".QUESTION_UID = :questionUid").append(i).append(" AND ").append(answerPart).append(")");
                                    values.put("questionUid" + i, questionUid);
                                } else {
                                    where.append("( a").append(joincounter).append(".QUESTION_ID = :questionId").append(i).append(" AND ").append(answerPart).append(")");
                                    values.put("questionId" + i, questionId);
                                }
                            }
                        }
                        i++;
                        first = false;
                        where.append(" )");
                    }
                    where.append(" )");
                }
            }
        }
        switch(ResultFilterSortKey.parse(filter.getSortKey())) {
            case NAME:
                where.append(" ORDER BY CASE WHEN ans.RESPONDER_EMAIL IS NULL THEN ans.UNIQUECODE ELSE ans.RESPONDER_EMAIL END ").append(filter.getSortOrder());
                break;
            case SCORE:
                where.append(" ORDER BY ans.SCORE ").append(filter.getSortOrder());
                break;
            case DATE:
                where.append(" ORDER BY ans.ANSWER_SET_DATE ").append(filter.getSortOrder());
                break;
            case ECFSCORE:
                where.append(" ORDER BY ans.ECF_TOTAL_SCORE ").append(filter.getSortOrder());
                break;
            case ECFGAP:
                where.append(" ORDER BY ABS(ans.ECF_TOTAL_GAP) ").append(filter.getSortOrder());
                break;
            default:
        }
    }
    // if flag is set then we need to use the join with the surveys table
    if (useSurveysTable)
        joinSurveys = " JOIN SURVEYS s ON ans.SURVEY_ID = s.SURVEY_ID";
    if (useDraftSurveysTable)
        joinSurveys += " JOIN SURVEYS d ON d.SURVEY_UID = s.SURVEY_UID AND d.ISDRAFT = 1";
    if (usePublicationsTable)
        joinSurveys += " JOIN PUBLICATION p ON p.PUB_ID = s.publication_PUB_ID";
    if (prefix.contains("inv.")) {
        return sql + " RIGHT JOIN ANSWERS_SET ans ON a1.AS_ID = ans.ANSWER_SET_ID " + joinSurveys + " LEFT OUTER JOIN INVITATIONS inv ON inv.INVITATION_ID = ans.ANSWER_SET_INVID WHERE " + where;
    }
    if (prefix.contains("inv.") || filter != null && filter.getDraftId() != null && filter.getDraftId().length() > 0) {
        return sql + " RIGHT JOIN ANSWERS_SET ans ON a1.AS_ID = ans.ANSWER_SET_ID " + joinSurveys + " LEFT JOIN DRAFTS d ON ans.ANSWER_SET_ID = d.answerSet_ANSWER_SET_ID WHERE " + where;
    }
    return sql + " RIGHT JOIN ANSWERS_SET ans ON a1.AS_ID = ans.ANSWER_SET_ID " + joinSurveys + " WHERE " + where;
}


@Transactional
public Map<String,String> getCompletionRates(Survey survey,ResultFilter filter){
    int totalNumberOfContributions = 0;
    int completedContributions = 0;
    Map<String, List<String>> questionsBySection = new HashMap<>();
    Map<String, Integer> answersByQuestion = new HashMap<>();
    Map<String, List<String>> sectionsByQuestion = new HashMap<>();
    Map<String, String> parentByQuestion = new HashMap<>();
    Map<String, Map<String, List<String>>> questionUidsPerAnswerAndSection = new HashMap<>();
    initializeHelperMaps(survey, questionsBySection, answersByQuestion, sectionsByQuestion, parentByQuestion, questionUidsPerAnswerAndSection);
    List<List<String>> answerRows = reportingService.getAnswerSets(survey, filter, null, false, false, true, false, false, false);
    if (answerRows != null) {
        totalNumberOfContributions = answerRows.size();
        Map<Integer, String> questionUidsByIndex = new HashMap<>();
        List<Question> questions = survey.getQuestions();
        for (Question question : questions) {
            if (question.isUsedInResults() && filter.getVisibleQuestions().contains(question.getId().toString())) {
                if (question instanceof MatrixOrTable) {
                    MatrixOrTable parent = (MatrixOrTable) question;
                    for (Element child : parent.getQuestions()) {
                        questionUidsByIndex.put(questionUidsByIndex.size(), child.getUniqueId());
                    }
                } else if (question instanceof RatingQuestion) {
                    RatingQuestion parent = (RatingQuestion) question;
                    for (Element child : parent.getQuestions()) {
                        questionUidsByIndex.put(questionUidsByIndex.size(), child.getUniqueId());
                    }
                } else {
                    questionUidsByIndex.put(questionUidsByIndex.size(), question.getUniqueId());
                }
            }
        }
        completedContributions = parseAnswerRowsForCompletionRates(answerRows, answersByQuestion, sectionsByQuestion, parentByQuestion, questionUidsPerAnswerAndSection, questionUidsByIndex);
    } else {
        List<AnswerSet> answers = getAllAnswers(survey.getId(), filter);
        totalNumberOfContributions = answers.size();
        completedContributions = parseAnswerSetsForCompletionRates(answers, answersByQuestion, sectionsByQuestion, parentByQuestion, questionUidsPerAnswerAndSection);
    }
    return createCompletionRatesResult(survey, questionsBySection, answersByQuestion, questionUidsPerAnswerAndSection, totalNumberOfContributions, completedContributions);
}


@Transactional(readOnly = false)
public int getNumberOfDrafts(String uid){
    List<Integer> allVersions = surveyService.getAllPublishedSurveyVersions(uid);
    return getNumberOfDraftsInner(allVersions);
}


public Map<String,String> createCompletionRatesResult(Survey survey,Map<String,List<String>> questionsBySection,Map<String,Integer> answersByQuestion,Map<String,Map<String,List<String>>> questionUidsPerAnswerAndSection,int totalNumberOfContributions,int completedContributions){
    Map<String, String> result = new HashMap<>();
    for (Element element : survey.getElements()) {
        if (element instanceof Section) {
            String section = element.getUniqueId();
            int counter = 0;
            int numberOfQuestionsInSection = questionsBySection.get(section).size();
            Map<String, List<String>> questionUidsPerAnswer = questionUidsPerAnswerAndSection.get(section);
            for (String answerSetUniqueCode : questionUidsPerAnswer.keySet()) {
                if (questionUidsPerAnswer.get(answerSetUniqueCode).size() == numberOfQuestionsInSection) {
                    counter++;
                }
            }
            result.put(element.getUniqueId(), getPercentage(counter / (double) totalNumberOfContributions));
        } else if (element.isDelphiElement()) {
            result.put(element.getUniqueId(), getPercentage(answersByQuestion.get(element.getUniqueId()) / (double) totalNumberOfContributions));
        }
    }
    result.put("0", getPercentage(completedContributions / (double) totalNumberOfContributions));
    return result;
}


@Transactional
public List<AnswerSet> getAllAnswers(int surveyId,ResultFilter filter){
    Session session = sessionFactory.getCurrentSession();
    HashMap<String, Object> parameters = new HashMap<>();
    String answersetsql = getSql(null, surveyId, filter, parameters, true);
    String sql = "select a1.AS_ID, a1.QUESTION_ID, a1.QUESTION_UID, a1.VALUE, a1.ANSWER_COL, a1.ANSWER_ID, a1.ANSWER_ROW, a1.PA_ID, a1.PA_UID, ans.UNIQUECODE, ans.ANSWER_SET_DATE, ans.ANSWER_SET_UPDATE, ans.ANSWER_SET_INVID, ans.RESPONDER_EMAIL, ans.ANSWER_SET_LANG FROM ANSWERS a1 JOIN ANSWERS_SET ans ON a1.AS_ID = ans.ANSWER_SET_ID WHERE ans.ANSWER_SET_ID IN (" + answersetsql + ")";
    SQLQuery query = session.createSQLQuery(sql);
    sqlQueryService.setParameters(query, parameters);
    @SuppressWarnings("rawtypes")
    List res = query.list();
    HashMap<Integer, AnswerSet> result = new HashMap<>();
    for (Object o : res) {
        Object[] a = (Object[]) o;
        Answer answer = new Answer();
        answer.setAnswerSetId(ConversionTools.getValue(a[0]));
        answer.setQuestionId(ConversionTools.getValue(a[1]));
        answer.setQuestionUniqueId((String) a[2]);
        answer.setValue((String) a[3]);
        answer.setColumn(ConversionTools.getValue(a[4]));
        answer.setId(ConversionTools.getValue(a[5]));
        answer.setRow(ConversionTools.getValue(a[6]));
        answer.setPossibleAnswerId(ConversionTools.getValue(a[7]));
        answer.setPossibleAnswerUniqueId((String) a[8]);
        if (result.containsKey(answer.getAnswerSetId())) {
            result.get(answer.getAnswerSetId()).addAnswer(answer);
        } else {
            AnswerSet answerSet = new AnswerSet();
            answerSet.setId(answer.getAnswerSetId());
            answerSet.getAnswers().add(answer);
            answerSet.setUniqueCode((String) a[9]);
            answerSet.setDate((Date) a[10]);
            answerSet.setUpdateDate((Date) a[11]);
            answerSet.setInvitationId((String) a[12]);
            answerSet.setResponderEmail((String) a[13]);
            answerSet.setLanguageCode((String) a[14]);
            result.put(answerSet.getId(), answerSet);
        }
    }
    // now check "empty" answers
    query = session.createSQLQuery(answersetsql);
    sqlQueryService.setParameters(query, parameters);
    @SuppressWarnings("unchecked")
    List<Integer> answersetids = query.list();
    for (Integer id : answersetids) {
        if (!result.containsKey(id)) {
            AnswerSet answerSet = get(id);
            result.put(answerSet.getId(), answerSet);
        }
    }
    return new ArrayList<>(result.values());
}


public int parseAnswerSetsForCompletionRates(List<AnswerSet> answers,Map<String,Integer> answersByQuestion,Map<String,List<String>> sectionsByQuestion,Map<String,String> parentByQuestion,Map<String,Map<String,List<String>>> questionUidsPerAnswerAndSection){
    int completedContributions = 0;
    for (AnswerSet answerSet : answers) {
        List<String> listOfAnswer = new ArrayList<String>();
        String uniqueCode = answerSet.getUniqueCode();
        for (Answer answer : answerSet.getAnswers()) {
            String questionUid = answer.getQuestionUniqueId();
            internalParseForCompletionRates(uniqueCode, questionUid, listOfAnswer, answersByQuestion, sectionsByQuestion, parentByQuestion, questionUidsPerAnswerAndSection);
        }
        if (listOfAnswer.size() == answersByQuestion.size()) {
            completedContributions++;
        }
    }
    return completedContributions;
}


@SuppressWarnings("unchecked")
@Transactional(readOnly = true)
public List<String> getQuestionsWithUploadedFiles(Survey survey){
    Set<String> uids = new HashSet<>();
    for (Element q : survey.getElements()) {
        if (q instanceof Upload) {
            uids.add("'" + q.getUniqueId() + "'");
        }
    }
    if (uids.isEmpty()) {
        return new ArrayList<>();
    }
    Session session = sessionFactory.getCurrentSession();
    SQLQuery query = session.createSQLQuery("SELECT DISTINCT a.QUESTION_UID FROM ANSWERS a LEFT JOIN ANSWERS_SET ans ON a.AS_ID = ans.ANSWER_SET_ID LEFT JOIN SURVEYS s ON s.SURVEY_ID = ans.SURVEY_ID WHERE ans.ISDRAFT = 0 AND s.ISDRAFT = :draft AND a.QUESTION_UID In (" + StringUtils.collectionToCommaDelimitedString(uids) + ")");
    query.setInteger("draft", survey.getIsDraft() ? 1 : 0);
    return query.list();
}


public void initializeHelperMaps(Survey survey,Map<String,List<String>> questionsBySection,Map<String,Integer> answersByQuestion,Map<String,List<String>> sectionsByQuestion,Map<String,String> parentByQuestion,Map<String,Map<String,List<String>>> questionUidsPerAnswerAndSection){
    String lastSection = "";
    String lastL1section = "";
    questionsBySection.put(lastSection, new ArrayList<String>());
    for (Element element : survey.getElements()) {
        if (element instanceof Section) {
            lastSection = element.getUniqueId();
            Section section = (Section) element;
            if (section.getLevel() == 1) {
                lastL1section = element.getUniqueId();
            }
            questionsBySection.put(lastSection, new ArrayList<String>());
            questionUidsPerAnswerAndSection.put(lastSection, new HashMap<String, List<String>>());
        } else if (element.isDelphiElement()) {
            if (element instanceof MatrixOrTable) {
                MatrixOrTable matrix = (MatrixOrTable) element;
                for (Element matrixQuestion : matrix.getQuestions()) {
                    parentByQuestion.put(matrixQuestion.getUniqueId(), element.getUniqueId());
                }
            } else if (element instanceof RatingQuestion) {
                RatingQuestion rating = (RatingQuestion) element;
                for (Element ratingQuestion : rating.getQuestions()) {
                    parentByQuestion.put(ratingQuestion.getUniqueId(), element.getUniqueId());
                }
            }
            questionsBySection.get(lastSection).add(element.getUniqueId());
            sectionsByQuestion.put(element.getUniqueId(), new ArrayList<>());
            sectionsByQuestion.get(element.getUniqueId()).add(lastSection);
            if (!lastSection.equals(lastL1section)) {
                questionsBySection.get(lastL1section).add(element.getUniqueId());
                sectionsByQuestion.get(element.getUniqueId()).add(lastL1section);
            }
            answersByQuestion.put(element.getUniqueId(), 0);
        }
    }
}


@Transactional(readOnly = true)
public String serializeOriginal(Integer id){
    StringBuilder result = new StringBuilder();
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createSQLQuery("SELECT QUESTION_UID, VALUE FROM ANSWERS WHERE AS_ID = :id ORDER BY ANSWER_ID DESC");
    @SuppressWarnings("rawtypes")
    List answers = query.setInteger("id", id).list();
    for (Object o : answers) {
        Object[] a = (Object[]) o;
        result.append(" ").append(a[0]).append(":").append(a[1]).append(";");
    }
    return result.toString();
}


@Transactional(propagation = Propagation.REQUIRED)
public void internalSaveAnswerSet(AnswerSet answerSet,String fileDir,String draftid,boolean invalidateExportsAndStatistics,boolean createAttendees){
    Session session = sessionFactory.getCurrentSession();
    if (answerSet.getSurvey().getShortname().equalsIgnoreCase("NewSelfRegistrationSurvey")) {
        User user = new User();
        user.setPasswordSalt(Tools.newSalt());
        user.setValidated(false);
        user.setComment("Self Registered");
        int nameQuestion = 0;
        int emailQuestion = 0;
        Element emailElement = null;
        int passwordQuestion = 0;
        int languageQuestion = 0;
        int firstNameQuestion = 0;
        int lastNameQuestion = 0;
        SingleChoiceQuestion languageQuestionElement = null;
        for (Element element : answerSet.getSurvey().getElements()) {
            if (element.getShortname().equalsIgnoreCase("name"))
                nameQuestion = element.getId();
            else if (element.getShortname().equalsIgnoreCase(Constants.EMAIL)) {
                emailQuestion = element.getId();
                emailElement = element;
            } else if (element.getShortname().equalsIgnoreCase("password")) {
                passwordQuestion = element.getId();
            } else if (element.getShortname().equalsIgnoreCase("language")) {
                languageQuestion = element.getId();
                languageQuestionElement = (SingleChoiceQuestion) element;
            } else if (element.getShortname().equalsIgnoreCase("firstname")) {
                firstNameQuestion = element.getId();
            } else if (element.getShortname().equalsIgnoreCase("lastname")) {
                lastNameQuestion = element.getId();
            }
        }
        if (nameQuestion == 0 || emailQuestion == 0 || passwordQuestion == 0 || languageQuestion == 0) {
            throw new MessageException("Not all needed registration questions found!");
        }
        for (Answer answer : answerSet.getAnswers()) {
            if (answer.getQuestionId() == nameQuestion) {
                user.setLogin(answer.getValue());
            } else if (answer.getQuestionId() == emailQuestion) {
                user.setEmail(answer.getValue());
            } else if (answer.getQuestionId() == firstNameQuestion) {
                user.setGivenName(answer.getValue());
            } else if (answer.getQuestionId() == lastNameQuestion) {
                user.setSurName(answer.getValue());
            } else if (answer.getQuestionId() == passwordQuestion) {
                user.setPassword(Tools.hash(answer.getValue() + user.getPasswordSalt()));
            } else if (answer.getQuestionId() == languageQuestion) {
                for (PossibleAnswer possibleAnswer : languageQuestionElement.getPossibleAnswers()) {
                    if (possibleAnswer.getId().toString().equalsIgnoreCase(answer.getValue())) {
                        user.setLanguage(possibleAnswer.getShortname().toUpperCase());
                    }
                }
            }
        }
        List<Role> roles = administrationService.getAllRoles();
        for (Role role : roles) {
            if (role.getName().equalsIgnoreCase("Form Manager")) {
                user.getRoles().add(role);
                break;
            }
        }
        user.setType(User.SYSTEM);
        // first check email
        if (!MailService.isValidEmailAddress(user.getEmail())) {
            throw new InvalidEmailException(emailElement, "");
        }
        if (!administrationService.isSmtpServerConfigured()) {
            throw new SmtpServerNotConfiguredException();
        }
        administrationService.createUser(user);
        administrationService.sendValidationEmail(user);
    } else {
        // restore passwords from drafts
        if (draftid != null) {
            Draft draft = getDraft(draftid);
            if (draft != null) {
                for (Element element : answerSet.getSurvey().getElements()) {
                    if (element instanceof FreeTextQuestion) {
                        FreeTextQuestion q = (FreeTextQuestion) element;
                        if (q.getIsPassword()) {
                            List<Answer> originalAnswers = draft.getAnswerSet().getAnswers(q.getId());
                            if (!originalAnswers.isEmpty()) {
                                List<Answer> currentPasswordAnswers = answerSet.getAnswers(q.getId());
                                if (!currentPasswordAnswers.isEmpty()) {
                                    String currentAnswer = currentPasswordAnswers.get(0).getValue();
                                    if (currentAnswer != null && currentAnswer.equalsIgnoreCase("********")) {
                                        answerSet.getAnswers(q.getId()).get(0).setValue(originalAnswers.get(0).getValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (Answer answer : answerSet.getAnswers()) {
            answer.setIsDraft(answerSet.getIsDraft());
        }
        answerSet.setUpdateDate(new Date());
        boolean newAnswer = answerSet.getId() == null;
        if (answerSet.getSurvey().getIsECF()) {
            this.ecfService.setAnswerSetECFComponents(answerSet.getSurvey(), answerSet);
        }
        session.saveOrUpdate(answerSet);
        session.flush();
        if (answerSet.getSurvey().getIsDelphi()) {
            answerExplanationService.deleteCommentsForDeletedAnswers(answerSet);
            answerExplanationService.createComments(answerSet);
            answerExplanationService.createUpdateOrDeleteExplanations(answerSet);
        }
        if (!answerSet.getSurvey().getIsDraft()) {
            if (newAnswer) {
                reportingService.addToDo(ToDo.NEWCONTRIBUTION, answerSet.getSurvey().getUniqueId(), answerSet.getUniqueCode());
            } else {
                reportingService.addToDo(ToDo.CHANGEDCONTRIBUTION, answerSet.getSurvey().getUniqueId(), answerSet.getUniqueCode());
            }
        } else {
            if (newAnswer) {
                reportingService.addToDo(ToDo.NEWTESTCONTRIBUTION, answerSet.getSurvey().getUniqueId(), answerSet.getUniqueCode());
            } else {
                reportingService.addToDo(ToDo.CHANGEDTESTCONTRIBUTION, answerSet.getSurvey().getUniqueId(), answerSet.getUniqueCode());
            }
        }
        if (invalidateExportsAndStatistics) {
            // mark exports invalid
            exportService.invalidate(answerSet.getSurveyId());
            // delete precomputed statistics and pdfs
            try {
                deleteStatisticsForSurvey(answerSet.getSurvey().getId());
                java.io.File folder = fileService.getSurveyExportsFolder(answerSet.getSurvey().getUniqueId());
                java.io.File target = new java.io.File(String.format("%s/publishedanswer%s.pdf", folder.getPath(), answerSet.getId()));
                Files.deleteIfExists(target.toPath());
                target = new java.io.File(String.format("%s/answer%s.pdf", folder.getPath(), answerSet.getUniqueCode()));
                Files.deleteIfExists(target.toPath());
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage(), e);
            }
            // Do not delete uploaded answer files when submitting a Delphi questionnaire as these would be lost if
            // they had not been saved yet. Let the DeleteTemporaryFolderUpdater worker clean up the mess.
            if (!answerSet.getSurvey().getIsDelphi()) {
                final String surveyUid = answerSet.getSurvey().getUniqueId();
                final String answerSetUniqueCode = answerSet.getUniqueCode();
                fileService.deleteUploadedAnswerFiles(surveyUid, answerSetUniqueCode);
            }
        }
        if (createAttendees && answerSet.getSurvey().getRegistrationForm()) {
            // this is a registration form -> create attendee
            Attendee attendee = new Attendee();
            attendee.setOwnerId(answerSet.getSurvey().getOwner().getId());
            attendee.setRegFormId(answerSet.getSurvey().getId());
            Map<Integer, Question> questionsById = answerSet.getSurvey().getQuestionMap();
            for (Answer answer : answerSet.getAnswers()) {
                Question question = questionsById.get(answer.getQuestionId());
                if (question != null && question.getIsAttribute()) {
                    if (question.getAttributeName().equalsIgnoreCase("name"))
                        attendee.setName(answer.getValue());
                    else if (question.getAttributeName().equalsIgnoreCase(Constants.EMAIL))
                        attendee.setEmail(answer.getValue());
                    else {
                        Attribute a = new Attribute();
                        AttributeName attributeName = attendeeService.getAttributeName(question.getAttributeName(), answerSet.getSurvey().getOwner().getId());
                        if (attributeName == null) {
                            attributeName = new AttributeName();
                            attributeName.setName(question.getAttributeName());
                            attributeName.setOwnerId(answerSet.getSurvey().getOwner().getId());
                            attendeeService.add(attributeName);
                        }
                        a.setAttributeName(attributeName);
                        if (question instanceof ChoiceQuestion) {
                            // replace ID by label of the answer
                            ChoiceQuestion choiceQuestion = (ChoiceQuestion) question;
                            PossibleAnswer possibleAnswer = choiceQuestion.getPossibleAnswer(answer.getPossibleAnswerId());
                            a.setValue(possibleAnswer.getTitle());
                        } else {
                            a.setValue(answer.getValue());
                        }
                        attendee.getAttributes().add(a);
                    }
                }
            }
            attendeeService.add(attendee);
        }
    }
}


public String getDraftURL(AnswerSet answerSet,String draftid,User user){
    Survey survey = answerSet.getSurvey();
    String mode = survey.getIsDraft() ? "test" : "runner";
    String invitationId = answerSet.getInvitationId();
    String url = "";
    if (invitationId != null && invitationId.trim().length() > 0) {
        // the draft comes from an invitation
        Invitation invitation = attendeeService.getInvitationByUniqueId(invitationId);
        ParticipationGroup group = participationService.get(invitation.getParticipationGroupId());
        if (group.getType() == ParticipationGroupType.Token) {
            url = serverPrefix + "runner/" + survey.getUniqueId() + Constants.PATH_DELIMITER + invitation.getUniqueId();
        } else {
            url = serverPrefix + "runner/invited/" + invitation.getParticipationGroupId() + Constants.PATH_DELIMITER + invitation.getUniqueId();
        }
    } else if (mode.equalsIgnoreCase("test")) {
        url = serverPrefix + survey.getShortname() + "/management/test?draftid=" + draftid;
    } else if (mode.equalsIgnoreCase("runner")) {
        if (survey.getEcasSecurity() && user != null) {
            url = serverPrefix + "runner/" + survey.getUniqueId();
        } else {
            url = serverPrefix + "runner/" + survey.getUniqueId() + "?draftid=" + draftid;
        }
    }
    return url;
}


public int clearAnswersForQuestionInMainDatabase(Survey survey,ResultFilter filter,String questionUID,String answerUID){
    Session session = sessionFactory.getCurrentSession();
    Map<String, Object> parameters = new HashMap<>();
    String sql = "UPDATE ANSWERS a INNER JOIN ANSWERS_SET ans ON ans.ANSWER_SET_ID = a.AS_ID SET a.VALUE = '' WHERE a.QUESTION_UID = :quid ";
    if (answerUID != null) {
        sql = "UPDATE ANSWERS a INNER JOIN ANSWERS_SET ans ON ans.ANSWER_SET_ID = a.AS_ID SET a.VALUE = '' WHERE a.PA_UID = :auid ";
    }
    sql += " AND ans.SURVEY_ID";
    if (survey.getIsDraft()) {
        sql += " = " + survey.getId();
    } else {
        List<Integer> allVersions = surveyService.getAllPublishedSurveyVersions(survey.getUniqueId());
        sql += " IN (" + StringUtils.collectionToCommaDelimitedString(allVersions) + ")";
    }
    if (!filter.isEmpty()) {
        String sqlAnswerSets = getSql(null, survey.getId(), filter, parameters, true);
        Query queryAnswerSets = session.createSQLQuery(sqlAnswerSets);
        sqlQueryService.setParameters(queryAnswerSets, parameters);
        @SuppressWarnings("rawtypes")
        List answersetIds = queryAnswerSets.list();
        sql += " AND ans.ANSWER_SET_ID IN (" + StringUtils.collectionToCommaDelimitedString(answersetIds) + ")";
    }
    Query query = session.createSQLQuery(sql);
    if (answerUID != null) {
        query.setString("auid", questionUID + "#" + answerUID);
    } else {
        query.setString("quid", questionUID);
    }
    return query.executeUpdate();
}


public void deleteContributionPDFs(Survey survey){
    // delete contribution PDFs
    List<Integer> surveyIDs = new ArrayList<>();
    if (survey.getIsDraft()) {
        surveyIDs.add(survey.getId());
    } else {
        surveyIDs = surveyService.getAllPublishedSurveyVersions(survey.getUniqueId());
    }
    Set<java.io.File> files = fileService.getPDFContributionFilesForSurvey(surveyIDs);
    for (java.io.File file : files) {
        Files.deleteIfExists(file.toPath());
    }
}


@Transactional(readOnly = false)
public void setPublishingDates(Survey survey){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createSQLQuery("SELECT MIN(SURVEY_CREATED), MAX(SURVEY_CREATED) FROM SURVEYS WHERE SURVEY_UID = :uid AND ISDRAFT = 0");
    @SuppressWarnings("rawtypes")
    List result = query.setString("uid", survey.getUniqueId()).list();
    if (!result.isEmpty()) {
        Object[] a = (Object[]) result.get(0);
        survey.setFirstPublished((Date) a[0]);
        survey.setPublished((Date) a[1]);
    }
}


@Transactional(readOnly = true)
public AnswerSet getByInvitationCode(String invitationId){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT a FROM AnswerSet a WHERE a.isDraft = false AND a.invitationId = :invitationId order by date DESC").setString("invitationId", invitationId);
    @SuppressWarnings("unchecked")
    List<AnswerSet> list = query.list();
    if (list.isEmpty()) {
        return null;
    }
    if (list.size() > 1) {
        logger.warn("Multiple answerSets found for invitationId " + invitationId);
    }
    return list.get(0);
}


@Transactional
public void saveStatisticsRequest(StatisticsRequest sr){
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(sr);
}


@Transactional
public int deleteInvalidStatistics(){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT s.id from Statistics s where s.invalid = true");
    @SuppressWarnings("unchecked")
    List<Integer> statistics = query.list();
    int counter = 0;
    for (Integer statid : statistics) {
        Statistics stat = (Statistics) session.get(Statistics.class, statid);
        session.delete(stat);
        counter++;
    }
    return counter;
}


@Transactional
public int deleteOldDrafts(Date date){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT d.id from Draft d where d.answerSet.updateDate < :date");
    query.setDate("date", date).setMaxResults(1000);
    @SuppressWarnings("unchecked")
    List<Integer> drafts = query.list();
    int counter = 0;
    for (Integer draftid : drafts) {
        Draft draft = (Draft) session.get(Draft.class, draftid);
        for (Answer answer : draft.getAnswerSet().getAnswers()) {
            if (answer.getFiles() != null) {
                for (File f : answer.getFiles()) {
                    // new file system
                    java.io.File file = fileService.getSurveyFile(draft.getAnswerSet().getSurvey().getUniqueId(), f.getUid());
                    Files.deleteIfExists(file.toPath());
                    // old file system
                    file = new java.io.File(fileDir + f.getUid());
                    Files.deleteIfExists(file.toPath());
                }
            }
        }
        session.delete(draft);
        counter++;
    }
    return counter;
}


@Transactional(readOnly = true)
public Map<String,Map<String,List<File>>> getAllUploadedFilesByContribution(int surveyId,ResultFilter filter,int page,int rowsPerPage){
    Session session = sessionFactory.getCurrentSession();
    HashMap<String, Object> parameters = new HashMap<>();
    String sql;
    if (filter.isEmpty()) {
        List<Integer> allVersions = surveyService.getAllSurveyVersions(surveyId);
        sql = "SELECT f.FILE_ID, f.FILE_NAME, f.FILE_UID, af.ANSWERS_ANSWER_ID, ans.UNIQUECODE, a.QUESTION_UID FROM FILES f JOIN ANSWERS_FILES af ON f.FILE_ID = af.files_FILE_ID JOIN ANSWERS a ON af.ANSWERS_ANSWER_ID = a.ANSWER_ID JOIN ANSWERS_SET ans ON a.AS_ID = ans.ANSWER_SET_ID JOIN SURVEYS s ON ans.SURVEY_ID = s.SURVEY_ID WHERE ans.SURVEY_ID IN (" + StringUtils.collectionToCommaDelimitedString(allVersions) + ") AND ans.ISDRAFT = FALSE";
    } else {
        sql = "SELECT f.FILE_ID, f.FILE_NAME, f.FILE_UID, af.ANSWERS_ANSWER_ID, ans.UNIQUECODE, a.QUESTION_UID FROM FILES f JOIN ANSWERS_FILES af ON f.FILE_ID = af.files_FILE_ID JOIN ANSWERS a ON af.ANSWERS_ANSWER_ID = a.ANSWER_ID JOIN ANSWERS_SET ans ON a.AS_ID = ans.ANSWER_SET_ID where ans.ANSWER_SET_ID in (" + getSql(null, surveyId, filter, parameters, true) + ")";
    }
    SQLQuery query = session.createSQLQuery(sql);
    sqlQueryService.setParameters(query, parameters);
    @SuppressWarnings("rawtypes")
    List res = query.list();
    Map<String, Map<String, List<File>>> result = new HashMap<>();
    Map<String, String> nicenames = new HashMap<>();
    for (Object o : res) {
        Object[] a = (Object[]) o;
        File file = new File();
        file.setId(ConversionTools.getValue(a[0]));
        file.setName((String) a[1]);
        file.setUid((String) a[2]);
        file.setAnswerId(ConversionTools.getValue(a[3]));
        String code = (String) a[4];
        String questionUID = (String) a[5];
        if (!result.containsKey(code)) {
            result.put(code, new HashMap<String, List<File>>());
        }
        if (!nicenames.containsKey(questionUID)) {
            nicenames.put(questionUID, "Upload_" + (nicenames.size() + 1));
        }
        if (!result.get(code).containsKey(nicenames.get(questionUID))) {
            result.get(code).put(nicenames.get(questionUID), new ArrayList<File>());
        }
        result.get(code).get(nicenames.get(questionUID)).add(file);
    }
    return result;
}


@Transactional(readOnly = true)
public Draft getDraft(String draftid){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Draft d WHERE d.uniqueId = :uniqueId").setString("uniqueId", draftid);
    @SuppressWarnings("unchecked")
    List<Draft> list = query.list();
    if (list.isEmpty()) {
        return null;
    }
    if (list.size() > 1) {
        logger.error("Multiple drafts found for id " + draftid);
    }
    return list.get(0);
}


@Transactional(readOnly = true)
public Date getNewestAnswerDate(int surveyId){
    Session session = sessionFactory.getCurrentSession();
    List<Integer> allVersions = surveyService.getAllPublishedSurveyVersions(surveyId);
    Query query = session.createQuery("SELECT max(a.updateDate) FROM AnswerSet a WHERE a.surveyId IN (" + StringUtils.collectionToCommaDelimitedString(allVersions) + ") AND a.isDraft = 0");
    return (Date) query.uniqueResult();
}


@Transactional(readOnly = true)
public int userContributionsToSurvey(Survey survey,User user){
    Session session = sessionFactory.getCurrentSession();
    String queryString = "SELECT count(ans.ANSWER_SET_ID) from ANSWERS_SET ans inner join SURVEYS s on ans.SURVEY_ID = s.SURVEY_ID WHERE s.SURVEY_UID = :uid AND s.ISDRAFT = 0 AND ans.ISDRAFT = 0 AND (ans.RESPONDER_EMAIL = :mail1 OR ans.RESPONDER_EMAIL = :mail2)";
    SQLQuery query = session.createSQLQuery(queryString);
    query.setString("uid", survey.getUniqueId()).setString("mail1", user.getEmail()).setString("mail2", Tools.md5hash(user.getEmail()));
    return ConversionTools.getValue(query.uniqueResult());
}


@Transactional(readOnly = true)
public List<AnswerSet> getAnswersAndDrafts(int surveyId){
    Session session = sessionFactory.getCurrentSession();
    String sql = "select DISTINCT ANSWER_SET_ID from ANSWERS_SET where SURVEY_ID = :surveyId";
    SQLQuery query = session.createSQLQuery(sql);
    query.setInteger("surveyId", surveyId);
    @SuppressWarnings("rawtypes")
    List res = query.list();
    List<AnswerSet> result = new ArrayList<>();
    for (Object o : res) {
        Integer i = ConversionTools.getValue(o);
        AnswerSet answerSet = (AnswerSet) session.get(AnswerSet.class, i);
        result.add(answerSet);
    }
    return result;
}


@Transactional(readOnly = false)
public void save(Statistics s){
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(s);
    session.flush();
}


@Transactional
public Date getOldestAnswerSetDate(){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT min(a.date) FROM AnswerSet a");
    return (Date) query.uniqueResult();
}


@Transactional
public void clearAnswersForQuestion(Survey survey,ResultFilter filter,String questionUID,String childUID,int userId){
    // blank answers in main database
    clearAnswersForQuestionInMainDatabase(survey, filter, questionUID, childUID);
    // blank answers in reporting database
    reportingService.clearAnswersForQuestionInReportingDatabase(survey, filter, questionUID, childUID);
    deleteContributionPDFs(survey);
    activityService.log(315, null, questionUID, userId, survey.getUniqueId());
}


@Transactional(readOnly = true)
public int getNumberAnswersForValue(String value,int questionId,String questionUid,boolean surveyIsDraft,String answerSetUniqueCode){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createSQLQuery("select count(*) from ANSWERS a INNER JOIN ANSWERS_SET ans ON ans.ANSWER_SET_ID = a.AS_ID INNER JOIN SURVEYS s ON s.SURVEY_ID = ans.SURVEY_ID where s.ISDRAFT = :isdraft AND ans.UNIQUECODE != :ansuid AND ((a.QUESTION_ID= :questionId and a.VALUE= :value and ans.ISDRAFT=0) or (a.QUESTION_UID= :questionUid and a.VALUE= :value and ans.ISDRAFT=0))").setBoolean("isdraft", surveyIsDraft).setString("value", value).setString("ansuid", answerSetUniqueCode).setString("questionUid", questionUid).setInteger("questionId", questionId);
    return ConversionTools.getValue(query.uniqueResult());
}


@Transactional(readOnly = true)
public int getNumberOfAnswerSets(Survey survey,ResultFilter filter){
    Session session = sessionFactory.getCurrentSession();
    if (!survey.getIsDraft() && filter.isEmpty())
        return getNumberOfAnswerSetsPublished(survey.getShortname(), survey.getUniqueId());
    HashMap<String, Object> parameters = new HashMap<>();
    String queryString = "";
    queryString = getSql("SELECT count(DISTINCT a1.AS_ID)", survey.getId(), filter, parameters, true);
    SQLQuery query = session.createSQLQuery(queryString);
    sqlQueryService.setParameters(query, parameters);
    return ConversionTools.getValue(query.uniqueResult());
}


@Transactional(readOnly = true)
public List<AnswerSet> getAnswersFromReporting(Survey survey,SqlPagination sqlPagination){
    return this.getAnswers(survey, null, sqlPagination, false, false, true);
}


@Transactional(readOnly = true)
public AnswerSet getUserContributionToSurvey(Survey survey,User user){
    Session session = sessionFactory.getCurrentSession();
    String queryString = "SELECT ans.ANSWER_SET_ID from ANSWERS_SET ans inner join SURVEYS s on ans.SURVEY_ID = s.SURVEY_ID WHERE s.SURVEY_UID = :uid AND s.ISDRAFT = 0 AND ans.ISDRAFT = 0 AND (ans.RESPONDER_EMAIL = :mail1 OR ans.RESPONDER_EMAIL = :mail2)";
    SQLQuery query = session.createSQLQuery(queryString);
    query.setString("uid", survey.getUniqueId()).setString("mail1", user.getEmail()).setString("mail2", Tools.md5hash(user.getEmail()));
    @SuppressWarnings("rawtypes")
    List res = query.setMaxResults(1).list();
    for (Object o : res) {
        Integer i = ConversionTools.getValue(o);
        AnswerSet answerSet = (AnswerSet) session.get(AnswerSet.class, i);
        return answerSet;
    }
    return null;
}


@Transactional
public StatisticsRequest getStatisticRequest(int id){
    Session session = sessionFactory.getCurrentSession();
    return (StatisticsRequest) session.get(StatisticsRequest.class, id);
}


public int parseAnswerRowsForCompletionRates(List<List<String>> answerRows,Map<String,Integer> answersByQuestion,Map<String,List<String>> sectionsByQuestion,Map<String,String> parentByQuestion,Map<String,Map<String,List<String>>> questionUidsPerAnswerAndSection,Map<Integer,String> questionUidsByIndex){
    int completedContributions = 0;
    for (List<String> answerRow : answerRows) {
        List<String> listOfAnswer = new ArrayList<>();
        String uniqueCode = answerRow.get(0);
        for (int i = 0; i < questionUidsByIndex.size(); i++) {
            // the first two items in the array are the contribution code and contribution id
            String value = answerRow.get(2 + i);
            if (value != null && value.length() > 0) {
                String questionUid = questionUidsByIndex.get(i);
                internalParseForCompletionRates(uniqueCode, questionUid, listOfAnswer, answersByQuestion, sectionsByQuestion, parentByQuestion, questionUidsPerAnswerAndSection);
            }
        }
        if (listOfAnswer.size() == answersByQuestion.size()) {
            completedContributions++;
        }
    }
    return completedContributions;
}


@Transactional
public DelphiMedian getMedian(Survey survey,NumberQuestion numberQuestion,Answer answer,ResultFilter filter){
    double maxDistance = numberQuestion.getMaxDistance();
    Session session = sessionFactory.getCurrentSession();
    String sql = "SELECT a.VALUE, count(*) FROM ANSWERS a " + "JOIN ANSWERS_SET an on a.AS_ID = an.ANSWER_SET_ID " + "JOIN SURVEYS s ON an.SURVEY_ID = s.SURVEY_ID " + "WHERE s.SURVEY_UID = :surveyUid AND s.ISDRAFT = :isDraft AND QUESTION_UID = :questionUid ";
    HashMap<String, Object> parameters = new HashMap<>();
    if (filter != null) {
        String answersetsql = getSql(null, survey.getId(), filter, parameters, true);
        sql += "AND an.ANSWER_SET_ID IN (" + answersetsql + ") ";
    }
    sql += "GROUP BY a.VALUE";
    SQLQuery query = session.createSQLQuery(sql);
    sqlQueryService.setParameters(query, parameters);
    query.setString("surveyUid", survey.getUniqueId());
    query.setBoolean("isDraft", survey.getIsDraft());
    query.setString("questionUid", numberQuestion.getUniqueId());
    @SuppressWarnings("rawtypes")
    List res = query.list();
    List<Double> values = new ArrayList<>();
    for (Object o : res) {
        Object[] a = (Object[]) o;
        double value = Double.parseDouble((String) a[0]);
        int count = ConversionTools.getValue(a[1]);
        for (int i = 0; i < count; i++) {
            values.add(value);
        }
    }
    DelphiMedian median = new DelphiMedian();
    int length = values.size();
    if (length == 0)
        return null;
    Double medianNumber = MathUtils.computeMedian(values.toArray(new Double[0]));
    median.setMedian(medianNumber);
    if (answer != null) {
        double answerValue = Double.parseDouble(answer.getValue());
        double distance = Math.abs(medianNumber - answerValue);
        if (distance > maxDistance) {
            median.setMaxDistanceExceeded(true);
        }
    }
    return median;
}


@Transactional(readOnly = true)
public AnswerSet get(String uniqueCode){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT a FROM AnswerSet a WHERE a.isDraft = false AND a.uniqueCode = :uniqueCode order by date DESC").setString(Constants.UNIQUECODE, uniqueCode);
    @SuppressWarnings("unchecked")
    List<AnswerSet> list = query.list();
    if (list.isEmpty()) {
        return null;
    }
    if (list.size() > 1) {
        logger.warn("Multiple answerSets found for uniqueCode " + uniqueCode);
    }
    return list.get(0);
}


@Transactional(readOnly = true)
public int getNumberOfAnswerSetsPublished(String surveyname,String uid){
    Session session = sessionFactory.getCurrentSession();
    if (uid != null && uid.length() > 0) {
        SQLQuery query = session.createSQLQuery("SELECT count(ANSWERS_SET.ANSWER_SET_ID) FROM ANSWERS_SET inner join SURVEYS s on  ANSWERS_SET.SURVEY_ID = s.SURVEY_ID where ANSWERS_SET.ISDRAFT = 0 AND s.SURVEY_UID = :uid AND s.ISDRAFT = 0");
        query.setString("uid", uid);
        return ConversionTools.getValue(query.uniqueResult());
    } else {
        SQLQuery query = session.createSQLQuery("SELECT count(ANSWERS_SET.ANSWER_SET_ID) FROM ANSWERS_SET inner join SURVEYS s on  ANSWERS_SET.SURVEY_ID = s.SURVEY_ID where ANSWERS_SET.ISDRAFT = 0 AND  s.SURVEYNAME = :surveyname AND s.ISDRAFT = 0");
        query.setString("surveyname", surveyname);
        return ConversionTools.getValue(query.uniqueResult());
    }
}


@Transactional
public void anonymiseAnswerSets(List<AnswerSet> answerSets){
    Session session = sessionFactory.getCurrentSession();
    for (AnswerSet answerSet : answerSets) {
        answerSet.setIP(null);
        session.merge(answerSet);
    }
    session.flush();
}


@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public void saveDraft(Draft draft){
    boolean saved = false;
    int counter = 1;
    draft.getAnswerSet().setUpdateDate(new Date());
    while (!saved) {
        try {
            internalSaveDraft(draft);
            // delete temporary files folder
            try {
                if (fileDir != null) {
                    java.io.File directory = fileService.getSurveyUploadsFolder(draft.getAnswerSet().getSurvey().getUniqueId(), false);
                    FileUtils.delete(directory);
                }
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage(), e);
            }
            saved = true;
        } catch (org.hibernate.exception.LockAcquisitionException ex) {
            logger.info("lock on draft/answerset table catched; retry counter: " + counter);
            counter++;
            if (counter > 60) {
                logger.error(ex.getLocalizedMessage(), ex);
                throw ex;
            }
            Thread.sleep(1000);
        }
    }
}


@Transactional
public Statistics getStatisticsOrStartCreator(Survey survey,ResultFilter filter,boolean useEagerLoading,boolean allanswers,boolean asynchronous){
    filter = answerService.initialize(filter);
    Statistics statistics = getStatisticsForFilterHash(survey.getId(), filter.getHash(allanswers), useEagerLoading);
    if (statistics == null) {
        StatisticsCreator creator = (StatisticsCreator) context.getBean("statisticsCreator");
        creator.init(survey, filter, allanswers);
        if (asynchronous && !allanswers) {
            try {
                for (Runnable r : running) {
                    StatisticsCreator s = (StatisticsCreator) r;
                    if (s.getFilter().getHash(allanswers).equals(filter.getHash(allanswers))) {
                        return null;
                    }
                }
            } catch (java.util.ConcurrentModificationException cme) {
            // this can happen when a runner is added or removed while the list is checked
            }
            this.getAnswerPool().execute(creator);
            return null;
        } else {
            int counter = 0;
            while (statistics == null && counter < 120) {
                boolean found = false;
                try {
                    for (Runnable runnable : running) {
                        StatisticsCreator statisticsCreator = (StatisticsCreator) runnable;
                        if (statisticsCreator.getFilter().getHash(allanswers).equals(filter.getHash(allanswers))) {
                            found = true;
                            break;
                        }
                    }
                } catch (java.util.ConcurrentModificationException cme) {
                // this can happen when a runner is added or removed while the list is checked
                }
                // if there is no computation running, start one
                if (!found) {
                    if (asynchronous) {
                        this.getAnswerPool().execute(creator);
                    } else {
                        creator.runSync();
                    }
                    found = true;
                }
                if (found) {
                    try {
                        Thread.sleep(5000);
                        counter++;
                    } catch (InterruptedException e) {
                        logger.error(e.getLocalizedMessage(), e);
                    }
                    if (allanswers && !survey.isMissingElementsChecked()) {
                        surveyService.checkAndRecreateMissingElements(survey, filter);
                    }
                    statistics = this.getStatisticsForFilterHash(survey.getId(), filter.getHash(allanswers), useEagerLoading);
                }
            }
        }
    }
    return statistics;
}


public AnswerSet automaticParseAnswerSet(HttpServletRequest request,Survey survey,String uniqueCode,boolean update,String lang,User user){
    AnswerSet answerSet = null;
    if (survey.getIsDelphi() && surveyService.answerSetExists(uniqueCode, false, true)) {
        AnswerSet existingAnswerSet = answerService.get(uniqueCode);
        answerSet = SurveyHelper.parseAndMergeAnswerSet(request, survey, uniqueCode, existingAnswerSet, lang, user, fileService);
    }
    if (answerSet == null) {
        answerSet = SurveyHelper.parseAnswerSet(request, survey, uniqueCode, false, lang, user, fileService);
    }
    return answerSet;
}


@Transactional
public void getCompleteAnswers4Statistics(Survey survey,ResultFilter filter,Map<Integer,Integer> map,Map<Integer,Map<Integer,Integer>> mapMatrix){
    Session session = sessionFactory.getCurrentSession();
    for (Question q : survey.getQuestions()) {
        if (q instanceof ChoiceQuestion) {
            ChoiceQuestion choice = (ChoiceQuestion) q;
            for (PossibleAnswer a : choice.getPossibleAnswers()) {
                SQLQuery query = session.createSQLQuery("SELECT NUM FROM LIVESTATISTICS WHERE PAID = :possibleAnswerId");
                query.setInteger("possibleAnswerId", a.getId());
                Object num = query.uniqueResult();
                int result = 0;
                if (num != null)
                    if (num instanceof BigInteger) {
                        result = ((BigInteger) num).intValue();
                    } else if (num instanceof BigDecimal) {
                        result = ((BigDecimal) num).intValue();
                    } else if (num != null) {
                        result = (Integer) num;
                    }
                map.put(a.getId(), result);
            }
        } else if (q instanceof Matrix) {
            Matrix matrix = (Matrix) q;
            for (Element matrixQuestion : matrix.getQuestions()) {
                for (Element matrixAnswer : matrix.getAnswers()) {
                    SQLQuery query = session.createSQLQuery("SELECT NUM FROM LIVESTATISTICS WHERE PAID = :possibleAnswerId AND QID = :questionId");
                    query.setInteger("possibleAnswerId", matrixAnswer.getId());
                    query.setInteger("questionId", matrixQuestion.getId());
                    Object num = query.uniqueResult();
                    int result = 0;
                    if (num instanceof BigInteger) {
                        result = ((BigInteger) num).intValue();
                    } else if (num instanceof BigDecimal) {
                        result = ((BigDecimal) num).intValue();
                    } else if (num != null) {
                        result = (Integer) num;
                    }
                    if (!mapMatrix.containsKey(matrixQuestion.getId()))
                        mapMatrix.put(matrixQuestion.getId(), new HashMap<>());
                    mapMatrix.get(matrixQuestion.getId()).put(matrixAnswer.getId(), result);
                }
            }
        }
    }
}


public String getPercentage(double value){
    int number = (int) Math.round(value * 100);
    return number + " %";
}


public void internalSaveDraft(Draft draft){
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(draft);
}


@Transactional
public List<AnswerSet> getAnswerSetsToAnonymize(Date maxDate,int max){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("SELECT a FROM AnswerSet a WHERE (a.date < :maxDate) and (a.IP IS NOT NULL) order by date ASC");
    query.setDate("maxDate", maxDate);
    query.setMaxResults(max);
    @SuppressWarnings("unchecked")
    List<AnswerSet> list1 = query.list();
    return list1;
}


@Transactional(readOnly = true)
public Draft getDraftByAnswerUID(String uniqueCode){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Draft d WHERE d.answerSet.uniqueCode = :uniqueCode order by answerSet.date DESC").setString(Constants.UNIQUECODE, uniqueCode);
    @SuppressWarnings("unchecked")
    List<Draft> list = query.list();
    if (list.isEmpty()) {
        return null;
    }
    if (list.size() > 1) {
        logger.error("Multiple drafts found for answerset uniqueCode " + uniqueCode);
    }
    return list.get(0);
}


@Transactional(readOnly = true)
public List<File> getUploadedFilesForAnswerset(int answersetId){
    Session session = sessionFactory.getCurrentSession();
    String sql = "SELECT f.FILE_ID, f.FILE_NAME, f.FILE_UID, af.ANSWERS_ANSWER_ID, a.QUESTION_UID FROM FILES f JOIN ANSWERS_FILES af ON f.FILE_ID = af.files_FILE_ID JOIN ANSWERS a ON af.ANSWERS_ANSWER_ID = a.ANSWER_ID WHERE a.AS_ID = :id";
    SQLQuery query = session.createSQLQuery(sql);
    query.setInteger("id", answersetId);
    @SuppressWarnings("rawtypes")
    List res = query.list();
    List<File> result = new ArrayList<>();
    for (Object o : res) {
        Object[] a = (Object[]) o;
        File file = new File();
        file.setId(ConversionTools.getValue(a[0]));
        file.setName((String) a[1]);
        file.setUid((String) a[2]);
        file.setAnswerId(ConversionTools.getValue(a[3]));
        file.setQuestionUid((String) a[4]);
        result.add(file);
    }
    return result;
}


@Transactional(readOnly = false)
public void deleteStatisticsForSurvey(int surveyId){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("UPDATE Statistics s SET s.invalid = true WHERE s.surveyId = :surveyId").setInteger("surveyId", surveyId);
    query.executeUpdate();
    query = session.createQuery("DELETE FROM ExportCache c WHERE c.surveyId = :surveyId").setInteger("surveyId", surveyId);
    query.executeUpdate();
}


@Transactional(readOnly = true)
public Draft getDraftForInvitation(String uniqueCode){
    return internalGetDraftForInviation(uniqueCode);
}


public void deleteFilesForAnswerSet(AnswerSet answerSet){
    java.io.File folder = fileService.getSurveyExportsFolder(answerSet.getSurvey().getUniqueId());
    java.io.File target = new java.io.File(String.format("%s/publishedanswer%s.pdf", folder.getPath(), answerSet.getId()));
    Files.deleteIfExists(target.toPath());
    target = new java.io.File(String.format("%sanswer%s.pdf", tempFileDir, answerSet.getUniqueCode()));
    Files.deleteIfExists(target.toPath());
    for (Answer answer : answerSet.getAnswers()) {
        if (answer.getFiles() != null) {
            for (File f : answer.getFiles()) {
                // new file system
                java.io.File file = fileService.getSurveyFile(answerSet.getSurvey().getUniqueId(), f.getUid());
                Files.deleteIfExists(file.toPath());
                // old file system
                file = new java.io.File(fileDir + f.getUid());
                Files.deleteIfExists(file.toPath());
            }
        }
    }
}


public Map<Date,Integer> getAnswersPerDay(int surveyId,String span){
    Session session = sessionFactory.getCurrentSession();
    List<Integer> allVersions = surveyService.getAllPublishedSurveyVersions(surveyId);
    Calendar cal = Calendar.getInstance();
    cal.setTime(DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH));
    if (span.equalsIgnoreCase("month")) {
        cal.add(Calendar.DATE, -30);
    } else if (span.equalsIgnoreCase("week")) {
        cal.add(Calendar.DATE, -7);
    } else {
        cal.add(Calendar.YEAR, -20);
    }
    Date firstDay = cal.getTime();
    if (span.equalsIgnoreCase("total")) {
        Survey firstPublished = !allVersions.isEmpty() ? surveyService.getSurvey(allVersions.get(0), true) : null;
        if (firstPublished != null) {
            firstDay = firstPublished.getCreated();
        }
    }
    String sql = "SELECT DATE(ANSWER_SET_DATE), count(*) FROM ANSWERS_SET WHERE SURVEY_ID IN (" + StringUtils.collectionToCommaDelimitedString(allVersions) + ") AND ISDRAFT = 0 AND ANSWER_SET_DATE > :start GROUP BY DATE(ANSWER_SET_DATE) ORDER BY DATE(ANSWER_SET_DATE)";
    SQLQuery query = session.createSQLQuery(sql);
    query.setDate("start", cal.getTime());
    @SuppressWarnings("rawtypes")
    List res = query.list();
    Map<Date, Integer> result = new TreeMap<>();
    Date first = null;
    Date last = null;
    for (Object o : res) {
        Object[] a = (Object[]) o;
        last = (Date) a[0];
        result.put(last, ConversionTools.getValue(a[1]));
        if (first == null) {
            first = last;
        }
    }
    if (span.equalsIgnoreCase("week") || span.equalsIgnoreCase("month") || span.equalsIgnoreCase("total")) {
        Date lastDay = DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH);
        if (first == null || first.after(firstDay)) {
            result.put(firstDay, 0);
        }
        if (last == null || last.before(lastDay)) {
            result.put(lastDay, 0);
        }
    }
    return result;
}


@Transactional(readOnly = true)
public Set<String> getCaseIds(Integer surveyId,ResultFilter filter,int page,int rowsPerPage,boolean searchallsurveys){
    Session session = sessionFactory.getCurrentSession();
    HashMap<String, Object> parameters = new HashMap<>();
    String sql = getSql("select DISTINCT ans.UNIQUECODE", surveyId, filter, parameters, searchallsurveys);
    SQLQuery query = session.createSQLQuery(sql);
    sqlQueryService.setParameters(query, parameters);
    @SuppressWarnings("rawtypes")
    List res = query.setFirstResult((page - 1) * rowsPerPage).setMaxResults(rowsPerPage).list();
    Set<String> result = new HashSet<>();
    for (Object o : res) {
        if (o != null) {
            result.add(o.toString());
        }
    }
    return result;
}


@Override
public String getFileDir(){
    return fileDir;
}


@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public void deleteAnswer(AnswerSet answerSet){
    this.deleteAnswer(answerSet, true);
}


@SuppressWarnings("unchecked")
@Transactional(readOnly = true)
public List<String[]> getFilesForQuestion(String uid,boolean draft){
    Session session = sessionFactory.getCurrentSession();
    SQLQuery query = session.createSQLQuery("SELECT ans.UNIQUECODE, f.FILE_UID, f.FILE_NAME FROM FILES f JOIN ANSWERS_FILES af ON f.FILE_ID = af.files_FILE_ID JOIN ANSWERS a ON af.ANSWERS_ANSWER_ID = a.ANSWER_ID JOIN ANSWERS_SET ans ON ans.ANSWER_SET_ID = a.AS_ID AND ans.ISDRAFT = 0 JOIN SURVEYS s ON s.SURVEY_ID = ans.SURVEY_ID WHERE a.QUESTION_UID = :uid AND s.ISDRAFT = :draft");
    query.setString("uid", uid).setInteger("draft", draft ? 1 : 0);
    return query.list();
}


@Transactional(readOnly = true)
public Set<Integer> getAllAnswerIds(Integer surveyId,ResultFilter filter,int page,int maxValue){
    Session session = sessionFactory.getCurrentSession();
    HashMap<String, Object> parameters = new HashMap<>();
    String sql = getSql(null, surveyId, filter, parameters, true);
    SQLQuery query = session.createSQLQuery(sql);
    sqlQueryService.setParameters(query, parameters);
    @SuppressWarnings("rawtypes")
    List res = query.list();
    Set<Integer> result = new HashSet<>();
    for (Object o : res) {
        result.add(ConversionTools.getValue(o));
    }
    return result;
}


@Transactional(readOnly = true)
public List<AnswerSet> getAnswers(List<Integer> answerSetIDs,boolean initFiles){
    Session session = sessionFactory.getCurrentSession();
    List<AnswerSet> result = new ArrayList<>();
    for (Integer i : answerSetIDs) {
        AnswerSet answerSet = (AnswerSet) session.get(AnswerSet.class, i);
        if (initFiles) {
            for (Answer answer : answerSet.getAnswers()) {
                Hibernate.initialize(answer.getFiles());
            }
        }
        result.add(answerSet);
    }
    return result;
}


public int[] getContributionStatisticsForUser(Integer id){
    Session session = sessionFactory.getCurrentSession();
    User user = administrationService.getUser(id);
    if (user == null)
        return null;
    List<String> allemails = user.getAllEmailAddresses();
    int[] result = new int[3];
    StringBuilder sqlb = new StringBuilder();
    sqlb.append("SELECT count(*) FROM ( SELECT i.INVITATION_ID, i.ATTENDEE_INVITED FROM INVITATIONS i JOIN ATTENDEE a ON i.ATTENDEE_ID = a.ATTENDEE_ID JOIN PARTICIPANTS_ATTENDEE pa ON pa.attendees_ATTENDEE_ID = a.ATTENDEE_ID JOIN PARTICIPANTS p ON p.PARTICIPATION_ID = pa.PARTICIPANTS_PARTICIPATION_ID WHERE ");
    sqlb.append("p.PARTICIPATION_TYPE = 1 AND a.ATTENDEE_EMAIL IN (:emails) AND i.ATTENDEE_ANSWERS = 0");
    sqlb.append(" UNION SELECT i.INVITATION_ID, i.ATTENDEE_INVITED FROM INVITATIONS i JOIN ECASUSERS a ON i.ATTENDEE_ID = a.USER_ID JOIN PARTICIPANTS_ECASUSERS pa ON pa.ecasUsers_USER_ID = a.USER_ID JOIN PARTICIPANTS p ON p.PARTICIPATION_ID = pa.PARTICIPANTS_PARTICIPATION_ID WHERE ");
    sqlb.append("p.PARTICIPATION_TYPE = 2 AND a.USER_EMAIL IN (:emails) AND i.ATTENDEE_ANSWERS = 0 ) AS d");
    SQLQuery query = session.createSQLQuery(sqlb.toString());
    query.setParameterList("emails", allemails);
    result[0] = ConversionTools.getValue(query.uniqueResult());
    String sql = "SELECT COUNT(*) FROM ANSWERS_SET WHERE ISDRAFT = 0 AND RESPONDER_EMAIL IN (:emails)";
    query = session.createSQLQuery(sql);
    query.setParameterList("emails", allemails);
    result[1] = ConversionTools.getValue(query.uniqueResult());
    sql = "SELECT COUNT(*) FROM (SELECT 1 FROM ANSWERS_SET ans WHERE ans.RESPONDER_EMAIL IN (:emails) AND ans.ISDRAFT = 1 AND NOT ans.UNIQUECODE IN (SELECT ans.UNIQUECODE FROM ANSWERS_SET ans WHERE ans.RESPONDER_EMAIL IN (:emails) AND ans.ISDRAFT = 0  )) as x";
    query = session.createSQLQuery(sql);
    query.setParameterList("emails", allemails);
    result[2] = ConversionTools.getValue(query.uniqueResult());
    return result;
}


@Transactional(readOnly = true)
public List<File> getAllUploadedFiles(int surveyId,ResultFilter filter,int page,int rowsPerPage){
    Session session = sessionFactory.getCurrentSession();
    HashMap<String, Object> parameters = new HashMap<>();
    String sql;
    if (filter.isEmpty()) {
        List<Integer> allVersions = surveyService.getAllSurveyVersions(surveyId);
        sql = "SELECT f.FILE_ID, f.FILE_NAME, f.FILE_UID, af.ANSWERS_ANSWER_ID FROM FILES f JOIN ANSWERS_FILES af ON f.FILE_ID = af.files_FILE_ID JOIN ANSWERS a ON af.ANSWERS_ANSWER_ID = a.ANSWER_ID JOIN ANSWERS_SET ans ON a.AS_ID = ans.ANSWER_SET_ID JOIN SURVEYS s ON ans.SURVEY_ID = s.SURVEY_ID WHERE ans.SURVEY_ID IN (" + StringUtils.collectionToCommaDelimitedString(allVersions) + ") AND ans.ISDRAFT = FALSE";
    } else {
        sql = "SELECT f.FILE_ID, f.FILE_NAME, f.FILE_UID, af.ANSWERS_ANSWER_ID FROM FILES f JOIN ANSWERS_FILES af ON f.FILE_ID = af.files_FILE_ID JOIN ANSWERS a ON af.ANSWERS_ANSWER_ID = a.ANSWER_ID JOIN ANSWERS_SET ans ON a.AS_ID = ans.ANSWER_SET_ID where ans.ANSWER_SET_ID in (" + getSql(null, surveyId, filter, parameters, true) + ")";
    }
    SQLQuery query = session.createSQLQuery(sql);
    sqlQueryService.setParameters(query, parameters);
    @SuppressWarnings("rawtypes")
    List res = query.list();
    List<File> result = new ArrayList<>();
    for (Object o : res) {
        Object[] a = (Object[]) o;
        File file = new File();
        file.setId(ConversionTools.getValue(a[0]));
        file.setName((String) a[1]);
        file.setUid((String) a[2]);
        file.setAnswerId(ConversionTools.getValue(a[3]));
        result.add(file);
    }
    return result;
}


public Draft internalGetDraftForInviation(String uniqueCode){
    Session session = sessionFactory.getCurrentSession();
    int counter = 1;
    while (true) {
        try {
            Query query = session.createQuery("FROM Draft d WHERE d.answerSet.invitationId = :uniqueCode").setString(Constants.UNIQUECODE, uniqueCode);
            @SuppressWarnings("unchecked")
            List<Draft> list = query.list();
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (org.hibernate.exception.LockAcquisitionException ex) {
            logger.info("lock on draft table catched; retry counter: " + counter);
            counter++;
            if (counter > 60) {
                logger.error(ex.getLocalizedMessage(), ex);
                throw ex;
            }
            Thread.sleep(1000);
        }
    }
}


@Transactional(readOnly = true)
public ResultFilter initialize(ResultFilter filter){
    if (filter.getId() == 0) {
        return filter;
    }
    Session session = sessionFactory.getCurrentSession();
    session.evict(filter);
    filter = (ResultFilter) session.merge(filter);
    Hibernate.initialize(filter.getFilterValues());
    Hibernate.initialize(filter.getExportedQuestions());
    Hibernate.initialize(filter.getVisibleQuestions());
    Hibernate.initialize(filter.getExportedExplanations());
    Hibernate.initialize(filter.getVisibleExplanations());
    Hibernate.initialize(filter.getExportedDiscussions());
    Hibernate.initialize(filter.getVisibleDiscussions());
    Hibernate.initialize(filter.getLanguages());
    return filter;
}


@Transactional
public Statistics getStatisticsForFilterHash(int surveyId,String hash,boolean useEagerLoading){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("from Statistics s where s.surveyId=:surveyId and filterHash=:filterHash order by id DESC");
    query.setInteger("surveyId", surveyId);
    query.setString("filterHash", hash);
    query.setMaxResults(1);
    Statistics result = (Statistics) query.uniqueResult();
    if (result != null) {
        if (result.getInvalid() != null && result.getInvalid()) {
            return null;
        }
        Hibernate.initialize(result.getRequestedRecordsPercent());
        Hibernate.initialize(result.getRequestedRecords());
        Hibernate.initialize(result.getTotalsPercent());
        Hibernate.initialize(result.getRequestedRecordsScore());
        Hibernate.initialize(result.getRequestedRecordsPercentScore());
        Hibernate.initialize(result.getMeanSectionScore());
        Hibernate.initialize(result.getBestSectionScore());
        Hibernate.initialize(result.getMaxSectionScore());
    }
    return result;
}


@Transactional(readOnly = true)
public int[] getAnswerStatistics(int surveyId){
    int[] intArray = new int[3];
    Survey survey = surveyService.getSurvey(surveyId);
    intArray[0] = this.getNumberOfAnswerSetsPublished(survey.getUniqueId());
    intArray[1] = this.getNumberOfDrafts(survey.getUniqueId());
    intArray[2] = this.participationService.getNumberOfOpenInvitations(survey.getUniqueId());
    return intArray;
}


}