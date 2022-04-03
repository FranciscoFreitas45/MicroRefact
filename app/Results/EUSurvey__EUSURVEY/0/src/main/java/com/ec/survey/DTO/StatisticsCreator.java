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


@Transactional
public Statistics runSync(){
    Statistics statistics = new Statistics();
    statistics.setSurveyId(survey.getId());
    statistics.setFilterHash(filter.getHash(allanswers));
    Session session = sessionFactory.getCurrentSession();
    survey = (Survey) session.merge(survey);
    surveyService.initializeSurvey(survey);
    session.evict(survey);
    if (allanswers && !survey.isMissingElementsChecked()) {
        surveyService.checkAndRecreateMissingElements(survey, filter);
    }
    Map<Integer, Integer> numberOfAnswersMap = new HashMap<>();
    Map<Integer, Map<Integer, Integer>> numberOfAnswersMapMatrix = new HashMap<>();
    Map<Integer, Map<Integer, Integer>> numberOfAnswersMapRatingQuestion = new HashMap<>();
    Map<Integer, Map<Integer, Integer>> numberOfAnswersMapGallery = new HashMap<>();
    Map<Integer, Map<String, Set<String>>> multipleChoiceSelectionsByAnswerset = new HashMap<>();
    Map<String, Integer> numberOfNumberAnswersMap = new HashMap<>();
    int total = getAnswers4Statistics(survey, filter, numberOfAnswersMap, numberOfAnswersMapMatrix, numberOfAnswersMapGallery, multipleChoiceSelectionsByAnswerset, numberOfAnswersMapRatingQuestion, numberOfNumberAnswersMap);
    survey.setNumberOfAnswerSets(total);
    List<Question> quizquestions = new ArrayList<>();
    for (Element element : survey.getQuestions()) {
        if (element instanceof ChoiceQuestion) {
            addStatistics(survey, (ChoiceQuestion) element, statistics, numberOfAnswersMap, multipleChoiceSelectionsByAnswerset);
        } else if (element instanceof Matrix) {
            Matrix matrix = (Matrix) element;
            for (Element questionElement : matrix.getQuestions()) {
                for (Element answerElement : matrix.getAnswers()) {
                    addStatistics4Matrix(survey, answerElement, questionElement, statistics, numberOfAnswersMapMatrix);
                }
                int answered = numberOfAnswersMap.get(questionElement.getId());
                statistics.getRequestedRecords().put(questionElement.getId().toString(), survey.getNumberOfAnswerSets() - answered);
                double percent = survey.getNumberOfAnswerSets() == 0 ? 0 : (double) (survey.getNumberOfAnswerSets() - answered) / (double) survey.getNumberOfAnswerSets() * 100;
                statistics.getRequestedRecordsPercent().put(questionElement.getId().toString(), percent);
                statistics.getTotalsPercent().put(questionElement.getId().toString(), percent);
            }
        } else if (element instanceof RatingQuestion) {
            RatingQuestion rating = (RatingQuestion) element;
            for (Element questionElement : rating.getQuestions()) {
                for (int i = 1; i <= rating.getNumIcons(); i++) {
                    addStatistics4RatingQuestion(survey, i, questionElement, statistics, numberOfAnswersMapRatingQuestion);
                }
                int answered = numberOfAnswersMap.get(questionElement.getId());
                statistics.getRequestedRecords().put(questionElement.getId().toString(), survey.getNumberOfAnswerSets() - answered);
                double percent = survey.getNumberOfAnswerSets() == 0 ? 0 : (double) (survey.getNumberOfAnswerSets() - answered) / (double) survey.getNumberOfAnswerSets() * 100;
                statistics.getRequestedRecordsPercent().put(questionElement.getId().toString(), percent);
                statistics.getTotalsPercent().put(questionElement.getId().toString(), percent);
            }
        } else if (element instanceof GalleryQuestion) {
            addStatistics4Gallery(survey, (GalleryQuestion) element, statistics, numberOfAnswersMapGallery, numberOfAnswersMap);
        } else if (element instanceof NumberQuestion) {
            NumberQuestion number = (NumberQuestion) element;
            if (number.showStatisticsForNumberQuestion()) {
                addStatistics4NumberQuestion(survey, number, statistics, numberOfNumberAnswersMap, numberOfAnswersMap);
            }
            if (survey.getIsQuiz() && number.getScoring() > 0) {
                quizquestions.add(number);
            }
        } else if (survey.getIsQuiz() && element instanceof Question) {
            Question question = (Question) element;
            if (question.getScoring() > 0) {
                quizquestions.add(question);
            }
        }
    }
    Map<Integer, Map<Integer, Integer>> scorePoints = new HashMap<>();
    if (survey.getIsQuiz()) {
        int bestScore = 0;
        int maxScore = 0;
        int totalScore = 0;
        int counter = 0;
        Map<String, Integer> questionMaximumScores = new HashMap<>();
        List<AnswerSet> allanswers = answerService.getAllAnswers(survey.getId(), filter);
        for (AnswerSet answerSet : allanswers) {
            QuizResult quizResult = QuizHelper.getQuizResult(answerSet, survey);
            totalScore += quizResult.getScore();
            if (quizResult.getScore() > bestScore)
                bestScore = quizResult.getScore();
            if (maxScore == 0)
                maxScore = quizResult.getMaximumScore();
            counter++;
            for (Question question : quizquestions) {
                if (!questionMaximumScores.containsKey(question.getUniqueId())) {
                    questionMaximumScores.put(question.getUniqueId(), quizResult.getQuestionMaximumScore(question.getUniqueId()));
                }
                int score = quizResult.getQuestionScore(question.getUniqueId());
                if (score > 0) {
                    if (!scorePoints.containsKey(question.getId())) {
                        scorePoints.put(question.getId(), new HashMap<>());
                    }
                    if (!scorePoints.get(question.getId()).containsKey(score)) {
                        scorePoints.get(question.getId()).put(score, 1);
                    } else {
                        scorePoints.get(question.getId()).put(score, scorePoints.get(question.getId()).get(score) + 1);
                    }
                }
            }
            for (String sectionUid : quizResult.getSectionScores().keySet()) {
                String scorestring = quizResult.getSectionScores().get(sectionUid);
                double score = Double.parseDouble(scorestring.substring(0, scorestring.indexOf('/')));
                int max = Integer.parseInt(scorestring.substring(scorestring.indexOf('/') + 1));
                if (!statistics.getMaxSectionScore().containsKey(sectionUid)) {
                    statistics.getMaxSectionScore().put(sectionUid, max);
                }
                if (!statistics.getMeanSectionScore().containsKey(sectionUid)) {
                    statistics.getMeanSectionScore().put(sectionUid, 0.0d);
                }
                statistics.getMeanSectionScore().put(sectionUid, statistics.getMeanSectionScore().get(sectionUid) + score);
                if (!statistics.getBestSectionScore().containsKey(sectionUid)) {
                    statistics.getBestSectionScore().put(sectionUid, score);
                } else {
                    double oldscore = statistics.getBestSectionScore().get(sectionUid);
                    if (score > oldscore) {
                        statistics.getBestSectionScore().put(sectionUid, score);
                    }
                }
            }
        }
        for (String sectionUid : statistics.getMeanSectionScore().keySet()) {
            statistics.getMeanSectionScore().put(sectionUid, total == 0 ? 0 : statistics.getMeanSectionScore().get(sectionUid) / total);
        }
        statistics.setBestScore(bestScore);
        statistics.setMaxScore(maxScore);
        statistics.setMeanScore((counter == 0 || total == 0) ? 0.0 : ((double) totalScore / total));
        statistics.setTotal(total);
        for (Question question : quizquestions) {
            addStatistics4Quiz(survey, question, statistics, scorePoints, questionMaximumScores);
        }
    }
    for (Element element : survey.getMissingElements()) {
        if (element instanceof ChoiceQuestion) {
            addStatistics(survey, (ChoiceQuestion) element, statistics, numberOfAnswersMap, multipleChoiceSelectionsByAnswerset);
        } else if (element instanceof RatingQuestion) {
            RatingQuestion rating = (RatingQuestion) element;
            for (Element questionElement : rating.getQuestions()) {
                for (int i = 1; i <= rating.getNumIcons(); i++) {
                    addStatistics4RatingQuestion(survey, i, questionElement, statistics, numberOfAnswersMapRatingQuestion);
                }
                int answered = numberOfAnswersMap.get(questionElement.getId());
                statistics.getRequestedRecords().put(questionElement.getId().toString(), survey.getNumberOfAnswerSets() - answered);
                double percent = survey.getNumberOfAnswerSets() == 0 ? 0 : (double) (survey.getNumberOfAnswerSets() - answered) / (double) survey.getNumberOfAnswerSets() * 100;
                statistics.getRequestedRecordsPercent().put(questionElement.getId().toString(), percent);
                statistics.getTotalsPercent().put(questionElement.getId().toString(), percent);
            }
        } else if (element instanceof Matrix) {
            Matrix matrix = (Matrix) element;
            for (Element answerElement : matrix.getAnswers()) {
                for (Element questionElement : matrix.getQuestions()) {
                    addStatistics4Matrix(survey, answerElement, questionElement, statistics, numberOfAnswersMapMatrix);
                }
                for (Element questionElement : matrix.getMissingQuestions()) {
                    addStatistics4Matrix(survey, answerElement, questionElement, statistics, numberOfAnswersMapMatrix);
                }
            }
            for (Element answerElement : matrix.getMissingAnswers()) {
                for (Element questionElement : matrix.getQuestions()) {
                    addStatistics4Matrix(survey, answerElement, questionElement, statistics, numberOfAnswersMapMatrix);
                }
                for (Element questionElement : matrix.getMissingQuestions()) {
                    addStatistics4Matrix(survey, answerElement, questionElement, statistics, numberOfAnswersMapMatrix);
                }
            }
            for (Element questionElement : matrix.getQuestions()) {
                int answered = numberOfAnswersMap.get(questionElement.getId());
                statistics.getRequestedRecords().put(questionElement.getId().toString(), survey.getNumberOfAnswerSets() - answered);
                double percent = survey.getNumberOfAnswerSets() == 0 ? 0 : (double) (survey.getNumberOfAnswerSets() - answered) / (double) survey.getNumberOfAnswerSets() * 100;
                statistics.getRequestedRecordsPercent().put(questionElement.getId().toString(), percent);
                statistics.getTotalsPercent().put(questionElement.getId().toString(), percent);
            }
            for (Element questionElement : matrix.getMissingQuestions()) {
                int answered = numberOfAnswersMap.get(questionElement.getId());
                statistics.getRequestedRecords().put(questionElement.getId().toString(), survey.getNumberOfAnswerSets() - answered);
                double percent = survey.getNumberOfAnswerSets() == 0 ? 0 : (double) (survey.getNumberOfAnswerSets() - answered) / (double) survey.getNumberOfAnswerSets() * 100;
                statistics.getRequestedRecordsPercent().put(questionElement.getId().toString(), percent);
                statistics.getTotalsPercent().put(questionElement.getId().toString(), percent);
            }
        }
    }
    try {
        answerService.save(statistics);
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
    }
    return statistics;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/runSync"))

;
Statistics aux = restTemplate.getForObject(builder.toUriString(),Statistics.class);
return aux;
}


}