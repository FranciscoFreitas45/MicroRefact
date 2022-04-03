package com.ec.survey.model.survey;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("SECTION")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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


public void setTabTitle(String tabTitle){
    this.tabTitle = tabTitle;
}


public Section copy(String fileDir){
    Section copy = new Section();
    copy.setUniqueId(getUniqueId());
    copy.setShortname(this.getShortname());
    copy.setUseAndLogic(this.getUseAndLogic());
    copy.tabTitle = tabTitle;
    copy.setLevel(level);
    copy.setSourceId(this.getId());
    copy.setTitle(this.getTitle());
    copy.setPosition(this.getPosition());
    return copy;
}


@Override
public boolean differsFrom(Element element){
    if (basicDiffersFrom(element))
        return true;
    if (!(element instanceof Section))
        return true;
    Section section = (Section) element;
    return getLevel() != null && !getLevel().equals(section.getLevel());
}


public void setLevel(Integer level){
    this.level = level;
}


}