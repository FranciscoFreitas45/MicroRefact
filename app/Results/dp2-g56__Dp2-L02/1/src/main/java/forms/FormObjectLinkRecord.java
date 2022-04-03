package forms;
 import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import domain.Brotherhood;
import Interface.Brotherhood;
public class FormObjectLinkRecord {

 private  String title;

 private  String description;

 private  Integer id;

 private  Brotherhood brotherhood;


@NotNull
public Brotherhood getBrotherhood(){
    return this.brotherhood;
}


public void setBrotherhood(Brotherhood brotherhood){
    this.brotherhood = brotherhood;
}


@NotBlank
public String getTitle(){
    return this.title;
}


public void setTitle(String title){
    this.title = title;
}


public void setId(Integer id){
    this.id = id;
}


@NotNull
public Integer getId(){
    return this.id;
}


public void setDescription(String description){
    this.description = description;
}


@NotBlank
public String getDescription(){
    return this.description;
}


}