package com.ec.survey.DTO;
 import com.ec.survey.exception.TooManyFiltersException;
import com.ec.survey.model.AnswerSet;
import com.ec.survey.model.ResultFilter;
import com.ec.survey.model.Statistics;
import com.ec.survey.model.survey;
import com.ec.survey.model.delphi.NumberQuestionStatistics;
import com.ec.survey.model.survey.quiz.QuizResult;
import com.ec.survey.service.AnswerService;
import com.ec.survey.service.ReportingService;
import com.ec.survey.service.ReportingServiceProxy;
import com.ec.survey.service.SurveyService;
import com.ec.survey.tools.Constants;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.QuizHelper;
import org.apache.log4j.Logger;
import org.hibernate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import com.ec.survey.Interface.AnswerService;
import com.ec.survey.Interface.SurveyService;
import com.ec.survey.Interface.ReportingServiceProxy;
import com.ec.survey.Interface.Survey;
import com.ec.survey.Interface.ResultFilter;
public class StatisticsCreator implements Runnable{

 protected  AnswerService answerService;

 protected  SurveyService surveyService;

 protected  SessionFactory sessionFactory;

 protected  ReportingServiceProxy reportingService;

 protected  Logger logger;

 private  Survey survey;

 private  ResultFilter filter;

 private  boolean allanswers;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public Survey getSurvey(){
    return survey;
}


public ResultFilter getFilter(){
    return filter;
}


@Transactional
public NumberQuestionStatistics getAnswers4NumberQuestionStatistics(Survey survey,NumberQuestion question){
    Session session = sessionFactory.getCurrentSession();
    HashMap<String, Object> values = new HashMap<>();
    NumberQuestionStatistics numberQuestionStats = new NumberQuestionStatistics();
    String where = answerService.getSql(null, survey.getId(), filter, values, true);
    String sql = "SELECT a.VALUE, a.QUESTION_ID FROM ANSWERS_SET ans LEFT OUTER JOIN ANSWERS a ON a.AS_ID = ans.ANSWER_SET_ID where a.QUESTION_UID";
    sql += " = :questionuid AND ans.ANSWER_SET_ID IN (" + where + ")";
    values.put("questionuid", question.getUniqueId());
    SQLQuery query = session.createSQLQuery(sql);
    query.setReadOnly(true);
    for (Entry<String, Object> entry : values.entrySet()) {
        if (entry.getValue() instanceof String) {
            query.setString(entry.getKey(), (String) entry.getValue());
        } else if (entry.getValue() instanceof Integer) {
            query.setInteger(entry.getKey(), (Integer) entry.getValue());
        } else if (entry.getValue() instanceof Date) {
            query.setTimestamp(entry.getKey(), (Date) entry.getValue());
        }
    }
    query.setFetchSize(Integer.MIN_VALUE);
    ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
    Map<String, Integer> map = new HashMap<>();
    while (results != null && results.next()) {
        Object[] a = results.get();
        String value = (String) a[0];
        Integer qid = ConversionTools.getValue(a[1]);
        Integer count = map.getOrDefault(value, 0);
        map.put(value, count + 1);
        numberQuestionStats.incrementNumberVotes();
        if (qid.equals(question.getId())) {
            numberQuestionStats.setQuestionFound(true);
        }
    }
    if (null != results) {
        results.close();
    }
    numberQuestionStats.setValuesMagnitude(map);
    return numberQuestionStats;
}


@Transactional
public int getAnswers4Statistics(Survey survey,ResultFilter filter,Map<Integer,Integer> map,Map<Integer,Map<Integer,Integer>> mapMatrix,Map<Integer,Map<Integer,Integer>> mapGallery,Map<Integer,Map<String,Set<String>>> multipleChoiceSelectionsByAnswerset,Map<Integer,Map<Integer,Integer>> mapRatingQuestion,Map<String,Integer> mapNumberQuestion){
    boolean quiz = survey.getIsQuiz();
    Set<String> multipleChoiceQuestionUids = new HashSet<>();
    if (quiz) {
        for (Question q : survey.getQuestions()) {
            if (q instanceof MultipleChoiceQuestion) {
                multipleChoiceQuestionUids.add(q.getUniqueId());
            }
        }
    }
    Set<String> numberQuestionUids = new HashSet<>();
    for (Question q : survey.getQuestions()) {
        if (q instanceof NumberQuestion && ((NumberQuestion) q).showStatisticsForNumberQuestion()) {
            numberQuestionUids.add(q.getUniqueId());
        }
    }
    if (reportingService.OLAPTableExists(survey.getUniqueId(), survey.getIsDraft())) {
        Map<String, Object> values = new HashMap<>();
        String where = ReportingService.getWhereClause(filter, values, survey);
        try {
            for (Question q : survey.getQuestions()) {
                addReportingAnswers4Statistics(q, map, mapMatrix, mapGallery, mapRatingQuestion, values, where, mapNumberQuestion);
            }
            return reportingService.getCount(survey, where, values);
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage(), e);
        }
    }
    Session session = sessionFactory.getCurrentSession();
    HashMap<String, Object> values = new HashMap<>();
    Map<Integer, String> uniqueIdsById = SurveyService.getUniqueIdsById(survey);
    String where = answerService.getSql(null, survey.getId(), filter, values, true);
    String sql = "select a.PA_ID, a.PA_UID, a.QUESTION_ID, a.QUESTION_UID, a.VALUE, ans.ANSWER_SET_ID FROM ANSWERS_SET ans LEFT OUTER JOIN ANSWERS a ON a.AS_ID = ans.ANSWER_SET_ID where ans.ANSWER_SET_ID IN (" + where + ")";
    SQLQuery query = session.createSQLQuery(sql);
    query.setReadOnly(true);
    for (Entry<String, Object> entry : values.entrySet()) {
        if (entry.getValue() instanceof String) {
            query.setString(entry.getKey(), (String) entry.getValue());
        } else if (entry.getValue() instanceof Integer) {
            query.setInteger(entry.getKey(), (Integer) entry.getValue());
        } else if (entry.getValue() instanceof Date) {
            query.setTimestamp(entry.getKey(), (Date) entry.getValue());
        }
    }
    query.setFetchSize(Integer.MIN_VALUE);
    ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
    Map<String, Integer> counts = new HashMap<>();
    Map<String, Integer> countsUID = new HashMap<>();
    Map<String, Integer> matrixcounts = new HashMap<>();
    Map<String, Integer> matrixcountsUID = new HashMap<>();
    Map<String, Integer> ratingquestioncounts = new HashMap<>();
    Map<String, Integer> ratingquestioncountsUID = new HashMap<>();
    Map<String, Integer> gallerycounts = new HashMap<>();
    Set<Integer> resultSets = new HashSet<>();
    Map<String, Set<Integer>> answerSetQuestion = new HashMap<>();
    while (results != null && results.next()) {
        parseAnswers4Statistics(results, resultSets, quiz, multipleChoiceQuestionUids, multipleChoiceSelectionsByAnswerset, answerSetQuestion, counts, countsUID, matrixcounts, matrixcountsUID, ratingquestioncounts, ratingquestioncountsUID, gallerycounts, uniqueIdsById, numberQuestionUids, mapNumberQuestion);
    }
    results.close();
    for (Question q : survey.getQuestions()) {
        addMainAnswers4Statistics(q, map, mapMatrix, mapGallery, mapRatingQuestion, counts, countsUID, gallerycounts, answerSetQuestion, matrixcounts, matrixcountsUID, ratingquestioncounts, ratingquestioncountsUID, mapNumberQuestion);
    }
    return resultSets.size();
}


