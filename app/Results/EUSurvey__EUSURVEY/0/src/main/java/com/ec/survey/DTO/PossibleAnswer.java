package com.ec.survey.DTO;
 import com.ec.survey.model.ECFProfile;
import com.ec.survey.tools.Tools;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PossibleAnswer extends Element{

 private  long serialVersionUID;

 private  int questionId;

 private  DependencyItem dependentElements;

 private  String savedDependentElementsString;

 private  ScoringItem scoring;

 private  ECFProfile ecfProfile;

 private  Integer ecfScore;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "id_score", nullable = true)
public ScoringItem getScoring(){
    return scoring;
}


@OneToOne
@JoinColumn(name = "ECF_PROFILE", nullable = true)
public ECFProfile getEcfProfile(){
    return ecfProfile;
}


public Integer getEcfScore(){
    return ecfScore;
}


@Transient
public String getDependentElementsUIDString(){
    List<String> list = new ArrayList<>();
    for (Element element : dependentElements.getDependentElements()) {
        list.add(element.getUniqueId());
    }
    Collections.sort(list);
    StringBuilder s = new StringBuilder();
    for (String uid : list) {
        s.append(uid).append(";");
    }
    return s.toString();
}


@Transient
public String getTitleForDisplayMode(Integer displayMode){
    if (displayMode != null) {
        switch(displayMode) {
            case 0:
                return this.getTitle();
            case 1:
                return this.getShortname();
            case 2:
                return this.getShortname() + " - " + this.getTitle();
            case 3:
                return this.getTitle() + " (" + this.getShortname() + ")";
            default:
                break;
        }
    }
    return this.getTitle();
}


@OneToOne(cascade = CascadeType.ALL)
public DependencyItem getDependentElements(){
    return dependentElements;
}


@Column(name = "QUESTION_ID")
public int getQuestionId(){
    return questionId;
}


@Transient
public String getDependentElementsString(){
    StringBuilder s = new StringBuilder(savedDependentElementsString);
    for (Element element : dependentElements.getDependentElements()) {
        s.append(element.getId()).append(";");
    }
    return s.toString();
}


public void setQuestionId(int questionId){
    this.questionId = questionId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQuestionId"))

.queryParam("questionId",questionId)
;
restTemplate.put(builder.toUriString(),null);
}


public void clearForJSON(){
    savedDependentElementsString = getDependentElementsString();
    dependentElements.getDependentElements().clear();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/clearForJSON"))

;
restTemplate.put(builder.toUriString(),null);
}


}