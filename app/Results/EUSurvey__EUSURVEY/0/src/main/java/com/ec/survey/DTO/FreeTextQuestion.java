package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence;
public class FreeTextQuestion extends Question{

 private  long serialVersionUID;

 private  int minCharacters;

 private  int maxCharacters;

 private  int numRows;

 private  String answer;

 private  boolean isPassword;

 private  boolean isComparable;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";

public FreeTextQuestion() {
}public FreeTextQuestion(String title, String shortname, String uid) {
    super(title, shortname, uid);
}
@Column(name = "NUMROWS")
public Integer getNumRows(){
    return numRows;
}


@Column(name = "MAXCHARS")
public Integer getMaxCharacters(){
    return maxCharacters;
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


@Transient
@Override
public DelphiChartDataType getDelphiChartDataType(){
    return DelphiChartDataType.Textual;
}


@Column(name = "ISPASSWORD")
public Boolean getIsPassword(){
    return isPassword;
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


public void setMinCharacters(Integer min){
    this.minCharacters = min;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMinCharacters"))

.queryParam("min",min)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMaxCharacters(Integer max){
    this.maxCharacters = max;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMaxCharacters"))

.queryParam("max",max)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNumRows(Integer num){
    this.numRows = num;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNumRows"))

.queryParam("num",num)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsPassword(Boolean isPassword){
    this.isPassword = isPassword;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsPassword"))

.queryParam("isPassword",isPassword)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsComparable(Boolean isComparable){
    this.isComparable = isComparable;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsComparable"))

.queryParam("isComparable",isComparable)
;
restTemplate.put(builder.toUriString(),null);
}


}