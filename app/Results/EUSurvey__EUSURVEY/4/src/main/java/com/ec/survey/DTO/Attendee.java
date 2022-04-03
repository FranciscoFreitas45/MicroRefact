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


@Id
@Column(name = "ATTENDEE_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Transient
public String getOwner(){
    return owner;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "ATT_UPDATED", nullable = false)
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getUpdated(){
    return updated;
}


@Column(name = "OWNER_ID")
public Integer getOwnerId(){
    return ownerId;
}


@Column(name = "REGFORM_ID")
public Integer getRegFormId(){
    return regFormId;
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


@Transient
public String getNiceInvited(){
    return ConversionTools.getFullString(invited);
}


}