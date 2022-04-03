package com.weflors.util;
 import java.sql.Timestamp;
import java.util.List;
public class SalesReportHelperBean {

 private List<Integer> productIDs;

 private Timestamp reportStartDatePeriod;

 private Timestamp reportEndDatePeriod;


public Timestamp getReportStartDatePeriod(){
    return reportStartDatePeriod;
}


public List<Integer> getProductIDs(){
    return productIDs;
}


public void setReportStartDatePeriod(Timestamp reportStartDatePeriod){
    this.reportStartDatePeriod = reportStartDatePeriod;
}


public Timestamp getReportEndDatePeriod(){
    return reportEndDatePeriod;
}


public void setProductIDs(List<Integer> productIDs){
    this.productIDs = productIDs;
}


public void setReportEndDatePeriod(Timestamp reportEndDatePeriod){
    this.reportEndDatePeriod = reportEndDatePeriod;
}


}