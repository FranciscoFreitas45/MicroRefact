package com.ec.survey.model;
 import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "DRAFTS")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Draft {

@SuppressWarnings("unused")
 private  long serialVersionUID;

 private  Integer id;

 private  AnswerSet answerSet;

 private  String uniqueId;


@OneToOne(cascade = CascadeType.ALL)
public AnswerSet getAnswerSet(){
    return answerSet;
}


public void setAnswerSet(AnswerSet s){
    this.answerSet = s;
}


public void setId(Integer id){
    this.id = id;
}


@Id
@Column(name = "DRAFT_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setUniqueId(String uniqueId){
    this.uniqueId = uniqueId;
}


@Column(name = "DRAFT_UID")
public String getUniqueId(){
    return uniqueId;
}


}