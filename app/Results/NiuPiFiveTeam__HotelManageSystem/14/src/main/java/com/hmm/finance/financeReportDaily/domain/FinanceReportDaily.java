package com.hmm.finance.financeReportDaily.domain;
 import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "t_financeReportDaily")
public class FinanceReportDaily {

 private  Long financeReportDailyId;

 private  Date date;

 private  float roomIncome;

 private  float logisticstCost;

 private  float salaryCost;

 private  float totalIncome;

 private  float totalCost;

 private  float profit;

public FinanceReportDaily() {
    super();
}public FinanceReportDaily(float roomIncome, float logisticstCost, float salaryCost) {
    super();
    this.roomIncome = roomIncome;
    this.logisticstCost = logisticstCost;
    this.salaryCost = salaryCost;
}
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getFinanceReportDailyId(){
    return financeReportDailyId;
}


public float getLogisticstCost(){
    return logisticstCost;
}


public float getRoomIncome(){
    return roomIncome;
}


public void setProfit(float profit){
    this.profit = profit;
}


public void setSalaryCost(float salaryCost){
    this.salaryCost = salaryCost;
}


public float getTotalIncome(){
    return totalIncome;
}


public float getTotalCost(){
    return totalCost;
}


public void setFinanceReportDailyId(Long financeReportDailyId){
    this.financeReportDailyId = financeReportDailyId;
}


public void setLogisticstCost(float logisticstCost){
    this.logisticstCost = logisticstCost;
}


public float getProfit(){
    return profit;
}


public void setTotalIncome(float totalIncome){
    this.totalIncome = totalIncome;
}


public float getSalaryCost(){
    return salaryCost;
}


public void setDate(Date date){
    this.date = date;
}


@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
public Date getDate(){
    return date;
}


public void setRoomIncome(float roomIncome){
    this.roomIncome = roomIncome;
}


public void setTotalCost(float totalCost){
    this.totalCost = totalCost;
}


}