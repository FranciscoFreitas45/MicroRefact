package com.ec.survey.DTO;
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


}