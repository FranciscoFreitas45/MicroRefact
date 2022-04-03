package org.jugbd.mnet.domain;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jugbd.mnet.domain.enums.Habit;
import javax.persistence;
import javax.validation.constraints.Size;
import org.jugbd.mnet.Request.RegisterRequest;
import org.jugbd.mnet.Request.Impl.RegisterRequestImpl;
import org.jugbd.mnet.DTO.Register;
@Entity
public class LifeStyle extends PersistentObjectimplements Auditable{

@Id
@GeneratedValue
 private  Long id;

@Version
 private  Long version;

@Size(max = 50)
 private  String economicalStatus;

@Column(length = 20)
@Enumerated(EnumType.STRING)
 private  Habit habit;

 private  String otherHabit;

 private  String comment;

@Transient
 private  Register register;

@Column(name = "id")
 private Long id;

@Transient
 private RegisterRequest registerrequest = new RegisterRequestImpl();;


public Long getVersion(){
    return version;
}


public void setHabit(Habit habit){
    this.habit = habit;
}


public void setEconomicalStatus(String economicalStatus){
    this.economicalStatus = economicalStatus;
}


public void setVersion(Long version){
    this.version = version;
}


@Override
public Long getId(){
    return id;
}


public void setRegister(Register register){
 registerrequest.setRegister(register,this.id);
}



public String getOtherHabit(){
    return otherHabit;
}


public Register getRegister(){
  this.register = registerrequest.getRegister(this.id);
return this.register;
}


public String getEconomicalStatus(){
    return economicalStatus;
}


public void setId(Long id){
    this.id = id;
}


public void setComment(String comment){
    this.comment = comment;
}


public Habit getHabit(){
    return habit;
}


public String getComment(){
    return comment;
}


public void setOtherHabit(String otherHabit){
    this.otherHabit = otherHabit;
}


}