package com.ec.survey.DTO;
 import com.ec.survey.model.survey.base.File;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
public class AnswerExplanation {

 private  long serialVersionUID;

 private  Integer id;

 private  Integer answerSetId;

 private  String questionUid;

 private  String text;

 private  List<File> explanationFiles;

 private  Boolean changedForMedian;

public AnswerExplanation() {
}public AnswerExplanation(int answerSetId, String questionUid) {
    this.answerSetId = answerSetId;
    this.questionUid = questionUid;
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
@Column(name = "ANSWER_EXPLANATION_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@OneToMany(targetEntity = File.class, cascade = CascadeType.ALL)
@Fetch(value = FetchMode.SELECT)
@OrderBy(value = "name asc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<File> getFiles(){
    return explanationFiles;
}


@Column(name = "CHANGED")
public Boolean getChangedForMedian(){
    return changedForMedian;
}


@Column(name = "ANSWER_SET_ID")
public Integer getAnswerSetId(){
    return answerSetId;
}


}