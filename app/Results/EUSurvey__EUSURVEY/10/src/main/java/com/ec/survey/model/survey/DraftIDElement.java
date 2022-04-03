package com.ec.survey.model.survey;
 import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@DiscriminatorValue("DRAFTID")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DraftIDElement extends Element{

 private  long serialVersionUID;

public DraftIDElement(String ptitle, String shortname) {
    setTitle(ptitle);
    setShortname(shortname);
}public DraftIDElement() {
    this.setUniqueId("");
}
@Transient
public boolean getOptional(){
    return true;
}


@Transient
public String getCss(){
    return "";
}


public DraftIDElement copy(String fileDir){
    DraftIDElement copy = new DraftIDElement();
    copy.setUniqueId(getUniqueId());
    copy.setShortname(this.getShortname());
    copy.setSourceId(this.getId());
    copy.setTitle(this.getTitle());
    copy.setPosition(this.getPosition());
    return copy;
}


@Override
public boolean differsFrom(Element element){
    return false;
}


}