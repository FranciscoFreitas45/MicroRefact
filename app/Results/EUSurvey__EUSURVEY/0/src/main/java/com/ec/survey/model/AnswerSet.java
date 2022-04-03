package com.ec.survey.model;
 import com.ec.survey.model.survey.Element;
import com.ec.survey.model.survey.Matrix;
import com.ec.survey.model.survey.RatingQuestion;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.model.survey.base.File;
import com.ec.survey.tools.ConversionTools;
import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence;
import java.util;
@Entity
@Table(name = "ANSWERS_SET", uniqueConstraints = { @UniqueConstraint(columnNames = { "UNIQUECODE", "ISDRAFT" }, name = "UNIQUECODE_UNIQUE") })
public class AnswerSet {

 public  String text;

 public  List<File> files;

 private  long serialVersionUID;

 private  Integer id;

 private  String invitationId;

 private  Date date;

 private  Date updateDate;

 private  Survey survey;

 private  int surveyId;

 private  String responderEmail;

 private  String uniqueCode;

 private  String languageCode;

 private  String IP;

 private  List<Answer> answers;

 private  boolean isDraft;

 private  String draftId;

 private  Boolean disclaimerMinimized;

 private  Boolean wcagMode;

 private  boolean medianWarningVisible;

 private  Integer score;

 private  Map<String,ExplanationData> explanations;

 private  Map<String,List<AnswerComment>> comments;

 private  boolean changedForMedian;

 private  String ecfProfileUid;

 private  Integer ecfTotalScore;

 private  Integer ecfTotalGap;

 private  boolean changeExplanationText;

