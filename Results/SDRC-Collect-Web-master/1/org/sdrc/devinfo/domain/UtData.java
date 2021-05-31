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
@Entity
@Table(name = "ut_data")
@NamedQuery(name = "UtData.findAll", query = "SELECT u FROM UtData u")
public class UtData implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int data_NId;

 private  int area_NId;

 private  BigDecimal confidenceIntervalLower;

 private  BigDecimal confidenceIntervalUpper;

 private  int data_Denominator;

 private  Double data_Value;

@Temporal(TemporalType.TIMESTAMP)
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

@Temporal(TemporalType.TIMESTAMP)
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


public void setConfidenceIntervalUpper(BigDecimal confidenceIntervalUpper){
    this.confidenceIntervalUpper = confidenceIntervalUpper;
}


public void setData_Denominator(int data_Denominator){
    this.data_Denominator = data_Denominator;
}


public Integer getIC_IUS_Order(){
    return this.IC_IUS_Order;
}


public byte getIsMRD(){
    return this.isMRD;
}


public void setMultipleSource(byte multipleSource){
    this.multipleSource = multipleSource;
}


public byte getIsPlannedValue(){
    return this.isPlannedValue;
}


public void setStart_Date(Date start_Date){
    this.start_Date = start_Date;
}


public String getIUNId(){
    return this.IUNId;
}


public void setTextual_Data_Value(String textual_Data_Value){
    this.textual_Data_Value = textual_Data_Value;
}


public void setData_NId(int data_NId){
    this.data_NId = data_NId;
}


public void setSource_NId(int source_NId){
    this.source_NId = source_NId;
}


public void setTimePeriod_NId(int timePeriod_NId){
    this.timePeriod_NId = timePeriod_NId;
}


public byte getMultipleSource(){
    return this.multipleSource;
}


public int getSubgroup_Val_NId(){
    return this.subgroup_Val_NId;
}


public void setIC_IUS_Order(Integer IC_IUS_Order){
    this.IC_IUS_Order = IC_IUS_Order;
}


public void setIsPlannedValue(byte isPlannedValue){
    this.isPlannedValue = isPlannedValue;
}


public void setSubgroup_Val_NId(int subgroup_Val_NId){
    this.subgroup_Val_NId = subgroup_Val_NId;
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


public void setArea_NId(int area_NId){
    this.area_NId = area_NId;
}


public int getSource_NId(){
    return this.source_NId;
}


public int getTimePeriod_NId(){
    return this.timePeriod_NId;
}


public void setIUNId(String IUNId){
    this.IUNId = IUNId;
}


public void setIndicator_NId(int indicator_NId){
    this.indicator_NId = indicator_NId;
}


public void setEnd_Date(Date end_Date){
    this.end_Date = end_Date;
}


public void setIsTextualData(byte isTextualData){
    this.isTextualData = isTextualData;
}


public void setConfidenceIntervalLower(BigDecimal confidenceIntervalLower){
    this.confidenceIntervalLower = confidenceIntervalLower;
}


public Date getStart_Date(){
    return this.start_Date;
}


public void setFootNote_NId(int footNote_NId){
    this.footNote_NId = footNote_NId;
}


public void setIUSNId(int IUSNId){
    this.IUSNId = IUSNId;
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


public void setData_Value(Double data_Value){
    this.data_Value = data_Value;
}


public void setUnit_NId(int unit_NId){
    this.unit_NId = unit_NId;
}


public Date getEnd_Date(){
    return this.end_Date;
}


public BigDecimal getConfidenceIntervalUpper(){
    return this.confidenceIntervalUpper;
}


public void setIsMRD(byte isMRD){
    this.isMRD = isMRD;
}


}