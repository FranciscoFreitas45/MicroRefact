package com.ec.survey.model;
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
@Entity
@Table(name = "ANSWERS", indexes = { @Index(name = "PA_ID_IDX", columnList = "PA_ID") })
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


@Column(name = "PA_ID")
public Integer getPossibleAnswerId(){
    return possibleAnswerId;
}


public void setAnswerSetId(int answerSetId){
    this.answerSetId = answerSetId;
}


@Column(name = "SOURCE_QUESTION_ID")
public Integer getSourceQuestionId(){
    return sourceQuestionId;
}


public void setSourceQuestionId(Integer sourceQuestionId){
    this.sourceQuestionId = sourceQuestionId;
}


public void setIsDraft(Boolean isDraft){
    this.isDraft = isDraft;
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


public void setFiles(List<File> files){
    this.files = files;
}


@ManyToOne
@JoinColumn(name = "AS_ID")
public AnswerSet getAnswerSet(){
    return answerSet;
}


public void setColumn(Integer column){
    this.column = column;
}


@Transient
public String getTitle(){
    return title;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "ANSWER_COL")
public Integer getColumn(){
    return column;
}


public void setRow(Integer row){
    this.row = row;
}


public Answer copy(AnswerSet b,Map<Integer,List<File>> files){
    Answer bn = new Answer();
    bn.answerSet = b;
    bn.setColumn(getColumn());
    bn.setPossibleAnswerId(getPossibleAnswerId());
    bn.possibleAnswerUniqueId = possibleAnswerUniqueId;
    bn.setQuestionId(getQuestionId());
    bn.questionUniqueId = questionUniqueId;
    bn.setRow(getRow());
    bn.setSourceQuestionId(getSourceQuestionId());
    bn.setValue(value);
    bn.isDraft = isDraft;
    if (files.containsKey(id)) {
        for (File original : files.get(id)) {
            File copy = new File();
            copy.setName(original.getName());
            // files are recreated in SurveyExportHelper.importSurvey()
            copy.setUid(original.getUid());
            bn.files.add(copy);
        }
    }
    return bn;
}


@Transient
public int getAnswerSetId(){
    if (answerSet != null)
        return answerSet.getId();
    return answerSetId;
}


public void setQuestionUniqueId(String questionUniqueId){
    this.questionUniqueId = questionUniqueId;
}


@Column(name = "PA_UID")
public String getPossibleAnswerUniqueId(){
    return possibleAnswerUniqueId;
}


public void setQuestionId(Integer questionId){
    this.questionId = questionId;
}


public void setPossibleAnswerUniqueId(String possibleAnswerUniqueId){
    this.possibleAnswerUniqueId = possibleAnswerUniqueId;
}


public void setTitle(String title){
    this.title = title;
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


public void setAnswerSet(AnswerSet s){
    this.answerSet = s;
}


@Lob
@Column(name = "VALUE")
public String getValue(){
    return value;
}


public void setPossibleAnswerId(Integer possibleAnswerId){
    this.possibleAnswerId = possibleAnswerId;
}


@Column(name = "QUESTION_ID")
public Integer getQuestionId(){
    return questionId;
}


public void setValue(String value){
    if (value != null && Tools.containsNonUTF83Bytes(value)) {
        this.value = Tools.toUTF83Bytes(value);
    } else {
        this.value = value;
    }
}


}