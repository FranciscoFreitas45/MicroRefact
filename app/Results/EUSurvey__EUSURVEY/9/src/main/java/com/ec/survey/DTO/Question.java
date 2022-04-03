package com.ec.survey.DTO;
 import com.ec.survey.model.ECFCompetency;
import com.ec.survey.tools.Tools;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Question extends Element{

 public  String FEEDBACK;

 public  String FIRSTCELL;

 private  long serialVersionUID;

 private  String help;

 private  boolean optional;

 private  boolean readonly;

 private  boolean attribute;

 private  String attributeName;

 private  boolean isUnique;

 private  int scoring;

 private  int quizPoints;

 private  List<ScoringItem> scoringItems;

 private  boolean delphiQuestion;

 private  ECFCompetency ecfCompetency;

 private  DelphiChartType delphiChartType;

 private  boolean showExplanationBox;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";

public Question() {
}public Question(String title, String shortname, String uid) {
    this.setTitle(title);
    this.setUniqueId(uid);
    this.setShortname(shortname);
}
@ManyToOne
@JoinColumn(name = "ECF_COMPETENCY", nullable = true)
public ECFCompetency getEcfCompetency(){
    return ecfCompetency;
}


@Transient
public String getCss(){
    String css = "";
    if (!optional)
        css = "required";
    if (isUnique) {
        css += " unique";
    }
    return css;
}


@Column(name = "QATT")
public Boolean getIsAttribute(){
    return attribute;
}


@Column(name = "QREADONLY")
public Boolean getReadonly(){
    return readonly;
}


@Enumerated(EnumType.STRING)
@Column(name = "DELPHICHARTTYPE")
public DelphiChartType getDelphiChartType(){
    if (delphiQuestion && !(this instanceof FreeTextQuestion) && (delphiChartType == DelphiChartType.None)) {
        return getDefaultDelphiChartType();
    }
    return delphiChartType == null ? getDefaultDelphiChartType() : delphiChartType;
}


@Column(name = "DELPHI")
public Boolean getIsDelphiQuestion(){
    return delphiQuestion;
}


@Transient
public DelphiChartDataType getDelphiChartDataType(){
    return DelphiChartDataType.Numerical;
}


@Transient
public DelphiChartType getDefaultDelphiChartType(){
    return this.delphiQuestion ? DelphiChartType.Bar : DelphiChartType.None;
}


@Column(name = "POINTS")
public Integer getQuizPoints(){
    return quizPoints;
}


@Column(name = "SCORING")
public Integer getScoring(){
    return scoring;
}


@Column(name = "DELPHIEXPLANATION")
public Boolean getShowExplanationBox(){
    return showExplanationBox;
}


@Column(name = "QATTNAME")
public String getAttributeName(){
    if (attributeName == null || attributeName.length() == 0) {
        return getShortname();
    }
    return attributeName;
}


@OneToMany(targetEntity = ScoringItem.class, cascade = CascadeType.ALL)
@Fetch(value = FetchMode.SELECT)
@OrderBy(value = "position asc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<ScoringItem> getScoringItems(){
    return scoringItems;
}


@Column(name = "QOPTIONAL")
public Boolean getOptional(){
    return optional;
}


@Column(name = "QHELP")
@Lob
public String getHelp(){
    return help;
}


@Column(name = "ISUNIQUE")
public Boolean getIsUnique(){
    return isUnique;
}


public void setEcfCompetency(ECFCompetency ecfCompetency){
    this.ecfCompetency = ecfCompetency;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEcfCompetency"))

.queryParam("ecfCompetency",ecfCompetency)
;
restTemplate.put(builder.toUriString(),null);
}


}