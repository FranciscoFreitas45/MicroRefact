package org.jugbd.mnet.domain;
 import org.jugbd.mnet.domain.enums.Condition;
import javax.persistence;
import java.io.Serializable;
@Entity
public class DiagnosisData implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

 private  Long percentage;

@Column(length = 12)
@Enumerated(EnumType.STRING)
 private  Condition conditions;

 private  Long daysOld;


public Long getVersion(){
    return version;
}


public void setConditions(Condition conditions){
    this.conditions = conditions;
}


public void setVersion(Long version){
    this.version = version;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public Condition getConditions(){
    return conditions;
}


public Long getDaysOld(){
    return daysOld;
}


public void setDaysOld(Long daysOld){
    this.daysOld = daysOld;
}


public Long getPercentage(){
    return percentage;
}


public void setPercentage(Long percentage){
    this.percentage = percentage;
}


}