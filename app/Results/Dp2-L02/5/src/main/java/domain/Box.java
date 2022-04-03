package domain;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "name") })
public class Box extends DomainEntity{

 private  String name;

 private  Boolean isSystem;

 private  Box fatherBox;

 private  List<Message> messages;


public void setName(String name){
    this.name = name;
}


@NotBlank
public String getName(){
    return this.name;
}


public void setIsSystem(Boolean isSystem){
    this.isSystem = isSystem;
}


public void setMessages(List<Message> messages){
    this.messages = messages;
}


@Valid
@ManyToOne(optional = true)
public Box getFatherBox(){
    return this.fatherBox;
}


public void setFatherBox(Box fatherBox){
    this.fatherBox = fatherBox;
}


@NotNull
public Boolean getIsSystem(){
    return this.isSystem;
}


@Valid
@ManyToMany
public List<Message> getMessages(){
    return this.messages;
}


}