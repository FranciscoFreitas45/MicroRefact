package com.lingxiang2014.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "lx_passwordProtection")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "lx_passwordProtection_sequence")
public class PasswordProtection extends BaseEntity{

 private  long serialVersionUID;

 private  Member member;

 private  String question;

 private  String answer;

 private  Boolean isEnabled;


@NotNull
@Column(nullable = false)
public String getQuestion(){
    return question;
}


public void setMember(Member member){
    this.member = member;
}


@NotNull
@Column(nullable = false)
public String getAnswer(){
    return answer;
}


public void setAnswer(String answer){
    this.answer = answer;
}


public void setIsEnabled(Boolean isEnabled){
    this.isEnabled = isEnabled;
}


@ManyToOne(fetch = FetchType.LAZY)
public Member getMember(){
    return member;
}


public void setQuestion(String question){
    this.question = question;
}


@NotNull
@Column(nullable = false)
public Boolean getIsEnabled(){
    return isEnabled;
}


}