package com.ec.survey.model.survey;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
@Entity
@DiscriminatorValue("EMAIL")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmailQuestion extends Question{

 private  long serialVersionUID;

 private  String answer;

public EmailQuestion() {
}public EmailQuestion(String title, String shortname, String uid) {
    super(title, shortname, uid);
}
public void setStringAnswer(String answer){
    this.answer = answer;
}


@Transient
@Override
public String getCss(){
    String css = super.getCss();
    css += " email";
    return css;
}


public EmailQuestion copy(String fileDir){
    EmailQuestion copy = new EmailQuestion();
    baseCopy(copy);
    copy.answer = answer;
    return copy;
}


@Transient
public String getStringAnswer(){
    return answer;
}


@Override
public boolean differsFrom(Element element){
    return basicDiffersFrom(element);
}


}