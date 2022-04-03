package com.ec.survey.model;
 import java.util.Date;
import javax.persistence;
import org.springframework.format.annotation.DateTimeFormat;
import com.ec.survey.tools.ConversionTools;
@Entity
@Table(name = "ANSWERS_COMMENTS", indexes = { @Index(name = "ANSWERCOMMENT_IDX", columnList = "ANSWER_SET_ID, QUESTION_UID") })
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


public void setAnswerSetId(Integer answerSetId){
    this.answerSetId = answerSetId;
}


public void setReadByParent(Boolean readByParent){
    this.readByParent = readByParent;
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


public void setReadByParticipant(Boolean readByParticipant){
    this.readByParticipant = readByParticipant;
}


public void setUniqueCode(String uniqueCode){
    this.uniqueCode = uniqueCode;
}


public void setId(Integer id){
    this.id = id;
}


public void setDate(Date date){
    this.date = date;
}


@Column(name = "COMMENT_DATE")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getDate(){
    return date;
}


public void setParent(AnswerComment parent){
    this.parent = parent;
}


@Column(name = "ANSWER_SET_ID")
public Integer getAnswerSetId(){
    return answerSetId;
}


public void setQuestionUid(String questionUid){
    this.questionUid = questionUid;
}


@Column(name = "ANSWER_SET_CODE")
public String getUniqueCode(){
    return uniqueCode;
}


public void setText(String text){
    this.text = text;
}


}