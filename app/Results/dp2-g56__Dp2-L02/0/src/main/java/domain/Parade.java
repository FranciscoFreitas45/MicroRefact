package domain;
 import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "isDraftMode, moment"), @Index(columnList = "paradeStatus, isDraftMode"), @Index(columnList = "title, description"), @Index(columnList = "moment"), @Index(columnList = "isDraftMode"), @Index(columnList = "paradeStatus") })
public class Parade extends DomainEntity{

 private  String title;

 private  String description;

 private  Date moment;

 private  String ticker;

 private  Boolean isDraftMode;

 private  int rowNumber;

 private  int columnNumber;

 private  ParadeStatus paradeStatus;

 private  String rejectedReason;

 private  List<Float> floats;

 private  List<Request> requests;

 private  Path path;


public void setRejectedReason(String rejectedReason){
    this.rejectedReason = rejectedReason;
}


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


@ManyToMany
public List<Float> getFloats(){
    return this.floats;
}


public void setRequests(List<Request> requests){
    this.requests = requests;
}


public void setTitle(String title){
    this.title = title;
}


@NotNull
@Min(1)
@Digits(integer = 6, fraction = 0)
public int getColumnNumber(){
    return this.columnNumber;
}


public void setPath(Path path){
    this.path = path;
}


public void setDescription(String description){
    this.description = description;
}


@NotBlank
public String getDescription(){
    return this.description;
}


public void setParadeStatus(ParadeStatus paradeStatus){
    this.paradeStatus = paradeStatus;
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


@Valid
@OneToMany(mappedBy = "parade")
public List<Request> getRequests(){
    return this.requests;
}


public void setIsDraftMode(Boolean isDraftMode){
    this.isDraftMode = isDraftMode;
}


@Valid
public String getRejectedReason(){
    return this.rejectedReason;
}


public void setTicker(String ticker){
    this.ticker = ticker;
}


@Valid
@OneToOne(optional = true)
public Path getPath(){
    return this.path;
}


public void setMoment(Date moment){
    this.moment = moment;
}


public void setFloats(List<Float> floats){
    this.floats = floats;
}


@Valid
@Enumerated(EnumType.STRING)
public ParadeStatus getParadeStatus(){
    return this.paradeStatus;
}


@Pattern(regexp = "[0-9]{2}[0-1]{1}[0-9]{3}-([A-Za-z0-9]{6})")
@NotBlank
@Column(unique = true)
public String getTicker(){
    return this.ticker;
}


}