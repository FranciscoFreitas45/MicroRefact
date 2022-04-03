package com.ec.survey.DTO;
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
public class Draft {

 private  long serialVersionUID;

 private  Integer id;

 private  AnswerSet answerSet;

 private  String uniqueId;


@OneToOne(cascade = CascadeType.ALL)
public AnswerSet getAnswerSet(){
    return answerSet;
}


@Id
@Column(name = "DRAFT_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "DRAFT_UID")
public String getUniqueId(){
    return uniqueId;
}


}