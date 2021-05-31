import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class UtData implements Serializable{

 private  long serialVersionUID;

 private  int data_NId;

 private  int area_NId;

 private  BigDecimal confidenceIntervalLower;

 private  BigDecimal confidenceIntervalUpper;

 private  int data_Denominator;

 private  Double data_Value;

 private  Date end_Date;

 private  int footNote_NId;

 private  Integer IC_IUS_Order;

 private  int indicator_NId;

 private  byte isMRD;

 private  byte isPlannedValue;

 private  byte isTextualData;

 private  String IUNId;

 private  int IUSNId;

 private  byte multipleSource;

 private  int source_NId;

 private  Date start_Date;

 private  int subgroup_Val_NId;

 private  String textual_Data_Value;

 private  int timePeriod_NId;

 private  int unit_NId;


public int getIUSNId(){
    return this.IUSNId;
}


public byte getIsTextualData(){
    return this.isTextualData;
}


public int getArea_NId(){
    return this.area_NId;
}


public int getIndicator_NId(){
    return this.indicator_NId;
}


public Integer getIC_IUS_Order(){
    return this.IC_IUS_Order;
}


public byte getIsMRD(){
    return this.isMRD;
}


public byte getIsPlannedValue(){
    return this.isPlannedValue;
}


public String getIUNId(){
    return this.IUNId;
}


public byte getMultipleSource(){
    return this.multipleSource;
}


public int getSubgroup_Val_NId(){
    return this.subgroup_Val_NId;
}


public BigDecimal getConfidenceIntervalLower(){
    return this.confidenceIntervalLower;
}


public Double getData_Value(){
    return this.data_Value;
}


public int getFootNote_NId(){
    return this.footNote_NId;
}


public int getData_Denominator(){
    return this.data_Denominator;
}


public int getSource_NId(){
    return this.source_NId;
}


public int getTimePeriod_NId(){
    return this.timePeriod_NId;
}


public Date getStart_Date(){
    return this.start_Date;
}


public int getUnit_NId(){
    return this.unit_NId;
}


public int getData_NId(){
    return this.data_NId;
}


public String getTextual_Data_Value(){
    return this.textual_Data_Value;
}


public Date getEnd_Date(){
    return this.end_Date;
}


public BigDecimal getConfidenceIntervalUpper(){
    return this.confidenceIntervalUpper;
}


}