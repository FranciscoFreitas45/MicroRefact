package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence;
import java.text.Collator;
import java.util;
public class Matrix extends MatrixOrTable{

 private  long serialVersionUID;

 private  boolean isSingleChoice;

 private  Boolean isInterdependent;

 private  boolean useRadioButtons;

 private  Integer order;

 private  Integer minRows;

 private  Integer maxRows;

 private  boolean foreditor;

 private  List<DependencyItem> dependentElements;

 private  Map<Integer,String> mapDependentElements;

 private  String[] cachedDependentElementsStrings;

 private  List<Element> childElementsOrdered;

public Matrix(String ptitle, String shortname, String uid) {
    setTitle(ptitle);
    setShortname(shortname);
    setUniqueId(uid);
}public Matrix() {
}
@Transient
@Override
public String getCss(){
    String css = super.getCss();
    if (isInterdependent != null && isInterdependent && isSingleChoice) {
        css += " interdependent";
    }
    if (minRows != null && minRows > 0) {
        css += " minrows" + minRows;
    }
    if (maxRows != null && maxRows > 0) {
        css += " maxrows" + maxRows;
    }
    return css;
}


@Column(name = "MATRIXROWS")
public int getRows(){
    return rows;
}


@Transient
public Collection<Element> getQuestionsOrdered(){
    if (foreditor)
        return getQuestions();
    if (order != null && order == 1) {
        final Collator instance = Collator.getInstance();
        Map<String, Element> elements = new TreeMap<>(instance);
        for (Element element : getQuestions()) {
            if (elements.containsKey(element.getTitle())) {
                elements.put(element.getTitle() + elements.size(), element);
            } else {
                elements.put(element.getTitle(), element);
            }
        }
        return elements.values();
    } else if (order != null && order == 2) {
        List<Element> questionsOrdered = getQuestions();
        Collections.shuffle(questionsOrdered);
        return questionsOrdered;
    } else {
        return getQuestions();
    }
}


@Transient
public String getDependentElementUIDsStrings(){
    List<String> result = new ArrayList<>();
    for (DependencyItem dep : dependentElements) {
        for (Element element : dep.getDependentElements()) {
            result.add(element.getUniqueId() + dep.getPosition());
        }
    }
    result.sort(Comparator.comparing(String::toString));
    return String.join(",", result);
}


@Column(name = "MATRIXCOLUMNS")
public int getColumns(){
    return columns;
}


@Transient
public boolean getIsUseRadioButtons(){
    return useRadioButtons;
}


@OneToMany(targetEntity = DependencyItem.class, cascade = CascadeType.ALL, orphanRemoval = true)
@JoinTable(name = "MATRIX_DEP")
@Fetch(value = FetchMode.SELECT)
@javax.persistence.OrderColumn(name = "MATDEP_ID")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<DependencyItem> getDependentElements(){
    return dependentElements;
}


@Transient
public String getDependentElementsString(Integer position){
    if (mapDependentElements == null) {
        mapDependentElements = new HashMap<>();
        for (DependencyItem dep : dependentElements) {
            for (Element element : dep.getDependentElements()) {
                if (mapDependentElements.containsKey(dep.getPosition())) {
                    String old = mapDependentElements.get(dep.getPosition());
                    mapDependentElements.put(dep.getPosition(), old + element.getId() + ";");
                } else {
                    mapDependentElements.put(dep.getPosition(), element.getId() + ";");
                }
            }
        }
    }
    if (mapDependentElements.containsKey(position)) {
        return mapDependentElements.get(position);
    }
    return "";
}


@Transient
public String[] getDependentElementsStrings(){
    if (cachedDependentElementsStrings != null)
        return cachedDependentElementsStrings;
    int maxposition = -1;
    if (mapDependentElements == null) {
        mapDependentElements = new HashMap<>();
        for (DependencyItem dep : dependentElements) {
            for (Element element : dep.getDependentElements()) {
                if (mapDependentElements.containsKey(dep.getPosition())) {
                    String old = mapDependentElements.get(dep.getPosition());
                    mapDependentElements.put(dep.getPosition(), old + element.getId() + ";");
                } else {
                    mapDependentElements.put(dep.getPosition(), element.getId() + ";");
                }
                if (dep.getPosition() > maxposition) {
                    maxposition = dep.getPosition();
                }
            }
        }
    }
    String[] result = new String[maxposition + 1];
    for (int i = 0; i <= maxposition; i++) {
        result[i] = mapDependentElements.getOrDefault(i, "");
    }
    cachedDependentElementsStrings = result;
    return result;
}


@Column(name = "MIN_ROWS")
public Integer getMinRows(){
    return minRows;
}


@Transient
public List<Element> getChildElementsOrdered(){
    if (childElementsOrdered != null)
        return childElementsOrdered;
    childElementsOrdered = new ArrayList<>();
    if (order != null && order > 0) {
        childElementsOrdered.add(getChildElements().get(0));
        childElementsOrdered.addAll(getAnswers());
        childElementsOrdered.addAll(getQuestionsOrdered());
        return childElementsOrdered;
    } else {
        childElementsOrdered = getChildElements();
        return childElementsOrdered;
    }
}


public Element getChildByUniqueId(String uid){
    for (Element child : getChildElements()) {
        if (child.getUniqueId().equals(uid))
            return child;
    }
    return null;
}


@Column(name = "MATRIXSINGLE")
public boolean getIsSingleChoice(){
    return isSingleChoice;
}


@Transient
public boolean getAllQuestionsDependent(){
    for (Element element : getQuestions()) {
        if (!element.getIsDependent())
            return false;
    }
    return true;
}


@Column(name = "QUESTIONORDER")
public Integer getOrder(){
    return order;
}


@Column(name = "MAX_ROWS")
public Integer getMaxRows(){
    return maxRows;
}


@Column(name = "MATRIXINTER")
public Boolean getIsInterdependent(){
    return isInterdependent;
}


}