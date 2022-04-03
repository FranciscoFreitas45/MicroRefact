package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsMaterielDetails implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer entryId;

 private  String goodsCd;

 private  String flagInspect;

 private  String isCheckOk;

 private  Integer goodsQuantity;

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


public String getGoodsCd(){
    return goodsCd;
}


public String getMakeUser(){
    return makeUser;
}


public String getFlagInspect(){
    return flagInspect;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public Integer getEntryId(){
    return entryId;
}


public Integer getGoodsQuantity(){
    return goodsQuantity;
}


public Long getId(){
    return id;
}


public String getReserveThird(){
    return reserveThird;
}


public String getIsCheckOk(){
    return isCheckOk;
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


public String getFlagStatus(){
    return flagStatus;
}


}