package com.ec.survey.DTO;
 import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import com.ec.survey.model.administration.User;
public class Share {

 private  Integer id;

 private  String name;

 private  Boolean readonly;

 private  List<Attendee> attendees;

 private  User owner;

 private  User recipient;


@Column(name = "NAME")
public String getName(){
    return name;
}


@Column(name = "READONLY")
public Boolean getReadonly(){
    return readonly;
}


@Id
@Column(name = "SHARE_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@ManyToOne
@JoinColumn(name = "OWNER", nullable = false)
public User getOwner(){
    return owner;
}


public void setReadonly(Boolean readonly){
    this.readonly = readonly;
}


@ManyToOne
@JoinColumn(name = "RECIPIENT", nullable = false)
public User getRecipient(){
    return recipient;
}


@ManyToMany()
@Fetch(value = FetchMode.SELECT)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<Attendee> getAttendees(){
    return attendees;
}


public void setAttendees(List<Attendee> attendees){
    this.attendees = attendees;
}


}