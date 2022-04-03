package com.optimize.chapter2.trip.flyweight;
 import java.util.HashMap;
import java.util.Map;
public class ReportManagerFactory {

 private Map<String,IReportManager> financialReportManager;

 private Map<String,IReportManager> employeeReportManager;


public IReportManager getEmployeeReportManager(String tenantId){
    IReportManager r = employeeReportManager.get(tenantId);
    if (r == null) {
        r = new EmployeeReportManager(tenantId);
        employeeReportManager.put(tenantId, r);
    }
    return r;
}


public IReportManager getFinancialReportManager(String tenantId){
    IReportManager r = financialReportManager.get(tenantId);
    if (r == null) {
        r = new FinancialReportManager(tenantId);
        financialReportManager.put(tenantId, r);
    }
    return r;
}


}