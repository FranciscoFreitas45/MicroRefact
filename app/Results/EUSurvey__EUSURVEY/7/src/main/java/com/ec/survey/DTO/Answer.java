package com.ec.survey.DTO;
 import com.ec.survey.model.survey.base.File;
import com.ec.survey.tools.Tools;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.json.simple.JSONObject;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class Answer {

 private  long serialVersionUID;

 private  Integer id;

 private  String questionUniqueId;

 private  String possibleAnswerUniqueId;

 private  int questionId;

 private  int possibleAnswerId;

 private  int sourceQuestionId;

 private  int answerSetId;

 private  AnswerSet answerSet;

 private  String value;

 private  String title;

 private  List<File> files;

 private  int row;

 private  int column;

 private  Boolean isDraft;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@Column(name = "PA_ID")
public Integer getPossibleAnswerId(){
    return possibleAnswerId;
}


@Column(name = "SOURCE_QUESTION_ID")
public Integer getSourceQuestionId(){
    return sourceQuestionId;
}


@Transient
public String getValueEscaped(){
    return JSONObject.escape(value);
}


@Id
@Column(name = "ANSWER_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "ANSWER_ISDRAFT")
public Boolean getIsDraft(){
    return isDraft;
}


@ManyToOne
@JoinColumn(name = "AS_ID")
public AnswerSet getAnswerSet(){
    return answerSet;
}


@Transient
public String getTitle(){
    return title;
}


@Column(name = "ANSWER_COL")
public Integer getColumn(){
    return column;
}


@Transient
public int getAnswerSetId(){
    if (answerSet != null)
        return answerSet.getId();
    return answerSetId;
}


@Column(name = "PA_UID")
public String getPossibleAnswerUniqueId(){
    return possibleAnswerUniqueId;
}


@Column(name = "ANSWER_ROW")
public Integer getRow(){
    return row;
}


@OneToMany(targetEntity = File.class, cascade = CascadeType.ALL)
@Fetch(value = FetchMode.SELECT)
@OrderBy(value = "name asc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<File> getFiles(){
    return files;
}


@Column(name = "QUESTION_UID")
public String getQuestionUniqueId(){
    return questionUniqueId;
}


@Lob
@Column(name = "VALUE")
public String getValue(){
    return value;
}


@Column(name = "QUESTION_ID")
public Integer getQuestionId(){
    return questionId;
}


public void setAnswerSet(AnswerSet s){
    this.answerSet = s;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAnswerSet"))

.queryParam("s",s)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQuestionId(Integer questionId){
    this.questionId = questionId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQuestionId"))

.queryParam("questionId",questionId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQuestionUniqueId(String questionUniqueId){
    this.questionUniqueId = questionUniqueId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQuestionUniqueId"))

.queryParam("questionUniqueId",questionUniqueId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPossibleAnswerUniqueId(String possibleAnswerUniqueId){
    this.possibleAnswerUniqueId = possibleAnswerUniqueId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPossibleAnswerUniqueId"))

.queryParam("possibleAnswerUniqueId",possibleAnswerUniqueId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setValue(String value){
    if (value != null && Tools.containsNonUTF83Bytes(value)) {
        this.value = Tools.toUTF83Bytes(value);
    } else {
        this.value = value;
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setValue"))

.queryParam("value",value)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRow(Integer row){
    this.row = row;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRow"))

.queryParam("row",row)
;
restTemplate.put(builder.toUriString(),null);
}


public void setColumn(Integer column){
    this.column = column;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setColumn"))

.queryParam("column",column)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPossibleAnswerId(Integer possibleAnswerId){
    this.possibleAnswerId = possibleAnswerId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPossibleAnswerId"))

.queryParam("possibleAnswerId",possibleAnswerId)
;
restTemplate.put(builder.toUriString(),null);
}


}