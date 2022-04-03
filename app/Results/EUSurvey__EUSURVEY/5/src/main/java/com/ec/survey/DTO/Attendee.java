package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import com.ec.survey.tools.ConversionTools;
import javax.persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Attendee {

 private  Integer id;

 private  Integer regFormId;

 private  Integer ownerId;

 private  String owner;

 private  String name;

 private  String email;

 private  Date created;

 private  Date updated;

 private  boolean readonly;

 private  List<Attribute> attributes;

 private  Integer originalId;

 private  Boolean hidden;

 private  Date invited;

 private  Date reminded;

 private  int answers;


public void setName(String name){
    this.name = name;
}


@Column(name = "ATTENDEE_NAME")
public String getName(){
    return name;
}


@Transient
public String getAttributeValue(int id,String attributename){
    for (Attribute attribute : attributes) {
        if (attribute.getAttributeName().getId() == id && attribute.getValue() != null)
            return attribute.getValue();
    }
    for (Attribute attribute : attributes) {
        if (attribute.getAttributeName().getName().equals(attributename) && attribute.getValue() != null)
            return attribute.getValue();
    }
    return " ";
}


@Transient
public boolean getReadonly(){
    return readonly;
}


public void setRegFormId(Integer regFormId){
    this.regFormId = regFormId;
}


@Id
@Column(name = "ATTENDEE_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setAttributes(List<Attribute> attributes){
    this.attributes = attributes;
}


public void setOwnerId(Integer ownerId){
    this.ownerId = ownerId;
}


@Transient
public String getOwner(){
    return owner;
}


public void setOwner(String owner){
    this.owner = owner;
}


public void setReminded(Date reminded){
    this.reminded = reminded;
}


public void setAnswers(int answers){
    this.answers = answers;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "ATT_UPDATED", nullable = false)
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getUpdated(){
    return updated;
}


public void setOriginalId(Integer originalId){
    this.originalId = originalId;
}


public void setHidden(Boolean hidden){
    this.hidden = hidden;
}


@Column(name = "OWNER_ID")
public Integer getOwnerId(){
    return ownerId;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "REGFORM_ID")
public Integer getRegFormId(){
    return regFormId;
}


public Attendee copy(){
    Attendee copy = new Attendee();
    copy.created = created;
    copy.email = email;
    copy.invited = invited;
    copy.name = name;
    copy.owner = owner;
    copy.ownerId = ownerId;
    copy.readonly = readonly;
    copy.regFormId = regFormId;
    copy.reminded = reminded;
    copy.updated = updated;
    for (Attribute att : attributes) {
        Attribute attcopy = new Attribute();
        attcopy.setAttributeName(att.getAttributeName());
        attcopy.setValue(att.getValue());
        copy.attributes.add(attcopy);
    }
    return copy;
}


@OneToMany(cascade = { CascadeType.ALL })
@Fetch(value = FetchMode.SELECT)
@JoinTable(name = "ATTENDEE_ATTRIBUTES", joinColumns = { @JoinColumn(name = "ATTENDEE_ID") }, inverseJoinColumns = { @JoinColumn(name = "ATTRIBUTE_ID") })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<Attribute> getAttributes(){
    return attributes;
}


@Column(name = "ATTENDEE_HIDDEN")
public Boolean getHidden(){
    return hidden;
}


@Transient
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getInvited(){
    return invited;
}


@Transient
public void clear(){
    name = "";
    email = "";
    attributes.clear();
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "ATT_CREATED", nullable = false)
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getCreated(){
    return created;
}


@Transient
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getReminded(){
    return reminded;
}


public void setCreated(Date created){
    this.created = created;
}


public void setInvited(Date invited){
    this.invited = invited;
}


public void setReadonly(boolean b){
    readonly = b;
}


public void setEmail(String email){
    this.email = email;
}


@Transient
public String getDisplayName(){
    return name;
}


@Transient
public int getAnswers(){
    return answers;
}


@Column(name = "ATTENDEE_EMAIL")
public String getEmail(){
    return email != null ? email : "";
}


@Column(name = "ATTENDEE_ORIGID")
public Integer getOriginalId(){
    return originalId;
}


@Transient
public String getNiceReminded(){
    return ConversionTools.getFullString(reminded);
}


public void setUpdated(Date updated){
    this.updated = updated;
}


@Transient
public String getNiceInvited(){
    return ConversionTools.getFullString(invited);
}


}