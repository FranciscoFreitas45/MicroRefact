package com.ec.survey.model;
 import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.io.Serializable;
import javax.persistence;
@Entity
@Table(name = "ECF_EXPECTED_SCORE")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ECFExpectedScore implements Serializable{

 private  long serialVersionUID;

@EmbeddedId
 private  ECFExpectedScoreToProfileEid id;

@Column(name = "SCORE")
 private  int score;

 protected  Logger logger;

private ECFExpectedScore() {
}public ECFExpectedScore(ECFExpectedScoreToProfileEid eid, Integer score) {
    this.setECFExpectedScoreToProfileEid(eid);
    this.setScore(score);
}
public void setECFExpectedScoreToProfileEid(ECFExpectedScoreToProfileEid eid){
    this.id = eid;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + score;
    return result;
}


public ECFExpectedScoreToProfileEid getECFExpectedScoreToProfileEid(){
    return id;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    ECFExpectedScore other = (ECFExpectedScore) obj;
    if (id == null) {
        if (other.id != null)
            return false;
    } else if (!id.equals(other.id))
        return false;
    if (score != other.score)
        return false;
    return true;
}


public ECFExpectedScore copy(ECFProfile alreadyCopiedProfile){
    ECFExpectedScoreToProfileEid eid = new ECFExpectedScoreToProfileEid();
    eid.setECFProfile(alreadyCopiedProfile);
    eid.setECFCompetency(this.getECFExpectedScoreToProfileEid().getECFCompetency());
    ECFExpectedScore theCopy = new ECFExpectedScore(eid, this.getScore());
    this.getECFExpectedScoreToProfileEid().getECFCompetency().replaceScore(this.getECFExpectedScoreToProfileEid().getECFProfile(), theCopy);
    return theCopy;
}


public Integer getScore(){
    return score;
}


public void setScore(Integer score){
    this.score = score >= 0 && score <= 5 ? score : 0;
}


}