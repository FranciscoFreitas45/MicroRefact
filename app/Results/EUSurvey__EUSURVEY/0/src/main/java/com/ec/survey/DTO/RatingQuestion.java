package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
public class RatingQuestion extends Question{

 private  long serialVersionUID;

 private  List<Element> missingQuestions;

 private  int numIcons;

 private  int iconType;

 protected  List<Element> childElements;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";

public RatingQuestion() {
}public RatingQuestion(String title, String shortname, String uid) {
    super(title, shortname, uid);
}
@Column(name = "NUMICONS")
public int getNumIcons(){
    return numIcons;
}


@Transient
public List<Element> getQuestions(){
    List<Element> result = new ArrayList<>();
    result.addAll(childElements);
    result.addAll(missingQuestions);
    return result;
}


@Column(name = "ICONTYPE")
public int getIconType(){
    return iconType;
}


@SuppressWarnings("deprecation")
@OneToMany(targetEntity = Element.class, cascade = CascadeType.ALL)
@Fetch(value = FetchMode.SELECT)
@OrderBy(value = "position asc")
@JoinColumn(nullable = true, foreignKey = @ForeignKey(javax.persistence.ConstraintMode.NO_CONSTRAINT))
@org.hibernate.annotations.ForeignKey(name = "none")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<Element> getChildElements(){
    return childElements;
}


@Transient
public List<Element> getMissingQuestions(){
    return missingQuestions;
}


public boolean containsChild(int id){
    for (Element element : childElements) {
        if (element.getId().equals(id)) {
            return true;
        }
    }
    return false;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/containsChild"))

.queryParam("id",id)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}