package com.optimize.chapter2.duplicate;
 public class FinancialReportManager implements IReportManager{

 protected  String tenantId;

public FinancialReportManager(String tenantId) {
    this.tenantId = tenantId;
}
@Override
public String createReport(){
    return "This is a financial report";
}


}