import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
public class UtIndicatorEn implements Serializable{

 private  long serialVersionUID;

 private  int indicator_NId;

 private  byte data_Exist;

 private  byte highIsGood;

 private  String indicator_GId;

 private  byte indicator_Global;

 private  String indicator_Info;

 private  String indicator_Name;

 private  Integer indicator_Order;

 private  String keywords;

 private  String short_Name;


public String getShort_Name(){
    return this.short_Name;
}


public int getIndicator_NId(){
    return this.indicator_NId;
}


public String getKeywords(){
    return this.keywords;
}


public byte getHighIsGood(){
    return this.highIsGood;
}


public byte getIndicator_Global(){
    return this.indicator_Global;
}


public String getIndicator_Info(){
    return this.indicator_Info;
}


public String getIndicator_Name(){
    return this.indicator_Name;
}


public Integer getIndicator_Order(){
    return this.indicator_Order;
}


public byte getData_Exist(){
    return this.data_Exist;
}


public String getIndicator_GId(){
    return this.indicator_GId;
}


}