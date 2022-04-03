package com.ec.survey.model;
 import java.io.Serializable;
import java.util.Objects;
import javax.persistence;
@Embeddable
public class ECFExpectedScoreToProfileEid implements Serializable{

 private  long serialVersionUID;

@ManyToOne
@JoinColumn(name = "COMPETENCY", referencedColumnName = "COMPETENCY_ID")
 protected  ECFCompetency competency;

@ManyToOne
@JoinColumn(name = "PROFILE", referencedColumnName = "PROFILE_ID")
 protected  ECFProfile profile;


public void setECFProfile(ECFProfile profile){
    this.profile = profile;
}


@Override
public int hashCode(){
    return Objects.hash(getECFCompetency(), getECFProfile());
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof ECFExpectedScoreToProfileEid))
        return false;
    ECFExpectedScoreToProfileEid that = (ECFExpectedScoreToProfileEid) o;
    return Objects.equals(getECFCompetency(), that.getECFCompetency()) && Objects.equals(getECFProfile(), that.getECFProfile());
}


public void setECFCompetency(ECFCompetency competency){
    this.competency = competency;
}


public ECFProfile getECFProfile(){
    return profile;
}


public ECFCompetency getECFCompetency(){
    return competency;
}


}