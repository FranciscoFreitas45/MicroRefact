package com.ec.survey.model;
 import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.ec.survey.model.administration.User;
@Entity
@Table(name = "INVTEMPL", uniqueConstraints = { @UniqueConstraint(columnNames = { "INVTEMPL_NAME", "OWNER" }, name = "INVTEMPL_NAME") })
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InvitationTemplate {

 private  long serialVersionUID;

 private  Integer id;

 private  String name;

 private  String template1;

 private  String template2;

 private  String templateSubject;

 private  String templateMail;

 private  int templateText;

 private  User owner;

 private  String replyto;


public void setName(String name){
    this.name = name;
}


public void setTemplateSubject(String templateSubject){
    this.templateSubject = templateSubject;
}


@Column(name = "INVTEMPL_NAME")
public String getName(){
    return name;
}


@Column(name = "INVTEMPLMAIL")
public String getTemplateMail(){
    return templateMail;
}


@Column(name = "INVTEMPLREPLY")
public String getReplyto(){
    return replyto;
}


public void setTemplateText(Integer templateText){
    this.templateText = templateText;
}


@Column(name = "INVTEMPLSUBJ")
public String getTemplateSubject(){
    return templateSubject;
}


public void setTemplate2(String template2){
    this.template2 = template2;
}


@Id
@Column(name = "INVTEMPL_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setTemplate1(String template1){
    this.template1 = template1;
}


@ManyToOne
@JoinColumn(name = "OWNER", nullable = false)
public User getOwner(){
    return owner;
}


@Column(name = "INVTEMPL1")
@Lob
public String getTemplate1(){
    return template1;
}


@Column(name = "INVTEMPL2")
@Lob
public String getTemplate2(){
    return template2;
}


public void setOwner(User owner){
    this.owner = owner;
}


public void setReplyto(String replyto){
    this.replyto = replyto;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "INVTEMPLTEXT")
public Integer getTemplateText(){
    return templateText;
}


public void setTemplateMail(String templateMail){
    this.templateMail = templateMail;
}


}