package com.ec.survey.model.survey;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import com.ec.survey.tools.Tools;
import java.util.Objects;
@Entity
@DiscriminatorValue("SINGLECHOICE")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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


public void setUseLikert(Boolean useLikert){
    this.useLikert = useLikert != null ? useLikert : false;
}


@Column(name = "NUMCOLUMNS")
public int getNumColumns(){
    return numColumns;
}


public void setNumColumns(int numColumns){
    this.numColumns = numColumns;
}


public void setMaxDistance(Integer maxDistance){
    this.maxDistance = maxDistance != null ? maxDistance : -1;
}


@Column(name = "MAXDISTANCE")
public Integer getMaxDistance(){
    return maxDistance;
}


public void setUseRadioButtons(boolean useRadioButtons){
    this.useRadioButtons = useRadioButtons;
}


public SingleChoiceQuestion copy(String fileDir){
    SingleChoiceQuestion copy = new SingleChoiceQuestion();
    baseCopy(copy);
    copy.numColumns = numColumns;
    copy.useRadioButtons = useRadioButtons;
    copy.useLikert = useLikert;
    copy.setOrder(getOrder());
    copy.maxDistance = maxDistance;
    for (PossibleAnswer possibleAnswer : getPossibleAnswers()) {
        PossibleAnswer answerCopy = possibleAnswer.copy(fileDir);
        copy.getPossibleAnswers().add(answerCopy);
    }
    return copy;
}


@Column(name = "RADIO")
public boolean getUseRadioButtons(){
    return useRadioButtons;
}


@Override
public boolean differsFrom(Element element){
    if (basicDiffersFrom(element))
        return true;
    if (!(element instanceof SingleChoiceQuestion))
        return true;
    SingleChoiceQuestion single = (SingleChoiceQuestion) element;
    if (useRadioButtons != single.useRadioButtons)
        return true;
    if (useLikert != single.useLikert)
        return true;
    if (numColumns != single.numColumns)
        return true;
    if (getPossibleAnswers().size() != single.getPossibleAnswers().size())
        return true;
    if (maxDistance != single.maxDistance)
        return true;
    if (!Objects.equals(getOrder(), single.getOrder()))
        return true;
    for (int i = 0; i < getPossibleAnswers().size(); i++) {
        if (!getPossibleAnswers().get(i).getTitle().equals(single.getPossibleAnswers().get(i).getTitle()))
            return true;
        if (!getPossibleAnswers().get(i).getDependentElementsUIDString().equals(single.getPossibleAnswers().get(i).getDependentElementsUIDString()))
            return true;
        if (!Tools.isEqual(getPossibleAnswers().get(i).getShortname(), single.getPossibleAnswers().get(i).getShortname()))
            return true;
        if (getPossibleAnswers().get(i).getScoring() != null && getPossibleAnswers().get(i).getScoring().differsFrom(single.getPossibleAnswers().get(i).getScoring())) {
            return true;
        }
    }
    return false;
}


}