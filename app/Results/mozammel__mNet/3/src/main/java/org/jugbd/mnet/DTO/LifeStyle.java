package org.jugbd.mnet.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jugbd.mnet.domain.enums.Habit;
import javax.persistence;
import javax.validation.constraints.Size;
public class LifeStyle extends PersistentObjectimplements Auditable{

 private  Long id;

 private  Long version;

 private  String economicalStatus;

 private  Habit habit;

 private  String otherHabit;

 private  String comment;

 private  Register register;


public Long getVersion(){
    return version;
}


@Override
public Long getId(){
    return id;
}


public String getOtherHabit(){
    return otherHabit;
}


public Register getRegister(){
    return register;
}


public String getEconomicalStatus(){
    return economicalStatus;
}


public Habit getHabit(){
    return habit;
}


public String getComment(){
    return comment;
}


}