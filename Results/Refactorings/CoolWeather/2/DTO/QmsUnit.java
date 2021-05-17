import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsUnit implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String unitCd;

 private  String unitName;

 private  String flagStatus;

 private  String compPkid;

 private  String remark;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;


public String getUnitName(){
    return unitName;
}


public String getUnitCd(){
    return unitCd;
}


public Long getId(){
    return id;
}


public String getMakeUser(){
    return makeUser;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public String getModifyUser(){
    return modifyUser;
}


public String getFlagStatus(){
    return flagStatus;
}


}