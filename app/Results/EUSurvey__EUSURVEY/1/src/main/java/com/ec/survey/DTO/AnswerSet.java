package com.ec.survey.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@Column(name = "ANSWER_SET_STARTED")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getStartDate(){
    return startDate;
}


@Column(name = "ANSWER_SET_DISCLAIMER")
public Boolean getDisclaimerMinimized(){
    return disclaimerMinimized != null && disclaimerMinimized;
}


@Transient
public String getNiceDate(){
    return date != null ? ConversionTools.getFullString(date) : "";
}


@Column(name = "ECF_TOTAL_GAP")
public Integer getEcfTotalGap(){
    return ecfTotalGap;
}


@Column(name = "ISDRAFT")
public boolean getIsDraft(){
    return isDraft;
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


@Transient
public String getMatrixAnswer(String questionUid,String answerUid){
    for (Answer answer : answers) {
        if (answer.getQuestionUniqueId() != null && answer.getPossibleAnswerUniqueId() != null && answer.getQuestionUniqueId().equals(questionUid) && answer.getPossibleAnswerUniqueId().equals(answerUid)) {
            return answer.getValue();
        }
    }
    return null;
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


@Transient
public String getDraftId(){
    return draftId;
}


@Transient
public Boolean getChangeExplanationText(){
    return changeExplanationText;
}


@Column(name = "ANSWER_SET_INVID")
public String getInvitationId(){
    return invitationId;
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


@Column(name = "ANSWER_SET_WCAG")
public Boolean getWcagMode(){
    return wcagMode;
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


@Column(name = "UNIQUECODE", length = 36)
public String getUniqueCode(){
    return uniqueCode;
}


@Column(name = "ANSWER_SET_LANG")
public String getLanguageCode(){
    return languageCode;
}


@Transient
public Map<String,ExplanationData> getExplanations(){
    return explanations;
}


@Transient
public boolean getMedianWarningVisible(){
    return this.medianWarningVisible;
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


@Column(name = "SURVEY_ID", insertable = false, updatable = false)
public int getSurveyId(){
    return surveyId;
}


@Transient
public String getNiceUpdateDate(){
    return updateDate != null ? ConversionTools.getFullString(updateDate) : "";
}


@Column(name = "SCORE")
public Integer getScore(){
    return score;
}


public void setChangeExplanationText(Boolean changeExplanationText){
    this.changeExplanationText = changeExplanationText;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setChangeExplanationText"))

.queryParam("changeExplanationText",changeExplanationText)
;
restTemplate.put(builder.toUriString(),null);
}


public void setChangedForMedian(Boolean changedForMedian){
    this.changedForMedian = changedForMedian;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setChangedForMedian"))

.queryParam("changedForMedian",changedForMedian)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInvitationId(String invitationId){
    this.invitationId = invitationId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInvitationId"))

.queryParam("invitationId",invitationId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setResponderEmail(String responderEmail){
    this.responderEmail = responderEmail;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setResponderEmail"))

.queryParam("responderEmail",responderEmail)
;
restTemplate.put(builder.toUriString(),null);
}


@Transient
public String serialize(){
    StringBuilder result = new StringBuilder();
    for (Answer answer : answers) {
        result.append(" ").append(answer.getQuestionUniqueId()).append(":").append(answer.getValue()).append(";");
    }
    return result.toString();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/serialize"))

;
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


}