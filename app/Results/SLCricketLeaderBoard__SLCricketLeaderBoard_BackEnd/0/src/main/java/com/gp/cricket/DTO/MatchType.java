package com.gp.cricket.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
public class MatchType {

 private  Integer matchTypeId;

 private  String matchType;

public MatchType() {
    super();
// TODO Auto-generated constructor stub
}public MatchType(Integer matchTypeId, String type) {
    super();
    this.matchTypeId = matchTypeId;
    this.matchType = type;
}
public Integer getMatchTypeId(){
    return matchTypeId;
}


public String getMatchType(){
    return matchType;
}


}