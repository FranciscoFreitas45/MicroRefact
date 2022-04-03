package forms;
 import java.util.Date;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
public class FormObjectParadeFloat {

 private  String titleParade;

 private  String descriptionParade;

 private  Date moment;

 private  Boolean isDraftMode;

 private  int rowNumber;

 private  int columnNumber;

 private  String title;

 private  String description;


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


public void setTitle(String title){
    this.title = title;
}


public void setDescription(String description){
    this.description = description;
}


@NotBlank
public String getDescription(){
    return this.description;
}


@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
@NotNull
@Future
public Date getMoment(){
    return this.moment;
}


@NotBlank
public String getTitle(){
    return this.title;
}


public void setIsDraftMode(boolean isDraftMode){
    this.isDraftMode = isDraftMode;
}


public void setTitleParade(String titleParade){
    this.titleParade = titleParade;
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