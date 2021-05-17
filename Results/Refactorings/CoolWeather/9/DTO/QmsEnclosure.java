import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsEnclosure implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer inspectionInfoId;

 private  String inspectionKbn;

 private  String enclosureAddress;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;


public Long getId(){
    return id;
}


public String getMakeUser(){
    return makeUser;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public Integer getInspectionInfoId(){
    return inspectionInfoId;
}


public String getInspectionKbn(){
    return inspectionKbn;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getModifyUser(){
    return modifyUser;
}


public String getEnclosureAddress(){
    return enclosureAddress;
}


}