package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
public class Section extends Element{

 public  String TABTITLE;

 private  long serialVersionUID;

 private  int level;

 private  String tabTitle;

public Section(String ptitle, String shortname, String uid) {
    setTitle(ptitle);
    setShortname(shortname);
    setUniqueId(uid);
}public Section() {
}
@Column(name = "HLEVEL")
public Integer getLevel(){
    return level;
}


@Column(name = "TABTITLE")
public String getTabTitle(){
    return tabTitle;
}


}