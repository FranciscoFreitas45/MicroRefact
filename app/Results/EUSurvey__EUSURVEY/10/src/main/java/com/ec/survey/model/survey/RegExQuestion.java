package com.ec.survey.model.survey;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence;
@Entity
@DiscriminatorValue("REGEX")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RegExQuestion extends Question{

 private  long serialVersionUID;

 private  int numRows;

 private  String answer;

 private  String regex;

 private  boolean isPassword;

 private  boolean isComparable;

public RegExQuestion() {
}public RegExQuestion(String title, String shortname, String uid) {
    super(title, shortname, uid);
}
@Column(name = "NUMROWS")
public Integer getNumRows(){
    return numRows;
}


public void setIsPassword(Boolean isPassword){
    this.isPassword = isPassword;
}


@Column(name = "REG")
public String getRegex(){
    return regex;
}


@Column(name = "MAXCHARS")
public Integer getMaxCharacters(){
    return 0;
}


public void setRegex(String regex){
    this.regex = regex;
}


public void setNumRows(Integer num){
    this.numRows = num;
}


@Transient
@Override
public String getCss(){
    String css = super.getCss();
    css += " freetext regex";
    if (isComparable) {
        css += " comparable";
    }
    return css;
}


public void setIsComparable(Boolean isComparable){
    this.isComparable = isComparable;
}


@Override
public boolean differsFrom(Element element){
    if (basicDiffersFrom(element))
        return true;
    if (!(element instanceof RegExQuestion))
        return true;
    RegExQuestion regex = (RegExQuestion) element;
    if (getNumRows() != null && !getNumRows().equals(regex.getNumRows()))
        return true;
    if (this.regex != null && !this.regex.equals(regex.regex))
        return true;
    if (getIsPassword() != null && !getIsPassword().equals(regex.getIsPassword()))
        return true;
    if (getIsUnique() != null && !getIsUnique().equals(regex.getIsUnique()))
        return true;
    if (getIsComparable() != null && !getIsComparable().equals(regex.getIsComparable()))
        return true;
    return false;
}


public void setStringAnswer(String answer){
    this.answer = answer;
}


public void setMinCharacters(Integer min){
}


@Column(name = "ISPASSWORD")
public Boolean getIsPassword(){
    return isPassword;
}


public RegExQuestion copy(String fileDir){
    RegExQuestion copy = new RegExQuestion();
    baseCopy(copy);
    copy.setNumRows(numRows);
    copy.answer = answer;
    copy.setIsPassword(this.getIsPassword());
    copy.regex = regex;
    copy.setIsComparable(isComparable);
    return copy;
}


public void setMaxCharacters(Integer max){
}


@Transient
public String getStringAnswer(){
    return answer;
}


@Column(name = "MINCHARS")
public Integer getMinCharacters(){
    return 0;
}


@Column(name = "ISCOMPARABLE")
public Boolean getIsComparable(){
    return isComparable;
}


}