package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.ec.survey.tools.Tools;
import javax.persistence;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;
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


@Lob
@Column(name = "FEEDBACK", length = 40000)
public String getFeedback(){
    return feedback;
}


@Column(name = "UID")
public String getUniqueId(){
    return uid;
}


@Column(name = "MIN")
public Double getMin(){
    return min;
}


@Column(name = "POINTS")
public int getPoints(){
    return points;
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


@Column(name = "SOURCE_ID")
public Integer getSourceId(){
    return sourceId;
}


}