 private  Date startDate;


@Column(name = "ANSWER_SET_STARTED")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getStartDate(){
    return startDate;
}


@Column(name = "ANSWER_SET_DISCLAIMER")
public Boolean getDisclaimerMinimized(){
    return disclaimerMinimized != null && disclaimerMinimized;
}


public void setIsDraft(boolean isDraft){
    this.isDraft = isDraft;
}


@Transient
public String getNiceDate(){
    return date != null ? ConversionTools.getFullString(date) : "";
}


@Column(name = "ECF_TOTAL_GAP")
public Integer getEcfTotalGap(){
    return ecfTotalGap;
}


public void setEcfTotalGap(Integer ecfTotalGap){
    this.ecfTotalGap = ecfTotalGap;
}


@Column(name = "ISDRAFT")
public boolean getIsDraft(){
    return isDraft;
}


public void setIP(String iP){
    IP = iP;
}


public void setAnswers(List<Answer> answers){
    this.answers = answers;
}


public String completionTime(){
    if (this.startDate == null || this.date == null) {
        return "-";
    }
    double created = this.date.getTime();
    created = created / 1000;
    double started = this.startDate.getTime();
    started = started / 1000;
    long seconds = Math.round(created) - Math.round(started);
    if (seconds < 0) {
        return "-";
    }
    long hours = seconds / 3600;
    seconds = seconds - (hours * 3600);
    long minutes = seconds / 60;
    seconds = seconds - (minutes * 60);
    return hours + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
}


@Column(name = "ANSWER_SET_UPDATE")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getUpdateDate(){
    return updateDate;
}


@Transient
public Map<String,List<AnswerComment>> getComments(){
    return comments;
}


public void setId(Integer id){
    this.id = id;
}


@Transient
public List<Answer> getMatrixAnswers(Matrix matrix){
    List<Answer> result = new ArrayList<>();
    Set<Integer> questionIds = new HashSet<>();
    for (Element question : matrix.getQuestions()) {
        questionIds.add(question.getId());
    }
    for (Answer answer : answers) {
        if (questionIds.contains(answer.getQuestionId())) {
            result.add(answer);
        }
    }
    return result;
}


@Transient
public List<Answer> getRatingAnswers(RatingQuestion rating){
    List<Answer> result = new ArrayList<>();
    Set<Integer> questionIds = new HashSet<>();
    for (Element question : rating.getChildElements()) {
        questionIds.add(question.getId());
    }
    for (Answer answer : answers) {
        if (questionIds.contains(answer.getQuestionId())) {
            result.add(answer);
        }
    }
    return result;
}


public void setEcfProfileUid(String ecfProfileUid){
    this.ecfProfileUid = ecfProfileUid;
}


public void setStartDate(Date startDate){
    this.startDate = startDate;
}


@Transient
public String getMatrixAnswer(String questionUid,String answerUid){
    for (Answer answer : answers) {
        if (answer.getQuestionUniqueId() != null && answer.getPossibleAnswerUniqueId() != null && answer.getQuestionUniqueId().equals(questionUid) && answer.getPossibleAnswerUniqueId().equals(answerUid)) {
            return answer.getValue();
        }
    }
    return null;
}


public void setUpdateDate(Date updateDate){
    this.updateDate = updateDate;
}


public void setExplanations(Map<String,ExplanationData> explanations){
    this.explanations = explanations;
}


@Column(name = "ECF_PROFILE_UID")
public String getEcfProfileUid(){
    return ecfProfileUid;
}


public String getIP(){
    return IP;
}


@ManyToOne
@JoinColumn(name = "SURVEY_ID")
public Survey getSurvey(){
    return survey;
}


public void setWcagMode(Boolean wcagMode){
    this.wcagMode = wcagMode;
}


@Transient
public String getDraftId(){
    return draftId;
}


@Transient
public Boolean getChangeExplanationText(){
    return changeExplanationText;
}


public void addAnswers(List<Answer> answers){
    for (Answer answer : answers) {
        addAnswer(answer);
    }
}


@Transient
public String serialize(){
    StringBuilder result = new StringBuilder();
    for (Answer answer : answers) {
        result.append(" ").append(answer.getQuestionUniqueId()).append(":").append(answer.getValue()).append(";");
    }
    return result.toString();
}


public void setChangeExplanationText(Boolean changeExplanationText){
    this.changeExplanationText = changeExplanationText;
}


@Column(name = "ANSWER_SET_INVID")
public String getInvitationId(){
    return invitationId;
}


public void setSurveyId(int surveyId){
    this.surveyId = surveyId;
}


@Transient
public void addAnswer(Answer answer){
    answers.add(answer);
}


@Id
@Column(name = "ANSWER_SET_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "RESPONDER_EMAIL")
public String getResponderEmail(){
    return responderEmail;
}


public void setResponderEmail(String responderEmail){
    this.responderEmail = responderEmail;
}


@Column(name = "ANSWER_SET_WCAG")
public Boolean getWcagMode(){
    return wcagMode;
}


public void setUniqueCode(String uniqueCode){
    this.uniqueCode = uniqueCode;
}


@Column(name = "ECF_TOTAL_SCORE")
public Integer getEcfTotalScore(){
    return ecfTotalScore;
}


@Column(name = "ANSWER_SET_DATE")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getDate(){
    return date;
}


@Transient
public AnswerSet copy(Survey survey,Map<Integer,List<File>> files){
    AnswerSet copy = new AnswerSet();
    copy.date = date;
    copy.invitationId = invitationId;
    copy.IP = IP;
    copy.setIsDraft(isDraft);
    copy.languageCode = languageCode;
    copy.disclaimerMinimized = disclaimerMinimized;
    copy.wcagMode = wcagMode;
    copy.responderEmail = responderEmail;
    copy.survey = survey;
    copy.surveyId = survey.getId();
    copy.uniqueCode = uniqueCode;
    copy.updateDate = updateDate;
    copy.score = score;
    copy.ecfProfileUid = ecfProfileUid;
    copy.ecfTotalScore = ecfTotalScore;
    copy.ecfTotalGap = ecfTotalGap;
    for (Answer answer : answers) {
        Answer copyanswer = answer.copy(copy, files);
        copy.answers.add(copyanswer);
    }
    return copy;
}


public void setEcfTotalScore(Integer ecfTotalScore){
    this.ecfTotalScore = ecfTotalScore;
}


public void setComments(Map<String,List<AnswerComment>> comments){
    this.comments = comments;
}


@Column(name = "UNIQUECODE", length = 36)
public String getUniqueCode(){
    return uniqueCode;
}


public void setDisclaimerMinimized(Boolean disclaimerMinimized){
    this.disclaimerMinimized = disclaimerMinimized;
}


@Column(name = "ANSWER_SET_LANG")
public String getLanguageCode(){
    return languageCode;
}


public void setLanguageCode(String languageCode){
    this.languageCode = languageCode;
}


public void setMedianWarningVisible(boolean medianWarningVisible){
    this.medianWarningVisible = medianWarningVisible;
}


@Transient
public Map<String,ExplanationData> getExplanations(){
    return explanations;
}


@Transient
public boolean getMedianWarningVisible(){
    return this.medianWarningVisible;
}


public void setDraftId(String draftId){
    this.draftId = draftId;
}


@Transient
public List<String> getAllFiles(){
    List<String> result = new ArrayList<>();
    for (Answer answer : answers) {
        if (answer.getFiles() != null) {
            for (File file : answer.getFiles()) {
                result.add(file.getUid());
            }
        }
    }
    return result;
}


public void setInvitationId(String invitationId){
    this.invitationId = invitationId;
}


public void setSurvey(Survey survey){
    this.survey = survey;
}


@Transient
public Boolean getChangedForMedian(){
    return changedForMedian;
}


@Transient
public List<Answer> getAnswers(int questionId,String questionUid){
    List<Answer> result = new ArrayList<>();
    for (Answer answer : answers) {
        if (answer.getQuestionId().equals(questionId) || (answer.getQuestionUniqueId() != null && answer.getQuestionUniqueId().equals(questionUid))) {
            result.add(answer);
        }
    }
    return result;
}


@Transient
public String getTableAnswer(Element question,int row,int col,boolean escape){
    for (Answer answer : answers) {
        if ((answer.getQuestionId().equals(question.getId()) || (answer.getQuestionUniqueId() != null && answer.getQuestionUniqueId().equals(question.getUniqueId()))) && answer.getRow().equals(row) && answer.getColumn().equals(col)) {
            if (escape) {
                return StringEscapeUtils.escapeXml(answer.getValue());
            }
            return answer.getValue();
        }
    }
    return null;
}


public void setDate(Date date){
    this.date = date;
}


@Column(name = "SURVEY_ID", insertable = false, updatable = false)
public int getSurveyId(){
    return surveyId;
}


@Transient
public void clearAnswers(Element question){
    List<Answer> matchinganswers = getAnswers(question.getId());
    answers.removeAll(matchinganswers);
}


@Transient
public String getNiceUpdateDate(){
    return updateDate != null ? ConversionTools.getFullString(updateDate) : "";
}


@Column(name = "SCORE")
public Integer getScore(){
    return score;
}


public void setScore(Integer score){
    this.score = score;
}


public void setChangedForMedian(Boolean changedForMedian){
    this.changedForMedian = changedForMedian;
}


}