package com.ec.survey.DTO;
 import java.util.Date;
import javax.persistence;
import org.springframework.format.annotation.DateTimeFormat;
import com.ec.survey.tools.ConversionTools;
public class AnswerComment {

 private  long serialVersionUID;

 private  Integer id;

 private  String uniqueCode;

 private  Integer answerSetId;

 private  String questionUid;

 private  String text;

 private  AnswerComment parent;

 private  Date date;

 private  Boolean readByParent;

 private  Boolean readByParticipant;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

public AnswerComment() {
}public AnswerComment(int answerSetId, String questionUid) {
    this.answerSetId = answerSetId;
    this.questionUid = questionUid;
}
@ManyToOne
@JoinColumn(name = "PARENT")
public AnswerComment getParent(){
    return parent;
}


@Column(name = "READ_BY_PARTICIPANT")
public Boolean getReadByParticipant(){
    return readByParticipant != null ? readByParticipant : false;
}


@Column(name = "QUESTION_UID")
public String getQuestionUid(){
    return questionUid;
}


@Lob
@Column(name = "TEXT", nullable = false)
public String getText(){
    return text;
}


@Id
@Column(name = "ANSWER_COMMENT_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "READ_BY_PARENT")
public Boolean getReadByParent(){
    return readByParent != null ? readByParent : false;
}


@Column(name = "COMMENT_DATE")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getDate(){
    return date;
}


@Column(name = "ANSWER_SET_ID")
public Integer getAnswerSetId(){
    return answerSetId;
}


@Column(name = "ANSWER_SET_CODE")
public String getUniqueCode(){
    return uniqueCode;
}


public void setQuestionUid(String questionUid){
    this.questionUid = questionUid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQuestionUid"))

.queryParam("questionUid",questionUid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUniqueCode(String uniqueCode){
    this.uniqueCode = uniqueCode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUniqueCode"))

.queryParam("uniqueCode",uniqueCode)
;
restTemplate.put(builder.toUriString(),null);
}


public void setText(String text){
    this.text = text;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setText"))

.queryParam("text",text)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDate(Date date){
    this.date = date;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDate"))

.queryParam("date",date)
;
restTemplate.put(builder.toUriString(),null);
}


public void setParent(AnswerComment parent){
    this.parent = parent;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setParent"))

.queryParam("parent",parent)
;
restTemplate.put(builder.toUriString(),null);
}


}