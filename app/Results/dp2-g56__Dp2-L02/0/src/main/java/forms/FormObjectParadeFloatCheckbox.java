package forms;
 import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
public class FormObjectParadeFloatCheckbox {

 private  String titleParade;

 private  String descriptionParade;

 private  Date moment;

 private  Boolean isDraftMode;

 private  int rowNumber;

 private  int columnNumber;

 private  int id;

 private  List<Integer> floats;


@NotNull
@Min(1)
@Digits(integer = 6, fraction = 0)
public int getRowNumber(){
    return this.rowNumber;
}


public void setRowNumber(int rowNumber){
    this.rowNumber = rowNumber;
}


@NotNull
public Boolean getIsDraftMode(){
    return this.isDraftMode;
}


public void setColumnNumber(int columnNumber){
    this.columnNumber = columnNumber;
}


@ElementCollection(targetClass = Integer.class)
@NotEmpty
public List<Integer> getFloats(){
    return this.floats;
}


@NotBlank
public String getDescriptionParade(){
    return this.descriptionParade;
}


@NotNull
@Min(1)
@Digits(integer = 6, fraction = 0)
public int getColumnNumber(){
    return this.columnNumber;
}


@NotNull
public int getId(){
    return this.id;
}


@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
@NotNull
@Future
public Date getMoment(){
    return this.moment;
}


public void setIsDraftMode(boolean isDraftMode){
    this.isDraftMode = isDraftMode;
}


public void setTitleParade(String titleParade){
    this.titleParade = titleParade;
}


public void setId(int id){
    this.id = id;
}


public void setFloats(List<Integer> floats){
    this.floats = floats;
}


public void setMoment(Date moment){
    this.moment = moment;
}


@NotBlank
public String getTitleParade(){
    return this.titleParade;
}


public void setDescriptionParade(String descriptionParade){
    this.descriptionParade = descriptionParade;
}


}