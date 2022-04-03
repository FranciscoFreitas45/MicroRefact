package DTO;
 import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;
public class Enrolment extends DomainEntity{

 private  Date creationMoment;

 private  Position position;

 private  StatusEnrolment statusEnrolment;

 private  Date dropOutDate;

 private  Member member;

 private  Brotherhood brotherhood;


@Past
@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
public Date getDropOutDate(){
    return this.dropOutDate;
}


public void setMember(Member member){
    this.member = member;
}


@ManyToOne(optional = true)
public Brotherhood getBrotherhood(){
    return this.brotherhood;
}


@Valid
@ManyToOne(optional = true)
public Position getPosition(){
    return this.position;
}


public void setBrotherhood(Brotherhood brotherhood){
    this.brotherhood = brotherhood;
}


@Past
@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
public Date getCreationMoment(){
    return this.creationMoment;
}


public void setCreationMoment(Date creationMoment){
    this.creationMoment = creationMoment;
}


public void setDropOutDate(Date dropOutDate){
    this.dropOutDate = dropOutDate;
}


public void setStatusEnrolment(StatusEnrolment statusEnrolment){
    this.statusEnrolment = statusEnrolment;
}


@ManyToOne(optional = true)
public Member getMember(){
    return this.member;
}


public void setPosition(Position position){
    this.position = position;
}


@Valid
@Enumerated(EnumType.STRING)
public StatusEnrolment getStatusEnrolment(){
    return this.statusEnrolment;
}


}