import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsMaterielSupplier implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer materielId;

 private  Integer supplierId;

 private  String flagStatus;

 private  String compPkid;

 private  String remark;

 private  String reserveFirst;

 private  String reserveSecond;

 private  String reserveThird;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;


public Integer getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public String getMakeUser(){
    return makeUser;
}


public String getReserveThird(){
    return reserveThird;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public String getReserveFirst(){
    return reserveFirst;
}


public String getReserveSecond(){
    return reserveSecond;
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


public Integer getSupplierId(){
    return supplierId;
}


public String getFlagStatus(){
    return flagStatus;
}


}