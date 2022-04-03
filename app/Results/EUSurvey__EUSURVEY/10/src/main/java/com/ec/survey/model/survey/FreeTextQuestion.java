package com.ec.survey.model.survey;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence;
@Entity
@DiscriminatorValue("FREETEXT")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FreeTextQuestion extends Question{

 private  long serialVersionUID;

 private  int minCharacters;

 private  int maxCharacters;

 private  int numRows;

 private  String answer;

 private  boolean isPassword;

 private  boolean isComparable;

public FreeTextQuestion() {
}public FreeTextQuestion(String title, String shortname, String uid) {
    super(title, shortname, uid);
}
@Column(name = "NUMROWS")
public Integer getNumRows(){
    return numRows;
}


public void setIsPassword(Boolean isPassword){
    this.isPassword = isPassword;
}


@Column(name = "MAXCHARS")
public Integer getMaxCharacters(){
    return maxCharacters;
}


public void setNumRows(Integer num){
    this.numRows = num;
}


@Transient
@Override
public String getCss(){
    String css = super.getCss();
    css += " freetext";
    if (minCharacters > 0) {
        css += " min" + minCharacters;
    }
    if (maxCharacters > 0) {
        css += " max" + maxCharacters;
    } else {
        css += " max5000";
    }
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
    if (!(element instanceof FreeTextQuestion))
        return true;
    FreeTextQuestion text = (FreeTextQuestion) element;
    if (getMinCharacters() != null && !getMinCharacters().equals(text.getMinCharacters()))
        return true;
    if (getMaxCharacters() != null && !getMaxCharacters().equals(text.getMaxCharacters()))
        return true;
    if (getNumRows() != null && !getNumRows().equals(text.getNumRows()))
        return true;
    if (getIsPassword() != null && !getIsPassword().equals(text.getIsPassword()))
        return true;
    if (getIsUnique() != null && !getIsUnique().equals(text.getIsUnique()))
        return true;
    return (getIsComparable() != null && !getIsComparable().equals(text.getIsComparable()));
}


public void setStringAnswer(String answer){
    this.answer = answer;
}


public void setMinCharacters(Integer min){
    this.minCharacters = min;
}


@Transient
@Override
public DelphiChartDataType getDelphiChartDataType(){
    return DelphiChartDataType.Textual;
}


@Column(name = "ISPASSWORD")
public Boolean getIsPassword(){
    return isPassword;
}


public FreeTextQuestion copy(String fileDir){
    FreeTextQuestion copy = new FreeTextQuestion();
    baseCopy(copy);
    copy.setMaxCharacters(maxCharacters);
    copy.setMinCharacters(minCharacters);
    copy.setNumRows(numRows);
    copy.answer = answer;
    copy.setIsPassword(this.getIsPassword());
    copy.setIsComparable(isComparable);
    return copy;
}


public void setMaxCharacters(Integer max){
    this.maxCharacters = max;
}


@Transient
public String getStringAnswer(){
    return answer;
}


@Column(name = "MINCHARS")
public Integer getMinCharacters(){
    return minCharacters;
}


@Column(name = "ISCOMPARABLE")
public Boolean getIsComparable(){
    return isComparable;
}


}