import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsProcess implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String processCd;

 private  String processName;

 private  String flagStatus;

 private  String compPkid;

 private  String remark;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;


public String getProcessName(){
    return processName;
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


public String getProcessCd(){
    return processCd;
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