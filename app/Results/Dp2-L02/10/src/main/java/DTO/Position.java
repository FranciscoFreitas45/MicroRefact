package DTO;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotBlank;
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


public void setTitleEnglish(String titleEnglish){
    this.titleEnglish = titleEnglish;
}


}