@Transactional
public List<String> getAnswers4FreeTextStatistics(Survey survey,Question question){
    Session session = sessionFactory.getCurrentSession();
    HashMap<String, Object> values = new HashMap<>();
    List<String> answers = new ArrayList<>();
    String where = answerService.getSql(null, survey.getId(), filter, values, true);
    String sql = "SELECT a.VALUE, a.QUESTION_ID FROM ANSWERS_SET ans LEFT OUTER JOIN ANSWERS a ON a.AS_ID = ans.ANSWER_SET_ID where a.QUESTION_UID";
    sql += " = :questionuid AND ans.ANSWER_SET_ID IN (" + where + ")";
    values.put("questionuid", question.getUniqueId());
    SQLQuery query = session.createSQLQuery(sql);
    query.setReadOnly(true);
    for (Entry<String, Object> entry : values.entrySet()) {
        if (entry.getValue() instanceof String) {
            query.setString(entry.getKey(), (String) entry.getValue());
        } else if (entry.getValue() instanceof Integer) {
            query.setInteger(entry.getKey(), (Integer) entry.getValue());
        } else if (entry.getValue() instanceof Date) {
            query.setTimestamp(entry.getKey(), (Date) entry.getValue());
        }
    }
    query.setFetchSize(Integer.MIN_VALUE);
    ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
    while (results != null && results.next()) {
        Object[] a = results.get();
        String value = (String) a[0];
        answers.add(value);
    }
    if (null != results) {
        results.close();
    }
    return answers;
}


public int addStatistics4RatingQuestion(Survey survey,Integer answer,Element question,Statistics statistics,Map<Integer,Map<Integer,Integer>> numberOfAnswersMapRatingQuestion){
    String id = question.getId().toString() + answer.toString();
    int numberOfAnswers = 0;
    int total = survey.getNumberOfAnswerSets();
    double percent = 0;
    if (numberOfAnswersMapRatingQuestion.containsKey(question.getId())) {
        numberOfAnswers = numberOfAnswersMapRatingQuestion.get(question.getId()).getOrDefault(answer, 0);
        percent = total == 0 ? 0 : (((double) numberOfAnswers) / ((double) total) * 100);
    }
    statistics.getRequestedRecords().put(id, numberOfAnswers);
    statistics.getRequestedRecordsPercent().put(id, percent);
    statistics.getTotalsPercent().put(id, percent);
    return numberOfAnswers;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addStatistics4RatingQuestion"))

.queryParam("survey",survey)
.queryParam("answer",answer)
.queryParam("question",question)
.queryParam("statistics",statistics)
.queryParam("numberOfAnswersMapRatingQuestion",numberOfAnswersMapRatingQuestion)
;
int aux = restTemplate.getForObject(builder.toUriString(),int.class);
return aux;
}


