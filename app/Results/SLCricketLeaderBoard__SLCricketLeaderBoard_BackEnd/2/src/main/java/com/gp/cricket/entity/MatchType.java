package com.gp.cricket.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "match_type")
public class MatchType {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "match_type_id")
 private  Integer matchTypeId;

@Column(name = "match_type")
 private  String matchType;

public MatchType() {
    super();
// TODO Auto-generated constructor stub
}public MatchType(Integer matchTypeId, String type) {
    super();
    this.matchTypeId = matchTypeId;
    this.matchType = type;
}
public void setMatchType(String matchType){
    this.matchType = matchType;
}


public Integer getMatchTypeId(){
    return matchTypeId;
}


@Override
public String toString(){
    return "MatchType [matchTypeId=" + matchTypeId + ", matchType=" + matchType + "]";
}


public void setMatchTypeId(Integer matchTypeId){
    this.matchTypeId = matchTypeId;
}


public String getMatchType(){
    return matchType;
}


}