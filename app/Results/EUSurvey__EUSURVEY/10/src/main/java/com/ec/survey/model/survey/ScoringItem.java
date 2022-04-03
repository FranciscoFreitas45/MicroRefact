package com.ec.survey.model.survey;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.ec.survey.tools.Tools;
import javax.persistence;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "SCORINGITEMS")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ScoringItem {

 private  long serialVersionUID;

 private  Integer id;

 private  String uid;

 private  Integer sourceId;

 private  int type;

 private  int points;

 private  boolean correct;

 private  Double min;

 private  Double max;

 private  Date minDate;

 private  Date maxDate;

 private  String feedback;

 private  String value;

 private  String value2;

 private  int position;


public void setCorrect(boolean correct){
    this.correct = correct;
}


@Column(name = "MINDATE")
public Date getMinDate(){
    return minDate;
}


@Id
@Column(name = "ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setUniqueId(String uid){
    this.uid = uid;
}


@Lob
@Column(name = "FEEDBACK", length = 40000)
public String getFeedback(){
    return feedback;
}


public void setPosition(int position){
    this.position = position;
}


public boolean differsFrom(ScoringItem scoringItem){
    if (scoringItem == null)
        return true;
    if (correct != scoringItem.correct)
        return true;
    if (points != scoringItem.points)
        return true;
    if (!Tools.isEqual(value, scoringItem.value))
        return true;
    if (!Tools.isEqual(value2, scoringItem.value2))
        return true;
    if (type != scoringItem.type)
        return true;
    if (!Tools.isEqual(min, scoringItem.min))
        return true;
    if (!Tools.isEqual(max, scoringItem.max))
        return true;
    if (!Tools.isEqual(feedback, scoringItem.feedback))
        return true;
    return false;
}


@Column(name = "UID")
public String getUniqueId(){
    return uid;
}


public void setMin(Double min){
    this.min = min;
}


public void setValue2(String value2){
    this.value2 = value2;
}


@Column(name = "MIN")
public Double getMin(){
    return min;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "POINTS")
public int getPoints(){
    return points;
}


public ScoringItem copy(){
    ScoringItem copy = new ScoringItem();
    copy.setUniqueId(getUniqueId());
    copy.setSourceId(id);
    copy.setCorrect(correct);
    copy.setPoints(points);
    copy.setValue(value);
    copy.setValue2(value2);
    copy.setType(type);
    copy.setMin(min);
    copy.setMax(max);
    copy.setMinDate(minDate);
    copy.setMaxDate(maxDate);
    copy.setFeedback(feedback);
    copy.setPosition(position);
    return copy;
}


public void setPoints(int points){
    this.points = points;
}


public void setMax(Double max){
    this.max = max;
}


@Column(name = "MAX")
public Double getMax(){
    return max;
}


@Column(name = "MAXDATE")
public Date getMaxDate(){
    return maxDate;
}


@Column(name = "VALUE2")
public String getValue2(){
    return value2;
}


public void setType(Integer type){
    this.type = type != null ? type : 0;
}


public void setFeedback(String feedback){
    this.feedback = feedback;
}


public void setSourceId(Integer sourceId){
    this.sourceId = sourceId;
}


@Column(name = "VALUE")
public String getValue(){
    return value;
}


@Column(name = "POSITION")
public int getPosition(){
    return position;
}


@Column(name = "TYPE")
public Integer getType(){
    return type;
}


public void setMaxDate(Date maxDate){
    this.maxDate = maxDate;
}


public void setValue(String value){
    this.value = value;
}


@Column(name = "SOURCE_ID")
public Integer getSourceId(){
    return sourceId;
}


@Column(name = "CORRECT")
public boolean isCorrect(){
    return correct;
}


public void setMinDate(Date minDate){
    this.minDate = minDate;
}


}