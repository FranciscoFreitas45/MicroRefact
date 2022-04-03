package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import com.ec.survey.tools.Tools;
import java.util.Objects;
public class SingleChoiceQuestion extends ChoiceQuestion{

 private  long serialVersionUID;

 private  boolean useRadioButtons;

 private  Boolean useLikert;

 private  int numColumns;

 private  Integer maxDistance;

public SingleChoiceQuestion() {
}public SingleChoiceQuestion(String title, String shortname, String uid) {
    super(title, shortname, uid);
}
@Column(name = "LIKERT")
public Boolean getUseLikert(){
    return useLikert != null && useLikert && getIsDelphiQuestion();
}


@Column(name = "NUMCOLUMNS")
public int getNumColumns(){
    return numColumns;
}


@Column(name = "MAXDISTANCE")
public Integer getMaxDistance(){
    return maxDistance;
}


@Column(name = "RADIO")
public boolean getUseRadioButtons(){
    return useRadioButtons;
}


}