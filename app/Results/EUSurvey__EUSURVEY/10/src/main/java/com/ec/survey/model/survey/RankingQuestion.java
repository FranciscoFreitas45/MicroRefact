package com.ec.survey.model.survey;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.owasp.esapi.errors.ValidationException;
import com.ec.survey.tools.Tools;
@Entity
@DiscriminatorValue("RANKINGQUESTION")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RankingQuestion extends Question{

 private  long serialVersionUID;

 private  List<RankingItem> childElements;

 private  List<RankingItem> missingElements;

public RankingQuestion() {
}public RankingQuestion(String title, String shortname, String uid) {
    super(title, shortname, uid);
}
@Transient
public Map<String,RankingItem> getChildElementsByUniqueId(){
    Map<String, RankingItem> map = new HashMap<>();
    for (RankingItem thatElement : getAllChildElements()) {
        map.put(thatElement.getUniqueId(), thatElement);
    }
    return map;
}


@Transient
@Override
public String getCss(){
    String css = super.getCss();
    css += " ranking";
    return css.trim();
}


@OneToMany(targetEntity = RankingItem.class, cascade = CascadeType.ALL)
@Fetch(value = FetchMode.SELECT)
@OrderBy(value = "position asc")
@JoinColumn(nullable = true, foreignKey = @ForeignKey(javax.persistence.ConstraintMode.NO_CONSTRAINT))
@org.hibernate.annotations.ForeignKey(name = "none")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<RankingItem> getChildElements(){
    return childElements;
}


@Transient
public List<RankingItem> getMissingElements(){
    return missingElements;
}


@Transient
public RankingItem getChildElement(int id){
    for (RankingItem thatElement : getAllChildElements()) {
        if (thatElement.getId() == id) {
            return thatElement;
        }
    }
    return null;
}


@Override
public boolean differsFrom(Element element){
    if (basicDiffersFrom(element))
        return true;
    if (!(element instanceof RankingQuestion))
        return true;
    RankingQuestion other = (RankingQuestion) element;
    if (getChildElements().size() != other.getChildElements().size())
        return true;
    for (int i = 0; i < getChildElements().size(); i++) {
        if (!getChildElements().get(i).getTitle().equals(other.getChildElements().get(i).getTitle()))
            return true;
        if (!Tools.isEqual(getChildElements().get(i).getShortname(), other.getChildElements().get(i).getShortname()))
            return true;
    }
    return false;
}


@Transient
public List<String> getAnswerWithStrippedTitleNoEscape(String answerValue){
    Map<String, RankingItem> children = getChildElementsByUniqueId();
    List<String> rankingAnswerList = new ArrayList<>();
    for (String uniqueId : answerValue.split(";")) {
        RankingItem child = children.get(uniqueId);
        if (null != child) {
            rankingAnswerList.add(child.getStrippedTitleNoEscape());
        }
    }
    return rankingAnswerList;
}


@Transient
public List<String> getAnswer(String answerValue){
    Map<String, RankingItem> children = getChildElementsByUniqueId();
    List<String> rankingAnswerList = new ArrayList<>();
    for (String uniqueId : answerValue.split(";")) {
        RankingItem child = children.get(uniqueId);
        if (null != child) {
            rankingAnswerList.add(child.getTitle());
        }
    }
    return rankingAnswerList;
}


public RankingQuestion copy(String fileDir){
    RankingQuestion copy = new RankingQuestion();
    baseCopy(copy);
    for (RankingItem thatElement : getChildElements()) {
        RankingItem elementCopy = thatElement.copy(fileDir);
        copy.getChildElements().add(elementCopy);
    }
    return copy;
}


@Transient
public boolean isValidAnswer(String answerValues){
    String[] answerValuesStringArray = answerValues.split(";");
    List<RankingItem> children = getChildElements();
    if (answerValuesStringArray.length != children.size()) {
        return false;
    }
    for (String valueString : answerValuesStringArray) {
        boolean isIdFound = false;
        for (RankingItem child : children) {
            if (child.getUniqueId().equals(valueString)) {
                isIdFound = true;
                break;
            }
        }
        if (!isIdFound) {
            return false;
        }
    }
    return true;
}


@Transient
public List<RankingItem> getAllChildElements(){
    if (!missingElements.isEmpty()) {
        List<RankingItem> result = new ArrayList<>();
        for (RankingItem child : childElements) {
            if (!result.contains(child)) {
                result.add(child);
            }
        }
        for (RankingItem child : missingElements) {
            if (!result.contains(child)) {
                result.add(child);
            }
        }
        result.sort(Survey.newElementByPositionComparator());
        return result;
    } else {
        return childElements;
    }
}


public void setChildElements(List<RankingItem> childElements){
    this.childElements = childElements;
}


public void setMissingElements(List<RankingItem> missingElements){
    this.missingElements = missingElements;
}


}