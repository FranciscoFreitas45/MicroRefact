package com.ec.survey.DTO;
 import java.util.Date;
import java.util.UUID;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import com.ec.survey.tools.ConversionTools;
public class Invitation {

 private  int id;

 private  int participationGroupId;

 private  int attendeeId;

 private  String uniqueId;

 private  Date invited;

 private  Date reminded;

 private  int answers;

 private  Boolean deactivated;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

public Invitation() {
}public Invitation(int participationGroupId, int attendeeId) {
    this.participationGroupId = participationGroupId;
    this.attendeeId = attendeeId;
    this.uniqueId = UUID.randomUUID().toString();
    this.invited = new Date();
}public Invitation(int participationGroupId, String token) {
    this.participationGroupId = participationGroupId;
    this.uniqueId = token;
    this.deactivated = false;
    this.invited = new Date();
}
@Column(name = "ATTENDEE_INVITED")
@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getInvited(){
    return invited;
}


@Id
@Column(name = "INVITATION_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "ATTENDEE_REMINDED")
@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getReminded(){
    return reminded;
}


@Column(name = "PARTICIPATIONGROUP_ID")
public Integer getParticipationGroupId(){
    return participationGroupId;
}


@Column(name = "INV_DEACTIVATED")
public Boolean getDeactivated(){
    return deactivated;
}


@Column(name = "UNIQUE_ID")
public String getUniqueId(){
    return uniqueId;
}


@Column(name = "ATTENDEE_ID")
public Integer getAttendeeId(){
    return attendeeId;
}


@Column(name = "ATTENDEE_ANSWERS")
public Integer getAnswers(){
    return answers;
}


@Transient
public String getNiceInvited(){
    return ConversionTools.getFullString(invited);
}


public void setDeactivated(Boolean deactivated){
    this.deactivated = deactivated;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDeactivated"))

.queryParam("deactivated",deactivated)
;
restTemplate.put(builder.toUriString(),null);
}


}