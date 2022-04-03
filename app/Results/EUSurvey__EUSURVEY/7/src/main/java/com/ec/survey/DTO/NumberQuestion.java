package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence;
public class NumberQuestion extends Question{

 public  String MAXLABEL;

 public  String MINLABEL;

 public  String UNIT;

 private  long serialVersionUID;

 private  int decimalPlaces;

 private  String unit;

 private  Double minD;

 private  Double maxD;

 private  String minLabel;

 private  String maxLabel;

 private  String display;

 private  String initialSliderPosition;

 private  Boolean displayGraduationScale;

 private  Double maxDistance;

 private  double min;

 private  double max;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";

public NumberQuestion() {
}public NumberQuestion(String title, String shortname, String uid) {
    super(title, shortname, uid);
}
@Column(name = "MINLABEL")
public String getMinLabel(){
    return minLabel != null ? minLabel : "";
}


@Transient
@Override
public String getCss(){
    String css = super.getCss();
    css += " number";
    if (minD != null) {
        css += " min" + minD;
    }
    if (maxD != null) {
        css += " max" + maxD;
    }
    // decimal places are always to be set
    css += " prec" + decimalPlaces;
    return css;
}


@Transient
public List<String> getAllPossibleAnswers(){
    List<String> answers = new ArrayList<>();
    if (!showStatisticsForNumberQuestion()) {
        return answers;
    }
    NumberFormat nf = DecimalFormat.getInstance();
    nf.setMaximumFractionDigits(0);
    double v = minD;
    while (v <= maxD) {
        answers.add(nf.format(v));
        v++;
    }
    return answers;
}


@Column(name = "MINNUMBER")
public Double getMin(){
    return minD;
}


@Transient
public String getMaxString(){
    if (maxD == null)
        return "";
    String s = String.valueOf(maxD);
    if (s.endsWith(".0"))
        s = s.replace(".0", "");
    return s;
}


@Column(name = "GRADSCALE")
public Boolean getDisplayGraduationScale(){
    return displayGraduationScale == null ? false : displayGraduationScale;
}


@Transient
public String getMinString(){
    if (minD == null)
        return "";
    String s = String.valueOf(minD);
    if (s.endsWith(".0"))
        s = s.replace(".0", "");
    return s;
}


@Transient
public String getAnswerWithPrefix(String answer){
    return getId() + answer;
}


@Column(name = "MAXNUMBER")
public Double getMax(){
    return maxD;
}


@Column(name = "DECIMALPLACES")
public Integer getDecimalPlaces(){
    return decimalPlaces;
}


@Column(name = "MAXDISTANCEDOUBLE")
public Double getMaxDistance(){
    return maxDistance;
}


@Column(name = "INITSLIDER")
public String getInitialSliderPosition(){
    return (initialSliderPosition != null && initialSliderPosition.length() > 0) ? initialSliderPosition : "Left";
}


@Column(name = "NUMBERUNIT")
public String getUnit(){
    return unit;
}


@Column(name = "MAXLABEL")
public String getMaxLabel(){
    return maxLabel != null ? maxLabel : "";
}


@Column(name = "DISPLAY")
public String getDisplay(){
    return (display != null && display.length() > 0) ? display : "Number";
}


@Transient
public boolean isSlider(){
    return getDisplay().equals("Slider");
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSlider"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public void setUnit(String unit){
    this.unit = unit;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUnit"))

.queryParam("unit",unit)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMinLabel(String minLabel){
    this.minLabel = minLabel != null ? minLabel : "";
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMinLabel"))

.queryParam("minLabel",minLabel)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMaxLabel(String maxLabel){
    this.maxLabel = maxLabel != null ? maxLabel : "";
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMaxLabel"))

.queryParam("maxLabel",maxLabel)
;
restTemplate.put(builder.toUriString(),null);
}


}