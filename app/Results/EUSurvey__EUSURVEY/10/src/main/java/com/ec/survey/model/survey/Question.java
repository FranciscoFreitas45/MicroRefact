package com.ec.survey.model.survey;
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
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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

public Question() {
}public Question(String title, String shortname, String uid) {
    this.setTitle(title);
    this.setUniqueId(uid);
    this.setShortname(shortname);
}
public void setIsDelphiQuestion(Boolean delphiQuestion){
    this.delphiQuestion = delphiQuestion != null && delphiQuestion;
}


@ManyToOne
@JoinColumn(name = "ECF_COMPETENCY", nullable = true)
public ECFCompetency getEcfCompetency(){
    return ecfCompetency;
}


public void setShowExplanationBox(Boolean showExplanationBox){
    this.showExplanationBox = showExplanationBox == null ? true : showExplanationBox;
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


@Transient
@Override
public boolean basicDiffersFrom(Element element){
    if (getShortname() != null && !getShortname().equals(element.getShortname()))
        return true;
    if (getTitle() != null && !getTitle().equals(element.getTitle()))
        return true;
    if (getUseAndLogic() != null && !getUseAndLogic().equals(element.getUseAndLogic()))
        return true;
    if (element instanceof Question) {
        Question question = (Question) element;
        if (help != null && !help.equals(question.help)) {
            if (help.length() == 0 && question.help == null) {
            // this can happen
            } else {
                return true;
            }
        }
        if (scoring != question.scoring) {
            return true;
        }
        if (quizPoints != question.quizPoints) {
            return true;
        }
        if (scoring > 0 && (scoringItems != null || question.scoringItems != null)) {
            if (scoringItems != null && question.scoringItems == null) {
                return true;
            } else if (scoringItems == null && question.scoringItems != null) {
                return true;
            }
            if (scoringItems.size() != question.scoringItems.size()) {
                return true;
            }
            for (int i = 0; i < scoringItems.size(); i++) {
                if (scoringItems.get(i).differsFrom(question.getScoringItems().get(i))) {
                    return true;
                }
            }
        }
        if (!(Objects.equals(getOptional(), question.getOptional())))
            return true;
        if (!(Objects.equals(getReadonly(), question.getReadonly())))
            return true;
        if (!(Objects.equals(getAttributeName(), question.getAttributeName())))
            return true;
        if (!(Objects.equals(getIsDelphiQuestion(), question.getIsDelphiQuestion())))
            return true;
        if (!(Objects.equals(getShowExplanationBox(), question.getShowExplanationBox())))
            return true;
        if (!(Objects.equals(getDelphiChartType(), question.getDelphiChartType())))
            return true;
    } else {
        return true;
    }
    return false;
}


@Column(name = "QATT")
public Boolean getIsAttribute(){
    return attribute;
}


@Column(name = "QREADONLY")
public Boolean getReadonly(){
    return readonly;
}


public void setHelp(String help){
    this.help = help;
}


@Enumerated(EnumType.STRING)
@Column(name = "DELPHICHARTTYPE")
public DelphiChartType getDelphiChartType(){
    if (delphiQuestion && !(this instanceof FreeTextQuestion) && (delphiChartType == DelphiChartType.None)) {
        return getDefaultDelphiChartType();
    }
    return delphiChartType == null ? getDefaultDelphiChartType() : delphiChartType;
}


public void setIsAttribute(Boolean attribute){
    this.attribute = attribute != null && attribute;
}


@Column(name = "DELPHI")
public Boolean getIsDelphiQuestion(){
    return delphiQuestion;
}


public void setAttributeName(String attributeName){
    this.attributeName = attributeName;
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


public void setScoring(Integer scoring){
    this.scoring = scoring != null ? scoring : 0;
}


public void setQuizPoints(Integer quizPoints){
    this.quizPoints = quizPoints != null ? quizPoints : 1;
}


public void setScoringItems(List<ScoringItem> scoringItems){
    this.scoringItems = scoringItems;
}


@Column(name = "SCORING")
public Integer getScoring(){
    return scoring;
}


@Column(name = "DELPHIEXPLANATION")
public Boolean getShowExplanationBox(){
    return showExplanationBox;
}


public void setEcfCompetency(ECFCompetency ecfCompetency){
    this.ecfCompetency = ecfCompetency;
}


@Column(name = "QATTNAME")
public String getAttributeName(){
    if (attributeName == null || attributeName.length() == 0) {
        return getShortname();
    }
    return attributeName;
}


public void setReadonly(Boolean readonly){
    this.readonly = readonly != null && readonly;
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


public void setDelphiChartType(DelphiChartType delphiChartType){
    this.delphiChartType = delphiChartType == null ? getDefaultDelphiChartType() : delphiChartType;
}


public void baseCopy(Question copy){
    copy.setIsAttribute(getIsAttribute());
    copy.setAttributeName(getAttributeName());
    copy.setShortname(this.getShortname());
    copy.setUniqueId(getUniqueId());
    copy.setHelp(Tools.filterHTML(getHelp()));
    copy.setOptional(getOptional());
    copy.setReadonly(getReadonly());
    copy.setSourceId(getId());
    copy.setTitle(Tools.filterHTML(getTitle()));
    copy.setPosition(this.getPosition());
    copy.setIsUnique(getIsUnique());
    copy.setQuizPoints(quizPoints);
    copy.setScoring(scoring);
    copy.setLocked(getLocked());
    copy.setSubType(getSubType());
    copy.setDisplayMode(getDisplayMode());
    copy.setIsDelphiQuestion(getIsDelphiQuestion());
    copy.setUseAndLogic(getUseAndLogic());
    copy.setShowExplanationBox(getShowExplanationBox());
    copy.setDelphiChartType(getDelphiChartType());
    if (ecfCompetency != null) {
        copy.setEcfCompetency(this.getEcfCompetency());
    }
    if (scoringItems != null) {
        copy.setScoringItems(new ArrayList<>());
        for (ScoringItem item : scoringItems) {
            copy.getScoringItems().add(item.copy());
        }
    }
}


public void setIsUnique(Boolean isUnique){
    this.isUnique = isUnique != null && isUnique;
}


@Column(name = "ISUNIQUE")
public Boolean getIsUnique(){
    return isUnique;
}


public void setOptional(Boolean optional){
    this.optional = optional != null && optional;
}


}