public void init(Survey survey,ResultFilter filter,boolean allanswers){
    this.survey = survey;
    this.filter = filter;
    this.allanswers = allanswers;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/init"))

.queryParam("survey",survey)
.queryParam("filter",filter)
.queryParam("allanswers",allanswers)
;
restTemplate.put(builder.toUriString(),null);
}


public void addStatistics(Survey survey,ChoiceQuestion question,Statistics statistics,Map<Integer,Integer> numberOfAnswersMap,Map<Integer,Map<String,Set<String>>> multipleChoiceSelectionsByAnswerset){
    boolean quiz = survey.getIsQuiz() && question.getScoring() > 0;
    int total = survey.getNumberOfAnswerSets();
    int correct = 0;
    for (PossibleAnswer answer : question.getAllPossibleAnswers()) {
        int numberOfAnswers = numberOfAnswersMap.getOrDefault(answer.getId(), 0);
        double percent = total == 0 ? 0 : (double) numberOfAnswers / (double) total * 100;
        statistics.getRequestedRecords().put(answer.getId().toString(), numberOfAnswers);
        statistics.getRequestedRecordsPercent().put(answer.getId().toString(), percent);
        statistics.getTotalsPercent().put(answer.getId().toString(), percent);
        if (quiz && answer.getScoring().isCorrect()) {
            correct += numberOfAnswers;
        }
    }
    if (quiz) {
        if (question instanceof MultipleChoiceQuestion) {
            // correct means exactly all correct answers are selected
            correct = 0;
            for (Entry<Integer, Map<String, Set<String>>> entry : multipleChoiceSelectionsByAnswerset.entrySet()) {
                int ascorrect = 1;
                if (entry.getValue().containsKey(question.getUniqueId())) {
                    Set<String> answerUIDs = entry.getValue().get(question.getUniqueId());
                    for (PossibleAnswer answer : question.getAllPossibleAnswers()) {
                        if ((answer.getScoring().isCorrect() && !answerUIDs.contains(answer.getUniqueId())) || (!answer.getScoring().isCorrect() && answerUIDs.contains(answer.getUniqueId()))) {
                            ascorrect = 0;
                            break;
                        }
                    }
                }
                correct += ascorrect;
            }
        }
        statistics.getRequestedRecordsScore().put(question.getId().toString(), correct);
        double percent = total == 0 ? 0 : (double) correct / (double) total * 100;
        statistics.getRequestedRecordsPercentScore().put(question.getId().toString(), percent);
    }
    int answered = numberOfAnswersMap.get(question.getId());
    statistics.getRequestedRecords().put(question.getId().toString(), survey.getNumberOfAnswerSets() - answered);
    double percent = total == 0 ? 0 : (double) (survey.getNumberOfAnswerSets() - answered) / (double) total * 100;
    statistics.getRequestedRecordsPercent().put(question.getId().toString(), percent);
    statistics.getTotalsPercent().put(question.getId().toString(), percent);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addStatistics"))

.queryParam("survey",survey)
.queryParam("question",question)
.queryParam("statistics",statistics)
.queryParam("numberOfAnswersMap",numberOfAnswersMap)
.queryParam("multipleChoiceSelectionsByAnswerset",multipleChoiceSelectionsByAnswerset)
;
restTemplate.put(builder.toUriString(),null);
}


public int addStatistics4Matrix(Survey survey,Element answer,Element question,Statistics statistics,Map<Integer,Map<Integer,Integer>> numberOfAnswersMapMatrix){
    String id = question.getId().toString() + answer.getId().toString();
    int numberOfAnswers = 0;
    int total = survey.getNumberOfAnswerSets();
    double percent = 0;
    if (numberOfAnswersMapMatrix.containsKey(question.getId())) {
        numberOfAnswers = numberOfAnswersMapMatrix.get(question.getId()).getOrDefault(answer.getId(), 0);
        percent = total == 0 ? 0 : (((double) numberOfAnswers) / ((double) total) * 100);
    }
    statistics.getRequestedRecords().put(id, numberOfAnswers);
    statistics.getRequestedRecordsPercent().put(id, percent);
    statistics.getTotalsPercent().put(id, percent);
    return numberOfAnswers;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addStatistics4Matrix"))

.queryParam("survey",survey)
.queryParam("answer",answer)
.queryParam("question",question)
.queryParam("statistics",statistics)
.queryParam("numberOfAnswersMapMatrix",numberOfAnswersMapMatrix)
;
int aux = restTemplate.getForObject(builder.toUriString(),int.class);
return aux;
}


}