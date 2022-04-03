package domain;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotBlank;
@Entity
@Access(AccessType.PROPERTY)
public class Position extends DomainEntity{

 private  String titleEnglish;

 private  String titleSpanish;


@NotBlank
public String getTitleEnglish(){
    return this.titleEnglish;
}


@NotBlank
public String getTitleSpanish(){
    return this.titleSpanish;
}


public void setTitleSpanish(String titleSpanish){
    this.titleSpanish = titleSpanish;
}


public void setTitleEnglish(String titleEnglish){
    this.titleEnglish = titleEnglish;
}


}